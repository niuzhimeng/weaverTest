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
 * 薪资申请-wdd
 */
public class XinZiAction extends BaseAction {
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

        this.writeLog("薪资申请-wdd Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            // 创建人
            String xm = recordSet.getString("xm");
            ChangeVo newChangeVo = new ChangeVo();

            // 调入岗位名称
            newChangeVo.setGwmc(recordSet.getString("gwmctzhStr"));
            String drgwmc = recordSet.getString("gwmch");
            // 调入岗位类别
            newChangeVo.setGwlb(recordSet.getString("gwlbtzhStr"));
            String drgwlb = recordSet.getString("gwlbh");
            // 调入岗位级别
            newChangeVo.setGwjb(recordSet.getString("gwjbtzhStr"));
            String drgwjb = recordSet.getString("gwjbh");
            // 调入岗位地图
            newChangeVo.setGwdt(recordSet.getString("gwdttzhStr"));
            String gwdttzh = recordSet.getString("gwdttzh");
            this.writeLog("新数据对象： " + newChangeVo.toString());

            ChangeVo oldChangeVo = new ChangeVo();
            // 调出岗位名称
            oldChangeVo.setGwmc(recordSet.getString("gwmctzqStr"));
            // 调出岗位类别
            oldChangeVo.setGwlb(recordSet.getString("gwlbtzqStr"));
            // 调出岗位级别
            oldChangeVo.setGwjb(recordSet.getString("gwjbtzqStr"));
            // 调出岗位地图
            oldChangeVo.setGwdt(recordSet.getString("gwdttzqStr"));
            this.writeLog("旧数据对象： " + oldChangeVo.toString());

            // 更新自定义字段
            RecordSet updateSet = new RecordSet();
            recordSet.executeQuery("select * from CUS_FIELDDATA where id = " + xm);
            if (recordSet.next()) {
                // 更新
                String updateSql = "update CUS_FIELDDATA set " + GWMC_FIELD + " = ?, " + GWLB_FIELD + " = ?, " +
                        GWJB_FIELD + " = ?," + GWDT_FIELD + " = ? where id = ?";
                this.writeLog("薪资申请updateSql: " + updateSql);
                updateSet.executeUpdate(updateSql,
                        drgwmc, drgwlb, drgwjb, gwdttzh, xm);
            } else {
                // 新增
                String insertSql = "insert into CUS_FIELDDATA(" + GWMC_FIELD + "," + GWLB_FIELD + "," + GWJB_FIELD + "," + GWDT_FIELD +
                        ", scope, scopeid, id) values(?,?,?,?,?, ?,?)";
                this.writeLog("薪资申请insertSql： " + insertSql);
                updateSet.executeUpdate(insertSql,
                        drgwmc, drgwlb, drgwjb, gwdttzh,
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

            this.writeLog("薪资申请-wdd End ===============");
        } catch (Exception e) {
            this.writeLog("薪资申请-wdd 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("薪资申请-wdd 异常： " + e);
            return "0";
        }

        return "1";
    }


}
