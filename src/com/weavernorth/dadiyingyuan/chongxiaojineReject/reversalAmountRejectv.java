package com.weavernorth.dadiyingyuan.chongxiaojineReject;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;
public class reversalAmountRejectv extends BaseAction {
    public String execute(RequestInfo request){
        //获取流程请求id
        String requestId = Util.null2String(request.getRequestid());
        this.writeLog("请求id---->"+requestId);
        //获取流程主表单
        String tableName = request.getRequestManager().getBillTableName();
        this.writeLog("表单名称---->"+tableName);
        //获取流程操作类型
        String operator = request.getRequestManager().getSrc();
        this.writeLog("流程操作类型---->"+operator);
        RecordSet rs=new RecordSet();
        this.writeLog("select * from "+tableName+" where requestid='"+requestId+"'");
        rs.execute("select * from "+tableName+" where requestid="+requestId);
        while(rs.next()){
            String cxjk = rs.getString("cxjk");
            this.writeLog("冲销借款------>"+cxjk);
            RecordSet rsWrite=new RecordSet();
            this.writeLog("更新语句----->"+"update 表名 set yjje='"+cxjk+"'");
            rsWrite.execute("update 表名 set yjje='"+cxjk+"'");
        }
        return SUCCESS;
    }
}
