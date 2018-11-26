package com.weavernorth;

import org.example.www.oatogys.Oa_to_mdm_gysPortTypeProxy;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.filed.FieldValueUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.UUID;

public class ZhongXinTongBu extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        User user = requestInfo.getRequestManager().getUser();
        try {
            RecordSet recordSet = new RecordSet();
            String url = "http://39.105.52.38:6090/press.citic.esb.domain.mdm.pro_Gys_oa?wsdl";
            this.writeLog("调用同步供应数据接口地址：" + url);
            Oa_to_mdm_gysPortTypeProxy client = new Oa_to_mdm_gysPortTypeProxy(url);

            String maintable = "formtable_main_72";

            String reuestid = requestInfo.getRequestid();
            String workflowid = requestInfo.getWorkflowid();
            String dwmcdw = "";                      //供应商名称
            String dwdzdw = "";                      //完整地址
            String fl = "";                      //基本分类
            String yhmc = "";                      //开户银行
            String yhzh = "";                      //账户名
            String xydm = "";                      //统一社会信用代码
            String jingbanr = "";                      //联系人
            String jingbanrdh = "";                      //手机
            String sql = "select * from " + maintable + " where requestid='" + reuestid + "'";
            recordSet.executeSql(sql);
            String my_fl = null;
            FieldValueUtil fieldvalueutil = new FieldValueUtil();
            if (recordSet.next()) {
                dwmcdw = Util.null2String(recordSet.getString("dwmcdw"));
                dwdzdw = Util.null2String(recordSet.getString("dwdzdw"));
                fl = Util.null2String(recordSet.getString("fl"));
                my_fl = fieldvalueutil.getDisplayFieldValue(user, workflowid, "fl", fl);
                my_fl = my_fl.replaceAll("\\s*", "");
                if (!"".equals(my_fl)) {
                    int i = my_fl.indexOf("-");
                    my_fl = my_fl.substring(0, i);
                    this.writeLog("nzm 测试的fl更替后====>>> " + my_fl);
                }

                yhmc = Util.null2String(recordSet.getString("yhmc"));
                yhzh = Util.null2String(recordSet.getString("yhzh"));
                xydm = Util.null2String(recordSet.getString("xydm"));
                jingbanr = Util.null2String(recordSet.getString("jingbanr"));
                jingbanrdh = Util.null2String(recordSet.getString("jingbanrdh"));

            }


            StringBuffer sb = new StringBuffer();

            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            sb.append("<DATAINFOS uuid=\"" + org.apache.commons.lang.StringUtils.leftPad(reuestid, 32, '0') + "\">");
            sb.append("<DATAINFO>");

            sb.append("<DESC1 REMARK=\"供应商名称\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "dwmcdw", dwmcdw) + "</DESC1>");
            sb.append("<DESC2 REMARK=\"供应商简称\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "dwmcdw", dwmcdw) + "</DESC2>");
            sb.append("<DESC3 REMARK=\"供应商类型\"></DESC3>");
            sb.append("<DESC4 REMARK=\"身份证号\"></DESC4>");
            sb.append("<DESC5 REMARK=\"护照号码\"></DESC5>");
            sb.append("<DESC6 REMARK=\"统一社会信用代码\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "xydm", xydm) + "</DESC6>");
            sb.append("<DESC7 REMARK=\"邓白氏码\"></DESC7>");
            sb.append("<DESC8 REMARK=\"组织机构代码\"></DESC8>");
            sb.append("<DESC9 REMARK=\"纳税人识别号\"></DESC9>");
            sb.append("<DESC10 REMARK=\"法人代表\"></DESC10>");

            sb.append("<DESC11 REMARK=\"既是客户又是供应商\" />");
            sb.append("<DESC12 REMARK=\"对应客户\" />");

            sb.append("<DESC14 REMARK=\"国家地区\"></DESC14>");
            sb.append("<DESC15 REMARK=\"省\"></DESC15>");
            sb.append("<DESC16 REMARK=\"市\"></DESC16>");
            sb.append("<DESC17 REMARK=\"县\"></DESC17>");
            sb.append("<DESC18 REMARK=\"地址\"></DESC18>");
            sb.append("<DESC20 REMARK=\"完整地址\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "dwdzdw", dwdzdw) + "</DESC20>");
            sb.append("<DESC22 REMARK=\"供应商状态\"></DESC22>");
            sb.append("<DESC23 REMARK=\"基本分类\">" + my_fl + "</DESC23>");
            sb.append("<DESC24 REMARK=\"上级供应商\"></DESC24>");
            sb.append("<DESC25 REMARK=\"供应商来源\">OA系统</DESC25>");
            sb.append("<CODE REMARK=\"主编码\"></CODE>");
            sb.append("<UUID REMARK=\"UUID\">" + UUID.randomUUID().toString().replace("-", "").toLowerCase() + "</UUID>");

            sb.append("<SPECIALITYCODES>");

            sb.append("<SPECIALITYCODE SPECIALITYNAME=\"供应商信息\" CATEGORYCODE=\"\" SPECIALITYCODE=\"A01\">");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"B023\" STANDARDCODE=\"\" PROPERTYNAME=\"是否国内\" />");
            sb.append("</SPECIALITYCODE>");

            sb.append("<SPECIALITYCODE SPECIALITYNAME=\"银行账户\" CATEGORYCODE=\"\" SPECIALITYCODE=\"A02\">");
            sb.append("<VALUELIST REMARK=\"列表属性\">");
            sb.append(" <PROPERTYCODE PROPERTYCODE=\"LISTCODE\" STANDARDCODE=\"\" PROPERTYNAME=\"节点编码\"></PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C004\" STANDARDCODE=\"\" PROPERTYNAME=\"银行账号\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "yhzh", yhzh) + "</PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C001\" STANDARDCODE=\"\" PROPERTYNAME=\"银行行别\"></PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C002\" STANDARDCODE=\"\" PROPERTYNAME=\"开户银行\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "yhmc", yhmc) + "</PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C003\" STANDARDCODE=\"\" PROPERTYNAME=\"账户名\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "yhzh", yhzh) + "</PROPERTYCODE>");
            sb.append("</VALUELIST>");
            sb.append("</SPECIALITYCODE>");
            sb.append("<SPECIALITYCODE SPECIALITYNAME=\"联系人信息\" CATEGORYCODE=\"\" SPECIALITYCODE=\"A04\">");
            sb.append("<VALUELIST REMARK=\"列表属性\">");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"LISTCODE\" STANDARDCODE=\"\" PROPERTYNAME=\"节点编码\"></PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C010\" STANDARDCODE=\"\" PROPERTYNAME=\"联系人\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "jingbanr", jingbanr) + "</PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C011\" STANDARDCODE=\"\" PROPERTYNAME=\"电话\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "jingbanrdh", jingbanrdh) + "</PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C012\" STANDARDCODE=\"\" PROPERTYNAME=\"固定电话\"></PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C013\" STANDARDCODE=\"\" PROPERTYNAME=\"传真\"></PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C014\" STANDARDCODE=\"\" PROPERTYNAME=\"邮箱\"></PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C015\" STANDARDCODE=\"\" PROPERTYNAME=\"QQ\"></PROPERTYCODE>");
            sb.append("<PROPERTYCODE PROPERTYCODE=\"C016\" STANDARDCODE=\"\" PROPERTYNAME=\"微信号\"></PROPERTYCODE>");
            sb.append("</VALUELIST>");
            sb.append("</SPECIALITYCODE>");
            sb.append("</SPECIALITYCODES>");
            sb.append("</DATAINFO>");
            sb.append("</DATAINFOS>");
            writeLog("参数====>>>" + sb.toString());
            String result = client.getGys(sb.toString());
            writeLog("接口返回====>>>" + result);
            String code = result.substring(result.indexOf("<CODE>") + 5, result.indexOf("</CODE>"));
            writeLog("主数据返回====>>>" + code);
            if (code.contains("S")) {
                return SUCCESS;
            } else {
                requestInfo.getRequestManager().setMessageid("10000");
                requestInfo.getRequestManager().setMessagecontent("主数据同步出错");
            }

        } catch (Exception e) {
            writeLog("调用同步供应数据接口出错！");
            writeLog(e);
            requestInfo.getRequestManager().setMessageid("10000");
            requestInfo.getRequestManager().setMessagecontent("主数据同步出错");
        }

        return SUCCESS;
    }
}
