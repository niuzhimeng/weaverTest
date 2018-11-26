package com.weavernorth.zgsy.webUtil.util;

import com.weavernorth.zgsy.webUtil.BaseDataInfo;
import com.weavernorth.zgsy.webUtil.LoadServiceLocator;
import com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType;
import com.weavernorth.zgsy.webUtil.MaterialInfo;
import weaver.general.BaseBean;
import weaver.hrm.webservice.HrmServiceXmlUtil;

public class BaseDataUtil extends BaseBean {
    /**
     * 根据编码获取金蝶基础数据
     *
     * @param code 数据对应编码
     */
    public String getBaseData(String code) {
        String returnXml = "";
        LoadServiceLocator locator = new LoadServiceLocator();
        try {
            LoadServiceSoap_PortType loadServiceSoap = locator.getLoadServiceSoap();
            if ("004".equals(code.trim())) {
                //物料
                MaterialInfo[] materialInfos = loadServiceSoap.materialData(code);
                returnXml = ObjectToXml(materialInfos);
            } else {
                //其他基础数据
                BaseDataInfo[] baseDataInfos = loadServiceSoap.baseData(code);
                returnXml = ObjectToXml(baseDataInfos);
            }
        } catch (Exception e) {
            writeLog("webservice客户端出错： " + e);
        }
        return returnXml;
    }

    /**
     * 对象转String
     */
    private String ObjectToXml(Object obj) {
        HrmServiceXmlUtil xmlUtil = HrmServiceXmlUtil.getInstance();
        return xmlUtil.objToXml(obj);
    }


}
