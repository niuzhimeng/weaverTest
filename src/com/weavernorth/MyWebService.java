package com.weavernorth;

import org.dom4j.DocumentException;

import javax.jws.WebMethod;
import javax.jws.WebService;

public interface MyWebService {

    String getName(String name) throws DocumentException;

    String getAge(String name) throws DocumentException;
}
