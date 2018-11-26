package com.weavernorth.gaoji.sap.action;

import com.google.gson.Gson;
import com.weavernorth.gaoji.sap.action.util.ActionUtil;
import com.weavernorth.gaoji.sap.action.vo.POTableList;
import com.weavernorth.gaoji.sap.action.vo.POTableVo;
import com.weavernorth.gaoji.sap.action.vo.BaoXiaoDataVo;
import com.weavernorth.gaoji.sap.action.vo.BaoXiaoDetailVo;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

/**
 * 连锁借款申请
 */
public class JieKuanLianSuo extends BaseAction {

    private Gson gson = new Gson();

    private String fycdzt = "";//费用承担主体
    private String workCode = "";//员工编号
    private String lastname = "";//员工姓名
    private String skyh = "";//收款银行
    private String yhzh = "";//银行账号

    private String lx = "02";//类型 01报销   02借款
    private String fycdbm = "";//费用承担部门
    private String hb = "CNY";//货币
    private String fylxjk = "";//费用类型
    private String jkje = "";//借款金额

    private String zfje = "";//支付金额
    private String djbh = "";//单据编号
    private String oaid = "";//OA的requestId

    private String djzt = "01";//单据状态
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
        this.writeLog("借款申请节点后操作执行------表名： " + tableName + " 请求id： " + requestId);

        rs.execute("update " + tableName + " set zt = '"+djzt+"' where requestId = '" + requestId + "'");
        String selectSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
        rs.execute(selectSql);
        //-------------------------------------
        String tbr = "";//填报人
        String email = "";//员工邮箱
        if (rs.next()) {
            tbr = rs.getString("tdr");
            fycdzt = rs.getString("sapfycdzt");
            fycdbm = rs.getString("sapfycdbm");
            jkje = rs.getString("jkje");
            djbh = rs.getString("djbh");
            oaid = requestId;
            tbname = tableName;
            fylxjk = rs.getString("xzbfylx");
            nbdd = rs.getString("nbdd");
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
        List<POTableVo> list = new ArrayList<POTableVo>();
        POTableVo baoXiaoVo = new POTableVo("BX");
        BaoXiaoDataVo baoXiaoDataVo = mainJson();//主表字段
        List<BaoXiaoDetailVo> baoXiaoDetailVoList = new ArrayList<BaoXiaoDetailVo>();//明细表集合
        BaoXiaoDetailVo baoXiaoDetailVo = new BaoXiaoDetailVo();
        baoXiaoDetailVo.setZDJBH(djbh);//单据编号
        baoXiaoDetailVo.setZFYLX(fylxjk);//费用类型
        baoXiaoDetailVo.setWRBTR(jkje);//借款金额
        baoXiaoDetailVo.setZBX("");//报销金额
        baoXiaoDetailVo.setZSE("");//税额
        baoXiaoDetailVo.setSGTXT("");//发票号
        baoXiaoDetailVo.setWAERS(hb);//币种
        baoXiaoDetailVoList.add(baoXiaoDetailVo);
        //设置明细表数据
        baoXiaoDataVo.setDETAILVOLIST(baoXiaoDetailVoList);
        String dataJson = gson.toJson(baoXiaoDataVo);
        baoXiaoVo.setBdata(dataJson);

        String md5Str = ActionUtil.md5(dataJson);
        this.writeLog("md5加密后字段： " + md5Str);
        baoXiaoVo.setBdatahash(md5Str);//设置校验字段
        list.add(baoXiaoVo);

        POTableList pOTableList = new POTableList();
        pOTableList.setTable(list);
        String sendJson = gson.toJson(pOTableList);
        this.writeLog("借款申请拼接的json：" + TimeUtil.getCurrentTimeString() + " ---------- " + sendJson);

        //本地存储
        ActionUtil.saveDataJson(list);
        //发送--------------------
        String code = ActionUtil.sendPo(sendJson);
        this.writeLog("推送返回状态码： " + code);
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
        baoXiaoDataVo.setZJK(jkje);//借款金额
        baoXiaoDataVo.setZFYLX(fylxjk);//费用类型

        baoXiaoDataVo.setWRBTR(zfje);//支付金额
        baoXiaoDataVo.setZDJBH(djbh);//单据编号
        baoXiaoDataVo.setZOAID(oaid);
        baoXiaoDataVo.setZDJZT(djzt);//单据状态
        baoXiaoDataVo.setAUFNR(nbdd);//内部订单
        baoXiaoDataVo.setZFKFS(fkfs);//付款方式
        baoXiaoDataVo.setZTBNA(tbname);

        return baoXiaoDataVo;//主表字段
    }
}
