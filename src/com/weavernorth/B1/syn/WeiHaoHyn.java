package com.weavernorth.B1.syn;

import javax.jws.WebService;
@WebService(targetNamespace = "http://datasyn.wellhope.com/")
public interface WeiHaoHyn {
    /**
     * 组织机构和用户数据都是通过一个接口推送，而且每种数据有增加、删除、修改等操作类型。
     *
     * @param xmlString  变动的数据(xml格式)
     * @param strTrustId 应用的唯一标识
     * @return 1：应用接收数据成功
     * -101：增加机构唯一标识重复
     * -102：增加机构的上级机构不存在
     * -103：增加机构时同级机构名称重复
     * -201：机构唯一标识对应的机构不存在
     * -202：删除的机构下存在子机构
     * -203：删除的机构下存在用户
     * -301：机构唯一标识对应的机构不存在
     * -302：修改机构的上级机构不存在
     * -303：修改机构时同级机构名称重复
     *
     * -401：增加用户上级机构不存在
     * -402：增加用户信任号重复
     * -501：用户信任号对应的用户不存在
     * -502：修改用户时上级机构不存在
     * -601：用户信任号对应的用户不存在
     * -701：用户信任号对应的用户不存在
     * -702：用户信任号对应的用户处于删除状态
     * -703：用户信任号对应的用户处于取消授权状态
     *
     * -801：数据格式错误
     * -802：应用系统唯一标识错误
     * -803：其他错误
     * -804：应用系统Webservice服务异常
     */
    int CA_UpdateForSame(String xmlString, String strTrustId);
}
