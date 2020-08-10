package com.test;

public class MyTest0724 implements TestInter {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("有个name: " + this.name);
    }
}
