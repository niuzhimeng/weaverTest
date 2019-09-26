package com.weavernorth.jiajie.personchange;

import com.alibaba.fastjson.JSONObject;
import com.weaver.general.TimeUtil;
import com.weavernorth.jiajie.personchange.vo.Different;
import com.weavernorth.jiajie.personchange.vo.XuQianVo;
import com.weavernorth.jiajie.util.JiaJieUtil;
import weaver.conn.RecordSet;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 劳动合同续签意向确认表-wdd
 */
public class XuQianAction extends BaseAction {

    /**
     * 公共选择框 - 法人体id
     */
    private static final Integer FRT_ID = 1041;
    /**
     * 自定义字段 - 法人体id
     */
    private static final String FRT_FIELD_ID = "field8";
    /**
     * 自定义字段 - 签约合同开始日期id
     */
    private static final String START_FIELD_ID = "field68";
    /**
     * 自定义字段 - 签约合同结束日期id
     */
    private static final String END_FIELD_ID = "field69";

    private static Map<String, String> zdMap = new HashMap<String, String>();

    static {
        zdMap.put("frt", "法人体");
        zdMap.put("startDate", "签约合同开始日期");
        zdMap.put("endDate", "签约合同结束日期");
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

        this.writeLog("合同续签 Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询法人体中文名
            Map<String, String> frtMap = new HashMap<String, String>();
            recordSet.executeQuery("select DISORDER, name from MODE_SELECTITEMPAGEDETAIL where mainid = " + FRT_ID);
            while (recordSet.next()) {
                frtMap.put(recordSet.getString("DISORDER"), recordSet.getString("name"));
            }

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            // 续签人
            String xm = recordSet.getString("xm");
            // 新对象
            XuQianVo newVo = new XuQianVo();
            // 续签法人体
            String xqfrt = recordSet.getString("xqfrt");
            newVo.setFrt(frtMap.get(xqfrt));
            // 续签劳动合同期限
            newVo.setStartDate(recordSet.getString("xqldhtqx"));
            // 续签劳动终止合同期限
            newVo.setEndDate(recordSet.getString("xqldzzhtqx"));
            this.writeLog("续签流程新对象： " + newVo.toString());

            RecordSet updateSet = new RecordSet();

            String oldFrt = "";
            String startDate = "";
            String endDate = "";

            // 基本信息(scopeid=-1)、个人信息（scopeid=1）、工作信息（scopeid=3）、自定义字段 人力资源模块字段标识：HrmCustomFieldByInfoType
            // 查询基本信息
            recordSet.executeQuery("select * from CUS_FIELDDATA where id = " + xm + " and scopeid = -1");
            if (recordSet.next()) {
                // 更新
                oldFrt = recordSet.getString(FRT_FIELD_ID);
                String updateSql = "update CUS_FIELDDATA set " + FRT_FIELD_ID + " = '" + xqfrt + "' where id = " + xm + " and scopeid = -1";
                this.writeLog("转正申请updateSql: " + updateSql);
                updateSet.executeUpdate(updateSql);
            } else {
                // 新增
                String insertSql = "insert into CUS_FIELDDATA(" + FRT_FIELD_ID + ", scope, scopeid, id) values(?,?,?,?)";
                this.writeLog("转正申请insertSql： " + insertSql);
                updateSet.executeUpdate(insertSql,
                        xqfrt, "HrmCustomFieldByInfoType", "-1", xm);
            }

            // 处理工作信息
            recordSet.executeQuery("select * from CUS_FIELDDATA where id = " + xm + " and scopeid = 3");
            if (recordSet.next()) {
                // 更新
                startDate = recordSet.getString(START_FIELD_ID);
                endDate = recordSet.getString(END_FIELD_ID);
                String updateSql = "update CUS_FIELDDATA set " + START_FIELD_ID + " = '" + newVo.getStartDate() + "', "
                        + END_FIELD_ID + " = '" + newVo.getEndDate() + "' where id = " + xm + " and scopeid = 3";
                this.writeLog("转正申请updateSql: " + updateSql);
                updateSet.executeUpdate(updateSql);
            } else {
                // 新增
                String insertSql = "insert into CUS_FIELDDATA(" + START_FIELD_ID + ", " + END_FIELD_ID
                        + " scope, scopeid, id) values(?,?,?,?,?)";
                this.writeLog("转正申请insertSql： " + insertSql);
                updateSet.executeUpdate(insertSql,
                        newVo.getStartDate(), newVo.getEndDate(), "HrmCustomFieldByInfoType", "3", xm);
            }
            this.writeLog("更新自定义字段表结束=================");

            // 旧对象
            XuQianVo oldVo = new XuQianVo();
            oldVo.setFrt(frtMap.get(oldFrt));
            oldVo.setStartDate(startDate);
            oldVo.setEndDate(endDate);
            this.writeLog("续签流程旧对象： " + oldVo.toString());

            //清除缓存
            new ResourceComInfo().removeResourceCache();

            // 查询变动字段
            List<Different> differentList = JiaJieUtil.compareObj(oldVo, newVo);
            this.writeLog("变动字段集合： " + JSONObject.toJSONString(differentList));

            // 插入人员信息变更记录表
            for (Different different : differentList) {
                JiaJieUtil.insertPerCord(requestId, different.getBeforeValue(), different.getAlterValue(), zdMap.get(different.getFieldId()), xm);
            }

            this.writeLog("建模授权开始=====");
            JiaJieUtil.rebuildModeDataShare(currentTimeString);

            this.writeLog("合同续签 End ===============");
        } catch (Exception e) {
            this.writeLog("合同续签 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("合同续签 异常： " + e);
            return "0";
        }

        return "1";
    }
}
