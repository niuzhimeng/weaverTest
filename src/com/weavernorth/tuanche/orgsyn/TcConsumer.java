package com.weavernorth.tuanche.orgsyn;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weavernorth.tuanche.orgsyn.vo.TcHrmDepartment;
import com.weavernorth.tuanche.orgsyn.vo.TcHrmJobTitles;
import com.weavernorth.tuanche.orgsyn.vo.TcHrmResource;
import com.weavernorth.tuanche.util.TcConnUtil;
import com.weavernorth.tuanche.util.TcOkHttpUtils;
import com.weavernorth.tuanche.util.TcUtils;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.job.JobTitlesComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.schedule.BaseCronJob;

import java.util.ArrayList;
import java.util.List;

/**
 * 团车组织架构同步
 *
 * @author 29529
 */
public class TcConsumer extends BaseCronJob {
    /**
     * 分部id
     */
    private static final String SUB_COMPANY_ID = "2";
    /**
     * 分部编码
     */
    private static final String SUB_COMPANY_CODE = "TC00001";
    /**
     * 职务id
     */
    private static final String DUTY_ID = "15";

    private BaseBean baseBean = new BaseBean();
    private Gson gson = new Gson();
    /**
     * HR 部门 调用地址
     */
    private static final String ORG_HR_URL = "http://t.beisendata.tuanche.net/organization/getOrganizationResultList";
    /**
     * HR 部门 调用地址
     */
    private static final String JOB_HR_URL = "http://t.beisendata.tuanche.net/jobPosition/getJobPositionResultList";
    /**
     * HR 人员 调用地址
     */
    private static final String PER_HR_URL = "http://t.beisendata.tuanche.net/employee/getEmployeeResultList";


    @Override
    public void execute() {
        baseBean.writeLog("团车架构同步 Start " + TimeUtil.getCurrentTimeString());
        // 部门
        synHrmDepartment();
        // 岗位
        hrmJobTitles();
        // 人员
        synHrmResource();
        baseBean.writeLog("团车架构同步 End " + TimeUtil.getCurrentTimeString());
    }

    /**
     * 部门同步
     */
    public void synHrmDepartment() {
        baseBean.writeLog("部门同步 Start ========================= " + TimeUtil.getCurrentTimeString());
        try {
            // 开始时间戳
            long start = System.currentTimeMillis();
            String returns = TcOkHttpUtils.get(ORG_HR_URL);

            baseBean.writeLog("部门返回json： " + returns);
            List<TcHrmDepartment> hrmDepartments = gson.fromJson(returns, new TypeToken<List<TcHrmDepartment>>() {
            }.getType());

            // 同步总数
            int stnCount = hrmDepartments.size();
            baseBean.writeLog("部门总数： " + stnCount);
            List<TcHrmDepartment> insertHrmDepartments = new ArrayList<TcHrmDepartment>();
            List<TcHrmDepartment> updateHrmDepartments = new ArrayList<TcHrmDepartment>();
            for (TcHrmDepartment hrmDepartment : hrmDepartments) {
                //部门编码
                String subCode = Util.null2String(hrmDepartment.getDepcode()).trim();
                //父编码
                String fatherCode = Util.null2String(hrmDepartment.getSupdepcode()).trim();

                //部门ID
                int depId = Util.getIntValue(TcUtils.getIdByCode("hrmdepartment", subCode), 0);
                //上级部门ID
                int supDepId = Util.getIntValue(TcUtils.getIdByCode("hrmdepartment", fatherCode), 0);

                if (SUB_COMPANY_CODE.equalsIgnoreCase(fatherCode)) {
                    // 上级是分部
                    hrmDepartment.setSupsubid(SUB_COMPANY_ID);
                    //上级部门设为0
                    hrmDepartment.setSupdepid("0");
                } else {
                    // 上级是部门
                    hrmDepartment.setSupsubid(SUB_COMPANY_ID);
                    hrmDepartment.setSupdepid(String.valueOf(supDepId));
                }

                baseBean.writeLog("---------------------------");
                baseBean.writeLog("部门id: " + depId);
                baseBean.writeLog("部门名称： " + hrmDepartment.getDepname());
                baseBean.writeLog("部门编码： " + subCode);
                baseBean.writeLog("上级部门id: " + supDepId);
                baseBean.writeLog("父编码: " + fatherCode);

                hrmDepartment.setId(String.valueOf(depId));

                if (depId <= 0) {
                    insertHrmDepartments.add(hrmDepartment);
                } else {
                    updateHrmDepartments.add(hrmDepartment);
                }
            }

            // 新增部门
            TcConnUtil.insertHrmDepartment(insertHrmDepartments);
            // 更新部门
            TcConnUtil.updateHrmDepartment(updateHrmDepartments);

            new DepartmentComInfo().removeCompanyCache();
            // 结束时间戳
            long end = System.currentTimeMillis();
            long cha = (end - start) / 1000;

            String logStr = "部门信息同步完成，新增部门： " + insertHrmDepartments.size() + ", 更新部门: " + updateHrmDepartments.size()
                    + " ，耗时：" + cha + " 秒。";
            // 插入日志
            TcConnUtil.insertTimedLog("HrmDepartment", logStr, stnCount);
        } catch (Exception e) {
            baseBean.writeLog("部门同步 异常： " + e);
        }
        baseBean.writeLog("部门同步 End ========================= " + TimeUtil.getCurrentTimeString());
    }

