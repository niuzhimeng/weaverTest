package com.weavernorth.dadiyingyuan.acceptPayFeeStatus;

import javax.jws.WebMethod;

/*
 * @function 接收付款状态更新至流程表单
 * @date 2019-1-8
 * */
public interface acceptPayFeeStatusI {
    @WebMethod(action = "acceptPayFeeStatus")
    String acceptPayFeeStatus(String json);

}

