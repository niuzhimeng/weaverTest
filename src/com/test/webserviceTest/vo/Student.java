package com.test.webserviceTest.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName(value = "mz", alternate = {"mz1", "mz2"})
    private String name;
    //@Expose(serialize = false)
    private String age;
    //@Expose(serialize = true, deserialize = false)
    private String identityCardNumber;

    public Student(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }
}
