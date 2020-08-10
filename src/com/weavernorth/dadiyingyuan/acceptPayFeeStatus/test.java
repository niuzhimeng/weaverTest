package com.weavernorth.dadiyingyuan.acceptPayFeeStatus;

import org.junit.Test;

public class test {
    @Test
    public void test(){
        String dh="CWZX07-201901-00003";
        String s = dh.substring(4,6);
        System.out.println("截取的数据"+s);
        String ee="";
        String ll="111";
        if(ee.equals(null)||"".equals(ee)){
            System.out.println("----"+ee);
        }else{
            System.out.println("000000");
        }
        String name=ee.equals("") ? "" : ee;
        System.out.println("ee----->"+ee);
        //String nn=ll.equals("") ? "" : ll;
        String str = "null".equals(ll) || ll.trim().length()== 0 ? "" : ll;
        System.out.println("ll---->"+str);
    }
}
