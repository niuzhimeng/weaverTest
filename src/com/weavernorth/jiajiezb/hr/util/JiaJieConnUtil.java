package com.weavernorth.jiajiezb.hr.util;

import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.TimeUtil;

import java.util.*;

public class JiaJieConnUtil {
    private static RecordSet updateSet = new RecordSet();
    public static Map<String, String> zjMap = new HashMap<String, String>();

    static {
        zjMap.put("0", "A");
        zjMap.put("1", "B");
        zjMap.put("2", "C");
        zjMap.put("3", "D");
        zjMap.put("4", "E");
        zjMap.put("5", "F");
        zjMap.put("6", "G");
    }

    /**
     * 查询人员自定义的某个字段
     *
     * @param userId 人员id
     * @param fileId 自定义字段id
     */
    public static String getCusById(String userId, String fileId) {
        if (userId == null || "".equals(userId) || fileId == null || "".equals(fileId)) {
            return "";
        }
        RecordSet recordSet = new RecordSet();
        String returnStr = "";
        recordSet.executeQuery("select " + fileId + " from CUS_FIELDDATA where id = '" + userId + "'");
        while (recordSet.next()) {
            if (!"".equals(recordSet.getString(fileId))) {
                returnStr = recordSet.getString(fileId);
            }
        }
        return returnStr;
    }

    /**
     * 建模赋权
     */
    public static void fuQuan(String currentTimeString, String tableName, int modeId) {
        ModeRightInfo moderightinfo = new ModeRightInfo();
        moderightinfo.setNewRight(true);
        RecordSet maxSet = new RecordSet();
        maxSet.executeSql("select id from " + tableName + " where MODEDATACREATEDATE || ' ' || MODEDATACREATETIME >= '" + currentTimeString + "'");

        int maxId;
        while (maxSet.next()) {
            maxId = maxSet.getInt("id");
            moderightinfo.rebuildModeDataShareByEdit(1, modeId, maxId); //创建人id， 模块id， 该条数据id
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
        String shiFouStr = "";
        if ("0".equals(sf)) {
            shiFouStr = "是";
        } else if ("1".equals(sf)) {
            shiFouStr = "否";
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











