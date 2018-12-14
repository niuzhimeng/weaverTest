package com.weavernorth.huaLian;

import com.google.gson.Gson;
import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import com.weavernorth.huaLian.vo.ModelVo;
import weaver.conn.RecordSet;
import weaver.interfaces.schedule.BaseCronJob;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时同步建模表中的在职人数
 */
public class TimedSynHrm extends BaseCronJob {
    private Gson gson = new Gson();
    private BaseBean baseBean = new BaseBean();


    @Override
    public void execute() {
        baseBean.writeLog("定时同步建模表中的在职人数start" + TimeUtil.getCurrentTimeString());
        try {
            List<ModelVo> modelVoList = new ArrayList<ModelVo>();
            RecordSet recordSet = new RecordSet();
            RecordSet countSet = new RecordSet();
            recordSet.executeQuery("select id, subcompanyid1 from HrmDepartment where supdepid = 0 and (canceled is null or canceled = 0)");
            while (recordSet.next()) {
                List<Integer> sonIdList = new ArrayList<Integer>();
                ModelVo modelVo = new ModelVo();
                int depId = recordSet.getInt("id");
                modelVo.setFb(recordSet.getInt("subcompanyid1"));
                modelVo.setBm(depId);

                //所有子部门id的集合（包括当前父部们）
                getAllSon(recordSet.getInt("id"), sonIdList);
                sonIdList.add(depId);//添加当前部门
                modelVo.setSonDepList(sonIdList);

                //获取所有子部门人数（包括当前父部们）
                String idStr = gson.toJson(sonIdList);
                String substring = idStr.substring(1, idStr.length() - 1);
                countSet.executeQuery("select count(1) myCount from hrmresource where status < 4 and departmentid in (" + substring + ")");
                countSet.next();
                modelVo.setZbrs(countSet.getInt("myCount"));
                modelVoList.add(modelVo);
            }
            baseBean.writeLog("第一次拼装集合： " + new Gson().toJson(modelVoList));

            //新增或更新 uf_rsbzxx(人事编制信息)
            RecordSet sleectSet = new RecordSet();
            for (ModelVo modelVo : modelVoList) {
                sleectSet.executeQuery("select * from uf_rsbzxx where bm = " + modelVo.getBm());
                if (sleectSet.next()) {
                    //更新
                    int kbzrszs = sleectSet.getInt("kbzrszs") < 0 ? 0 : sleectSet.getInt("kbzrszs");//可编制人员总数
                    modelVo.setKsqrs(kbzrszs - modelVo.getZbrs());
                    modelVo.update();
                } else {
                    //新增
                    modelVo.insert();
                }
            }

        } catch (Exception e) {
            baseBean.writeLog("定时同步建模表中的在职人数异常： " + e);
        }

    }

    /**
     * 递归获取所有下级部门id
     *
     * @param id     父级id
     * @param idList 所有子部门id集合
     */
    private List<Integer> getAllSon(int id, List<Integer> idList) {
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select id from HrmDepartment where supdepid = " + id);
        while (recordSet.next()) {
            idList.add(recordSet.getInt("id"));
            getAllSon(recordSet.getInt("id"), idList);
        }
        new BaseBean().writeLog("当前list： " + new Gson().toJson(idList));
        return idList;
    }
}
