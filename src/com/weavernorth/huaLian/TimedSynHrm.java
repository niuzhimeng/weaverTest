package com.weavernorth.huaLian;

import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
import com.weavernorth.huaLian.util.HlConnUtil;
import com.weavernorth.huaLian.vo.ModelVo;
import weaver.conn.RecordSet;
import weaver.interfaces.schedule.BaseCronJob;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时同步建模表中的在职人数
 */
public class TimedSynHrm extends BaseCronJob {
    private BaseBean baseBean = new BaseBean();

    @Override
    public void execute() {
        baseBean.writeLog("定时同步建模表中的在职人数start" + TimeUtil.getCurrentTimeString());
        try {
            List<ModelVo> modelVoList = new ArrayList<ModelVo>();
            RecordSet recordSet = new RecordSet();
            RecordSet countSet = new RecordSet();

            recordSet.executeQuery("SELECT id, subcompanyname FROM HrmSubCompany WHERE id !=5 AND (canceled is null or canceled = 0)");
            baseBean.writeLog("查询分部总数： " + recordSet.getCounts());
            while (recordSet.next()) {
                ModelVo modelVo = new ModelVo();
                // 分部id
                modelVo.setFb(recordSet.getInt("id"));
                countSet.executeQuery("select count(1) myCount from hrmresource where status < 4 and subcompanyid1 = " + modelVo.getFb());
                countSet.next();
                // 在编人数
                modelVo.setZbrs(countSet.getInt("myCount"));
                modelVoList.add(modelVo);
            }

            //新增或更新 uf_rsbzxx(人事编制信息)
            List<ModelVo> insertVoList = new ArrayList<ModelVo>();
            List<ModelVo> updateVoList = new ArrayList<ModelVo>();

            RecordSet selectSet = new RecordSet();
            for (ModelVo modelVo : modelVoList) {
                selectSet.executeQuery("select * from uf_rsbzxx where fb = " + modelVo.getFb());
                if (selectSet.next()) {
                    //更新
                    // 可编制人员总数
                    int kbzrszs = selectSet.getInt("kbzrszs") < 0 ? 0 : selectSet.getInt("kbzrszs");
                    // 申请中人数
                    int sqzrs = selectSet.getInt("sqzrs") < 0 ? 0 : selectSet.getInt("sqzrs");
                    // 招聘中人数
                    int zpzrs = selectSet.getInt("zpzrs") < 0 ? 0 : selectSet.getInt("zpzrs");
                    // 在编人数
                    int zbrs = selectSet.getInt("zbrs") < 0 ? 0 : selectSet.getInt("zbrs");
                    // 计算可申请人数
                    modelVo.setKsqrs(kbzrszs - zbrs - sqzrs - zpzrs);
                    updateVoList.add(modelVo);
                } else {
                    //新增
                    insertVoList.add(modelVo);
                }
            }

            HlConnUtil.insertMode(insertVoList);
            HlConnUtil.updateMode(updateVoList);
            baseBean.writeLog("定时同步建模表中的在职人数 END" + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            baseBean.writeLog("定时同步建模表中的在职人数异常： " + e);
        }

    }
}
