package com.weavernorth.jiajiezb.hr.util;

public enum JiaJieConfigInfo {
    sfyjjbc("field89"), // JT是否有经济补偿金
    lzrq("field18"), // 离职日期
    zzrq("field17"), // 转正日期
    XZ_MODE_ID("23565"), // 薪资建模表模块id
    ZHI_JI("field71"), // 职级

    ZHI_JI_SEL("3042"), // 职级-公共选择框id
    DIAO_DONG_RI_QI("field80"), // 调动日期
    GWLX("field72"), // 岗位类型
    GWLX_SEL("3043"), // 岗位类型-公共选择框id
    BGDD("field70"), // 办公地点

    WXYJ("field91"), // 五险一金缴纳地
    LDHT("field74"), // 劳动合同签署主体
    LDHT_SEL("3044"), // 劳动合同签署主体-公共选择框id
    BPS("field73"), // BPS审批人
    CWOU("field76"), // 财务OU

    CWOU_SEL("3045"), // 财务OU-公共选择框id
    QYKSRQ("field68"), // 合同签约开始日期
    QYJSRQ("field69"), // 合同签约结束日期
    RZRQ("field21"), // 入职日期
    YPSQB("field93"), // 应聘申请表

    TIAO_ZHENG_RI_QI("field81"), // 调整日期
    ZONG_BU_ID("21"), // 总部id
    JT_SYQZZ_FLOW_ID("22001"), // JT_试用期转正提醒*
    JT_ZZ_FLOW_ID("22002"), // JT_转正提醒*
    TRIGGER_TABLE_NAME("uf_triggered_zb"), // 定时触发流程，记录表名

    QYCS("field87"); // 劳动合同签署次数

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
