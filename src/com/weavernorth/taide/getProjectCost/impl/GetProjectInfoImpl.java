package com.weavernorth.taide.getProjectCost.impl;

import com.weavernorth.taide.getProjectCost.GetProjectCost;
import com.weavernorth.taide.getProjectCost.vo.TiDiProjectInfo;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取项目花费信息
 */
public class GetProjectInfoImpl implements GetProjectCost {

    private BaseBean baseBean = new BaseBean();

    @Override
    public List<TiDiProjectInfo> getProjectCostInfo(String startDate, String endDate) {
        baseBean.writeLog("获取项目花费信息Start " + TimeUtil.getCurrentTimeString());
        try {
            String sqlWhere = " where 1 = 1 ";
            if (startDate != null && isValidDate(startDate)) {
                sqlWhere += " and LASTOPERATEDATE >= '" + startDate + "' ";
            }
            if (endDate != null && isValidDate(startDate)) {
                sqlWhere += " and LASTOPERATEDATE <= '" + endDate + "' ";
            }

            RecordSet recordSet = new RecordSet();
            String sql = "select * from YFFYBX " + sqlWhere;
            baseBean.writeLog("查询执行sql： " + sql);
            recordSet.executeQuery(sql);
            List<TiDiProjectInfo> tiDiProjectInfoList = new ArrayList<TiDiProjectInfo>(recordSet.getCounts());
            baseBean.writeLog("查得数据共计：" + recordSet.getCounts() + " 条");
            while (recordSet.next()) {
                TiDiProjectInfo tiDiProjectInfo = new TiDiProjectInfo();
                tiDiProjectInfo.setRequestId(recordSet.getString("REQUESTID"));
                tiDiProjectInfo.setMid(recordSet.getString("MID"));
                tiDiProjectInfo.setWorkCode(recordSet.getString("WORKCODE"));
                tiDiProjectInfo.setYsrq(recordSet.getString("YSRQ"));
                tiDiProjectInfo.setCbzxbm(recordSet.getString("CBZXBM"));

                tiDiProjectInfo.setRdh(recordSet.getString("RDH"));
                tiDiProjectInfo.setKmdm(recordSet.getString("KMDM"));
                tiDiProjectInfo.setYflcb(recordSet.getString("YFLCB"));
                tiDiProjectInfo.setJermb(recordSet.getString("JERMB"));
                tiDiProjectInfo.setLastOperateDate(recordSet.getString("LASTOPERATEDATE"));

                tiDiProjectInfo.setBxyy(recordSet.getString("BXYY"));

                tiDiProjectInfoList.add(tiDiProjectInfo);
            }
            return tiDiProjectInfoList;
        } catch (Exception e) {
            baseBean.writeLog("获取项目花费信息 error " + e);
        }
        return null;
    }

    private static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            convertSuccess = false;
        }
        if (str.length() != 10) {
            convertSuccess = false;
        }
        return convertSuccess;
    }
}
