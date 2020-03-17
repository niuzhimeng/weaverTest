package com.weavernorth.zhongsha.crmsap.timed;

import com.alibaba.fastjson.JSONObject;
import com.weaver.general.BaseBean;
import com.weaver.general.Util;
import com.weavernorth.zhongsha.crmsap.vo.CreateReportVO;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.TimeUtil;
import weaver.interfaces.schedule.BaseCronJob;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时更新台账
 */
public class CreateReport extends BaseCronJob {

    private BaseBean baseBean = new BaseBean();
    private ModeRightInfo moderightinfo = new ModeRightInfo();
    private RecordSet maxSet = new RecordSet();
    private RecordSet updateSet = new RecordSet();

    // private static final Integer modeId = 91; // 测试环境
    private static final Integer modeId = 97; // 正式环境

    @Override
    public void execute() {
        baseBean.writeLog("更新合同执行报表 Start=========");
        RecordSet recordSet = new RecordSet();
        // 判断合同号是否存在
        List<String> existList = new ArrayList<String>();
        recordSet.executeQuery("SELECT htbh from uf_htzxbb where htbh is not null and htbh != ''");
        while (recordSet.next()) {
            existList.add(recordSet.getString("htbh"));
        }

        // 合同号-计划费用总和
        Map<String, Double> hthFyzhMap = new HashMap<String, Double>();
        String selectFyzhSql = "Select hth, sum(jhfy) jhfy from uf_gdtzmxb GROUP BY hth HAVING hth is not null and hth != ''";
        baseBean.writeLog("合同号-计划费用总和sql： " + selectFyzhSql);
        recordSet.executeQuery(selectFyzhSql);
        while (recordSet.next()) {
            hthFyzhMap.put(recordSet.getString("hth"), Util.getDoubleValue(recordSet.getString("jhfy"), 0));
        }

        //
        /*
           计算【执行金额】
            第一优先级：将 zzjg 列求和。
            第二优先级：若 zzjg 为 null 或 空 ，那么就加 bsjg 。
            第三优先级：若 zzjg、bsjg 均为 null 或 空 ，那么就加 jhfy 。
         */
        Map<String, List<CreateReportVO>> hthZxjeMap = new HashMap<String, List<CreateReportVO>>();
        String selectZxjeSql = "Select hth, zzjg, bsjg, jhfy from uf_gdtzmxb where hth is not null and hth != '' ORDER BY hth";
        baseBean.writeLog("查询执行金额sql： " + selectZxjeSql);
        recordSet.executeQuery(selectZxjeSql);
        while (recordSet.next()) {
            String hth = recordSet.getString("hth");
            double zzjg = recordSet.getDouble("zzjg");
            double bsjg = recordSet.getDouble("bsjg");
            double jhfy = recordSet.getDouble("jhfy");
            CreateReportVO createReportVO = new CreateReportVO();
            createReportVO.setZzjg(zzjg);
            createReportVO.setBsjg(bsjg);
            createReportVO.setJhfy(jhfy);
            List<CreateReportVO> createReportList = hthZxjeMap.get(hth);
            if (createReportList != null) {
                // 像已有集合中添加对象
                createReportList.add(createReportVO);
            } else {
                // 添加新键值对
                List<CreateReportVO> CreateReportListNew = new ArrayList<CreateReportVO>();
                CreateReportListNew.add(createReportVO);
                hthZxjeMap.put(hth, CreateReportListNew);
            }
        }

        baseBean.writeLog("hthZxjeMap: " + JSONObject.toJSONString(hthZxjeMap));

        String selectSql = "SELECT DISTINCT a.hth, b.htbh, b.lxqxs, b.lxqxz, b.htmc, b.rmbje, b.httf FROM uf_gdtzmxb a LEFT JOIN uf_httz b ON a.hth = b.ylc WHERE a.hth IS NOT NULL AND a.hth != ''";
        baseBean.writeLog("查询基础字段sql： " + selectSql);
        recordSet.executeQuery(selectSql);

        String insertSql = "insert into uf_htzxbb (htbh, htbhwb, lxqxs, lxqxz, htmc, " +
                "htjg, httf, jhje, zxje, zxbfb_number, " +
                "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)";
        String updateSql = "update uf_htzxbb set htbhwb = ?, lxqxs = ?, lxqxz = ?, htmc = ?, htjg = ?, " +
                "httf = ?, jhje = ?, zxje = ?, zxbfb_number = ? where htbh = ?";
        String detailCurrentTimeString = TimeUtil.getCurrentTimeString();
        String dateStr = detailCurrentTimeString.substring(0, 10);
        String timeStr = detailCurrentTimeString.substring(11);

        while (recordSet.next()) {
            String htbh = recordSet.getString("hth"); // 合同编号
            String htbhwb = recordSet.getString("htbh"); // 合同编号（文本）
            String lxqxs = recordSet.getString("lxqxs"); // 履行期限-始
            String lxqxz = recordSet.getString("lxqxz"); // 履行期限-终
            String htmc = recordSet.getString("htmc"); // 合同名称

            String htjg = recordSet.getString("rmbje"); // 合同价格（不含税）
            String httf = recordSet.getString("httf"); // 合同他方
            double jhje = hthFyzhMap.get(htbh); // 计划金额
            double zxje = calculate(htbh, hthZxjeMap); // 执行金额

            // 执行金额 / 合同金额 * 100%
            double zxjeTemp = zxje * 100;
            BigDecimal bigOne = BigDecimal.valueOf(zxjeTemp);
            BigDecimal bigTwo = new BigDecimal(htjg);
            double zxbfb_number = bigOne.divide(bigTwo, 2, BigDecimal.ROUND_HALF_UP).doubleValue(); // 执行百分比

            if (existList.contains(htbh)) {
                // 更新
                updateSet.executeUpdate(updateSql,
                        htbhwb, lxqxs, lxqxz, htmc, htjg,
                        httf, String.valueOf(jhje), String.valueOf(zxje), String.valueOf(zxbfb_number), htbh);
            } else {
                // 新增
                updateSet.executeUpdate(insertSql,
                        htbh, htbhwb, lxqxs, lxqxz, htmc,
                        htjg, httf, String.valueOf(jhje), String.valueOf(zxje), String.valueOf(zxbfb_number),
                        String.valueOf(modeId), "1", "0", dateStr, timeStr);
                existList.add(htbh);
                // 建模赋权
                empower(htbh);
            }
        }
        baseBean.writeLog("更新合同执行报表 End =========");
    }

