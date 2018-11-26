package com.weavernorth.gaoji.vo;

import com.weavernorth.gaoji.ConsumerImpl;
import com.weavernorth.gaoji.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class PersonRunnable implements Runnable {
    private ConsumerImpl consumer = new ConsumerImpl();
    private List<PersonVo> personVoList;

    @Override
    public void run() {
        //人员集合
        List<HrmResource> hrmSubCompanyList = new ArrayList<HrmResource>();
        //人员对象赋值
        for (PersonVo vo : personVoList) {
            HrmResource hrmResource = new HrmResource();

            hrmResource.setWorkcode(vo.getSTAFFNUMBER());//员工编号
            hrmResource.setLastname(vo.getLASTNAME());//员工姓名
            hrmResource.setSex(Utils.sexChange(vo.getSEX()));//员工性别
            hrmResource.setLoginid(vo.getLOGINID());//系统登录号
            hrmResource.setCertificatenum(vo.getIDCARD());//身份证号

            hrmResource.setMobile(vo.getPHONE());//手机号码
            hrmResource.setStatus(vo.getSTATUS());//员工状态
            hrmResource.setLocation(vo.getLOCATION());//工作地点
            hrmResource.setEmail(vo.getEMAIL());//邮箱
            hrmResource.setManagerCode(vo.getDIRECTLEADER());//直接上级编码

            vo.getJOBACTIVITIENAME();//职位名称
            vo.getJOBLEVEL();//职位等级
            vo.getANNUALLEAVE();//年假结余
            vo.getCOMPANY();//单位
            hrmResource.setSeclevel(vo.getSECLEVEL());//安全等级

            hrmResource.setCreatedate(vo.getENTRYDATEChange());//入职时间
            hrmResource.setStartdate(vo.getDATEFIELDChange());//合同生效时间
            hrmResource.setEnddate(vo.getENDDATEChange());//合同结束时间
            hrmResource.setLaborrelation(vo.getLABORRELATION());//合同主体（合同签订单位）=================建模表
            hrmResource.setBankaccount(vo.getBANKACCOUNT());//银行账户

            hrmResource.setAccountcity(vo.getACCOUNTCITY());//开户城市
            hrmResource.setOpeningbank(vo.getOPENINGBANK());//开户行
            hrmResource.setSubbranchmess(vo.getSUBBRANCHMESS());//支行信息                =================建模表
            hrmResource.setDepcode(vo.getDEPCODE());//所属部门编号
            hrmResource.setJobtitlecode(vo.getJOBTITLECODE());//岗位编码

            vo.getSAPCOST();//成本中心对应SAP

            hrmSubCompanyList.add(hrmResource);
        }

        if (hrmSubCompanyList.size() > 0) {
            consumer.operateRenYuan(hrmSubCompanyList);
        }
    }

    public List<PersonVo> getPersonVoList() {
        return personVoList;
    }

    public void setPersonVoList(List<PersonVo> personVoList) {
        this.personVoList = personVoList;
    }
}
