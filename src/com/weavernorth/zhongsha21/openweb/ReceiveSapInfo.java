package com.weavernorth.zhongsha21.openweb;

import com.weavernorth.zhongsha21.openweb.vo.MainTable;
import com.weavernorth.zhongsha21.openweb.vo.PaymentWithdrawVO;
import com.weavernorth.zhongsha21.openweb.vo.ResultVO;
import com.weavernorth.zhongsha21.openweb.vo.TradeResultVO;

import java.util.List;

public interface ReceiveSapInfo {
    /**
     * 交易结果回传oa，并更新oa台账
     *
     * @return
     */
    ResultVO receiveTradeResult(TradeResultVO[] tradeResultVO);

    /**
     * 创建排款流程
     */
    ResultVO createFlowOfPaiKuan(MainTable mainTable);

    /**
     * 付款撤回更新OA明细表
     */
    ResultVO paymentWithdraw(PaymentWithdrawVO[] paymentWithdrawVO);
}
