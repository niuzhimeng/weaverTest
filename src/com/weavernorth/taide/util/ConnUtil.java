package com.weavernorth.taide.util;

import weaver.conn.RecordSet;

public class ConnUtil {
    /**
     * 获取建模id
     *
     * @param type 数据类型
     * @return 建模id
     */
    public static Integer getModeIdByType(int type) {
        RecordSet recordSet = new RecordSet();
        int id = 0;
        recordSet.executeQuery("select jmid from  uf_loginInfo WHERE DATATYPE = " + type);
        if (recordSet.next()) {
            id = recordSet.getInt("jmid");
        }
        return id;
    }
}