    private Double calculate(String hth, Map<String, List<CreateReportVO>> hthZxjeMap) {
        double returnMoney = 0;
        List<CreateReportVO> reportVOList = hthZxjeMap.get(hth);
        for (CreateReportVO reportVO : reportVOList) {
            returnMoney += getUsableMoney(reportVO);
        }
        return returnMoney;
    }

    /**
     * 找到可用金额
     * <p>
     * 先用 zzjg 最终价格
     * 如 zzjg为空或为0 取 bsjg 报审价格
     * 如 bsjg 取 jhfy 计划费用
     */
    private Double getUsableMoney(CreateReportVO reportVO) {
        if (reportVO.getZzjg() > 0) {
            return reportVO.getZzjg();
        }
        if (reportVO.getBsjg() > 0) {
            return reportVO.getBsjg();
        }
        if (reportVO.getJhfy() > 0) {
            return reportVO.getJhfy();
        }
        return 0.0;
    }

    /**
     * 建模新增数据赋权
     *
     * @param hth 合同号
     */
    private void empower(String hth) {
        try {
            moderightinfo.setNewRight(true);
            maxSet.executeSql("select id from uf_htzxbb where htbh = '" + hth + "'");
            while (maxSet.next()) {
                int maxId = maxSet.getInt("id");
                moderightinfo.rebuildModeDataShareByEdit(1, modeId, maxId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            new BaseBean().writeLog("GetGdtz数据授权异常： " + e);
        }

    }
}
