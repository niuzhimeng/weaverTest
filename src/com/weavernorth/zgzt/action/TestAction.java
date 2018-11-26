package com.weavernorth.zgzt.action;

import com.weaver.general.TimeUtil;
import com.weavernorth.zgzt.UserLogList;
import com.weavernorth.zgzt.UserLogRequest;
import com.weavernorth.zgzt.UserOperation;
import com.weavernorth.zgzt.UserOperationServiceLocator;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        //清空表里数据，重新添加
        RecordSet recordSet = new RecordSet();
        recordSet.execute("delete from uf_ygqj");

        UserOperationServiceLocator locator = new UserOperationServiceLocator();
        ConnStatement statement = new ConnStatement();
        RecordSet maxSet = new RecordSet();
        //设置权限
        ModeRightInfo ModeRightInfo = new ModeRightInfo();
        ModeRightInfo.setNewRight(true);
        try {
            UserOperation userOperationSoap11 = locator.getUserOperationSoap11();
            UserLogList[] userLogLists = userOperationSoap11.userLog(new UserLogRequest(1));
            String sql = "insert into uf_ygqj (startTime, endTime, reason, YgCode, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "values(?, ?, ?, ?,  ?,?,?,?,?)";
            statement.setStatementSql(sql);
            for (UserLogList u : userLogLists) {
                statement.setString(1, formatTime(u.getStartTime()));
                statement.setString(2, formatTime(u.getEndTime()));
                statement.setString(3, u.getReason().trim());
                statement.setString(4, String.valueOf(u.getId()).trim());

                statement.setString(5, "1");//模块id
                statement.setString(6, "8");//创建人id
                statement.setString(7, "0");//一个默认值0
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();

                maxSet.executeSql("select max(id) id from uf_ygqj");
                int maxId = 0;
                if (maxSet.next()) {
                    maxId = maxSet.getInt("id");
                }
                ModeRightInfo.editModeDataShare(8, 1, maxId);//创建人id， 模块id， 该条数据id
            }
        } catch (Exception e) {
            this.writeLog(e);
        } finally {
            statement.close();
        }

        return "1";
    }

    private String formatTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
}
