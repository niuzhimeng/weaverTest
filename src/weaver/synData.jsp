<%@ page language="java" contentType="text/html; charset=gbk" %>
<%@ page import="org.example.www.oatogys.Oa_to_mdm_gysPortTypeProxy" %>
<%@ page import="weaver.file.Prop" %>
<%@ page import="weaver.general.BaseBean" %>
<%@ page import="weaver.general.Util" %>
<%@ page import="weaver.hrm.HrmUserVarify" %>
<%@ page import="weaver.hrm.User" %>
<%@ page import="weaver.hrm.resource.ResourceComInfo" %>
<%@ page import="weaver.interfaces.filed.FieldValueUtil" %>
<%@ page import="java.util.UUID" %>


<jsp:useBean id="RecordSet" class="weaver.conn.RecordSet" scope="page"/>

<%
    User user = HrmUserVarify.getUser(request, response);
    try {

        String url = Util.null2String(Prop.getPropValue("DMD", "url")).trim();
        new BaseBean().writeLog("����ͬ����Ӧ���ݽӿڵ�ַ��" + url);
        Oa_to_mdm_gysPortTypeProxy client = new Oa_to_mdm_gysPortTypeProxy(url);

        String maintable = Util.null2String(Prop.getPropValue("DMD", "maintable")).trim();


        String reuestid = Util.null2String(request.getParameter("requestid"));
        String workflowid = Util.null2String(request.getParameter("workflowid"));
        String dwmcdw = "";                      //��Ӧ������
        String dwdzdw = "";                      //������ַ
        String fl = "";                      //��������
        String yhmc = "";                      //��������
        String yhzh = "";                      //�˻���
        String xydm = "";                      //ͳһ������ô���
        String jingbanr = "";                      //��ϵ��
        String jingbanrdh = "";                      //�ֻ�
        String sql = "select * from " + maintable + " where requestid='" + reuestid + "'";
        RecordSet.executeSql(sql);
        String my_fl = null;
        FieldValueUtil fieldvalueutil = new FieldValueUtil();
        if (RecordSet.next()) {
            dwmcdw = Util.null2String(RecordSet.getString("dwmcdw"));
            dwdzdw = Util.null2String(RecordSet.getString("dwdzdw"));
            fl = Util.null2String(RecordSet.getString("fl"));
            my_fl = fieldvalueutil.getDisplayFieldValue(user, workflowid, "fl", fl);
            my_fl = my_fl.replaceAll("\\s*", "");
            if (!"".equals(my_fl)) {
                int i = my_fl.indexOf("-");
                my_fl = my_fl.substring(0, i);
                new BaseBean().writeLog("nzm ���Ե�fl�����====>>> " + my_fl);
            }

            yhmc = Util.null2String(RecordSet.getString("yhmc"));
            yhzh = Util.null2String(RecordSet.getString("yhzh"));
            xydm = Util.null2String(RecordSet.getString("xydm"));
            jingbanr = Util.null2String(RecordSet.getString("jingbanr"));
            jingbanrdh = Util.null2String(RecordSet.getString("jingbanrdh"));

        }


        StringBuffer sb = new StringBuffer();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<DATAINFOS uuid=\"" + org.apache.commons.lang.StringUtils.leftPad(reuestid, 32, '0') + "\">");
        sb.append("<DATAINFO>");

        sb.append("<DESC1 REMARK=\"��Ӧ������\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "dwmcdw", dwmcdw) + "</DESC1>");
        sb.append("<DESC2 REMARK=\"��Ӧ�̼��\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "dwmcdw", dwmcdw) + "</DESC2>");
        sb.append("<DESC3 REMARK=\"��Ӧ������\"></DESC3>");
        sb.append("<DESC4 REMARK=\"���֤��\"></DESC4>");
        sb.append("<DESC5 REMARK=\"���պ���\"></DESC5>");
        sb.append("<DESC6 REMARK=\"ͳһ������ô���\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "xydm", xydm) + "</DESC6>");
        sb.append("<DESC7 REMARK=\"�˰�����\"></DESC7>");
        sb.append("<DESC8 REMARK=\"��֯��������\"></DESC8>");
        sb.append("<DESC9 REMARK=\"��˰��ʶ���\"></DESC9>");
        sb.append("<DESC10 REMARK=\"���˴���\"></DESC10>");

        sb.append("<DESC11 REMARK=\"���ǿͻ����ǹ�Ӧ��\" />");
        sb.append("<DESC12 REMARK=\"��Ӧ�ͻ�\" />");

        sb.append("<DESC14 REMARK=\"���ҵ���\"></DESC14>");
        sb.append("<DESC15 REMARK=\"ʡ\"></DESC15>");
        sb.append("<DESC16 REMARK=\"��\"></DESC16>");
        sb.append("<DESC17 REMARK=\"��\"></DESC17>");
        sb.append("<DESC18 REMARK=\"��ַ\"></DESC18>");
        sb.append("<DESC20 REMARK=\"������ַ\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "dwdzdw", dwdzdw) + "</DESC20>");
        sb.append("<DESC22 REMARK=\"��Ӧ��״̬\"></DESC22>");
        sb.append("<DESC23 REMARK=\"��������\">" + my_fl + "</DESC23>");
        sb.append("<DESC24 REMARK=\"�ϼ���Ӧ��\"></DESC24>");
        sb.append("<DESC25 REMARK=\"��Ӧ����Դ\">OAϵͳ</DESC25>");
        sb.append("<CODE REMARK=\"������\"></CODE>");
        sb.append("<UUID REMARK=\"UUID\">" + UUID.randomUUID().toString().replace("-", "").toLowerCase() + "</UUID>");

        sb.append("<SPECIALITYCODES>");

        sb.append("<SPECIALITYCODE SPECIALITYNAME=\"��Ӧ����Ϣ\" CATEGORYCODE=\"\" SPECIALITYCODE=\"A01\">");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"B023\" STANDARDCODE=\"\" PROPERTYNAME=\"�Ƿ����\" />");
        sb.append("</SPECIALITYCODE>");

        sb.append("<SPECIALITYCODE SPECIALITYNAME=\"�����˻�\" CATEGORYCODE=\"\" SPECIALITYCODE=\"A02\">");
        sb.append("<VALUELIST REMARK=\"�б�����\">");
        sb.append(" <PROPERTYCODE PROPERTYCODE=\"LISTCODE\" STANDARDCODE=\"\" PROPERTYNAME=\"�ڵ����\"></PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C004\" STANDARDCODE=\"\" PROPERTYNAME=\"�����˺�\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "yhzh", yhzh) + "</PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C001\" STANDARDCODE=\"\" PROPERTYNAME=\"�����б�\"></PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C002\" STANDARDCODE=\"\" PROPERTYNAME=\"��������\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "yhmc", yhmc) + "</PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C003\" STANDARDCODE=\"\" PROPERTYNAME=\"�˻���\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "yhzh", yhzh) + "</PROPERTYCODE>");
        sb.append("</VALUELIST>");
        sb.append("</SPECIALITYCODE>");
        sb.append("<SPECIALITYCODE SPECIALITYNAME=\"��ϵ����Ϣ\" CATEGORYCODE=\"\" SPECIALITYCODE=\"A04\">");
        sb.append("<VALUELIST REMARK=\"�б�����\">");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"LISTCODE\" STANDARDCODE=\"\" PROPERTYNAME=\"�ڵ����\"></PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C010\" STANDARDCODE=\"\" PROPERTYNAME=\"��ϵ��\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "jingbanr", jingbanr) + "</PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C011\" STANDARDCODE=\"\" PROPERTYNAME=\"�绰\">" + fieldvalueutil.getDisplayFieldValue(user, workflowid, "jingbanrdh", jingbanrdh) + "</PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C012\" STANDARDCODE=\"\" PROPERTYNAME=\"�̶��绰\"></PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C013\" STANDARDCODE=\"\" PROPERTYNAME=\"����\"></PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C014\" STANDARDCODE=\"\" PROPERTYNAME=\"����\"></PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C015\" STANDARDCODE=\"\" PROPERTYNAME=\"QQ\"></PROPERTYCODE>");
        sb.append("<PROPERTYCODE PROPERTYCODE=\"C016\" STANDARDCODE=\"\" PROPERTYNAME=\"΢�ź�\"></PROPERTYCODE>");
        sb.append("</VALUELIST>");
        sb.append("</SPECIALITYCODE>");
        sb.append("</SPECIALITYCODES>");
        sb.append("</DATAINFO>");
        sb.append("</DATAINFOS>");
        new BaseBean().writeLog("����====>>>" + sb.toString());
        String result = client.getGys(sb.toString());
        new BaseBean().writeLog("�ӿڷ���====>>>" + result);
        String code = result.substring(result.indexOf("<CODE>") + 5, result.indexOf("</CODE>"));
        new BaseBean().writeLog("code====>>>" + code);
        if (code.indexOf("S") > -1) {
            out.print("1");
            return;
        } else {
            out.print("0");
            return;
        }


    } catch (Exception e) {
        new BaseBean().writeLog("����ͬ����Ӧ���ݽӿڳ���");
        new BaseBean().writeLog(e);
        out.print("");
    }

%>
