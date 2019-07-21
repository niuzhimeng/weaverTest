package com.weavernorth.workflow;

import com.weavernorth.mail.util.MailSender;
import com.weavernorth.mail.util.MailSenderInfo;
import weaver.conn.RecordSet;
import weaver.docs.docs.DocComInfo;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.request.RequestComInfo;

/**
 * 1.公司发文、公司发函、公司会议纪要 2.部门发文、部门发函、部门会议纪要
 * 流程流转后，根据流程内“邮件提醒人员”字段，发送邮件给对应人员
 *
 * @author wgh
 */
public class DBN_MailRemind extends BaseAction {

    @Override
    public String execute(RequestInfo request) {
        this.writeLog("====节点后附加操作，发送邮件====");
        RequestComInfo reqInfo = null;
        try {
            reqInfo = new RequestComInfo();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RecordSet recordSet = new RecordSet();
        RecordSet rs = new RecordSet();
        // 邮件提醒人
        String strReceiveMail = "";
        // 邮件主题
        String strMailTheme = "";
        // 邮件内容
        String strMailContent = "";
        // 流程请求id
        String requestid = request.getRequestid();
        // 流程id
        String workflowid = request.getWorkflowid();
        // 流程表名
        String strTablename = "";
        // 获取表名
        String strSearchFormid = "select tablename from workflow_bill where id=(select formid from workflow_base where id=" + workflowid + ")";
        recordSet.executeSql(strSearchFormid);
        if (recordSet.next()) {
            strTablename = Util.null2String(recordSet.getString("tablename"));
        }

        // 查询sql
        String selectSql = "select * from " + strTablename + " where requestid=" + requestid;
        rs.execute(selectSql);
        if (rs.first()) {
            strReceiveMail = getEmails(Util.null2String(rs.getString("sjr")));
            strMailTheme = reqInfo.getRequestname(requestid);
            if (null == rs.getString("zw") || "".equals(rs.getString("zw"))) {
                // 没有正文字段
                strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("fj")), "附件", 0);
            } else {
                strMailContent = getHrefDocHtmlNames(Util.null2String(rs.getString("zw")), "套红版正文", -1)
                        + getHrefDocHtmlNames(Util.null2String(rs.getString("fj")), "附件", 0);
            }

        }

        this.writeLog("==邮件提醒人==strReceiveMail（默认）==" + strReceiveMail);
        this.writeLog("==邮件主题==strMailTheme==" + strMailTheme);
        this.writeLog("==邮件内容==strMailContent==" + strMailContent);

        // 调用发送邮件方法
        sendMail(strReceiveMail, strMailTheme, strMailContent);
        return SUCCESS;

    }

    /**
     * 发送邮件
     *
     * @param receiveAddress 接收者邮箱
     * @param mailTheme      邮件主题
     * @param mailContent    邮件内容
     */
    private void sendMail(String receiveAddress, String mailTheme, String mailContent) {
        this.writeLog("收件人邮箱： " + receiveAddress);
        this.writeLog("邮件主题： " + mailTheme);
        this.writeLog("邮件内容： " + mailContent);
        // 这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        // 发送邮件的服务器的IP
        mailInfo.setMailServerHost("10.102.176.215");
        // 发送邮件的服务器的端口
        mailInfo.setMailServerPort("587");
        // 是否需要身份验证
        mailInfo.setValidate(true);
        // 登陆邮件发送服务器的用户名和密码
        mailInfo.setUserName("OAadmin@ss-tpc.com");
        mailInfo.setPassword("W247811p");
        // 邮件发送者的地址
        mailInfo.setFromAddress("OAadmin@ss-tpc.com");
        // 邮件接收者的地址
        mailInfo.setToAddress(receiveAddress);
        // 邮件主题
        mailInfo.setSubject(mailTheme);
        // 邮件的文本内容
        mailInfo.setContent(mailContent);

        try {
            this.writeLog("====邮件发送是否成功====" + MailSender.sendHtmlMail(mailInfo));
        } catch (Exception e) {
            this.writeLog("====邮件发送异常====" + e);
        }
    }

    /**
     * 根据人员id，查询邮箱
     *
     * @param userIds 人员id，多个则用英文逗号分开
     */
    private static String getEmails(String userIds) {
        RecordSet rs = new RecordSet();
        // 邮件地址
        StringBuilder strEmails = new StringBuilder();
        // 用英文版逗号分隔人员id
        String[] strarray = userIds.split(",");
        for (String s : strarray) {
            String sql = "select email from hrmresource where id=" + s;
            rs.execute(sql);
            if (rs.first()) {
                strEmails.append(Util.null2String(rs.getString("email"))).append(",");
            }
        }
        return strEmails.toString();
    }

    /**
     * 根据docid查询出附件名称，并添加链接     如果是多个，则用英文逗号分开，附件名称则换行
     *
     * @param docids 附件id
     * @param label  附件名称前显示字段
     * @param num    起始数字， num 为-1时，不写数字
     * @return
     */
    private String getHrefDocHtmlNames(String docids, String label, int num) {
        DocComInfo docinfo = null;
        try {
            docinfo = new DocComInfo();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //附件名称，并添加链接
        String hrefDocHtmlNames;
        // 用英文版逗号分隔docid
        String[] strarray = docids.split(",");
        if (num < 0) {
            StringBuilder hrefDocHtmlNamesBuilder = new StringBuilder();
            for (String s : strarray) {
                if (!"".equals(Util.null2String(s))) {
                    hrefDocHtmlNamesBuilder.append("<br>").append(label).append(": <a href='https://oa.ss-tpc.com/login/loginSSO.jsp?gopage=/docs/docs/DocDsp.jsp?id=").append(s).append("'>").append(docinfo.getDocname(s)).append("</a></br>");
                }
            }
            hrefDocHtmlNames = hrefDocHtmlNamesBuilder.toString();
        } else {
            StringBuilder hrefDocHtmlNamesBuilder = new StringBuilder();
            for (String s : strarray) {
                this.writeLog("==for num====" + num);
                if (!"".equals(Util.null2String(s))) {
                    hrefDocHtmlNamesBuilder.append("<br>").append(label).append(num + 1).append(": <a href='https://oa.ss-tpc.com/login/loginSSO.jsp?gopage=/docs/docs/DocDsp.jsp?id=").append(s).append("'>").append(docinfo.getDocname(s)).append("</a></br>");
                }
                num += 1;
            }
            hrefDocHtmlNames = hrefDocHtmlNamesBuilder.toString();
        }

        return hrefDocHtmlNames;
    }
}
