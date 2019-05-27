package com.weavernorth;

import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
import weaver.workflow.action.BaseAction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuoJiAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        this.writeLog("GuoJiAction start: " + TimeUtil.getCurrentTimeString());
        List dtlTableDataList1 = new ArrayList();
        DetailTable dtltable1 = requestInfo.getDetailTableInfo().getDetailTable(0);
        Row[] rows1 = dtltable1.getRow();
        for (int i = 0; i < rows1.length; i++) {
            Row row = rows1[i];
            Map onerow = new HashMap();
            dtlTableDataList1.add(onerow);
            Cell[] cells = row.getCell();
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[j];
                onerow.put(cell.getName().toLowerCase(), Util.null2String(cell.getValue()));
            }
        }


        DecimalFormat df2 = new DecimalFormat("##########################################0.00");
        // 新增交通费进项税
        String jtgj; //交通工具
        Map<String, BigDecimal> jtfMap = new HashMap<String, BigDecimal>();
        for (Object o : dtlTableDataList1) {
            Map onerow = (Map) o;
            jtgj = (String) onerow.get("jtgj");
            // 汽车
            if ("2".equals(jtgj)) {
                if (jtfMap.containsKey("car")) {
                    jtfMap.put("car", new BigDecimal((String) onerow.get("jtfjxs")).add(jtfMap.get("car")));
                } else {
                    jtfMap.put("car", new BigDecimal((String) onerow.get("jtfjxs")));
                }

            } else {
                if (jtfMap.containsKey("other")) {
                    jtfMap.put("other", new BigDecimal(((String) onerow.get("jtfjxs"))).add(jtfMap.get("other")));
                } else {
                    jtfMap.put("other", new BigDecimal(((String) onerow.get("jtfjxs"))));
                }
            }
        }

        for (Map.Entry<String, BigDecimal> entry : jtfMap.entrySet()) {
            this.writeLog("key: " + entry.getKey());
            this.writeLog("value: " + entry.getValue());
            String format = df2.format(entry.getValue().doubleValue());
            this.writeLog("format: " + format);

        }

        return "1";
    }
}
