package com.weavernorth.zgsy.baseInfo;

import com.weavernorth.zgsy.webUtil.LoadServiceLocator;
import com.weavernorth.zgsy.webUtil.LoadServiceSoap_PortType;
import com.weavernorth.zgsy.webUtil.MaterialInfo;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.interfaces.schedule.BaseCronJob;

public class MaterialDataTimed extends BaseCronJob {

    @Override
    public void execute() {
        BaseBean baseBean = new BaseBean();

        String currentDateString = TimeUtil.getCurrentTimeString();
        LoadServiceLocator locator = new LoadServiceLocator();
        ConnStatement statement = new ConnStatement();
        String sql = "insert into my_material_data(fnumber, fname, fmodel, create_time) values(?, ?, ?, ?)";
        try {
            LoadServiceSoap_PortType loadServiceSoap = locator.getLoadServiceSoap();
            MaterialInfo[] materialInfos = loadServiceSoap.materialData("004");
            baseBean.writeLog("定时获取物料信息接口执行,此次获取 " + materialInfos.length + " 条");
            statement.setStatementSql(sql);
            for (MaterialInfo m : materialInfos) {
                statement.setString(1, m.getFNumber());
                statement.setString(2, m.getFName());
                statement.setString(3, m.getFModel());
                statement.setString(4, currentDateString);
                statement.executeUpdate();
            }
            if (materialInfos.length > 0) {
                //删除旧数据
                RecordSet recordSet = new RecordSet();
                recordSet.execute("delete from my_material_data where create_time < '" + currentDateString + "'");
            }
        } catch (Exception e) {
            baseBean.writeLog("调用物料接口出错： " + e);
        } finally {
            statement.close();
        }
    }
}
