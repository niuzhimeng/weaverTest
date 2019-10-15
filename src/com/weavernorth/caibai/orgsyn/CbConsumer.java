package com.weavernorth.caibai.orgsyn;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.weavernorth.caibai.orgsyn.vo.CbHrmResource;
import com.weavernorth.caibai.sap.CaiBaiPoolThree;
import com.weavernorth.caibai.util.CbConnUtil;
import com.weavernorth.caibai.util.CbUtils;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.schedule.BaseCronJob;

import java.util.*;

/**
 * 菜百组织架构同步
 *
 * @author 29529
 */
public class CbConsumer extends BaseCronJob {

    private BaseBean baseBean = new BaseBean();

    @Override
    public void execute() {
        baseBean.writeLog("菜百架构同步 Start " + TimeUtil.getCurrentTimeString());
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select kqtb from uf_timed_switch");
        String kqtb = "1";
        if (recordSet.next()) {
            kqtb = recordSet.getString("kqtb");
        }
        if ("0".equalsIgnoreCase(kqtb)) {
            synHrmResource();
        } else {
            // 插入日志
            CbConnUtil.insertTimedLog("hrmresource", "开关为关闭状态，此次不进行同步。", 0, "0", "");
        }

        baseBean.writeLog("菜百架构同步 End " + TimeUtil.getCurrentTimeString());
    }

