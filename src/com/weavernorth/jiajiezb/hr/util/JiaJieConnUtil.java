package com.weavernorth.jiajiezb.hr.util;

import com.weavernorth.jiajiezb.hr.vo.JtDifferent;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.TimeUtil;

import java.lang.reflect.Field;
import java.util.*;

public class JiaJieConnUtil {
    public static Map<String, String> zdMap = new HashMap<String, String>();

    static {
        zdMap.put("bm", "部门");
        zdMap.put("gw", "岗位");
        zdMap.put("ddrq", "调动日期");
        zdMap.put("gwlx", "岗位类型");
        zdMap.put("zz", "职级");

        zdMap.put("bgdd", "办公地点");
        zdMap.put("wxyj", "五险一金缴纳地");
        zdMap.put("ldhtqs", "劳动合同签署主体");
        zdMap.put("bps", "BPS审批人");
        zdMap.put("cwou", "财务OU");

        zdMap.put("htcfd", "合同存放地");
        zdMap.put("syqdz", "试用期是否打折");
    }

    /**
     * 对比找出两对象不同字段
     *
     * @param beforeObj 旧对象
     * @param afterObj  新对象
     * @throws Exception
     */
    public static List<JtDifferent> compareObj(Object beforeObj, Object afterObj) throws Exception {
        List<JtDifferent> differentList = new ArrayList<JtDifferent>();

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
                JtDifferent different = new JtDifferent();
                different.setFieldId(beforeFields[i].getName());
                different.setBeforeValue(beforeValue);
                different.setAlterValue(afterValue);
                differentList.add(different);
            }
        }
        return differentList;
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
        RecordSet updateSet = new RecordSet();
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











