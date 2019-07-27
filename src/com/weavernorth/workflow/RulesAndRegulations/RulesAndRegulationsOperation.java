package com.weavernorth.workflow.RulesAndRegulations;

import com.weavernorth.OA2archives.util.DocUtil;
import com.weavernorth.OA2archives.util.WorkflowUtil;
import com.weavernorth.util.LogUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

/**
 * 归档制度流程归档后生成表单pdf附件，
 * 将附件挂在触发的公司发文流程的规章制度附件中。
 *
 * @author llh
 * @create 2017-12-04 12:55
 **/
public class RulesAndRegulationsOperation {
    private BaseBean bs = new BaseBean();
    //公司发文流程中的规章制度附件上传字段
    private String rulesUpLoadField = bs.getPropValue("RulesAndRegulationsWorkflow", "fileUpLoad");
    private String rulesUpLoadField_pc = bs.getPropValue("RulesAndRegulationsWorkflow", "fileUpLoad_pc");

    //
    public String updateFilfile(String subRequestId) {
        String strWorlfowId = WorkflowUtil.getWorkflowId(subRequestId);
        String strDocField = "";
        if ("146".equals(strWorlfowId)) {
            strDocField = rulesUpLoadField;
        } else if ("178".equals(strWorlfowId)) {
            strDocField = rulesUpLoadField_pc;
        }
        LogUtil.debugLog("获取公司发文流程requestid>>>>>" + subRequestId);
        //规章制度流程请求id
        String mainRequestId = getMainRequestId(subRequestId);
        LogUtil.debugLog("规章制度流程请求id>>>>" + mainRequestId);
        //规章制度流程的pdf附件的docid
        String tablePDFDocId = DocUtil.getTablePdfDocid(mainRequestId);
        String subTableName = WorkflowUtil.getMainTableNameByReqId(subRequestId);
        String selSql = "select " + strDocField + " from " + subTableName + " where requestid='" + subRequestId + "'";
        RecordSet rsSel = new RecordSet();
        rsSel.executeSql(selSql);
        if (rsSel.next()) {
            String rulesfile = Util.null2String(rsSel.getString(strDocField));
            if (!"".equals(rulesfile)) {
                return "-1";
            }
        }
        String updateSql = "update " + subTableName + " set " + strDocField + "='" + tablePDFDocId + "' where requestid=" + subRequestId;
        LogUtil.debugLog("更新规章制度附件sql>>>" + updateSql);
        RecordSet rs = new RecordSet();
        if (rs.executeSql(updateSql)) {
            //成功
            return "0";
        }
        //更新失败
        return "1";
    }

    /**
     * @param subRequestId 主流程的requestid
     * @return 子流程requestid
     */
    public String getMainRequestId(String subRequestId) {
        String mainrequestid = "";
        if (!"".equals(subRequestId)) {
            String sel = "select mainrequestid  from workflow_subwfrequest where subrequestid = '" + subRequestId + "'";
            RecordSet rs = new RecordSet();
            rs.executeSql(sel);
            if (rs.next()) {
                mainrequestid = Util.null2String(rs.getString("mainrequestid"));
            }
        }
        return mainrequestid;
    }


}
