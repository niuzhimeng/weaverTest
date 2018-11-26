package com.weavernorth;


import org.dom4j.DocumentException;
import weaver.general.BaseBean;

public class MyWebServiceImpl implements MyWebService {
    @Override
    public String getName(String name) throws DocumentException {
        new BaseBean().writeLog("================访问到接口  参数========== " + name);
        return "<?xml version=1.0 encoding=utf-8?>\n" +
                "<RequestList>\n" +
                "    <RequestInfo>\n" +
                "        <RequestId>1</RequestId>\n" +
                "        <RequestName>XXX申请1</RequestName>\n" +
                "        <RequestUrl>http://xxxx:8080/xxx/ViewRequest.do?requestid=1</RequestUrl>\n" +
                "    </RequestInfo>\n" +
                "    <RequestInfo>\n" +
                "        <RequestId>2</RequestId>\n" +
                "        <RequestName>XXX申请2</RequestName>\n" +
                "        <RequestUrl>http://xxxx:8080/xxx/ViewRequest.do?requestid=2</RequestUrl>\n" +
                "    </RequestInfo>\n" +
                "</RequestList>";
    }

    @Override
    public String getAge(String name) {
        return "age接口-》 " + name;
    }
}
