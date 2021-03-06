package com.weavernorth.jiajiezb.hr.util;

public enum JiaJieConfigInfo {
    sfyjjbc("field94"), // JT是否有经济补偿金
    lzrq("field18"), // 离职日期
    zzrq("field17"), // 转正日期
    XZ_MODE_ID("22065"), // 薪资建模表uf_jtxz模块id
    ZHI_JI("field74"), // 职级

    ZHI_JI_SEL("2542"), // 职级-公共选择框id
    DIAO_DONG_RI_QI("field84"), // 调动日期
    GWLX("field75"), // 岗位类型
    GWLX_SEL("2543"), // 岗位类型-公共选择框id
    BGDD("field80"), // 办公地点

    WXYJ("field96"), // 五险一金缴纳地
    LDHT("field82"), // 劳动合同签署主体
    LDHT_SEL("2545"), // 劳动合同签署主体-公共选择框id
    BPS("field81"), // BPS审批人
    CWOU("field83"), // 财务OU

    CWOU_SEL("2546"), // 财务OU-公共选择框id
    QYKSRQ("field68"), // 合同签约开始日期
    QYJSRQ("field69"), // 合同签约结束日期
    RZRQ("field21"), // 入职日期
    YPSQB("field76"), // 应聘申请表

    TIAO_ZHENG_RI_QI("field85"), // 调整日期
    ZONG_BU_ID("21"), // 总部id
    JT_SYQZZ_FLOW_ID("21506"), // JT_试用期转正提醒*
    JT_ZZ_FLOW_ID("21507"), // JT_转正提醒*
    TRIGGER_TABLE_NAME("uf_triggered_zb"), // 定时触发流程，记录表名

    HTCFD("field89"), // 合同存放地
    HTCFD_SEL("2548"), // 合同存放地-公共选择框id
    SYQSFDZ("field93"), // JT_试用期是否打折
    MSPJB("field77"), // JT_面试评价表
    QYCS("field86"); // 劳动合同签署次数

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