    /**
     * 岗位同步
     */
    public void hrmJobTitles() {
        baseBean.writeLog("岗位同步 Start ========================= " + TimeUtil.getCurrentTimeString());
        // 开始时间戳
        long start = System.currentTimeMillis();
        String returns = TcOkHttpUtils.get(JOB_HR_URL);
        baseBean.writeLog("岗位返回json： " + returns);
        List<TcHrmJobTitles> hrmJobTitlesList = gson.fromJson(returns, new TypeToken<List<TcHrmJobTitles>>() {
        }.getType());

        // 同步总数
        int stnCount = hrmJobTitlesList.size();
        baseBean.writeLog("岗位总数： " + stnCount);
        List<TcHrmJobTitles> insertHrmJobTitlesList = new ArrayList<TcHrmJobTitles>();
        List<TcHrmJobTitles> updateHrmJobTitlesList = new ArrayList<TcHrmJobTitles>();
        for (TcHrmJobTitles hrmJobTitles : hrmJobTitlesList) {
            //岗位编码
            String jobTitleCode = Util.null2String(hrmJobTitles.getJobtitlecode()).trim();
            //岗位id
            int jobTitleId = Util.getIntValue(TcUtils.getIdByCode("hrmjobtitles", jobTitleCode), 0);
            hrmJobTitles.setId(String.valueOf(jobTitleId));
            hrmJobTitles.setJobactivityid(DUTY_ID);
            if (jobTitleId <= 0) {
                insertHrmJobTitlesList.add(hrmJobTitles);
            } else {
                updateHrmJobTitlesList.add(hrmJobTitles);
            }
        }

        // 插入岗位
        TcConnUtil.insertJobTitle(insertHrmJobTitlesList);
        // 更新岗位
        TcConnUtil.updateJobTitle(updateHrmJobTitlesList);

        new JobTitlesComInfo().removeJobTitlesCache();
        // 结束时间戳
        long end = System.currentTimeMillis();
        long cha = (end - start) / 1000;

        String logStr = "岗位信息同步完成，新增岗位： " + insertHrmJobTitlesList.size() + " 更新岗位："
                + updateHrmJobTitlesList.size() + " 耗时：" + cha + " 秒。";
        // 插入日志
        TcConnUtil.insertTimedLog("HrmJobTitles", logStr, stnCount);
        baseBean.writeLog("岗位同步 End ========================= " + TimeUtil.getCurrentTimeString());
    }

