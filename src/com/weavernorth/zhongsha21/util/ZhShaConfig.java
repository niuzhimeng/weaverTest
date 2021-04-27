package com.weavernorth.zhongsha21.util;

public enum ZhShaConfig {
    TI_XING_FLOW_ID("414"), // 提醒流程id
    FU_KUAN_FLOW_ID("417"), // 付款印鉴审批流程id
    FU_KUAN_TABLE_NAME("formtable_main_307"); // 付款印鉴审批流程表名

    ZhShaConfig(String value) {
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
