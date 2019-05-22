package com.weavernorth.financeBaseInfo;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface FinanceBasePxf {

    /**
     * 组织表
     */
    @WebMethod(action = "easOrg")
    String easOrg(String json);

    /**
     * 职员表
     */
    @WebMethod(action = "easUser")
    String easUser(String json);

    /**
     * 职员银行
     */
    @WebMethod(action = "easPersonBank")
    String easPersonBank(String json);

    /**
     * 客户1 - 银行N
     */
    @WebMethod(action = "easCustomerAndBank")
    String easCustomerAndBank(String json);

    /**
     * 供应商1 - 银行N
     */
    @WebMethod(action = "easSupplierAndBank")
    String easSupplierAndBank(String json);

    /**
     * 科目表
     */
    @WebMethod(action = "easAccount")
    String easAccount(String json);

    /**
     * 业务类别表
     */
    @WebMethod(action = "easExpenseCategory")
    String easExpenseCategory(String json);

    /**
     * 费用类型表
     */
    @WebMethod(action = "easExpenseType")
    String easExpenseType(String json);

    /**
     * 支付方式表
     */
    @WebMethod(action = "easPayType")
    String easPayType(String json);

    /**
     * 核算项目类别表
     */
    @WebMethod(action = "easAssistant")
    String easAssistant(String json);

    /**
     * 产品档案表
     */
    @WebMethod(action = "easProduct")
    String easProduct(String json);

    /**
     * 收付类别表
     */
    @WebMethod(action = "easReceiptsType")
    String easReceiptsType(String json);

    /**
     * 税目税率表
     */
    @WebMethod(action = "easTaxRate")
    String easTaxRate(String json);

    /**
     * 计税方法
     */
    @WebMethod(action = "easTaxCount")
    String easTaxCount(String json);

}
