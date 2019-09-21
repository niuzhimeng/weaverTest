package com.weavernorth.jiajie.util;

import com.weavernorth.jiajie.personchange.vo.Different;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.TimeUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JiaJieUtil {
    /**
     * 人员卡片更新 模块id
     */
    private static final Integer MODE_ID = 14564;

    private static ModeRightInfo moderightinfo = new ModeRightInfo();

    /**
     * 插入人员信息变更表
     *
     * @param requestId   流程id
     * @param oldVal      旧值
     * @param newVal      新值
     * @param fieldName   字段显示名
     * @param flowCreator 流程创建人
     */
    public static void insertPerCord(String requestId, String oldVal, String newVal, String fieldName, String flowCreator) throws Exception {
        String myDate = TimeUtil.getCurrentTimeString().substring(0, 10);
        String myTime = TimeUtil.getCurrentTimeString().substring(11);
        RecordSet updateSet = new RecordSet();
        updateSet.executeUpdate("insert into uf_rykplsjl(bglc, sxrq, yz, xz, zdmc, lccjr, " +
                        " formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values(?,?,?,?,?, ?,?,?,?,?, ?)",
                requestId, myDate, oldVal, newVal, fieldName, flowCreator,
                MODE_ID, 1, 0, myDate, myTime);
    }

    /**
     * 建模表授权
     *
     * @param myTime 操作开始时间
     */
    public static void rebuildModeDataShare(String myTime) throws Exception {
        //赋权
        moderightinfo.setNewRight(true);
        RecordSet maxSet = new RecordSet();
        maxSet.executeSql("select id from uf_rykplsjl where MODEDATACREATEDATE || MODEDATACREATETIME >= '" + myTime + "'");

        int maxId;
        while (maxSet.next()) {
            maxId = maxSet.getInt("id");
            // 创建人id， 模块id， 该条数据id
            moderightinfo.rebuildModeDataShareByEdit(1, MODE_ID, maxId);
        }
    }

    /**
     * 对比找出两对象不同字段
     *
     * @param beforeObj 旧对象
     * @param afterObj  新对象
     * @throws Exception
     */
    public static List<Different> compareObj(Object beforeObj, Object afterObj) throws Exception {
        List<Different> differentList = new ArrayList<Different>();

        if (!beforeObj.getClass().isAssignableFrom(afterObj.getClass())) {
            throw new Exception("两个对象不相同，无法比较");
        }

        //取出属性
        Field[] beforeFields = beforeObj.getClass().getDeclaredFields();
        Field[] afterFields = afterObj.getClass().getDeclaredFields();
        Field.setAccessible(beforeFields, true);
        Field.setAccessible(afterFields, true);

        //遍历取出差异值
        for (int i = 0; i < beforeFields.length; i++) {
            String beforeValue = String.valueOf(beforeFields[i].get(beforeObj));
            String afterValue = String.valueOf(afterFields[i].get(afterObj));
            // 有值 ->
            boolean a = (beforeValue != null && !"".equals(beforeValue)) && !beforeValue.equals(afterValue);
            // 无值 ->
            boolean b = (beforeValue == null || "".equals(beforeValue)) && (afterValue != null && !"".equals(afterValue));
            if (a || b) {
                Different different = new Different();
                different.setFieldId(beforeFields[i].getName());
                different.setBeforeValue(beforeValue);
                different.setAlterValue(afterValue);
                differentList.add(different);
            }
        }
        return differentList;
    }
}
