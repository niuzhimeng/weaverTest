package com.weavernorth.zhongsha21.openweb.vo;

/**
 * 付款申请撤回数据传VO
 */
public class PaymentWithdrawVO {
    private String ZAPPN; // 打包单号
    private String ZOAID; // OA付款单号
    private String ZCHYY; // 撤回原因
    private String ZAUTH; // 审批人

    public String getZAUTH() {
        return ZAUTH;
    }

    public void setZAUTH(String ZAUTH) {
        this.ZAUTH = ZAUTH;
    }

    public String getZAPPN() {
        return ZAPPN;
    }

    public void setZAPPN(String ZAPPN) {
        this.ZAPPN = ZAPPN;
    }

    public String getZOAID() {
        return ZOAID;
    }

    public void setZOAID(String ZOAID) {
        this.ZOAID = ZOAID;
    }

    public String getZCHYY() {
        return ZCHYY;
    }

    public void setZCHYY(String ZCHYY) {
        this.ZCHYY = ZCHYY;
    }
}
