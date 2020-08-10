package com.weavernorth.dadiyingyuan.currentNotUsedForFlowExamine.reversalAmountSubmit;

import weaver.conn.RecordSet;

import weaver.soa.workflow.request.*;
import weaver.workflow.action.BaseAction;

public class reversalAmountSubmit extends BaseAction {
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
        //获取明细表
        DetailTable[] detailtable = request.getDetailTableInfo().getDetailTable();// 获取所有明细表
        if (detailtable.length > 0) {
            for (int i = 0; i < detailtable.length; i++) {
                 DetailTable dt = detailtable[1];// 指定明细表--明细表2
                 Row[] s = dt.getRow();// 当前明细表的所有数据,按行存储
                 for (int j = 0; j < s.length; j++) {
                     Row r = s[j];// 指定行
                     Cell c[] = r.getCell();// 每行数据再按列存储
                     for (int k = 0; k < c.length; k++) {
                         Cell c2 = c[1];//第二列--->流程单号
                         Cell c3 = c[2];//第三列--->未还款金额
                         Cell c8 = c[7];// 指定列8、9两列--第8列--->借款余额
                         Cell c9 = c[8];//第九列---->冲借款金额
                         String c2Name = c2.getName();// 明细字段名称--->流程单号
                         String c2Value = c2.getValue();// 明细字段的值-->流程单号
                         String c3Name = c3.getName();// 明细字段名称--->未还款金额
                         String c3Value = c3.getValue();// 明细字段的值-->未还款金额
                         String c8Name = c8.getName();// 明细字段名称--->借款余额
                         String c8Value = c8.getValue();// 明细字段的值--->借款余额
                         String c9ame = c9.getName();// 明细字段名称--->冲借款金额
                         String c9Value = c9.getValue();// 明细字段的值--->冲借款金额
                         this.writeLog("未还款金额**"+c3Name + "****" + c3Value);
                         this.writeLog("借款余额**"+c8Name + "*****" + c8Value);
                         this.writeLog("冲借款金额**"+c9ame + "*****" + c9Value);
                         /******************************************************
                          *提交时，把明细的借款金额减掉。
                          *借款金额 - 冲销金额(实现方式：节点后附件操作Action接口)------>
                          *(借款金额-冲销金额=未还款金额)
                          *****************************************************/
                         Float RemainingRepayment = Float.parseFloat(c8Value) - Float.parseFloat(c9Value);
                         this.writeLog("未还款金额---->"+RemainingRepayment);
                         if(operator.equals("submit")){
                             RecordSet rsSub=new RecordSet();
                             this.writeLog("更新语句----->"+"update 表名 set whkje='"+RemainingRepayment+"'");
                             rsSub.execute("update uf_bxkjjk set whkje='"+RemainingRepayment+"' where sjid="+c2Value);
                         }
                     }
                 }
            }
        }
        return SUCCESS;
    }
}
