package com.weavernorth.jiajiezb.hr.util;

public enum JiaJieConfigInfo {
    sfyjjbc("field89"), // JT是否有经济补偿金
    lzrq("field18"), // 离职日期
    zzrq("field17"), // 转正日期
    XZ_MODE_ID("23565"), // 薪资建模表模块id
    enterpriseId("000001");


    JiaJieConfigInfo(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
