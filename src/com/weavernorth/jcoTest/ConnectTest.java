package com.weavernorth.jcoTest;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import weaver.general.BaseBean;


public class ConnectTest {
    static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";

    private BaseBean baseBean = new BaseBean();

    public JCoDestination test() {

        try {
            JCoDestination destination = SapConnPool.getJCoDestination(ABAP_AS_POOLED);
            JCoRepository repository = destination.getRepository();
            JCoFunction function = repository.getFunctionTemplate("function").getFunction();
            //二选一，不一定哪个好使
            JCoTable table = function.getTableParameterList().getTable("");
            //JCoTable table1 = function.getImportParameterList().getTable("");
            //赋值
            for (int i = 0; i < 3; i++) {
                table.appendRow();
                table.setRow(i);
                table.setValue("", "");
            }

            //执行
            function.execute(destination);
            //获取返回参数
            JCoTable exTable = function.getExportParameterList().getTable("");
            for (int i = 0; i < exTable.getNumRows(); i++) {
                exTable.setRow(i);
                System.out.println(exTable.getString("field01"));
                System.out.println(exTable.getString("field02"));
            }
            return destination;
        } catch (Exception e) {
            baseBean.writeLog(e);
            System.out.println("连接失败");

        }

        return null;
    }


}
