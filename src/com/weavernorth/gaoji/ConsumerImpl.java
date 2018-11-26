package com.weavernorth.gaoji;

import com.google.gson.Gson;
import com.weavernorth.gaoji.util.Utils;
import com.weavernorth.gaoji.vo.*;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.job.JobTitlesComInfo;
import weaver.hrm.resource.ResourceComInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 高济组织架构同步和基础数据同步
 */
public class ConsumerImpl {

    private BaseBean baseBean = new BaseBean();
    private Gson gson = new Gson();

    /**
     * 分部入口
     */
    public void operateFenbu(List<HrmSubCompany> hrmSubCompany) {
        baseBean.writeLog("分部收到的集合:>>>>>>>>>>>>>>>>>" + gson.toJson(hrmSubCompany));
        int subCompanyErrorCount = 1;
        for (int i = 0; i < 3; i++) {
            if (subCompanyErrorCount > 0) {
                hrmSubCompany = synHrmSubCompany(hrmSubCompany, i);
            }
            subCompanyErrorCount = hrmSubCompany.size();
            baseBean.writeLog("待插入分部数量： " + subCompanyErrorCount);
        }
    }

    /**
     * 部门入口
     */
    public void operateBumen(List<HrmDepartment> hrmDepartment) {
        baseBean.writeLog("部门收到的集合:>>>>>>>>>>>>>>>>>" + gson.toJson(hrmDepartment));
        int departmentErrorCount = 1;
        for (int i = 0; i < 4; i++) {
            if (departmentErrorCount > 0) {
                hrmDepartment = synHrmDepartment(hrmDepartment, i);
            }
            departmentErrorCount = hrmDepartment.size();
            baseBean.writeLog("待插入部门数量： " + departmentErrorCount);
        }
    }

    /**
     * 人员入口
     */
    public void operateRenYuan(List<HrmResource> hrmResource) {
        try {
            int hrmResourceErrorCount = 1;
            for (int i = 0; i < 3; i++) {
                if (hrmResourceErrorCount > 0) {
                    hrmResource = synHrmResource(hrmResource, i);
                }
                hrmResourceErrorCount = hrmResource.size();
                baseBean.writeLog("待插入人员数量： " + hrmResourceErrorCount);
            }

        } catch (Exception e) {
            baseBean.writeLog("人员同步异常： " + e);
        }
    }
	
