package com.weavernorth.taide.createWorkFlow.vo;


import java.lang.annotation.Documented;

public class Result {
    /**
     * 如大于0则代表流程id，小于0 请查看相应错误信息
     */

    private Integer number;
    /*
     * @function 返回信息
     */
    private String message;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
