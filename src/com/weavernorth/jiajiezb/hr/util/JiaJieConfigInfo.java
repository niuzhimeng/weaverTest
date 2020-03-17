package com.weavernorth.jiajiezb.hr.util;

public enum JiaJieConfigInfo {
    sfyjjbc("field89"),
    lzrq("field18"),
    zzrq("field17"),
    XZ_MODE_ID("23565"),
    ZHI_JI("field71"),
    ZHI_JI_SEL("3042"),
    DIAO_DONG_RI_QI("field80"),
    GWLX("field72"),
    GWLX_SEL("3043"),
    BGDD("field70"),
    WXYJ("field102"),
    LDHT("field74"),
    LDHT_SEL("3044"),
    BPS("field73"),
    CWOU("field76"),
    CWOU_SEL("3045"),
    QYKSRQ("field68"),
    QYJSRQ("field69"),
    RZRQ("field21"),
    YPSQB("field93"),
    TIAO_ZHENG_RI_QI("field81"),
    ZONG_BU_ID("21"),
    JT_SYQZZ_FLOW_ID("22001"),
    JT_ZZ_FLOW_ID("22002"),
    TRIGGER_TABLE_NAME("uf_triggered_zb"),
    HTCFD("field96"),
    HTCFD_SEL("4041"),
    SYQSFDZ("field95"),
    MSPJB("field94"),
    QYCS("field87");

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
