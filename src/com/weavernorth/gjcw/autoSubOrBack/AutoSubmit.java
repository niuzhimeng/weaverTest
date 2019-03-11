package com.weavernorth.gjcw.autoSubOrBack;


import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AutoSubmit extends BaseAction {

    /**
     * 当前操作者(接口处配置固定值)
     */
    private String currentOperate;

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);
    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'"); // 均可获取表名
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        baseBean.writeLog("国机自动校验执行============" + TimeUtil.getCurrentTimeString() + " requestId: " + requestId +
                ", tableName: " + tableName + ", currentOperate: " + currentOperate);

        // 提交或者退回
        String executeAction;
        recordSet.executeQuery("select cyjg from " + tableName + " where requestid = " + requestId);
        recordSet.next();
        String flag = recordSet.getString("cyjg").trim();
        if ("成功".equals(flag)) {
            executeAction = "submit";
        } else {
            executeAction = "reject";
        }

        //执行归档操作
        BlockingDeque blockingDeque = new BlockingDeque();
        blockingDeque.setRequestId(requestId);
        blockingDeque.setCurrentOperate(Integer.parseInt(currentOperate));
        blockingDeque.setOperateAction(executeAction);

        executorService.execute(blockingDeque);

        return "1";
    }

    public String getCurrentOperate() {
        return currentOperate;
    }

    public void setCurrentOperate(String currentOperate) {
        this.currentOperate = currentOperate;
    }
}
