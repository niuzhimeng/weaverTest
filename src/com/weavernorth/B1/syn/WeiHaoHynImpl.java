package com.weavernorth.B1.syn;

import com.weavernorth.B1.syn.util.Utils;
import com.weavernorth.B1.syn.vo.HrmDepartment;
import com.weavernorth.B1.syn.vo.HrmResource;
import com.weavernorth.B1.syn.vo.HrmSubCompany;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.resource.ResourceComInfo;

public class WeiHaoHynImpl extends BaseBean implements WeiHaoHyn {
    /**
     * 分部id
     */
    private static final String HRM_SUB_COMPANY_ID = "1";
    /**
     * 分部code
     */
    private static final String HRM_SUB_COMPANY_CODE = "55010001@qgw.zg";
    /**
     * 系统标识
     */
    private static final String TRUST_ID = "2501630004@rmyh.gov";

    @Override
    public int CA_UpdateForSame(String xmlString, String strTrustId) {
        if (!TRUST_ID.equals(strTrustId.trim())) {
            //应用系统唯一标识错误
            return -802;
        }
        try {
            Document doc = DocumentHelper.parseText(xmlString);
            Element rootElt = doc.getRootElement();
            Element user = rootElt.element("user");
            Element org = rootElt.element("org");
            this.writeLog("user: " + user);
            this.writeLog("org: " + org);
            if ((user != null && org != null) || (user == null && org == null)) {
                //数据格式错误
                return -801;
            }
            if (user != null) {
                int userFlag = 1;
                String oper = user.elementTextTrim("oper");
                //用户信任号
                String workcode = user.elementTextTrim("id");
                //用户姓名
                String name = user.elementTextTrim("name");
                //用户所属机构名称
                String dname = user.elementTextTrim("dname");
                //用户所属机构标识
                String did = user.elementTextTrim("did");
                //用户性别 1:男，0：女
                String sexual = user.elementTextTrim("sexual");
                //用户账号
                String account = user.elementTextTrim("account");
                //用户密级
                String grade = user.elementTextTrim("grade");
                //变更部门 新部门
                String newdid = user.elementTextTrim("newdid");

                this.writeLog("synHrmresource 人员编号 id-----" + workcode);
                this.writeLog("synHrmresource 人员姓名 name-----" + name);
                this.writeLog("synHrmresource 人员所属机构名称 dname-----" + dname);
                this.writeLog("synHrmresource 人员所属机构编号 did-----" + did);
                this.writeLog("synHrmresource 人员性别 sexual 1:男，0：女-----" + sexual);
                this.writeLog("synHrmresource 人员账号 account-----" + account);
                this.writeLog("synHrmresource 人员密级 grade-----" + grade);

                int oaId = Util.getIntValue(Utils.getIdByCode("hrmresource", workcode), 0);//得到人员ID
                int depid = Util.getIntValue(Utils.getIdByCode("hrmdepartment", did), 0);

                HrmResource hrmResource = new HrmResource();
                hrmResource.setId(String.valueOf(oaId));
                hrmResource.setWorkcode(workcode);
                hrmResource.setLastname(name);
                hrmResource.setLoginid(account);
                hrmResource.setStatus("1");
                hrmResource.setPassword(Util.getEncrypt("123456"));
                hrmResource.setAccounttype("0"); //账号类型
                hrmResource.setBelongto("");     //所属主帐号
                hrmResource.setDepartmentid(String.valueOf(depid));
                hrmResource.setSubcompanyid1(HRM_SUB_COMPANY_ID);//上级分部
                hrmResource.setGrade(grade);
                hrmResource.setSex(sexual);
                hrmResource.setStatus("1");
                hrmResource.setSystemlanguage("7");

                if ("add".equals(oper)) {
                    this.writeLog("新增用户===========start");
                    if (depid <= 0) {
                        this.writeLog("-401 增加用户上级机构不存在");
                        return -401;
                    }
                    RecordSet recordSet = new RecordSet();
                    recordSet.execute("select id from hrmresource where workcode = '" + workcode + "'");
                    if (recordSet.next()) {
                        //增加用户信任号重复
                        this.writeLog("-402 增加用户信任号重复: " + workcode);
                        return -402;
                    }
                    int id = Utils.getHrmMaxid();
                    hrmResource.setId(String.valueOf(id));
                    userFlag = hrmResource.insertHrmResource();
                    if (userFlag == 1) {
                        //新创建的人员，将其他权限数据删除
                        hrmResource.deleteHrmResource5(String.valueOf(id), "1");
                    }
                    this.writeLog("新增用户成功===========");
                    this.writeLog("新增用户===========end");
                } else if ("mod".equals(oper)) {
                    this.writeLog("修改用户===========start");
                    RecordSet recordSet = new RecordSet();
                    recordSet.execute("select id from hrmresource where workcode ='" + workcode + "'");
                    if (!recordSet.next()) {
                        //用户信任号对应的用户不存在
                        this.writeLog("-501 用户信任号对应的用户不存在: " + workcode);
                        return -501;
                    }
                    userFlag = hrmResource.updateHrmResource();
                    if(userFlag == 1){
                        this.writeLog("修改用户成功");
                    }
                    this.writeLog("修改用户===========end");
                } else if ("deluser".equals(oper)) {
                    this.writeLog("删除人员===========");
                }else if("change".equals(oper)){
                    this.writeLog("人员修改部门===========");
                    int changeDepid = Util.getIntValue(Utils.getIdByCode("hrmdepartment", newdid), 0);
                    if (changeDepid <= 0) {
                        this.writeLog("-502：修改用户时上级机构不存在");
                        return -502;
                    }
                    hrmResource.setDepartmentid(String.valueOf(changeDepid));
                    hrmResource.updateHrmResourceDepartment();
                }
                new ResourceComInfo().removeResourceCache();
                return userFlag;
            } else {
                int orgFlag = 1;
                String oper = org.elementTextTrim("oper");
                //机构名称
                String name = org.elementTextTrim("name");
                //机构唯一标识
                String departmentcode = org.elementTextTrim("id");
                //上级机构唯一标识
                String pid = org.elementTextTrim("pid");
                //机构排序号
                String order = org.elementTextTrim("order");
                //机构规范编码
                String standardcode = org.elementTextTrim("standardcode");
                //机构规范名称
                String standardname = org.elementTextTrim("standardname");

                this.writeLog("synSubcompany 机构名称 name-----" + name);
                this.writeLog("synSubcompany 机构唯一标识 departmentcode-----" + departmentcode);
                this.writeLog("synSubcompany 上级机构唯一标识 pid-----" + pid);
                this.writeLog("synSubcompany 机构排序号 order-----" + order);
                this.writeLog("synSubcompany 机构规范编码 standardcode-----" + standardcode);
                this.writeLog("synSubcompany 机构规范名称 standardname-----" + standardname);
                if ("".equals(pid)) {
                    this.writeLog("更新顶级机构");
                    //更新顶级机构
                    HrmSubCompany hrmSubCompany = new HrmSubCompany();
                    hrmSubCompany.setSubcompanycode(departmentcode);
                    hrmSubCompany.setSubcompanyname(name);
                    hrmSubCompany.setShoworder(order);
                    hrmSubCompany.setCanceled("0");
                    hrmSubCompany.updateHrmSubCompany();
                    new SubCompanyComInfo().removeCompanyCache();
                    return 1;
                }
                //部门ID
                int depid = Util.getIntValue(Utils.getIdByCode("hrmdepartment", departmentcode), 0);
                //上级id
                int Supdepid = Util.getIntValue(Utils.getIdByCode("hrmdepartment", pid), 0);

                this.writeLog("部门id： " + depid);
                this.writeLog("上级部门id： " + Supdepid);
                HrmDepartment hrmDepartment = new HrmDepartment();
                hrmDepartment.setId(String.valueOf(depid));
                hrmDepartment.setCanceled("0");
                hrmDepartment.setDepartmentcode(departmentcode);
                hrmDepartment.setDepartmentname(name);
                hrmDepartment.setSupdepid(String.valueOf(Supdepid));
                hrmDepartment.setSubcompanyid1(HRM_SUB_COMPANY_ID);
                hrmDepartment.setShoworder(order);
                if ("add".equals(oper)) {
                    this.writeLog("新增机构===========start");
                    RecordSet recordSet = new RecordSet();
                    recordSet.execute("select id from HrmDepartment where departmentcode = '" + departmentcode + "'");
                    if(recordSet.next()){
                        this.writeLog("-101：增加机构唯一标识重复");
                        return -101;
                    }
                    if (Supdepid == 0 && (!pid.equals(HRM_SUB_COMPANY_CODE))) {
                        //上级不是部门，也不是顶级机构
                        this.writeLog("-102 增加机构的上级机构不存在");
                        return -102;
                    }
                    boolean b = hrmDepartment.insertHrmDepartment();
                    if (b) {
                        this.writeLog("新增机构成功");
                    }
                    this.writeLog("新增机构===========end");
                } else if ("mod".equals(oper)) {
                    this.writeLog("更新机构===========start");
                    RecordSet recordSet = new RecordSet();
                    recordSet.execute("select id from HrmDepartment where departmentcode = '" + departmentcode + "'");
                    if(!recordSet.next()){
                        this.writeLog("-201 机构唯一标识对应的机构不存在");
                        return -201;
                    }
                    boolean b = hrmDepartment.updateHrmDepartment();
                    if (b) {
                        this.writeLog("更新机构成功");
                    }
                    this.writeLog("更新机构===========end");
                } else if ("del".equals(oper)) {
                    this.writeLog("删除机构===========start");
                    orgFlag = hrmDepartment.deleteHrmDepartment();
                    this.writeLog("删除机构===========end");
                }
                new DepartmentComInfo().removeCompanyCache();
                return orgFlag;
            }
        } catch (Exception e) {
            this.writeLog(e);
            this.writeLog("异常时接收的xml： " + xmlString);
            return -804;
        }
    }
}
