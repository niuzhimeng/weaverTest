package com.weavernorth.taide.invoice;

public enum ConfigInfo {
    InvoiceUrl("http://101.124.7.184:8111/rest/openApi/invoice/dii"),
    appSecKey("116837c1750110f87f285feb2148ad2c"),
    appId("BXSDK"),
    appSecId("d4bf814c02abb801a2a2b6742a6d140a"),
    enterpriseId("000001");


    ConfigInfo(String value) {
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
