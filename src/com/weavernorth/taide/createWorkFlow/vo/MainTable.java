package com.weavernorth.taide.createWorkFlow.vo;

/**
 * 主表实体类
 */
public class MainTable {

    private String workCode; // 创建人工号

    private String BANFN; // 采购申请号

    private String BSART; // 请购凭证类型

    private DetailTable[] detailTables; // 明细表数组

    public String getBANFN() {
        return BANFN;
    }

    public void setBANFN(String BANFN) {
        this.BANFN = BANFN;
    }

    public String getBSART() {
        return BSART;
    }

    public void setBSART(String BSART) {
        this.BSART = BSART;
    }

    public DetailTable[] getDetailTables() {
        return detailTables;
    }

    public void setDetailTables(DetailTable[] detailTables) {
        this.detailTables = detailTables;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

}
