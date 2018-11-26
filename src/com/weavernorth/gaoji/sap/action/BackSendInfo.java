package com.weavernorth.gaoji.sap.action;

import com.google.gson.Gson;
import com.weaver.general.TimeUtil;
import com.weavernorth.gaoji.sap.action.util.ActionUtil;
import com.weavernorth.gaoji.sap.action.vo.POTableList;
import com.weavernorth.gaoji.sap.action.vo.POTableVo;
import com.weavernorth.gaoji.sap.action.vo.BaoXiaoDataVo;
import com.weavernorth.gaoji.sap.action.vo.BaoXiaoDetailVo;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

/**
 * 退回到第一节点时，发送请求
 */
public class BackSendInfo extends BaseAction {

    private String djlx;//单据类型
    @Override
    public String execute(RequestInfo requestInfo) {
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
        this.writeLog("退回到第一节点，推送冲销借款数据------------------表名： " + tableName + ", 请求id： " + requestId +
                ", " + TimeUtil.getCurrentTimeString());

        rs.execute("update " + tableName + " set zt = '04' where requestId = '" + requestId + "'");
        String selectSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
        rs.execute(selectSql);
        //-------------------------------------
        String djbh = "";//单据编号
        String fycdzt = "";//费用承担主体
        if (rs.next()) {
            djbh = rs.getString("djbh");
            fycdzt = rs.getString("sapfycdzt");
        }
        //校验该费用承担主体是否需要调用SAP
        RecordSet checkSapSet = new RecordSet();
        checkSapSet.execute("select * from uf_sapgs where code = '" + fycdzt + "' and zt = '1'");
        if (!checkSapSet.next()) {
            return "1";
        }
        //----------------拼接json----------------
        List<POTableVo> list = new ArrayList<POTableVo>();
        List<BaoXiaoDetailVo> baoXiaoDetailVoList = new ArrayList<BaoXiaoDetailVo>();//明细表集合
        POTableVo baoXiaoVo = new POTableVo("BX");
        BaoXiaoDataVo baoXiaoDataVo = new BaoXiaoDataVo();//主表字段
        baoXiaoDataVo.setZOAID(requestId);
        baoXiaoDataVo.setZDJBH(djbh);
        baoXiaoDataVo.setZDJZT("04");//04：退单  00：已收单（预提）  01：已审核未付款（待付款）  02：已审核已付款（已付款）
        baoXiaoDataVo.setZTYPE(djlx);//类型 01报销   02借款

        baoXiaoDataVo.setDETAILVOLIST(baoXiaoDetailVoList);
        String dataJson = gson.toJson(baoXiaoDataVo);
        baoXiaoVo.setBdata(dataJson);

        String md5Str = ActionUtil.md5(dataJson);
        baoXiaoVo.setBdatahash(md5Str);//设置校验字段

        POTableList pOTableList = new POTableList();
        list.add(baoXiaoVo);
        pOTableList.setTable(list);
        //本地存储
        ActionUtil.saveDataJson(list);
        //发送
        String sendJson = gson.toJson(pOTableList);
        this.writeLog("退回到第一节点的json：" + weaver.general.TimeUtil.getCurrentTimeString() + " ---------- " + sendJson);
        ActionUtil.sendPo(sendJson);
        return SUCCESS;
    }

    public String getDjlx() {
        return djlx;
    }

    public void setDjlx(String djlx) {
        this.djlx = djlx;
    }
}
