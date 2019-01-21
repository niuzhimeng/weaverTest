package com.weavernorth.taide.createWorkFlow.impl;

import com.weavernorth.taide.createWorkFlow.CreatePurchase;
import com.weavernorth.taide.createWorkFlow.vo.MainTalbe;
import com.weavernorth.taide.createWorkFlow.vo.Result;

import javax.jws.WebService;

@WebService
public class CreatePurchaseImpl implements CreatePurchase {
    @Override
    public Result createWorkFlow(MainTalbe mainTalbe) {

        Result result = new Result();
        result.setNumber(100);
        result.setMessage("创建成功");
        return result;
    }
}