    /**
     * 内部订单
     */
    public void operateNbdd(List<UfNbdd> ufNbddList) {
        baseBean.writeLog("内部订单收到的集合:>>>>>>>>>>>>>>>>>" + gson.toJson(ufNbddList));
        for (UfNbdd ufNbdd : ufNbddList) {
            //内部订单编码
            String code = ufNbdd.getCode().trim();
            //通过id是否存在判断新增或修改
            int id = Util.getIntValue(Utils.getIdByCode("uf_nbdd", code), 0);//得到内部订单ID

            if (id <= 0) {
                ufNbdd.insertUfNbdd();
                baseBean.writeLog("提示消息：内部订单创建成功------");
            } else {
                ufNbdd.setId(String.valueOf(id));
                ufNbdd.updateUfNbdd();
                baseBean.writeLog("提示消息：内部订单更新成功------");
            }
        }

        try {
            //清除缓存
            new ResourceComInfo().removeResourceCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公司代码
     */
    public void operateWfqyzt(List<UfWfqyzt> ufWfqyztList) {
        baseBean.writeLog("公司代码收到的集合:>>>>>>>>>>>>>>>>>" + gson.toJson(ufWfqyztList));
        for (UfWfqyzt ufWfqyzt : ufWfqyztList) {
            //内部订单编码
            String bm = ufWfqyzt.getBm().trim();
            //通过id是否存在判断新增或修改
            int id = Util.getIntValue(Utils.getIdByCode("uf_wfqyzt", bm), 0);//得到公司代码ID

            if (id <= 0) {
                ufWfqyzt.insertUfWfqyzt();
                baseBean.writeLog("提示消息：公司代码创建成功------");
            } else {
                ufWfqyzt.setId(String.valueOf(id));
                ufWfqyzt.updateUfWfqyzt();
                baseBean.writeLog("提示消息：公司代码更新成功------");
            }
        }

        try {
            //清除缓存
            new ResourceComInfo().removeResourceCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 成本中心
     */
    public void operateWfqybm(List<UfWfqybm> ufWfqybmList) {
        baseBean.writeLog("成本中心收到的集合:>>>>>>>>>>>>>>>>>" + gson.toJson(ufWfqybmList));
        for (UfWfqybm ufWfqybm : ufWfqybmList) {
            // 编码
            String hrguid = ufWfqybm.getHrguid().trim();
            // 通过id是否存在判断新增或修改
            int id = Util.getIntValue(Utils.getIdByCode("uf_wfqybm", hrguid), 0);//得到成本中心ID

            if (id <= 0) {
                ufWfqybm.insertUfWfqybm();
                baseBean.writeLog("提示消息：成本中心创建成功------");
            } else {
                ufWfqybm.setId(String.valueOf(id));
                ufWfqybm.updateUfWfqybm();
                baseBean.writeLog("提示消息：成本中心更新成功------");
            }
        }

        try {
            //清除缓存
            new ResourceComInfo().removeResourceCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步分部
     */
    private List<HrmSubCompany> synHrmSubCompany(List<HrmSubCompany> hrmSubCompanies, int count) {
        baseBean.writeLog("第 " + count + " 次执行分部同步=========================");
        List<HrmSubCompany> errorList = new ArrayList<HrmSubCompany>();
        for (HrmSubCompany hrmSubCompany : hrmSubCompanies) {
            //HR唯一键
            String hrguid = hrmSubCompany.getHrguid().trim();
            //分部编码
            String subCode = hrmSubCompany.getSubcode().trim();
            //上级分部编码
            String supSubCode = hrmSubCompany.getSupsubcode().trim();
            //分部ID
            int subId = Util.getIntValue(Utils.getIdByGUID("hrmsubcompany", hrguid), 0);
            //上级分部ID
            int supsubcomid = 0;
            if (!subCode.equals(supSubCode)) {
                //编码 != 父编码 说明有上级分部
                supsubcomid = Util.getIntValue(Utils.getIdByCode("hrmsubcompany", supSubCode), 0);
            }

            baseBean.writeLog("---------------------------");
            baseBean.writeLog("分部id: " + subId);
            baseBean.writeLog("上级分部id: " + supsubcomid);
            baseBean.writeLog("分部名称： " + hrmSubCompany.getSubname());
            baseBean.writeLog("分部编码： " + subCode);
            baseBean.writeLog("上级分部编码： " + supSubCode);

            hrmSubCompany.setId(String.valueOf(subId));
            hrmSubCompany.setSupsubcomid(String.valueOf(supsubcomid));
            hrmSubCompany.setZt("0");
            hrmSubCompany.setSbyy("无");
            if (subId <= 0) {
                hrmSubCompany.setCzlx("新增");
            } else {
                hrmSubCompany.setCzlx("更新");
            }

            if ((!subCode.equals(supSubCode)) && supsubcomid <= 0) {
                //上级分部不存在
                if (count >= 2) {
                    //第3次仍有错误，插入错误信息
                    hrmSubCompany.setZt("1");
                    hrmSubCompany.setSbyy("上级分部不存在");
                    //hrmSubCompany.insertModel();
                    baseBean.writeLog("上级分部不存在 - 分部编码 - " + subCode + " - 分部名称 - " + hrmSubCompany.getSubname());
                }
                errorList.add(hrmSubCompany);
                continue;
            }

            if (subId <= 0) {
                hrmSubCompany.insertHrmSubCompany();
                int id = Util.getIntValue(Utils.getIdByGUID("hrmsubcompany", hrguid), 0);
                hrmSubCompany.insertMenuConfig(String.valueOf(id));
                baseBean.writeLog("提示消息：分部创建成功 - 分部编码 - " + subCode + " - 分部名称 - " + hrmSubCompany.getSubname());
            } else {
                hrmSubCompany.updateHrmSubCompany();
                baseBean.writeLog("提示消息：分部更新成功 - 分部编码 - " + subCode + " - 分部名称 - " + hrmSubCompany.getSubname());
            }
            //插入日志
            //hrmSubCompany.insertModel();
        }

        //清除缓存
        new SubCompanyComInfo().removeCompanyCache();
        return errorList;
    }

    /**
     * 同步部门
     */
    private List<HrmDepartment> synHrmDepartment(List<HrmDepartment> HrmDepartment, int count) {
        baseBean.writeLog("第 " + count + " 次执行部门同步=========================");
        List<HrmDepartment> errorList = new ArrayList<HrmDepartment>();
        List<HrmDepartment> errorLogInfo = new ArrayList<HrmDepartment>();
        for (HrmDepartment hrmDepartment : HrmDepartment) {
            //HR唯一键
            String hrguid = hrmDepartment.getHrguid().trim();
            //部门编码
            String subCode = hrmDepartment.getDepcode().trim();
            //父编码
            String fatherCode = hrmDepartment.getSupdepcode().trim();

            //部门ID
            int depid = Util.getIntValue(Utils.getIdByGUID("hrmdepartment", hrguid), 0);
            //上级部门ID
            int supdepid = Util.getIntValue(Utils.getIdByCode("hrmdepartment", fatherCode), 0);
            //上级分部ID
            int supdepid1 = Util.getIntValue(Utils.getIdByCode("hrmsubcompany", fatherCode), 0);

            if (supdepid1 > 0) {
                baseBean.writeLog("上级是分部，分部id： " + supdepid1);
                hrmDepartment.setSupsubid(String.valueOf(supdepid1));
                hrmDepartment.setSupdepid("0");//上级不是部门  故设为0
            } else if (supdepid > 0) {
                baseBean.writeLog("上级是部门，部门id： " + supdepid);
                //所属分部id
                String subcompanyid = Utils.getSomeByIdDepartment("subcompanyid1", String.valueOf(supdepid));
                hrmDepartment.setSupsubid(String.valueOf(subcompanyid));
                hrmDepartment.setSupdepid(String.valueOf(supdepid));
            } else {
                //上级部门不存在
                if (count >= 3) {
                    //所属分部也不存在，插入错误信息
                    hrmDepartment.setZt("1");
                    hrmDepartment.setSbyy("上级部门/分部均不存在");
                    //hrmDepartment.insertModel();
                    errorLogInfo.add(hrmDepartment);
                    continue;
                } else {
                    errorList.add(hrmDepartment);
                    continue;
                }
            }

            baseBean.writeLog("---------------------------");
            baseBean.writeLog("父编码: " + fatherCode);
            baseBean.writeLog("部门id: " + depid);
            baseBean.writeLog("上级部门id: " + supdepid);
            baseBean.writeLog("部门名称： " + hrmDepartment.getDepname());
            baseBean.writeLog("部门编码： " + subCode);
            baseBean.writeLog("上级分部id： " + hrmDepartment.getSupsubid());

            hrmDepartment.setId(String.valueOf(depid));

            hrmDepartment.setZt("0");
            hrmDepartment.setSbyy("无");
            if (depid <= 0) {
                hrmDepartment.setCzlx("新增");
            } else {
                hrmDepartment.setCzlx("更新");
            }

            if (depid <= 0) {
                hrmDepartment.insertHrmDepartment();
                baseBean.writeLog("提示消息：部门创建成功 - 所属分部编码 - " + subCode + " - 部门名称 - " + hrmDepartment.getDepname());
            } else {
                hrmDepartment.updateHrmDepartment();
                baseBean.writeLog("提示消息：部门更新成功 - 所属分部编码 - " + subCode + " - 部门名称 - " + hrmDepartment.getDepname());
            }
            //插入日志
            //hrmDepartment.insertModel();
        }

        //清除缓存
        new DepartmentComInfo().removeCompanyCache();
        baseBean.writeLog("部门插入失败共计： " + errorLogInfo.size());
//        errorLogInfo.forEach(errorListLam -> baseBean.writeLog("上级部门/分部均不存在" + " 上级机构编码 - " + errorListLam.getSupdepcode() + " - 部门名称 - " + errorListLam.getDepname() +
//                " 部门编码： " + errorListLam.getDepcode()));
        return errorList;
    }

//    /**
//     * 同步职务类别
//     */
//    private void synJobGroups(JsonArray jsonArray) {
//        baseBean.writeLog("----------------职务类别同步开始，数量为：" + jsonArray.size() + " -------------");
//        for (JsonElement je : jsonArray) {
//            JsonObject asJsonObject = je.getAsJsonObject();
//            //职务类别编码
//            String jobgroupcode = asJsonObject.get("jobgroupcode").isJsonNull() ? "" : asJsonObject.get("jobgroupcode").getAsString();
//            //职务类别名称
//            String jobgroupname = asJsonObject.get("jobgroupname").isJsonNull() ? "" : asJsonObject.get("jobgroupname").getAsString();
//
//            HrmJobGroups hrmJobGroups = new HrmJobGroups();
//            //职务类别id
//            int id = Util.getIntValue(hrmJobGroups.getJobGroupsIdByCode(jobgroupcode), 0);
//            hrmJobGroups.setId(String.valueOf(id));
//            hrmJobGroups.setJobgroupname(jobgroupname);
//            hrmJobGroups.setJobgroupremark(jobgroupname);
//            hrmJobGroups.setJobgrouprcode(jobgroupcode);
//            hrmJobGroups.setZt("0");
//            hrmJobGroups.setSbyy("无");
//            if (id <= 0) {
//                hrmJobGroups.setCzlx("新增");
//                hrmJobGroups.insertJobGroups();
//                baseBean.writeLog("提示消息：职务类别创建成功，职务ID为-----" + jobgroupcode + " --  名称为--" + jobgroupname);
//            } else {
//                hrmJobGroups.setCzlx("更新");
//                hrmJobGroups.updateJobGroups();
//                baseBean.writeLog("提示消息：职务类别更新成功，职务ID为-----" + jobgroupcode + " --  名称为--" + jobgroupname);
//            }
//            //清缓存
//            new JobGroupsComInfo().removeCompanyCache();
//
//            //-------------------插入日志-----------------
//            hrmJobGroups.insertModel();
//        }
//
//    }

//    /**
//     * 职务
//     */
//    private void synHrmJobActivities(JsonArray jsonArray) {
//        baseBean.writeLog("----------------职务同步开始，数量为：" + jsonArray.size() + " -------------");
//        for (JsonElement je : jsonArray) {
//            JsonObject asJsonObject = je.getAsJsonObject();
//            //职务编码
//            String jobactivitiecode = asJsonObject.get("jobactivitiecode").isJsonNull() ? "" : asJsonObject.get("jobactivitiecode").getAsString();
//            //职务名称
//            String jobactivitiename = asJsonObject.get("jobactivitiename").isJsonNull() ? "" : asJsonObject.get("jobactivitiename").getAsString();
//            //所属职务类别编码
//            String jobgroupcode = asJsonObject.get("jobgroupcode").isJsonNull() ? "" : asJsonObject.get("jobgroupcode").getAsString();
//
//            //所属职务类别id
//            String jobgroupid = new HrmJobGroups().getJobGroupsIdByCode(jobgroupcode);
//
//            if (jobgroupid.length() <= 0) {
//                //待定
//                jobgroupid = "11";
//            }
//
//            SynHrmJobActivities synHrmJobActivities = new SynHrmJobActivities();
//            //职务id
//            int activitiesId = Util.getIntValue(synHrmJobActivities.getActivitiesIdByCode(jobactivitiecode), 0);
//            synHrmJobActivities.setId(String.valueOf(activitiesId));
//            synHrmJobActivities.setJobactivityname(jobactivitiename);
//            synHrmJobActivities.setJobactivitymark(jobactivitiename);
//            synHrmJobActivities.setJobcode(jobactivitiecode);
//            synHrmJobActivities.setJobgroupid(jobgroupid);
//            synHrmJobActivities.setJobgroupcode(jobgroupcode);
//            synHrmJobActivities.setSbyy("无");
//            synHrmJobActivities.setZt("0");
//
//            if (activitiesId <= 0) {
//                synHrmJobActivities.setCzlx("新增");
//                synHrmJobActivities.insertActivities();
//                baseBean.writeLog("提示消息：职务创建成功，职务编码为-----" + jobactivitiecode + " --  职务名称为--"
//                        + jobactivitiename + " --  职务编码为--" + jobgroupcode);
//            } else {
//                synHrmJobActivities.setCzlx("更新");
//                synHrmJobActivities.updateActivities();
//                baseBean.writeLog("提示消息：职务更新成功，职务编码为-----" + jobactivitiecode + " --  职务名称为--"
//                        + jobactivitiename + " --  职务编码为--" + jobgroupcode);
//            }
//            //清缓存
//            new JobActivitiesComInfo().removeJobActivitiesCache();
//
//            //-------------------插入日志-----------------
//            synHrmJobActivities.insertModel();
//        }
//    }

    /**
     * 岗位
     */
    public void hrmJobTitles(List<HrmJobTitles> hrmJobTitlesList) {
        baseBean.writeLog("----------------岗位同步开始，数量为：" + hrmJobTitlesList.size() + " -------------");
        for (HrmJobTitles hrmJobTitles : hrmJobTitlesList) {
            //HR唯一键
            String hrguid = hrmJobTitles.getHrguid().trim();
            //岗位编码
            String jobtitlecode = hrmJobTitles.getJobtitlecode();
            //岗位名称
            String jobtitlename = hrmJobTitles.getJobtitlename();

            //岗位id
            int jobTitleId = Util.getIntValue(Utils.getIdByGUID("hrmjobtitles", hrguid), 0);

            hrmJobTitles.setId(String.valueOf(jobTitleId));
            hrmJobTitles.setSbyy("无");
            hrmJobTitles.setZt("0");

            if (jobTitleId <= 0) {
                hrmJobTitles.insertJobTitle();
                hrmJobTitles.setCzlx("新增");
                baseBean.writeLog("提示消息：岗位创建成功，岗位编码为-----" + jobtitlecode + " --  岗位名称为--"
                        + jobtitlename);
            } else {
                hrmJobTitles.updateJobTitle();
                hrmJobTitles.setCzlx("更新");
                baseBean.writeLog("提示消息：岗位更新成功，岗位编码为-----" + jobtitlecode + " --  岗位名称为--"
                        + jobtitlename);
            }

            //-------------------插入日志-----------------
            //hrmJobTitles.insertModel();
        }
        //清缓存
        new JobTitlesComInfo().removeJobTitlesCache();
    }

    /**
     * 人员
     */
    private List<HrmResource> synHrmResource(List<HrmResource> hrmResourceList, int count) {
        baseBean.writeLog("第 " + count + " 次执行人员同步=========================");
        List<String> errorLogList = new ArrayList<String>();
        List<HrmResource> errorList = new ArrayList<HrmResource>();
        for (HrmResource hrmResource : hrmResourceList) {
            //HR唯一键
            String hrguid = hrmResource.getHrguid().trim();
            //人员编码
            String workCode = hrmResource.getWorkcode();
            //人员id
            int id = Util.getIntValue(Utils.getIdByGUID("hrmresource", hrguid), 0);//得到人员ID
            //所属部门编码
            String depCode = hrmResource.getDepcode();
            //岗位编码
            String jobtitlecode = hrmResource.getJobtitlecode();
            //直接上级编码
            String managerCode = hrmResource.getManagerCode();
            if (id <= 0) {
                hrmResource.setCzlx("新增");
            } else {
                hrmResource.setCzlx("更新");
            }

            //部门ID
            int depId = Util.getIntValue(Utils.getIdByCode("hrmdepartment", depCode), 0);
            if (depId <= 0) {
                //所属部门不存在
                hrmResource.setZt("1");
                hrmResource.setSbyy("人员所属部门不存在");
                errorLogList.add("人员所属部门不存在，部门code： " + depCode + " ,人员编码： " + workCode + ", 姓名： " + hrmResource.getLastname());
                //插入日志
                //hrmResource.insertModel();
                continue;
            }

            //岗位id
            int jobTitleId = Util.getIntValue(Utils.getIdByCode("hrmjobtitles", jobtitlecode));
            if (jobTitleId <= 0) {
                //所属岗位不存在
                hrmResource.setZt("1");
                hrmResource.setSbyy("人员所属岗位不存在");
                errorLogList.add("人员所属岗位不存在，岗位code： " + jobtitlecode + " ,人员编码： " + workCode + ", 姓名： " + hrmResource.getLastname());
                //插入日志
                //hrmResource.insertModel();
                continue;
            }
            //所属分部id
            int subCompanyId = Util.getIntValue(Utils.getSomeByIdDepartment("subcompanyid1", String.valueOf(depId)), 0);
            //直接上级id
            int managerIdReal = Util.getIntValue(Utils.getIdByCode("hrmresource", managerCode), 0);
            if ((!workCode.equals(managerCode)) && managerIdReal <= 0) {
                //直接上级不存在
                if (count >= 2) {
                    //第3次仍有错误，插入错误信息
                    hrmResource.setZt("1");
                    hrmResource.setSbyy("直接上级不存在");
                    //hrmResource.insertModel();
                    errorLogList.add("直接上级不存在 - 直接上级编码 - " + managerCode + " - 人员名称 - " + hrmResource.getLastname());
                }
                errorList.add(hrmResource);
                continue;
            }

            //人员状态转换
            hrmResource.setStatus(Utils.changeStatus(hrmResource.getStatus()));
            //工作地点转换
            hrmResource.setLocation(Utils.insertLocation(hrmResource.getLocation()));
            //默认密码
            hrmResource.setPassWord(Util.getEncrypt(hrmResource.getMobile()));
            //直接上级
            hrmResource.setManagerIdReal(String.valueOf(managerIdReal));
            if (workCode.equals(managerCode)) {
                hrmResource.setManagerIdReal("");
            }
            //所有上级
            String managerIdAndStr = hrmResource.getManagerIdAndStr(String.valueOf(managerIdReal));
            hrmResource.setManagerstr(managerIdAndStr);

            baseBean.writeLog("---------------------------");
            baseBean.writeLog("人员code: " + workCode);
            baseBean.writeLog("人员名称: " + hrmResource.getLastname());
            baseBean.writeLog("人员id: " + id);
            baseBean.writeLog("人员上级id: " + managerIdReal);
            baseBean.writeLog("人员上级code: " + managerCode);
            baseBean.writeLog("人员所有上级: " + managerIdAndStr);
            baseBean.writeLog("所属分部id: " + subCompanyId);
            baseBean.writeLog("所属部门code: " + depCode);
            baseBean.writeLog("所属部门id: " + depId);
            baseBean.writeLog("所属岗位code: " + jobtitlecode);
            baseBean.writeLog("所属岗位id: " + jobTitleId);
            baseBean.writeLog("工作地点: " + hrmResource.getLocation());

            hrmResource.setId(String.valueOf(id));
            hrmResource.setDepId(String.valueOf(depId));
            hrmResource.setSubId(String.valueOf(subCompanyId));
            hrmResource.setJobtitleId(String.valueOf(jobTitleId));
            hrmResource.setZt("0");
            hrmResource.setSbyy("无");
            hrmResource.setAccounttype("0"); //账号类型
            hrmResource.setBelongto("");     //所属主帐号
            hrmResource.setSystemlanguage("7");//语言类型
            if (id <= 0) {
                hrmResource.setCzlx("新增");
            } else {
                hrmResource.setCzlx("更新");
            }

            if (id <= 0) {
                String newId = String.valueOf(Utils.getHrmMaxid());
                hrmResource.setId(newId);
                hrmResource.insertHrmResource();
                hrmResource.insertOrUpdateKhModel();
                hrmResource.deleteHrmResource5(newId, "1");
                baseBean.writeLog("提示消息：人员创建成功------ id = " + newId);
            } else {
                hrmResource.updateHrmResource();
                baseBean.writeLog("提示消息：人员更新成功------");
            }
            //插入日志
            //hrmResource.insertModel();
        }

        //errorLogList.forEach(e -> baseBean.writeLog(e));
        try {
            //清除缓存
            new ResourceComInfo().removeResourceCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorList;
    }

}
