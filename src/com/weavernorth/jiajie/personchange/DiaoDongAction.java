package com.weavernorth.jiajie.personchange;

import com.google.gson.Gson;
import com.weaver.general.TimeUtil;
import com.weavernorth.jiajie.personchange.vo.ChangeVo;
import com.weavernorth.jiajie.personchange.vo.Different;
import com.weavernorth.jiajie.util.JiaJieUtil;
import weaver.conn.RecordSet;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用申请-wdd
 */
public class DiaoDongAction extends BaseAction {
    //调入法人体
    private static final String FRT_FIELD = "field51";
    // 调入岗位名称
    private static final String GWMC_FIELD = "field78";
    // 调入岗位类别
    private static final String GWLB_FIELD = "field72";
    // 调入岗位级别
    private static final String GWJB_FIELD = "field73";
    // 调入岗位地图
    private static final String GWDT_FIELD = "field83";

    private Gson gson = new Gson();
    private static Map<String, String> zdMap = new HashMap<String, String>();

    static {
        zdMap.put("gzdd", "工作地点");
        zdMap.put("frt", "法人体");
        zdMap.put("bm", "部门");
        zdMap.put("gwmc", "岗位名称");
        zdMap.put("gwlb", "岗位类别");
        zdMap.put("gwjb", "岗位级别");
        zdMap.put("gwdt", "岗位地图");
    }

    @Override
    public String execute(RequestInfo requestInfo) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("调用申请-wdd Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            // 创建人
            String xm = recordSet.getString("xm");
            ChangeVo newChangeVo = new ChangeVo();
            // 调入工作地点
            newChangeVo.setGzdd(recordSet.getString("drgzddStr"));
            String drgzdd = recordSet.getString("drgzdd");
            // 调入部门
            newChangeVo.setBm(recordSet.getString("drbmStr"));
            String drbm = recordSet.getString("drbm");

            // 调入法人体
            newChangeVo.setFrt(recordSet.getString("drfartStr"));
            String drfart = recordSet.getString("drfart");
            // 调入岗位名称
            newChangeVo.setGwmc(recordSet.getString("drgwmcStr"));
            String drgwmc = recordSet.getString("drgwmc");
            // 调入岗位类别
            newChangeVo.setGwlb(recordSet.getString("drgwlbStr"));
            String drgwlb = recordSet.getString("drgwlb");
            // 调入岗位级别
            newChangeVo.setGwjb(recordSet.getString("drgwjbStr"));
            String drgwjb = recordSet.getString("drgwjb");
            // 调入岗位地图
            newChangeVo.setGwdt(recordSet.getString("drgwdtStr"));
            String drgwdt = recordSet.getString("drgwdt");
            this.writeLog("新数据对象： " + newChangeVo.toString());

            ChangeVo oldChangeVo = new ChangeVo();
            // 调出工作地点
            oldChangeVo.setGzdd(recordSet.getString("dcgzddstr"));
            // 调出部门
            oldChangeVo.setBm(recordSet.getString("dcbmstr"));

            // 调出法人体
            oldChangeVo.setFrt(recordSet.getString("dcfrtstr"));
            // 调出岗位名称
            oldChangeVo.setGwmc(recordSet.getString("dcgwmcstr"));
            // 调出岗位类别
            oldChangeVo.setGwlb(recordSet.getString("dcgwlbstr"));
            // 调出岗位级别
            oldChangeVo.setGwjb(recordSet.getString("dcgwjbstr"));
            // 调出岗位地图
            oldChangeVo.setGwdt(recordSet.getString("dcgwdtStr"));
            this.writeLog("旧数据对象： " + oldChangeVo.toString());

            // 更新系统表
            RecordSet updateSet = new RecordSet();
            updateSet.executeUpdate("update hrmresource set locationid = ?, departmentid = ? where id = ?",
                    drgzdd, drbm, xm);
            this.writeLog("更新系统表结束============");

            // 更新自定义字段
            recordSet.executeQuery("select * from CUS_FIELDDATA where id = " + xm);
            if (recordSet.next()) {
                // 更新
                String updateSql = "update CUS_FIELDDATA set " + FRT_FIELD + " = ?, " + GWMC_FIELD + " = ?, "
                        + GWLB_FIELD + " = ?, " + GWJB_FIELD + " = ?," + GWDT_FIELD + " = ? where id = ?";
                this.writeLog("调用申请updateSql: " + updateSql);
                updateSet.executeUpdate(updateSql,
                        drfart, drgwmc, drgwlb, drgwjb, drgwdt, xm);
            } else {
                // 新增
                String insertSql = "insert into CUS_FIELDDATA(" + FRT_FIELD + "," + GWMC_FIELD + "," + GWLB_FIELD +
                        "," + GWJB_FIELD + "," + GWDT_FIELD + ", scope, scopeid, id) values(?,?,?,?,?, ?,?,?)";
                this.writeLog("调用申请insertSql： " + insertSql);
                updateSet.executeUpdate(insertSql,
                        drfart, drgwmc, drgwlb, drgwjb, drgwdt,
                        "HrmCustomFieldByInfoType", "-1", xm);
            }

            //清除缓存
            new ResourceComInfo().removeResourceCache();

            // 查询变动字段
            List<Different> differentList = JiaJieUtil.compareObj(oldChangeVo, newChangeVo);
            this.writeLog("变动字段集合： " + gson.toJson(differentList));

            // 插入人员信息变更记录表
            for (Different different : differentList) {
                JiaJieUtil.insertPerCord(requestId, different.getBeforeValue(), different.getAlterValue(), zdMap.get(different.getFieldId()), xm);
            }

            this.writeLog("建模授权开始=====");
            JiaJieUtil.rebuildModeDataShare(currentTimeString);

            this.writeLog("调用申请-wdd End ===============");
        } catch (Exception e) {
            this.writeLog("调用申请-wdd 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("调用申请-wdd 异常： " + e);
            return "0";
        }

        return "1";
    }


}
