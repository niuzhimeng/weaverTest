package com.weavernorth.taide.kaoQin.jbxx;

import com.google.gson.Gson;
import com.weaver.general.TimeUtil;
import com.weavernorth.taide.kaoQin.jbxx.myWeb.*;
import com.weavernorth.taide.util.ConnUtil;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 总部新入职人员信息录入
 */
public class BaseInfoAction extends BaseAction {

    private Gson gson = new Gson();

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        int formid = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formid + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("总部新入职人员信息录入 start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName);
        try {
            if (operatetype.equals("submit")) {
                // 查询主表
                recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
                recordSet.next();
                int mainId = recordSet.getInt("id");
                String lcbh = recordSet.getString("lcbh"); // 流程编号
                String workCode = recordSet.getString("sqgh"); // 工号

                // 查询明细表 - 个人信息
                RecordSet mxSet = new RecordSet();
                mxSet.executeQuery("select * from " + tableName + "_dt1 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP0002[] dt_hri011_indataitemsp0002s = new DT_HRI011_INDATAITEMSP0002[mxSet.getCounts()];
                int i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "SUBTY");

                    DT_HRI011_INDATAITEMSP0002 dt_hri011_indataitemsp0002 = new DT_HRI011_INDATAITEMSP0002();
                    dt_hri011_indataitemsp0002.setPERNR(workCode); // 工号
                    dt_hri011_indataitemsp0002.setBEGDA(changeDays(mxSet.getString("BEGDA"))); // 开始日期
                    dt_hri011_indataitemsp0002.setENDDA(changeDays(usrties[2])); // 结束日期
                    dt_hri011_indataitemsp0002.setSUBTY(usrties[3]); //子信息类型
                    dt_hri011_indataitemsp0002.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp0002.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp0002.setFATXT(mxSet.getString("FATXT")); // 婚姻状况
                    dt_hri011_indataitemsp0002.setANZKD(mxSet.getString("ANZKD")); // 子女数目

                    dt_hri011_indataitemsp0002.setAdditional1("");
                    dt_hri011_indataitemsp0002.setAdditional2("");
                    dt_hri011_indataitemsp0002.setAdditional3("");
                    dt_hri011_indataitemsp0002.setAdditional4("");

                    dt_hri011_indataitemsp0002s[i] = dt_hri011_indataitemsp0002;
                    i++;
                }

                // 查询明细表 - 通讯类型
                mxSet.executeQuery("select * from " + tableName + "_dt2 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP0015[] dt_hri011_indataitemsp0015s = new DT_HRI011_INDATAITEMSP0015[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "USRTY");

                    DT_HRI011_INDATAITEMSP0015 dt_hri011_indataitemsp0015 = new DT_HRI011_INDATAITEMSP0015();
                    dt_hri011_indataitemsp0015.setPERNR(workCode); // 员工编号
                    dt_hri011_indataitemsp0015.setBEGDA(changeDays(mxSet.getString("BEGDA"))); // 开始日期
                    dt_hri011_indataitemsp0015.setENDDA(changeDays(usrties[2])); // 结束日期
                    dt_hri011_indataitemsp0015.setUSRTY(mxSet.getString("USRTY")); //通讯类型
                    dt_hri011_indataitemsp0015.setUSRID(mxSet.getString("USRID")); // 标识号

                    dt_hri011_indataitemsp0015.setSUBTY(usrties[3]); // 子信息类型(填通讯类型)
                    dt_hri011_indataitemsp0015.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数
                    dt_hri011_indataitemsp0015.setOPTION(usrties[0]); // INS/MOD

                    dt_hri011_indataitemsp0015.setAdditional1("");
                    dt_hri011_indataitemsp0015.setAdditional2("");
                    dt_hri011_indataitemsp0015.setAdditional3("");
                    dt_hri011_indataitemsp0015.setAdditional4("");
                    dt_hri011_indataitemsp0015s[i] = dt_hri011_indataitemsp0015;
                    i++;
                }

                // 查询明细表 - 社会关系
                mxSet.executeQuery("select * from " + tableName + "_dt3 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP0021[] dt_hri011_indataitemsp0021s = new DT_HRI011_INDATAITEMSP0021[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "FAMSA");

                    DT_HRI011_INDATAITEMSP0021 dt_hri011_indataitemsp0021 = new DT_HRI011_INDATAITEMSP0021();
                    dt_hri011_indataitemsp0021.setPERNR(workCode); // 工号
                    dt_hri011_indataitemsp0021.setBEGDA(changeDays(mxSet.getString("BEGDA"))); // 开始日期
                    dt_hri011_indataitemsp0021.setENDDA(changeDays(usrties[2])); // 结束日期
                    dt_hri011_indataitemsp0021.setSUBTY(usrties[3]); //子信息类型
                    dt_hri011_indataitemsp0021.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp0021.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp0021.setFAMSA(mxSet.getString("FAMSA")); // 成员
                    dt_hri011_indataitemsp0021.setFANAM(mxSet.getString("FANAM")); // 姓名
                    dt_hri011_indataitemsp0021.setFASEX(mxSet.getString("FASEX")); // 性别
                    dt_hri011_indataitemsp0021.setFGBDT(changeDays(mxSet.getString("FGBDT"))); // 出生日期

                    dt_hri011_indataitemsp0021.setTELNR(mxSet.getString("TELNR")); // 电话
                    dt_hri011_indataitemsp0021.setCITY1(mxSet.getString("CITY1")); // 单位名称
                    dt_hri011_indataitemsp0021.setDISTR(mxSet.getString("DISTR")); // 单位职位

                    dt_hri011_indataitemsp0021.setAdditional1("");
                    dt_hri011_indataitemsp0021.setAdditional2("");
                    dt_hri011_indataitemsp0021.setAdditional3("");
                    dt_hri011_indataitemsp0021.setAdditional4("");

                    dt_hri011_indataitemsp0021s[i] = dt_hri011_indataitemsp0021;
                    i++;
                }

                // 查询明细表 - 教育信息
                mxSet.executeQuery("select * from " + tableName + "_dt4 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP0022[] dt_hri011_indataitemsp0022s = new DT_HRI011_INDATAITEMSP0022[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "SLART");

                    DT_HRI011_INDATAITEMSP0022 dt_hri011_indataitemsp0022 = new DT_HRI011_INDATAITEMSP0022();
                    dt_hri011_indataitemsp0022.setBEGDA(changeDays(mxSet.getString("BEGDA")));
                    dt_hri011_indataitemsp0022.setENDDA(changeDays(usrties[2]));
                    dt_hri011_indataitemsp0022.setPERNR(workCode);
                    dt_hri011_indataitemsp0022.setSUBTY(usrties[3]); // 子信息类型(填教育类型)
                    dt_hri011_indataitemsp0022.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp0022.setOPTION(usrties[0]);
                    dt_hri011_indataitemsp0022.setSLART(mxSet.getString("SLART")); // 教育类型
                    dt_hri011_indataitemsp0022.setINSTI(mxSet.getString("INSTI")); // 毕业院校
                    dt_hri011_indataitemsp0022.setSLAND(mxSet.getString("SLAND")); // 国家
                    dt_hri011_indataitemsp0022.setANZKL(mxSet.getString("ANZKL")); // 学制(年)

                    dt_hri011_indataitemsp0022.setFACH3(mxSet.getString("FACH3")); // 所学专业
                    dt_hri011_indataitemsp0022.setACAQU(mxSet.getString("ACAQU")); // 学历
                    dt_hri011_indataitemsp0022.setACQID(mxSet.getString("ACQID")); // 学历证书编号
                    dt_hri011_indataitemsp0022.setACAQD(changeDays(mxSet.getString("ACAQD"))); // 学历授予日期
                    dt_hri011_indataitemsp0022.setACAQM(mxSet.getString("ACAQM")); // 最高学历标识

                    dt_hri011_indataitemsp0022.setSLABS(mxSet.getString("SLABS")); // 学位
                    dt_hri011_indataitemsp0022.setACCID(mxSet.getString("ACCID")); // 学位证书编号
                    dt_hri011_indataitemsp0022.setACACD(changeDays(mxSet.getString("ACACD"))); // 学位授予日期
                    dt_hri011_indataitemsp0022.setACACM(mxSet.getString("ACACM")); // 最高学位标识

                    dt_hri011_indataitemsp0022.setAdditional1("");
                    dt_hri011_indataitemsp0022.setAdditional2("");
                    dt_hri011_indataitemsp0022.setAdditional3("");
                    dt_hri011_indataitemsp0022.setAdditional4("");

                    dt_hri011_indataitemsp0022s[i] = dt_hri011_indataitemsp0022;
                    i++;
                }

                // 查询明细表 - 论著成果
                mxSet.executeQuery("select * from " + tableName + "_dt5 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP9001[] dt_hri011_indataitemsp9001s = new DT_HRI011_INDATAITEMSP9001[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "ZCGLX");

                    DT_HRI011_INDATAITEMSP9001 dt_hri011_indataitemsp9001 = new DT_HRI011_INDATAITEMSP9001();
                    dt_hri011_indataitemsp9001.setPERNR(workCode);
                    dt_hri011_indataitemsp9001.setBEGDA(changeDays(mxSet.getString("BEGDA")));
                    dt_hri011_indataitemsp9001.setENDDA(changeDays(usrties[2]));
                    dt_hri011_indataitemsp9001.setSUBTY(usrties[3]); // 子信息类型(填成果类型)
                    dt_hri011_indataitemsp9001.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp9001.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp9001.setZCGMC(mxSet.getString("ZCGMC")); // 成果名称
                    dt_hri011_indataitemsp9001.setZFBSJ(changeDays(mxSet.getString("ZFBSJ"))); // 发表或出版时间
                    dt_hri011_indataitemsp9001.setZFBQK(mxSet.getString("ZFBQK")); // 发表期刊或出版社
                    dt_hri011_indataitemsp9001.setZCGLX(mxSet.getString("ZCGLX")); // 成果类型

                    dt_hri011_indataitemsp9001.setAdditional1("");
                    dt_hri011_indataitemsp9001.setAdditional2("");
                    dt_hri011_indataitemsp9001.setAdditional3("");
                    dt_hri011_indataitemsp9001.setAdditional4("");

                    dt_hri011_indataitemsp9001s[i] = dt_hri011_indataitemsp9001;
                    i++;
                }

                // 查询明细表 - 资格证书
                mxSet.executeQuery("select * from " + tableName + "_dt6 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP9002[] dt_hri011_indataitemsp9002s = new DT_HRI011_INDATAITEMSP9002[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "ZZGDJ");

                    DT_HRI011_INDATAITEMSP9002 dt_hri011_indataitemsp9002 = new DT_HRI011_INDATAITEMSP9002();
                    dt_hri011_indataitemsp9002.setPERNR(workCode);
                    dt_hri011_indataitemsp9002.setBEGDA(changeDays(mxSet.getString("BEGDA")));
                    dt_hri011_indataitemsp9002.setENDDA(changeDays(usrties[2]));
                    dt_hri011_indataitemsp9002.setSUBTY(usrties[3]); // 子信息类型(填资格等级)
                    dt_hri011_indataitemsp9002.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp9002.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp9002.setZZGMC(mxSet.getString("ZZGMC")); // 资格名称
                    dt_hri011_indataitemsp9002.setZHDSJ(changeDays(mxSet.getString("ZHDSJ"))); // 获得时间
                    dt_hri011_indataitemsp9002.setZZSBH(mxSet.getString("ZZSBH")); // 证书编号
                    dt_hri011_indataitemsp9002.setZZGDJ(mxSet.getString("ZZGDJ")); // 资格等级

                    dt_hri011_indataitemsp9002.setZPDDW(mxSet.getString("ZPDDW")); // 授予/评定单位
                    dt_hri011_indataitemsp9002.setAdditional1("");
                    dt_hri011_indataitemsp9002.setAdditional2("");
                    dt_hri011_indataitemsp9002.setAdditional3("");
                    dt_hri011_indataitemsp9002.setAdditional4("");

                    dt_hri011_indataitemsp9002s[i] = dt_hri011_indataitemsp9002;
                    i++;
                }

                // 查询明细表 - 职称信息
                mxSet.executeQuery("select * from " + tableName + "_dt7 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP9003[] dt_hri011_indataitemsp9003s = new DT_HRI011_INDATAITEMSP9003[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "ZZCDJ");

                    DT_HRI011_INDATAITEMSP9003 dt_hri011_indataitemsp9003 = new DT_HRI011_INDATAITEMSP9003();
                    dt_hri011_indataitemsp9003.setPERNR(workCode);
                    dt_hri011_indataitemsp9003.setBEGDA(changeDays(mxSet.getString("BEGDA")));
                    dt_hri011_indataitemsp9003.setENDDA(changeDays(usrties[2]));
                    dt_hri011_indataitemsp9003.setSUBTY(usrties[3]); // 子信息类型(填职称等级)
                    dt_hri011_indataitemsp9003.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp9003.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp9003.setZZCMC(mxSet.getString("ZZCMC")); // 职称名称
                    dt_hri011_indataitemsp9003.setZSYSJ(changeDays(mxSet.getString("ZSYSJ"))); // 职称授予时间
                    dt_hri011_indataitemsp9003.setZZCZSH(mxSet.getString("ZZCZSH")); // 证书号
                    dt_hri011_indataitemsp9003.setZZCDJ(mxSet.getString("ZZCDJ")); // 职称等级

                    dt_hri011_indataitemsp9003.setZPDDW(mxSet.getString("ZPDDW")); // 评定单位
                    dt_hri011_indataitemsp9003.setAdditional1("");
                    dt_hri011_indataitemsp9003.setAdditional2("");
                    dt_hri011_indataitemsp9003.setAdditional3("");
                    dt_hri011_indataitemsp9003.setAdditional4("");

                    dt_hri011_indataitemsp9003s[i] = dt_hri011_indataitemsp9003;
                    i++;
                }

                // 查询明细表 - 荣誉信息
                mxSet.executeQuery("select * from " + tableName + "_dt8 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP9004[] dt_hri011_indataitemsp9004s = new DT_HRI011_INDATAITEMSP9004[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "ZRYLB");

                    DT_HRI011_INDATAITEMSP9004 dt_hri011_indataitemsp9004 = new DT_HRI011_INDATAITEMSP9004();
                    dt_hri011_indataitemsp9004.setPERNR(workCode);
                    dt_hri011_indataitemsp9004.setBEGDA(changeDays(mxSet.getString("BEGDA")));
                    dt_hri011_indataitemsp9004.setENDDA(changeDays(usrties[2]));
                    dt_hri011_indataitemsp9004.setSUBTY(usrties[3]); // 子信息类型(填荣誉类别)
                    dt_hri011_indataitemsp9004.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp9004.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp9004.setZRYLB(mxSet.getString("ZRYLB")); // 荣誉类别
                    dt_hri011_indataitemsp9004.setZRYMC(mxSet.getString("ZRYMC")); // 荣誉名称
                    dt_hri011_indataitemsp9004.setZHDSJ(changeDays(mxSet.getString("ZHDSJ"))); // 荣誉获得时间
                    dt_hri011_indataitemsp9004.setZSYDW(mxSet.getString("ZSYDW")); // 荣誉授予单位

                    dt_hri011_indataitemsp9004.setZSYJG(mxSet.getString("ZSYJG")); // 授予机构
                    dt_hri011_indataitemsp9004.setZSYSJ(changeDays(mxSet.getString("ZSYSJ"))); // 授予时间
                    dt_hri011_indataitemsp9004.setZPZWJ(mxSet.getString("ZPZWJ")); // 批准文件
                    dt_hri011_indataitemsp9004.setZBEIZ(mxSet.getString("ZBEIZ")); // 备注

                    dt_hri011_indataitemsp9004.setAdditional1("");
                    dt_hri011_indataitemsp9004.setAdditional2("");
                    dt_hri011_indataitemsp9004.setAdditional3("");
                    dt_hri011_indataitemsp9004.setAdditional4("");

                    dt_hri011_indataitemsp9004s[i] = dt_hri011_indataitemsp9004;
                    i++;
                }

                // 查询明细表 - 紧急联系人
                mxSet.executeQuery("select * from " + tableName + "_dt9 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP9007[] dt_hri011_indataitemsp9007s = new DT_HRI011_INDATAITEMSP9007[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "ZYBRGX");

                    DT_HRI011_INDATAITEMSP9007 dt_hri011_indataitemsp9007 = new DT_HRI011_INDATAITEMSP9007();
                    dt_hri011_indataitemsp9007.setPERNR(workCode);
                    dt_hri011_indataitemsp9007.setBEGDA(changeDays(mxSet.getString("BEGDA")));
                    dt_hri011_indataitemsp9007.setENDDA(changeDays(usrties[2]));
                    dt_hri011_indataitemsp9007.setSUBTY(usrties[3]); // 子信息类型(填与本人关系)
                    dt_hri011_indataitemsp9007.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp9007.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp9007.setZJJLXRXM(mxSet.getString("ZJJLXRXM")); // 紧急联系人姓名
                    dt_hri011_indataitemsp9007.setZJJLXRDH(mxSet.getString("ZJJLXRDH")); // 紧急联系人电话
                    dt_hri011_indataitemsp9007.setZYBRGX(mxSet.getString("ZYBRGX")); // 与本人关系

                    dt_hri011_indataitemsp9007.setAdditional1("");
                    dt_hri011_indataitemsp9007.setAdditional2("");
                    dt_hri011_indataitemsp9007.setAdditional3("");
                    dt_hri011_indataitemsp9007.setAdditional4("");

                    dt_hri011_indataitemsp9007s[i] = dt_hri011_indataitemsp9007;
                    i++;
                }

                // 查询明细表 - 培训信息
                mxSet.executeQuery("select * from " + tableName + "_dt10 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP9008[] dtHri011Indataitemsp9008s = new DT_HRI011_INDATAITEMSP9008[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "ZPXLX");

                    DT_HRI011_INDATAITEMSP9008 dt_hri011_indataitemsp9008 = new DT_HRI011_INDATAITEMSP9008();
                    dt_hri011_indataitemsp9008.setPERNR(workCode);
                    dt_hri011_indataitemsp9008.setBEGDA(changeDays(mxSet.getString("BEGDA")));
                    dt_hri011_indataitemsp9008.setENDDA(changeDays(usrties[2]));
                    dt_hri011_indataitemsp9008.setSUBTY(usrties[3]); // 子信息类型(填培训类型)
                    dt_hri011_indataitemsp9008.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp9008.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp9008.setZPXLX(mxSet.getString("ZPXLX")); // 培训类型
                    dt_hri011_indataitemsp9008.setZPXKC(mxSet.getString("ZPXKC")); // 培训课程
                    dt_hri011_indataitemsp9008.setZPXJG(mxSet.getString("ZPXJG")); // 培训机构
                    dt_hri011_indataitemsp9008.setZPXNR(mxSet.getString("ZPXNR")); // 培训内容

                    dt_hri011_indataitemsp9008.setAdditional1("");
                    dt_hri011_indataitemsp9008.setAdditional2("");
                    dt_hri011_indataitemsp9008.setAdditional3("");
                    dt_hri011_indataitemsp9008.setAdditional4("");

                    dtHri011Indataitemsp9008s[i] = dt_hri011_indataitemsp9008;
                    i++;
                }

                // 查询明细表 - 地址信息
                mxSet.executeQuery("select * from " + tableName + "_dt11 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP9011[] dt_hri011_indataitemsp9011s = new DT_HRI011_INDATAITEMSP9011[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "ZDZLX");

                    DT_HRI011_INDATAITEMSP9011 dt_hri011_indataitemsp9011 = new DT_HRI011_INDATAITEMSP9011();
                    dt_hri011_indataitemsp9011.setPERNR(workCode);
                    dt_hri011_indataitemsp9011.setBEGDA(changeDays(mxSet.getString("BEGDA")));
                    dt_hri011_indataitemsp9011.setENDDA(changeDays(usrties[2]));
                    dt_hri011_indataitemsp9011.setSUBTY(usrties[3]); // 子信息类型(填地址类型)
                    dt_hri011_indataitemsp9011.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp9011.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp9011.setZDZLX(mxSet.getString("ZDZLX")); // 地址类型
                    dt_hri011_indataitemsp9011.setLAND1(mxSet.getString("LAND1")); //国家代码
                    dt_hri011_indataitemsp9011.setZSHENG(mxSet.getString("ZSHENG")); // 省份
                    dt_hri011_indataitemsp9011.setZSHI(mxSet.getString("ZSHI")); // 城市/地区

                    dt_hri011_indataitemsp9011.setZXIAN(mxSet.getString("ZXIAN")); // 县
                    dt_hri011_indataitemsp9011.setZSTRAS(mxSet.getString("ZSTRAS")); // 详细地址
                    dt_hri011_indataitemsp9011.setZPSTLZ(mxSet.getString("PSTLZ")); // 邮政编码

                    dt_hri011_indataitemsp9011.setAdditional1("");
                    dt_hri011_indataitemsp9011.setAdditional2("");
                    dt_hri011_indataitemsp9011.setAdditional3("");
                    dt_hri011_indataitemsp9011.setAdditional4("");

                    dt_hri011_indataitemsp9011s[i] = dt_hri011_indataitemsp9011;
                    i++;
                }

                // 查询明细表 - 语言能力
                mxSet.executeQuery("select * from " + tableName + "_dt12 where mainid = " + mainId);
                DT_HRI011_INDATAITEMSP9012[] dt_hri011_indataitemsp9012s = new DT_HRI011_INDATAITEMSP9012[mxSet.getCounts()];
                i = 0;
                while (mxSet.next()) {
                    String[] usrties = fuZhi(mxSet, "ZYUZH");

                    DT_HRI011_INDATAITEMSP9012 dt_hri011_indataitemsp9012 = new DT_HRI011_INDATAITEMSP9012();
                    dt_hri011_indataitemsp9012.setPERNR(workCode);
                    dt_hri011_indataitemsp9012.setBEGDA(changeDays(mxSet.getString("BEGDA")));
                    dt_hri011_indataitemsp9012.setENDDA(changeDays(usrties[2]));
                    dt_hri011_indataitemsp9012.setSUBTY(usrties[3]); // 子信息类型(填语种)
                    dt_hri011_indataitemsp9012.setSEQNR(usrties[1]); // 有相同代码的信息类型记录数

                    dt_hri011_indataitemsp9012.setOPTION(usrties[0]); // INS/MOD
                    dt_hri011_indataitemsp9012.setZYUZH(mxSet.getString("ZYUZH")); // 语种
                    dt_hri011_indataitemsp9012.setZSLCD(mxSet.getString("ZSLCD")); // 语种熟练程度
                    dt_hri011_indataitemsp9012.setZZSMC(mxSet.getString("ZZSMC")); // 证书名称
                    dt_hri011_indataitemsp9012.setZZSBH(mxSet.getString("ZZSBH")); // 证书编号

                    dt_hri011_indataitemsp9012.setZHZRQ(changeDays(mxSet.getString("ZHZRQ"))); // 获证日期
                    dt_hri011_indataitemsp9012.setAdditional1("");
                    dt_hri011_indataitemsp9012.setAdditional2("");
                    dt_hri011_indataitemsp9012.setAdditional3("");
                    dt_hri011_indataitemsp9012.setAdditional4("");

                    dt_hri011_indataitemsp9012s[i] = dt_hri011_indataitemsp9012;
                    i++;
                }

                // 数组信息对象
                DT_HRI011_INDATAITEMS dt_hri011_indataitems = new DT_HRI011_INDATAITEMS();
                dt_hri011_indataitems.setP0002(dt_hri011_indataitemsp0002s);
                dt_hri011_indataitems.setP0015(dt_hri011_indataitemsp0015s);
                dt_hri011_indataitems.setP0021(dt_hri011_indataitemsp0021s);
                dt_hri011_indataitems.setP0022(dt_hri011_indataitemsp0022s);
                dt_hri011_indataitems.setP9001(dt_hri011_indataitemsp9001s);

                dt_hri011_indataitems.setP9002(dt_hri011_indataitemsp9002s);
                dt_hri011_indataitems.setP9003(dt_hri011_indataitemsp9003s);
                dt_hri011_indataitems.setP9004(dt_hri011_indataitemsp9004s);
                dt_hri011_indataitems.setP9007(dt_hri011_indataitemsp9007s);
                dt_hri011_indataitems.setP9008(dtHri011Indataitemsp9008s);

                dt_hri011_indataitems.setP9011(dt_hri011_indataitemsp9011s);
                dt_hri011_indataitems.setP9012(dt_hri011_indataitemsp9012s);

                DT_HRI011_INDATA data = new DT_HRI011_INDATA();
                data.setITEMS(dt_hri011_indataitems);

                // 固定对象信息
                DT_HRI011_INSENDER dt_hri011_insender = new DT_HRI011_INSENDER();
                dt_hri011_insender.setINTF_ID("");
                dt_hri011_insender.setSrc_System("OA");
                dt_hri011_insender.setDest_System("");
                dt_hri011_insender.setCompany_Code("");
                dt_hri011_insender.setSend_Time("");

                // 拼接最外层对象
                DT_HRI011_IN dt_hri011_in = new DT_HRI011_IN();
                dt_hri011_in.setDATA(data);
                dt_hri011_in.setSENDER(dt_hri011_insender);

                String sendJson = gson.toJson(dt_hri011_in);
                this.writeLog("发送json： " + sendJson);

                // 调用接口
                DT_HRI011_OUTRETURN[] returns = BaseInfoUtil.execute(dt_hri011_in);

                this.writeLog("总部新入职人员信息录入返回信息： " + gson.toJson(returns));

                StringBuilder builder = new StringBuilder();
                StringBuilder logBuilder = new StringBuilder();
                for (DT_HRI011_OUTRETURN en : returns) {
                    if ("E".equals(en.getMSG_TYP())) {
                        builder.append(en.getMSG_TYP()).append(": ").append(en.getMESSAGE()).append("</br>");
                    }
                    logBuilder.append("sap返回信息： ").append(en.getMSG_TYP()).append(": ").append(en.getMESSAGE()).append(";");
                    this.writeLog("sap返回信息： " + en.getMSG_TYP() + ": " + en.getMESSAGE());
                }

                // 返回标记
                String flag = "S";
                if (builder.length() > 0) {
                    flag = "E";
                }
                // 将返回信息插入日志
                ConnUtil.insertLogCoon(logBuilder.toString(), lcbh, sendJson, flag);

                if (builder.length() > 0) {
                    this.writeLog("流程终止， builder: " + builder.toString());
                    requestInfo.getRequestManager().setMessageid("110000");
                    requestInfo.getRequestManager().setMessagecontent("sap返回消息：--- " + gson.toJson(returns));
                    return "0";
                }
            }
            this.writeLog("总部新入职人员信息录入 END： " + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            this.writeLog("总部新入职人员信息录入 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。");
            return "0";
        }
        return "1";
    }

    /**
     * @param mxSet 明细表当前RecordSet
     * @param type  excel中对应字段名，每个明细表不一样
     * @return
     */
    private String[] fuZhi(RecordSet mxSet, String type) {

        String czlx = mxSet.getString("czlx");// 操作类型

        String option;  // 操作类型 INS/MOD
        String seqnr = "";
        String endda; // 结束日期
        String subty = "";
        if ("0".equals(czlx)) {
            option = "INS";
            seqnr = "";
            endda = "99991231";
            if ("0".equals(mxSet.getString("sfxy"))) {
                subty = mxSet.getString(type);
            }
        } else {
            option = "MOD";
            if ("0".equals(mxSet.getString("sfjl"))) {
                seqnr = mxSet.getString("seqnr");
            }
            endda = mxSet.getString("ENDDA");

            if ("0".equals(mxSet.getString("sfxy"))) {
                subty = mxSet.getString("SUBTY");
            }
        }
        return new String[]{option, seqnr, endda, subty};
    }


    /**
     * 日期去掉横杠
     */
    private String changeDays(String myDays) {
        if (myDays != null) {
            myDays = myDays.replace("-", "");
        } else {
            myDays = "00000000";
        }
        return myDays;
    }
}


















