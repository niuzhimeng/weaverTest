package com.weavernorth.gaoji.sap.action;


import com.google.gson.Gson;
import com.weavernorth.gaoji.sap.action.util.ActionUtil;
import com.weavernorth.gaoji.sap.action.vo.BaoXiaoDataVo;
import com.weavernorth.gaoji.sap.action.vo.BaoXiaoDetailVo;
import com.weavernorth.gaoji.sap.action.vo.POTableList;
import com.weavernorth.gaoji.sap.action.vo.POTableVo;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

/**
 * 平台日常费用报销
 */
public class RiChangBxPingTai extends BaseAction {

    private BaseBean baseBean = new BaseBean();
    private Gson gson = new Gson();
    private String status;//不同节点的状态
    private String fycdzt = "";//费用承担主体
    private String workCode = "";//员工编号
    private String lastname = "";//员工姓名
    private String skyh = "";//收款银行
    private String yhzh = "";//银行账号

    private String lx = "01";//类型 01报销   02借款
    private String fycdbm = "";//费用承担部门
    private String hb = "CNY";//货币
    private String fylx = "";//费用类型（明细表）
    private String jkye = "";//借款余额

    private String fyjehj = "";//报销金额（费用金额合计）
    private String se = "";//税额（增值税税额）（明细表）
    private String zfje = "";//支付金额
    private String fph = "";//发票号（明细表）
    private String djbh = "";//单据编号
    private String oaid = "";//OA的requestId

    private String djzt = "";//单据状态
    private String nbdd = "";//内部订单
    private String fkfs = "";//付款方式
    private String tbname = "";//流程表表名

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
        this.writeLog("日常费用节点后操作执行------表名： " + tableName + " 请求id： " + requestId);

        rs.execute("update " + tableName + " set zt = '" + status + "' where requestId = '" + requestId + "'");
        String selectSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
        rs.execute(selectSql);

        //-------------------------------------
        String tbr = "";//填报人
        String email = "";//员工邮箱
        if (rs.next()) {
            tbr = rs.getString("tbr");
            fycdzt = rs.getString("sapfycdzt");
            fycdbm = rs.getString("sapfycdbm");
            jkye = rs.getString("jkye");
            fyjehj = rs.getString("fyjehj");
            zfje = rs.getString("zfje");
            djbh = rs.getString("djbh");
            oaid = requestId;
            tbname = tableName;
            djzt = status;
            fkfs = rs.getString("fkfs");
        }

        //校验该费用承担主体是否需要调用SAP
        RecordSet checkSapSet = new RecordSet();
        checkSapSet.execute("select * from uf_sapgs where code = '" + fycdzt + "' and zt = '1'");
        if (!checkSapSet.next()) {
            return "1";
        }

        RecordSet perSet = new RecordSet();
        perSet.execute("select * from hrmresource where id = '" + tbr + "'");
        if (perSet.next()) {
            workCode = perSet.getString("workcode");
            lastname = perSet.getString("lastname");
            email = perSet.getString("email");
        }

        RecordSet yhSer = new RecordSet();
        yhSer.execute("select * from uf_fkyhzhxx where yx = '" + email + "'");
        if (yhSer.next()) {
            skyh = yhSer.getString("khh");
            yhzh = yhSer.getString("skyhzh");
        }

        //----------------拼接json----------------
        RecordSet detailSet = new RecordSet();
        detailSet.execute("SELECT d.* FROM " + tableName + " m LEFT JOIN " + tableName + "_DT1" + " d ON m.id = d.MAINID WHERE m.REQUESTID = " + requestId);
        List<POTableVo> list = new ArrayList<POTableVo>();
        List<BaoXiaoDetailVo> baoXiaoDetailVoList = new ArrayList<BaoXiaoDetailVo>();//明细表集合
        POTableVo baoXiaoVo = new POTableVo("BX");
        BaoXiaoDataVo baoXiaoDataVo = mainJson();//主表字段
        //data----------------DT1
        while (detailSet.next()) {
            this.writeLog("DT1 执行==========");
            if (detailSet.getString("bhsje") == null || "".equals(detailSet.getString("bhsje").trim())) {
                continue;
            }
            //明细表字段
            BaoXiaoDetailVo baoXiaoDetailVo = new BaoXiaoDetailVo();
            baoXiaoDetailVo.setZFYLX(detailSet.getString("ptfylx"));//费用类型
            baoXiaoDetailVo.setZBX(detailSet.getString("bhsje"));//不含税金额
            baoXiaoDetailVo.setZSE(detailSet.getString("zzsse"));//税额（增值税税额）
            baoXiaoDetailVo.setSGTXT(detailSet.getString("fphh"));//发票号
            baoXiaoDetailVo.setAUFNR(detailSet.getString("sapnbdd"));//费用承担项目
            baoXiaoDetailVoList.add(baoXiaoDetailVo);
        }
        //设置明细表数据
        baoXiaoDataVo.setDETAILVOLIST(baoXiaoDetailVoList);
        String dataJson = gson.toJson(baoXiaoDataVo);
        baoXiaoVo.setBdata(dataJson);

        String md5Str = ActionUtil.md5(dataJson);
        baoXiaoVo.setBdatahash(md5Str);//设置校验字段

        POTableList pOTableList = new POTableList();
        list.add(baoXiaoVo);
        pOTableList.setTable(list);
        String sendJson = gson.toJson(pOTableList);
        baseBean.writeLog("日常报销拼接的json：" + TimeUtil.getCurrentTimeString() + " ---------- " + sendJson);
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

    /**
     * 主表json
     *
     * @return
     */
    private BaoXiaoDataVo mainJson() {
        BaoXiaoDataVo baoXiaoDataVo = new BaoXiaoDataVo();

        baoXiaoDataVo.setBUKRS(fycdzt);//费用承担主体
        baoXiaoDataVo.setPERNR(workCode);//员工编号
        baoXiaoDataVo.setNAME1(lastname);//员工姓名
        baoXiaoDataVo.setHBKID(skyh);//收款银行
        baoXiaoDataVo.setBANKN(yhzh);//银行账号

        baoXiaoDataVo.setZTYPE(lx);//类型
        baoXiaoDataVo.setKOSTL(fycdbm);//费用承担部门
        baoXiaoDataVo.setWAERS(hb);//货币
        baoXiaoDataVo.setZJK(jkye);//借款余额
        baoXiaoDataVo.setZBX(fyjehj);//报销金额（费用金额合计）

        baoXiaoDataVo.setWRBTR(zfje);//支付金额
        baoXiaoDataVo.setZDJBH(djbh);//单据编号
        baoXiaoDataVo.setZOAID(oaid);
        baoXiaoDataVo.setZDJZT(djzt);//单据状态
        baoXiaoDataVo.setZFKFS(fkfs);//付款方式
        baoXiaoDataVo.setZTBNA(tbname);

        return baoXiaoDataVo;//主表字段
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
