package com.test.sendMainTest;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailTest {

    /**
     * 发送邮件
     */
    @Test
    public void test47() {
        {
            // 收件人电子邮箱
            java.lang.String to = "ccy0625@foxmail.com";
           // String to = "zhimeng.niu@weaver.com.cn";
            // 发件人电子邮箱
            String from = "295290968@qq.com";
            // 指定发送邮件的主机为 localhost
            String host = "smtp.qq.com";

            String html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/html\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<div style=\"margin-left: -30px\">\n" +
                    "    <p style=\"text-indent: 4ex;font-size: 14px;font-family: 微软雅黑;margin-top: 5px;margin-bottom: 0px;\">您好：</p>\n" +
                    "    <p style=\"text-indent: 4ex;font-size: 14px;font-family: 微软雅黑;margin-top: 5px;margin-bottom: 0px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以下内容为您本月工资信息</p>\n" +
                    "    <p style=\"text-indent: 4ex;font-size: 14px;font-family: 微软雅黑;margin-top: 5px;margin-bottom: 0px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如有不清楚的地方，请和人力资源部联系。</p>\n" +
                    "    <p style=\"text-indent: 4ex;font-size: 14px;font-family: 微软雅黑;margin-top: 5px;margin-bottom: 0px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;谢谢合作！</p>\n" +
                    "    <p style=\"text-indent: 4ex;font-size: 14px;font-family: 微软雅黑;margin-top: 5px;margin-bottom: 0px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人力资源部</p>\n" +
                    "</div>\n" +
                    "\n" +
                    "<br/>\n" +
                    "<table border=\"1\">\n" +
                    "    <tr>\n" +
                    "        <th style=\"width: 100px\">基本工资</th>\n" +
                    "        <th style=\"width: 100px\">计件工资</th>\n" +
                    "        <th style=\"width: 100px\">绩效奖金</th>\n" +
                    "        <th style=\"width: 100px\">加班工资</th>\n" +
                    "        <th style=\"width: 100px\">其他奖罚</th>\n" +
                    "        <th style=\"width: 100px\">新津</th>\n" +
                    "        <th style=\"width: 100px\">津贴</th>\n" +
                    "        <th style=\"width: 100px\">夜补</th>\n" +
                    "        <th style=\"width: 100px\">郊区补贴</th>\n" +
                    "        <th style=\"width: 100px\">餐补驻外</th>\n" +
                    "        <th style=\"width: 100px\">通讯补贴</th>\n" +
                    "        <th style=\"width: 100px\">党群津贴</th>\n" +
                    "        <th style=\"width: 100px\">补扣</th>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>11</td>\n" +
                    "        <td>10,000</td>\n" +
                    "        <td>1,000</td>\n" +
                    "        <td>9,000</td>\n" +
                    "        <td>11</td>\n" +
                    "        <td>10,000</td>\n" +
                    "        <td>1,000</td>\n" +
                    "        <td>9,000</td>\n" +
                    "        <td>11</td>\n" +
                    "        <td>10,000</td>\n" +
                    "        <td>1,000</td>\n" +
                    "        <td>9,000</td>\n" +
                    "        <td>11</td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "<br/>\n" +
                    "<table border=\"1\">\n" +
                    "    <tr>\n" +
                    "        <th style=\"width: 100px\">病扣</th>\n" +
                    "        <th style=\"width: 100px\">事扣</th>\n" +
                    "        <th style=\"width: 100px\">应发工资</th>\n" +
                    "        <th style=\"width: 100px\">住房补贴</th>\n" +
                    "        <th style=\"width: 100px\">养老保险</th>\n" +
                    "        <th style=\"width: 100px\">失业保险</th>\n" +
                    "        <th style=\"width: 100px\">基本医疗保险</th>\n" +
                    "        <th style=\"width: 100px\">大额医疗保险</th>\n" +
                    "        <th style=\"width: 100px\">住房公积金</th>\n" +
                    "        <th style=\"width: 100px\">本次代扣税</th>\n" +
                    "        <th style=\"width: 100px\">托补</th>\n" +
                    "        <th style=\"width: 100px\">其他</th>\n" +
                    "        <th style=\"width: 100px\">实发合计</th>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>11</td>\n" +
                    "        <td>10,000</td>\n" +
                    "        <td>1,000</td>\n" +
                    "        <td>9,000</td>\n" +
                    "        <td>11</td>\n" +
                    "        <td>10,000</td>\n" +
                    "        <td>1,000</td>\n" +
                    "        <td>9,000</td>\n" +
                    "        <td>11</td>\n" +
                    "        <td>10,000</td>\n" +
                    "        <td>1,000</td>\n" +
                    "        <td>9,000</td>\n" +
                    "        <td>111</td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "<br/>\n" +
                    "<table border=\"1\">\n" +
                    "    <tr>\n" +
                    "        <th style=\"width: 100px\">年终奖应发</th>\n" +
                    "        <th style=\"width: 100px\">年终奖个税</th>\n" +
                    "        <th style=\"width: 100px\">年终奖实发</th>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>11</td>\n" +
                    "        <td>10,000</td>\n" +
                    "        <td>1,000</td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>";

            // 获取系统属性
            Properties properties = System.getProperties();
            properties.put("mail.smtp.auth", "true");
            properties.setProperty("mail.user", from);
            properties.setProperty("mail.password", "adnnfwimpfqxbhje");
            // 设置邮件服务器
            properties.setProperty("mail.smtp.host", host);

            // 获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("295290968@qq.com", "adnnfwimpfqxbhje"); //发件人邮件用户名、授权码
                }
            });

            try {
                // 创建默认的 MimeMessage 对象
                MimeMessage message = new MimeMessage(session);
                // Set From: 头部头字段
                message.setFrom(new InternetAddress(from));
                // Set To: 头部头字段
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                // Set Subject: 头部头字段
                message.setSubject("工资");
                // 设置消息体
                // message.setText("消息体");
                // 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为gbk
                message.setContent(html, "text/html;charset=gbk"); // 会覆盖消息体
                // 发送消息
                Transport.send(message);
                System.out.println("Sent message successfully....");
            } catch (Exception mex) {
                mex.printStackTrace();
            }
        }

    }
}
