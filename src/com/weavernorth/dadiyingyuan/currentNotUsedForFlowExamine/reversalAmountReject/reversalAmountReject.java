package com.weavernorth.dadiyingyuan.currentNotUsedForFlowExamine.reversalAmountReject;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class reversalAmountReject extends BaseAction {
/*****************************************************
 **流程退回------->冲销金额退回(实现方式：节点后附件操作Action接口,部署到退回节点出口)
 **实现过程:第二审批节点退回时，原理与提交相反。流程退回时，需要将申请节点提交时扣除的报销冲借款金额返还，具体逻辑
 **为：根据提交时未还金额等于已借金额- 报销冲借款金额，得出，退回时借款余额=未还金额+报销冲借款金额。(操作的判断
 **条件为根据requestid获取主表id,然后通过主表的id获取明细表的mainId)
 *****************************************************/
    @Override
    public String execute(RequestInfo request){
        //获取流程请求id
        String requestId = request.getRequestid();
        this.writeLog("请求id---->"+requestId);
        //获取流程主表单
        String tableName = request.getRequestManager().getBillTableName();
        this.writeLog("表单名称---->"+tableName);
        //获取流程操作类型
        String operator = request.getRequestManager().getSrc();
        this.writeLog("流程操作类型---->"+operator);
        RecordSet rs=new RecordSet();
        this.writeLog("select * from uf_bxkjjk where requestid='"+requestId+"'");
        rs.execute("select * from uf_bxkjjk where requestid='"+requestId+"'");
        while(rs.next()){
            //获取冲销借款金额
            String cjkje = rs.getString("cjkje");
            this.writeLog("冲销借款------>"+cjkje);
            //获取未还金额
            String whkje = rs.getString("whkje");
            this.writeLog("未还金额------>"+whkje);
            //定义借款余额
            Float jkye=0f;
            //借款余额=未还金额+报销冲借款金额
            jkye=Float.parseFloat(cjkje)+Float.parseFloat(whkje);
            this.writeLog("借款余额为---->"+jkye);
            if(operator.equals("reject")){
                RecordSet rejectRs=new RecordSet();
                this.writeLog("更新借款余额---->"+"update uf_bxkjjk set jkye='"+jkye+"' where requestid='"+requestId+"'");
                rejectRs.execute("update uf_bxkjjk set jkye='"+jkye+"' where requestid='"+requestId+"'");
            }
        }
        return SUCCESS;
    }
}
