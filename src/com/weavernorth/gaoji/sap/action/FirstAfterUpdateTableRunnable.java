package com.weavernorth.gaoji.sap.action;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.weaver.general.TimeUtil;
import com.weavernorth.gaoji.sap.action.util.ActionUtil;
import com.weavernorth.gaoji.sap.action.vo.POTableList;
import com.weavernorth.gaoji.sap.action.vo.POTableVo;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第一节点前更新表单字段
 */
public class FirstAfterUpdateTableRunnable extends BaseAction {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public String execute(RequestInfo requestInfo) {
        this.writeLog("-----------------------对公付款第一节点前操作执行----------------------------------" + TimeUtil.getCurrentTimeString());
        try {
            Gson gson = new Gson();
            String tableName = requestInfo.getRequestManager().getBillTableName();
            String requestId = requestInfo.getRequestid();
            //当前操作类型 submit：提交  reject：退回
            String src = requestInfo.getRequestManager().getSrc();
            if (!"reject".equals(src)) {
                return "1";
            }
            RecordSet rs = new RecordSet();

            if (tableName == null || "".equals(tableName.trim())) {
                String selectTable = "SELECT tablename FROM workflow_bill WHERE id = (SELECT formid FROM workflow_base WHERE id = (SELECT workflowid FROM workflow_requestbase WHERE requestid = '" + requestId + "'))";
                rs.execute(selectTable);
                if (rs.next()) {
                    tableName = rs.getString("tablename");
                }
            }
            String selectSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
            rs.execute(selectSql);

            String fkdh = "";//SAP单据编号
            if (rs.next()) {
                fkdh = rs.getString("fkdh");
            }
            //----------------拼接json----------------
            List<POTableVo> list = new ArrayList<POTableVo>();
            POTableVo pOTableVo = new POTableVo("DGFK");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ZYFKDH", fkdh);
            jsonObject.put("ZFKZT", "03");//03 OA审核拒绝; 04 OA审核通过
            String dataJson = jsonObject.toString();
            pOTableVo.setBdata(dataJson);

            String md5Str = ActionUtil.md5(dataJson);
            pOTableVo.setBdatahash(md5Str);//设置校验字段

            POTableList pOTableList = new POTableList();
            list.add(pOTableVo);
            pOTableList.setTable(list);
            //发送
            String sendJson = gson.toJson(pOTableList);
            this.writeLog("对公付款退回至第一节点，发送的失败json==================： " + sendJson);

            //本地存储
            ActionUtil.saveDataJson(list);
            String code = ActionUtil.sendPo(sendJson);
            if ("200".equals(code) || "202".equals(code)) {
                RecordSet recordSet = new RecordSet();
                //修改表单中是否直接归档字段
                recordSet.execute("update " + tableName + " set cxgd = 0 where requestid = '" + requestId + "'");
                //执行归档操作
                BlockingDeque blockingDeque = new BlockingDeque();
                blockingDeque.setTableName(tableName);
                executorService.execute(blockingDeque);
                return "1";
            } else {
                requestInfo.getRequestManager().setMessageid("10000");
                requestInfo.getRequestManager().setMessagecontent("错误提示：网络原因 数据推送失败!");
                return "1";
            }

        } catch (Exception e) {
            this.writeLog(e);
        }
        return "1";
    }
}
