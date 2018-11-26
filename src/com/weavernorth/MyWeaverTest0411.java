package com.weavernorth;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.StaticObj;
import weaver.general.Util;
import weaver.hrm.HrmUserVarify;
import weaver.hrm.User;
import weaver.integration.logging.Logger;
import weaver.integration.logging.LoggerFactory;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class MyWeaverTest0411 extends BaseAction {

    private Logger logger = LoggerFactory.getLogger();

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();//请求ID
        String requestlevel = requestInfo.getRequestlevel();//请求紧急程度
        String src = requestInfo.getRequestManager().getSrc(); //当前操作类型 submit:提交/reject:退回
        logger.info("当前操作类型0411：" + src);
        String workflowId = requestInfo.getWorkflowid();//流程ID
        logger.info("流程ID：" + workflowId);
        String tableName = requestInfo.getRequestManager().getBillTableName();//表单名称
        logger.info("表单名称：" + tableName);
        int billid = requestInfo.getRequestManager().getBillid();//表单数据ID
        User usr = requestInfo.getRequestManager().getUser();//获取当前操作用户对象
        logger.info("用户名称： " + usr.getAccount());
        String requestName = requestInfo.getRequestManager().getRequestname();//请求标题
        logger.info("请求标题: " + requestName);
        String remark = requestInfo.getRequestManager().getRemark();//当前用户提交时的签字意见
        logger.info("签字意见0411： " + remark);
        requestInfo.getRequestManager().setRemark("新的签字意见~！！");
        int formid = requestInfo.getRequestManager().getFormid();//表单ID

        writeLog("");
        RecordSet recordSet = new RecordSet();
        boolean execute = recordSet.execute("select * from " + tableName + " where requestId = " + tableName);
        recordSet.executeQuery("", "", "", "");
        int colCounts = recordSet.getColCounts();

        while (recordSet.next()) {
            for (int i = 1; i <= colCounts; i++) {
                logger.info("循环输出： " + recordSet.getString(i));
            }

        }

        ConnStatement connStatement = new ConnStatement();

        StaticObj instance = StaticObj.getInstance();

        //控制流程流转，增加以下两行，流程不会向下流转，表单上显示返回的自定义错误信息
        requestInfo.getRequestManager().setMessagecontent("牛智萌的自定义的错误信息0411");
        requestInfo.getRequestManager().setMessageid("错误信息编号");
        return "0";//return返回固定返回`SUCCESS`
    }
}