    /**
     * 人员同步
     */
    public void synHrmResource() {
        baseBean.writeLog("人员同步 Start ========================= " + TimeUtil.getCurrentTimeString());
        // 开始时间戳
        long start = System.currentTimeMillis();
        try {
            String returns = TcOkHttpUtils.get(PER_HR_URL);
            List<TcHrmResource> hrmResourceList = gson.fromJson(returns, new TypeToken<List<TcHrmResource>>() {
            }.getType());
            // 同步总数
            int stnCount = hrmResourceList.size();
            baseBean.writeLog("人员同步总数： " + stnCount);

            List<TcHrmResource> insertHrmResourceList = new ArrayList<TcHrmResource>();
            List<TcHrmResource> updateHrmResourceList = new ArrayList<TcHrmResource>();
            List<TcHrmResource> errHrmResourceList = new ArrayList<TcHrmResource>();
            for (TcHrmResource hrmResource : hrmResourceList) {
                //人员编码
                String workCode = Util.null2String(hrmResource.getWorkcode()).trim();
                //人员id
                int id = Util.getIntValue(TcUtils.getIdByCode("hrmresource", workCode), 0);
                //所属部门编码
                String depCode = Util.null2String(hrmResource.getDepcode()).trim();
                //岗位编码
                String jobTitleCode = Util.null2String(hrmResource.getJobtitlecode()).trim();
                // 直接上级编码
                String managerCode = Util.null2String(hrmResource.getManagerCode()).trim();

                //部门ID
                int depId = Util.getIntValue(TcUtils.getIdByCode("hrmdepartment", depCode), 0);

                if ("".equalsIgnoreCase(workCode)) {
                    // 工号为空
                    errHrmResourceList.add(hrmResource);
                    continue;
                }
                if (depId <= 0) {
                    //所属部门不存在
                    baseBean.writeLog("人员所属部门不存在，部门code： " + depCode + " ,人员编码： " + workCode + ", 姓名： " + hrmResource.getLastname());
                    errHrmResourceList.add(hrmResource);
                    continue;
                }

                //岗位id
                int jobTitleId = Util.getIntValue(TcUtils.getIdByCode("hrmjobtitles", jobTitleCode));
                if (jobTitleId <= 0) {
                    //所属岗位不存在
                    baseBean.writeLog("人员所属岗位不存在，岗位code： " + jobTitleCode + " ,人员编码： " + workCode + ", 姓名： " + hrmResource.getLastname());
                    errHrmResourceList.add(hrmResource);
                    continue;
                }

                //直接上级id
                int managerIdReal = Util.getIntValue(TcUtils.getIdByCode("hrmresource", managerCode), 0);

                //工作地点转换
                String location = TcUtils.insertLocation(hrmResource.getLocation());
                if ("".equalsIgnoreCase(location)) {
                    hrmResource.setLocation("1");
                } else {
                    hrmResource.setLocation(location);
                }

                //默认密码
                hrmResource.setPassWord(Util.getEncrypt("123456"));
                //直接上级
                hrmResource.setManagerIdReal(String.valueOf(managerIdReal));

                // 所有上级
                String managerIdAndStr = hrmResource.getManagerIdAndStr(String.valueOf(managerIdReal));

                hrmResource.setManagerstr(managerIdAndStr);

                hrmResource.setId(String.valueOf(id));
                hrmResource.setDepId(String.valueOf(depId));
                hrmResource.setSubId(SUB_COMPANY_ID);
                hrmResource.setJobtitleId(String.valueOf(jobTitleId));

                //账号类型
                hrmResource.setAccounttype("0");
                //语言类型
                hrmResource.setSystemlanguage("7");
                // 安全级别默认10
                hrmResource.setSeclevel("10");

                if (id <= 0) {
                    String newId = String.valueOf(TcUtils.getHrmMaxid());
                    hrmResource.setId(newId);
                    insertHrmResourceList.add(hrmResource);
                } else {
                    updateHrmResourceList.add(hrmResource);
                }

            }

            // 插入人员
            baseBean.writeLog("插入人员数量： " + insertHrmResourceList.size());
            TcConnUtil.insertHrmResource(insertHrmResourceList);
            // 更新人员
            baseBean.writeLog("更新人员数量： " + updateHrmResourceList.size());
            TcConnUtil.updateHrmResource(updateHrmResourceList);

            // 处理自定义字段
            ArrayList<TcHrmResource> allList = new ArrayList<TcHrmResource>(insertHrmResourceList.size() + updateHrmResourceList.size());
            allList.addAll(updateHrmResourceList);
            allList.addAll(insertHrmResourceList);
            TcConnUtil.fieldDataExecute(allList);

            //清除缓存
            new ResourceComInfo().removeResourceCache();
            // 结束时间戳
            long end = System.currentTimeMillis();
            long cha = (end - start) / 1000;
            String logStr = "人员信息同步完成，此次新增人员： " + insertHrmResourceList.size() + " 更新人员: "
                    + updateHrmResourceList.size() + ", 信息缺失人员数量：" + errHrmResourceList.size() + " ,耗时：" + cha + " 秒。";
            // 插入日志
            TcConnUtil.insertTimedLog("hrmresource", logStr, stnCount);
        } catch (Exception e) {
            baseBean.writeLog("人员同步异常： " + e);
        }
        baseBean.writeLog("人员同步 End ========================= " + TimeUtil.getCurrentTimeString());
    }


}
