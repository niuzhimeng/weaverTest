package com.test.proxytest.impl;

import com.test.proxytest.Computer;

public class Lenovo implements Computer {
    @Override
    public String product(String pinPai) {
        return ("生产了： " + pinPai + " 电脑。");
    }
}
