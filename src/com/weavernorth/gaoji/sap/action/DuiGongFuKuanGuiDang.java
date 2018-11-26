package com.weavernorth.gaoji.sap.action;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.weavernorth.gaoji.sap.action.util.ActionUtil;
import com.weavernorth.gaoji.sap.action.vo.*;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

/**
 * 对公付款成功归档前发送SAP
 */
public class DuiGongFuKuanGuiDang extends BaseAction {

    private BaseBean baseBean = new BaseBean();
    private Gson gson = new Gson();

    @Override
    public String execute(RequestInfo requestInfo) {
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        //当前操作类型 submit：提交  reject：退回
        String src = requestInfo.getRequestManager().getSrc();
        if (!"submit".equals(src)) {
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
        this.writeLog("对公付款归档操作执行------表名： " + tableName + " 请求id： " + requestId);

        String selectSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
        rs.execute(selectSql);

        String cxgd = "";//程序归档
        String fkdh = "";//SAP单据编号
        if (rs.next()) {
            cxgd = rs.getString("cxgd");
            fkdh = rs.getString("djbh");
        }
        //程序归档值为0则不发SAP归档消息
        if("0".equals(cxgd)){
            return "1";
        }
        //----------------拼接json----------------
        List<POTableVo> list = new ArrayList<POTableVo>();
        //固定部分-------------
        POTableVo duiGongFKVo = new POTableVo("DGFK");
        //data----------------
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ZYFKDH", fkdh);
        jsonObject.put("ZFKZT", "04");//03 OA审核拒绝; 04 OA审核通过
        String dataJson = jsonObject.toString();
        String md5Str = ActionUtil.md5(dataJson);
        duiGongFKVo.setBdatahash(md5Str);//设置校验字段
        duiGongFKVo.setBdata(dataJson);
        list.add(duiGongFKVo);
        POTableList duiGongFKTable = new POTableList();
        duiGongFKTable.setTable(list);
        String sendJson = gson.toJson(duiGongFKTable);
        baseBean.writeLog("拼接的json：" + TimeUtil.getCurrentTimeString() + " ---------- " + sendJson);

        //本地存储
        ActionUtil.saveDataJson(list);

        //发送--------------------
        String code = ActionUtil.sendPo(sendJson);
        if ("200".equals(code) || "202".equals(code)) {
            return "1";
        } else {
            requestInfo.getRequestManager().setMessageid("10000");
            requestInfo.getRequestManager().setMessagecontent("错误提示：网络原因 数据推送失败!");
            return "1";
        }

    }
}