    /**
     * 人员同步
     */
    private void synHrmResource() {
        baseBean.writeLog("人员同步 Start ========================= " + TimeUtil.getCurrentTimeString());
        // 开始时间戳
        long start = System.currentTimeMillis();
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeQuery("select id, workcode from hrmresource");
            // 人员编码 - id map
            Map<String, String> codeIdMap = new HashMap<String, String>(recordSet.getCounts() + 50);
            while (recordSet.next()) {
                if (!"".equalsIgnoreCase(recordSet.getString("workcode").trim())) {
                    codeIdMap.put(recordSet.getString("workcode"), recordSet.getString("id"));
                }
            }
            baseBean.writeLog("当前人数： " + codeIdMap.size());

            // 部门编码 - id map
            recordSet.executeQuery("select id, departmentcode from hrmdepartment");
            Map<String, String> depIdMap = new HashMap<String, String>(recordSet.getCounts() + 50);
            while (recordSet.next()) {
                if (!"".equalsIgnoreCase(recordSet.getString("departmentcode").trim())) {
                    depIdMap.put(recordSet.getString("departmentcode"), recordSet.getString("id"));
                }
            }

            // 分部编码 - id map
            recordSet.executeQuery("select SUBCOMPANYCODE, ID from HRMSUBCOMPANY");
            Map<String, String> subIdMap = new HashMap<String, String>(recordSet.getCounts() + 10);
            while (recordSet.next()) {
                if (!"".equalsIgnoreCase(recordSet.getString("SUBCOMPANYCODE").trim())) {
                    subIdMap.put(recordSet.getString("SUBCOMPANYCODE"), recordSet.getString("ID"));
                }
            }

            // 岗位id 集合
            recordSet.executeQuery("select id, jobtitlecode from hrmjobtitles");
            List<String> jobIdList = new ArrayList<String>(recordSet.getCounts() + 10);
            while (recordSet.next()) {
                jobIdList.add(recordSet.getString("id"));
            }

            // 人员状态集合
            String[] statusAll = {"0", "1", "2", "3", "4", "5", "6", "7"};
            List<String> statusList = new ArrayList<String>();
            Collections.addAll(statusList, statusAll);

            JCoDestination jCoDestination = CaiBaiPoolThree.getJCoDestination();
            JCoFunction function = jCoDestination.getRepository().getFunction("ZOAIF0020_RFC");
            baseBean.writeLog("获取函数完成===== " + function);
            // 调用sap人员接口
            function.execute(jCoDestination);

            JCoTable otReturn = function.getTableParameterList().getTable("IT_TAB");
            int numRows = otReturn.getNumRows();
            baseBean.writeLog("返回表行数： " + numRows);

            List<CbHrmResource> insertHrmResourceList = new ArrayList<CbHrmResource>();
            List<CbHrmResource> updateHrmResourceList = new ArrayList<CbHrmResource>();
            List<CbHrmResource> errHrmResourceList = new ArrayList<CbHrmResource>();
            for (int i = 0; i < numRows; i++) {
                otReturn.setRow(i);
                // 人员编码
                String workCode = Util.null2String(otReturn.getString("WORKCODE")).trim();
                // 姓名
                String lastName = Util.null2String(otReturn.getString("LASTNAME")).trim();
                // 是否有系统登录账号
                String sfdlzh = Util.null2String(otReturn.getString("SFDLZH")).trim();
                // 登录名
                String loginId = "";
                if ("X".equalsIgnoreCase(sfdlzh)) {
                    loginId = workCode;
                } else if ("N".equalsIgnoreCase(sfdlzh)) {
                    continue;
                }

                // 员工状态
                String sapStatus = Util.null2String(otReturn.getString("STATUS")).trim();

                // 性别
                String sapSex = Util.null2String(otReturn.getString("SEX")).trim();
                String sex = "0";
                if ("2".equalsIgnoreCase(sapSex)) {
                    sex = "1";
                }
                // 工作地点
                String sapLocation = Util.null2String(otReturn.getString("LOCATION")).trim();
                if ("".equalsIgnoreCase(sapLocation)) {
                    sapLocation = "北京";
                }
                String location = CbUtils.insertLocation(sapLocation);
                // 邮箱
                String email = Util.null2String(otReturn.getString("EMAIL")).trim();
                // 手机
                String mobile = Util.null2String(otReturn.getString("PHONE")).trim();
                // 直接上级编码
                String managerCode = Util.null2String(otReturn.getString("MANAGERID")).trim();
                // 安全级别
                String seclevel = Util.null2String(otReturn.getString("SECLEVEL")).trim();
                // sap所属部门编码
                String sapDepCode = Util.null2String(otReturn.getString("DEPCODE")).trim();
                // 所属分部编码
                String subCode = Util.null2String(otReturn.getString("CODE")).trim();
                // 岗位id(sap直接传oa岗位表的id)
                String sapJobTitleId = Util.null2String(otReturn.getString("JOBTITLECODE")).trim();
                // 生日
                String birthday = Util.null2String(otReturn.getString("BIRTHDAY")).trim();
                // 身份证号码
                String certificatenum = Util.null2String(otReturn.getString("IDNUMBER")).trim();

                baseBean.writeLog("人员code: " + workCode + ", 人员名称: " + lastName);
                baseBean.writeLog("人员上级code: " + managerCode);
                baseBean.writeLog("sap所属部门编码: " + sapDepCode);
                baseBean.writeLog("sap所属岗位id: " + sapJobTitleId);
                baseBean.writeLog("sapStatus: " + sapStatus);
                baseBean.writeLog("sap工作地点: " + sapLocation + ", oa工作地点: " + location);
                baseBean.writeLog("---------------------------");

                CbHrmResource hrmResource = new CbHrmResource();
                hrmResource.setWorkcode(workCode);
                hrmResource.setLastname(lastName);
                hrmResource.setLoginid(loginId);
                hrmResource.setPassWord(Util.getEncrypt("123456"));
                hrmResource.setStatus(sapStatus);

                hrmResource.setSex(sex);
                hrmResource.setLocation(location);
                hrmResource.setEmail(email);
                hrmResource.setMobile(mobile);
                hrmResource.setManagerCode(managerCode);

                hrmResource.setDepcode(sapDepCode);
                hrmResource.setSubcode(subCode);
                hrmResource.setJobtitleId(sapJobTitleId);
                hrmResource.setBirthday(birthday);
                hrmResource.setCertificatenum(certificatenum);

                hrmResource.setSeclevel(seclevel);

                if ("00000000".equalsIgnoreCase(workCode)) {
                    // 工号为空
                    hrmResource.setErrMessage("人员【工号】为空, 部门code: " + sapDepCode + " ,人员编码: " + workCode + ", 姓名: " + hrmResource.getLastname());
                    errHrmResourceList.add(hrmResource);
                    continue;
                }

                if ("".equalsIgnoreCase(lastName)) {
                    // 姓名为空
                    hrmResource.setErrMessage("人员【姓名】为空, 部门code: " + sapDepCode + " ,人员编码: " + workCode + ", 姓名: " + hrmResource.getLastname());
                    errHrmResourceList.add(hrmResource);
                    continue;
                }

                if (!statusList.contains(sapStatus)) {
                    hrmResource.setErrMessage("人员【状态】填写有误, 部门code: " + sapDepCode + " ,人员编码: " + workCode + ", 姓名: " + hrmResource.getLastname());
                    errHrmResourceList.add(hrmResource);
                    continue;
                }
                // 部门ID
                int depId = Util.getIntValue(depIdMap.get(sapDepCode), 0);
                baseBean.writeLog("depId: " + depId);
                if (depId <= 0 && !"5".equals(sapStatus) && !"6".equals(sapStatus)) {
                    //所属部门不存在
                    hrmResource.setErrMessage("人员【部门】不存在, 部门编码: " + sapDepCode + " ,人员编码： " + workCode + ", 姓名: " + lastName);
                    errHrmResourceList.add(hrmResource);
                    continue;
                }

                //岗位id
                if (!jobIdList.contains(sapJobTitleId)) {
                    //所属岗位不存在
                    hrmResource.setErrMessage("人员【岗位】不存在, 岗位id: " + sapJobTitleId + " ,人员编码: " + workCode + ", 姓名: " + lastName);
                    errHrmResourceList.add(hrmResource);
                    continue;
                }

                // 手机号为空
                if ("".equalsIgnoreCase(mobile)) {
                    hrmResource.setErrMessage("人员【手机号码】为空, 岗位id: " + sapJobTitleId + " ,人员编码: " + workCode + ", 姓名: " + lastName);
                    errHrmResourceList.add(hrmResource);
                }

                // 直接上级为空
                if ("00000000".equalsIgnoreCase(managerCode)) {
                    hrmResource.setErrMessage("人员【直接上级】为空, 岗位id: " + sapJobTitleId + " ,人员编码: " + workCode + ", 姓名: " + lastName);
                    errHrmResourceList.add(hrmResource);
                }

                // 安全级别空
                if ("".equalsIgnoreCase(seclevel)) {
                    hrmResource.setErrMessage("人员【安全级别】为空, 岗位id: " + sapJobTitleId + " ,人员编码: " + workCode + ", 姓名: " + lastName);
                    errHrmResourceList.add(hrmResource);
                }

                // 分部不存在
                if (subIdMap.get(subCode) == null) {
                    hrmResource.setErrMessage("人员【分部】不存在, 分部编码: " + subCode + " ,人员编码: " + workCode + ", 姓名: " + lastName);
                    errHrmResourceList.add(hrmResource);
                    continue;
                } else {
                    hrmResource.setSubId(subIdMap.get(subCode));
                }

                //直接上级id
                int managerIdReal = Util.getIntValue(codeIdMap.get(managerCode), 0);
                if (managerIdReal <= 0) {
                    hrmResource.setErrMessage("人员【直接上级】不存在, 直接上级编码: " + managerCode + " ,人员编码: " + workCode + ", 姓名: " + lastName);
                    errHrmResourceList.add(hrmResource);
                }

                //直接上级
                hrmResource.setManagerIdReal(String.valueOf(managerIdReal));
                // 所有上级
                String managerIdAndStr = hrmResource.getManagerIdAndStr(String.valueOf(managerIdReal));
                hrmResource.setManagerstr(managerIdAndStr);
                hrmResource.setDepId(String.valueOf(depId));


                //账号类型
                hrmResource.setAccounttype("0");
                //语言类型
                hrmResource.setSystemlanguage("7");

                baseBean.writeLog("codeIdMap.get(workCode): " + codeIdMap.get(workCode));
                if (codeIdMap.get(workCode) == null) {
                    baseBean.writeLog("新增==========");
                    String newId = String.valueOf(CbUtils.getHrmMaxid());
                    hrmResource.setId(newId);
                    insertHrmResourceList.add(hrmResource);
                    codeIdMap.put(workCode, "");
                } else {
                    baseBean.writeLog("更新==========");
                    updateHrmResourceList.add(hrmResource);
                }
            }

            // 插入人员
            baseBean.writeLog("插入人员数量： " + insertHrmResourceList.size());
            CbConnUtil.insertHrmResource(insertHrmResourceList);
            // 更新人员
            baseBean.writeLog("更新人员数量： " + updateHrmResourceList.size());
            CbConnUtil.updateHrmResource(updateHrmResourceList);

            //清除缓存
            new ResourceComInfo().removeResourceCache();

            // 此次同步唯一标识
            String myUid = UUID.randomUUID().toString().replace("-", "");

            // 插入错误日志
            int errSize = errHrmResourceList.size();
            baseBean.writeLog("错误条数：" + errSize);

            // 同步状态
            String tbzt = "0";
            if (errSize > 0) {
                tbzt = "1";
                CbConnUtil.insertErrorLog("hrmreource", errHrmResourceList, myUid);
            }

            // 结束时间戳
            long end = System.currentTimeMillis();
            long cha = (end - start) / 1000;
            String logStr = "人员信息同步完成，此次新增人员： " + insertHrmResourceList.size() + " 更新人员: "
                    + updateHrmResourceList.size() + ", 错误条数：" + errHrmResourceList.size() + " ,耗时：" + cha + " 秒。";

            // 插入日志
            CbConnUtil.insertTimedLog("hrmresource", logStr, numRows, tbzt, myUid);
        } catch (Exception e) {
            baseBean.writeLog("人员同步异常： " + e);
        }
        baseBean.writeLog("人员同步 End ========================= " + TimeUtil.getCurrentTimeString());
    }


}
