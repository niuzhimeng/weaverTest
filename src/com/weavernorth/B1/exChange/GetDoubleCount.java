package com.weavernorth.B1.exChange;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

/**
 * 获取个人待收件数量、单位待收件数量
 */
public class GetDoubleCount {
    //模块id
    private static final String modelId = "1";

    public String userGetCount(String workcode) {

        BaseBean baseBean = new BaseBean();
        baseBean.writeLog("访问了userGetCount, workcode = " + workcode);
        //根据loginid获取工号
        RecordSet recordSet = new RecordSet();
        recordSet.executeSql("select * from hrmresource where workcode = '" + workcode + "'");
        String workCode = "";
        String lastname = "";
        String id = "";
        if (recordSet.next()) {
            workCode = recordSet.getString("workcode");
            lastname = recordSet.getString("lastname");
            id = recordSet.getString("id");
        }
        if (workCode == null || "".equals(workCode.trim())) {
            baseBean.writeLog("当前人员： " + workcode + " wordcode 为空");
            return "error";
        }

        baseBean.writeLog("获取交换接口的接收件数量方法start==========");
        baseBean.writeLog("当前人工号： " + workCode);
        baseBean.writeLog("当前人姓名： " + lastname);

        //调用交换系统接口 获取返回数据
        String requestXml = getCounts(workCode);
        if ("".equals(requestXml)) {
            return "";
        }

        try {
            Document doc = DocumentHelper.parseText(requestXml);
            Element rootElt = doc.getRootElement();
            String success = rootElt.elementTextTrim("success");
            if ("0".equals(success)) {
                //待收件数量节点；个人待收件数量
                String perUnhandled = rootElt.element("msg").elementTextTrim("perUnhandled");
                //待收件数量节点；单位待收件数量
                String orgUnhandled = rootElt.element("msg").elementTextTrim("orgUnhandled");
                baseBean.writeLog("个人待收件数量： " + perUnhandled);
                baseBean.writeLog("单位待收件数量： " + orgUnhandled);

                RecordSet recordSet1 = new RecordSet();
                recordSet1.execute("select workcode from uf_exchange where workcode = '" + workCode + "'");
                if (recordSet1.next()) {
                    update(workCode, lastname, perUnhandled, orgUnhandled);
                    baseBean.writeLog("个人/组织待接收件数量 更新成功");
                } else {
                    insert(id, workCode, lastname, perUnhandled, orgUnhandled);
                    baseBean.writeLog("个人/组织待接收件数量 插入成功 创建人id： " + id);
                }
            } else {
                baseBean.writeLog("待收件数量返回异常： " + requestXml);
            }
        } catch (Exception e) {
            baseBean.writeLog(e);
        }
        return "success";
    }

    private void insert(String id, String workCode, String lastname, String perUnhandled, String orgUnhandled) {
        ConnStatement statement = new ConnStatement();
        String sql = "insert into uf_exchange(workcode, lastname, perUnhandled, orgUnhandled,  formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                "values (?, ?, ?, ?, ?,?,?,?,?)";
        try {
            statement.setStatementSql(sql);
            statement.setString(1, workCode);
            statement.setString(2, lastname);
            statement.setString(3, perUnhandled);
            statement.setString(4, orgUnhandled);

            statement.setString(5, modelId);//模块id
            statement.setString(6, id);//创建人id
            statement.setString(7, "0");//一个默认值0
            statement.setString(8, com.weaver.general.TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(9, com.weaver.general.TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();

            //设置权限
            RecordSet maxSet = new RecordSet();
            ModeRightInfo ModeRightInfo = new ModeRightInfo();
            ModeRightInfo.setNewRight(true);
            maxSet.executeSql("select max(id) id from uf_exchange");
            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            ModeRightInfo.editModeDataShare(Integer.parseInt(id), Integer.parseInt(modelId), maxId);//创建人id， 模块id， 该条数据id
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(String workCode, String lastname, String perUnhandled, String orgUnhandled) {
        ConnStatement statement = new ConnStatement();
        String sql = "update uf_exchange set perUnhandled = ?, orgUnhandled = ?, lastname = ? where workcode = ?";
        try {
            statement.setStatementSql(sql);
            statement.setString(1, perUnhandled);
            statement.setString(2, orgUnhandled);
            statement.setString(3, lastname);
            statement.setString(4, workCode);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 访问交换系统接口
     *
     * @param workCode 工号
     */
    public String getCounts(String workCode) {
        try {
            String endpoint = "http://124.23.6.1:7888/tscp_jhx/tscpdxp/khdaipservice";
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
            call.setTimeout(3000);
            call.setOperationName(new javax.xml.namespace.QName("", "getUnhandledNumByOperId"));
            String requestXml = (String) call.invoke(new Object[]{workCode});
            new BaseBean().writeLog("交换接口返回xml： " + requestXml);
            requestXml = requestXml.replace("<?xml version=1.0 encoding=UTF-8?>", "");
            return requestXml;
        } catch (Exception e) {
            //e.printStackTrace();
            new BaseBean().writeLog("GetDoubleCount类 访问交换系统异常：" + e);
        }

        return "";
    }
}
