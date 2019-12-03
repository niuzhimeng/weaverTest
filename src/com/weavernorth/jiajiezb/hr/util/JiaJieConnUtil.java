package com.weavernorth.jiajiezb.hr.util;

import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.TimeUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class JiaJieConnUtil {
    private static RecordSet updateSet = new RecordSet();

    public static void fuQuan(String currentTimeString, String tableName, int modeId) {
        ModeRightInfo moderightinfo = new ModeRightInfo();
        moderightinfo.setNewRight(true);
        RecordSet maxSet = new RecordSet();
        maxSet.executeSql("select id from " + tableName + " where MODEDATACREATEDATE + ' ' + MODEDATACREATETIME >= '" + currentTimeString + "'");

        int maxId;
        while (maxSet.next()) {
            maxId = maxSet.getInt("id");
            moderightinfo.editModeDataShare(1, modeId, maxId);//创建人id， 模块id， 该条数据id
        }
    }

    /**
     * 插入人员信息变更表
     *
     * @param bgr  变更人
     * @param bglc 变更流程
     * @param zdmc 字段名称
     * @param yz   原值
     * @param xz   现值
     */
    public static void insertPerCord(String bgr, String bglc, String zdmc, String yz, String xz) throws Exception {
        String currentDateString = TimeUtil.getCurrentDateString();
        updateSet.executeUpdate("insert into UF_RYKPBG(bgr, bglc, sxrq, zdmc, yz, xz) values(?,?,?,?,?, ?)",
                bgr, bglc, currentDateString, zdmc, yz, xz);
    }

    /**
     * 是否下拉框转换为文字
     */
    public static String yesOrNoChange(String sf) {
        String shiFouStr = "否";
        if ("0".equals(sf)) {
            shiFouStr = "是";
        }
        return shiFouStr;
    }

    /**
     * 插入自定义表的三条数据，带上基础字段
     *
     * @param userId 用户id
     */
    public static void insertBaseCus(int userId) {
        String[] baseIds = {"-1", "1", "3"};
        Set<String> baseSet = new HashSet<String>(Arrays.asList(baseIds));

        Set<String> SelectSet = new HashSet<String>();

        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select scopeid from CUS_FIELDDATA where id = " + userId);
        while (recordSet.next()) {
            SelectSet.add(recordSet.getString("scopeid"));
        }

        baseSet.removeAll(SelectSet);
        for (String s : baseSet) {
            recordSet.executeUpdate("insert into CUS_FIELDDATA(scope, scopeid, id)values(?,?,?)",
                    "HrmCustomFieldByInfoType", s, userId);
        }
    }
}











