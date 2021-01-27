package com.weavernorth.zhongsha21.openweb.vo;

import java.util.List;

/**
 * 主表实体类
 */
public class MainTable {

    private String ZCOMU; // 提交人
    private String ERDAT; // 日期
    private String ZAPPN; // 打包单号
    private String ZTEXT; // 审批流
    private String ZPAYN; // 金额

    private DetailTable[] detailTables; // 明细表数组

    public String getZCOMU() {
        return ZCOMU;
    }

    public void setZCOMU(String ZCOMU) {
        this.ZCOMU = ZCOMU;
    }

    public String getERDAT() {
        return ERDAT;
    }

    public void setERDAT(String ERDAT) {
        this.ERDAT = ERDAT;
    }

    public String getZAPPN() {
        return ZAPPN;
    }

    public void setZAPPN(String ZAPPN) {
        this.ZAPPN = ZAPPN;
    }

    public String getZTEXT() {
        return ZTEXT;
    }

    public void setZTEXT(String ZTEXT) {
        this.ZTEXT = ZTEXT;
    }

    public String getZPAYN() {
        return ZPAYN;
    }

    public void setZPAYN(String ZPAYN) {
        this.ZPAYN = ZPAYN;
    }

    public DetailTable[] getDetailTables() {
        return detailTables;
    }

    public void setDetailTables(DetailTable[] detailTables) {
        this.detailTables = detailTables;
    }
}
