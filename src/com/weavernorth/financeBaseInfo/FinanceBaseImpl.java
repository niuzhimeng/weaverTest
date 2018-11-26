package com.weavernorth.financeBaseInfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

public class FinanceBaseImpl implements FinanceBase {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String easOrg(String json) {
        baseBean.writeLog("组织表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();

        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String corp = object.get("corp").isJsonNull() ? "" : object.get("corp").getAsString();
        String parent_id = object.get("parent_id").isJsonNull() ? "" : object.get("parent_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_zzb (MY_ID, eas_id, NAME, corp, parent_id, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " VALUES (?,?,?,?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_zzb SET NAME = ?, corp = ?, parent_id = ?, deleted = ? WHERE MY_ID = ?";
            RecordSet recordSet = new RecordSet();
            recordSet.execute("select * from uf_zzb where my_id = '" + id + "'");
            if (!recordSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, eas_id);
                statement.setString(3, name);
                statement.setString(4, corp);
                statement.setString(5, parent_id);
                statement.setString(6, deleted);

                statement.setString(7, "28");//模块id
                statement.setString(8, "1");//创建人id
                statement.setString(9, "0");//一个默认值0
                statement.setString(10, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(11, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入组织信息成功");
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, corp);
                statement.setString(3, parent_id);
                statement.setString(4, deleted);
                statement.setString(5, id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新组织信息成功");
            }
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select max(id) id from uf_zzb");

            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            //设置权限
            ModeRightInfo ModeRightInfo = new ModeRightInfo();
            ModeRightInfo.setNewRight(true);
            ModeRightInfo.editModeDataShare(1, 28, maxId);//创建人id， 模块id， 该条数据id
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入组织信息出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新组织信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easUser(String json) {
        baseBean.writeLog("职员表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();

        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String deptId = object.get("deptId").isJsonNull() ? "" : object.get("deptId").getAsString();
        String email = object.get("email").isJsonNull() ? "" : object.get("email").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        try {
            String insertSql = "INSERT INTO uf_zyb (MY_ID, eas_id, NAME, EMAIL, dept_id, deleted,formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " VALUES (?,?,?,?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_zyb SET NAME = ?, EMAIL = ?, dept_id = ?, deleted = ? WHERE MY_ID = ?";
            RecordSet recordSet = new RecordSet();
            recordSet.execute("select * from uf_zyb where my_id = '" + id + "'");
            if (!recordSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, eas_id);
                statement.setString(3, name);
                statement.setString(4, email);
                statement.setString(5, deptId);
                statement.setString(6, deleted);
                statement.setString(7, "13");//模块id
                statement.setString(8, "1");//创建人id
                statement.setString(9, "0");//一个默认值0
                statement.setString(10, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(11, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入职员信息成功");
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, deptId);
                statement.setString(4, deleted);
                statement.setString(5, id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新职员信息成功");
            }
            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select max(id) id from uf_zyb");

            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            //设置权限
            ModeRightInfo ModeRightInfo = new ModeRightInfo();
            ModeRightInfo.setNewRight(true);
            ModeRightInfo.editModeDataShare(1, 13, maxId);//创建人id， 模块id， 该条数据id
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入职员信息出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新职员信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easPersonBank(String json) {
        baseBean.writeLog("职员银行表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();

        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String account = object.get("account").isJsonNull() ? "" : object.get("account").getAsString();
        String bank = object.get("bank").isJsonNull() ? "" : object.get("bank").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String userId = object.get("userId").isJsonNull() ? "" : object.get("userId").getAsString();
        try {
            String insertSql = "INSERT INTO uf_zyyh (MY_ID, account, bank, deleted, name, user_id, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "VALUES (?,?,?,?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_zyyh SET name = ?, account = ?, bank = ?, user_id = ? , deleted = ? WHERE my_id = ?";

            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_zyyh where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, account);
                statement.setString(3, bank);
                statement.setString(4, deleted);
                statement.setString(5, name);
                statement.setString(6, userId);

                statement.setString(7, "14");//模块id
                statement.setString(8, "1");//创建人id
                statement.setString(9, "0");//一个默认值0
                statement.setString(10, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(11, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入职员银行信息成功");

                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_zyyh");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 14, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, account);
                statement.setString(3, bank);
                statement.setString(4, userId);
                statement.setString(5, deleted);
                statement.setString(6, id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新职员银行信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入职员银行信息出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新职员银行信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easCustomerAndBank(String json) {
        baseBean.writeLog("客户1 - 银行N表接收到的json-> " + json);

        JsonObject returnObject = new JsonObject();
        ConnStatement mainStatement = new ConnStatement();
        ConnStatement detailStatement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String mainInsertSql = "INSERT INTO uf_khb (my_id, eas_id, name, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "VALUES (?,?,?,?, ?,?,?,?,?)";
            String mainUpdateSql = "UPDATE uf_khb SET name = ?, deleted = ? WHERE my_id = ?";
            String detailInsertSql = "INSERT INTO uf_khyhb (MY_ID, account, bank, customer_id, deleted, name, formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime)" +
                    " VALUES (?,?,?,?,?,?, ?,?,?,?,?)";

            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_khb where my_id = '" + id + "'");

            ModeRightInfo modeRightInfo = new ModeRightInfo();
            modeRightInfo.setNewRight(true);
            if (!existSet.next()) {
                //插入主表
                mainStatement.setStatementSql(mainInsertSql);
                mainStatement.setString(1, id);
                mainStatement.setString(2, eas_id);
                mainStatement.setString(3, name);
                mainStatement.setString(4, deleted);

                mainStatement.setString(5, "15");//模块id
                mainStatement.setString(6, "1");//创建人id
                mainStatement.setString(7, "0");//一个默认值0
                mainStatement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                mainStatement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                mainStatement.executeUpdate();
                //设置主表权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_khb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                modeRightInfo.editModeDataShare(1, 15, maxId);

                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入客户1-银行N信息成功");
            } else {
                //更新主表
                mainStatement.setStatementSql(mainUpdateSql);
                mainStatement.setString(1, name);
                mainStatement.setString(2, deleted);
                mainStatement.setString(3, id);
                mainStatement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新客户1-银行N信息成功");
            }

            RecordSet detailSet = new RecordSet();
            //删除明细表信息
            detailSet.execute("delete from uf_khyhb where customer_id = '" + eas_id + "'");
            //插入明细表
            detailStatement.setStatementSql(detailInsertSql);
            int i = 0;
            int detailMaxId = 0;
            ModeRightInfo detailModeRightInfo = new ModeRightInfo();
            detailModeRightInfo.setNewRight(true);
            if (object.get("bankList").isJsonArray()) {
                //明细表
                JsonArray bankList = object.get("bankList").getAsJsonArray();
                for (JsonElement je : bankList) {
                    JsonObject jb = je.getAsJsonObject();

                    detailStatement.setString(1, jb.get("id").isJsonNull() ? "" : jb.get("id").getAsString());
                    detailStatement.setString(2, jb.get("account").isJsonNull() ? "" : jb.get("account").getAsString());
                    detailStatement.setString(3, jb.get("bank").isJsonNull() ? "" : jb.get("bank").getAsString());
                    detailStatement.setString(4, jb.get("customerId").isJsonNull() ? "" : jb.get("customerId").getAsString());
                    detailStatement.setString(5, jb.get("deleted").isJsonNull() ? "" : jb.get("deleted").getAsString());
                    detailStatement.setString(6, jb.get("name").isJsonNull() ? "" : jb.get("name").getAsString());

                    detailStatement.setString(7, "16");//模块id
                    detailStatement.setString(8, "1");//创建人id
                    detailStatement.setString(9, "0");//一个默认值0
                    detailStatement.setString(10, TimeUtil.getCurrentTimeString().substring(0, 10));
                    detailStatement.setString(11, TimeUtil.getCurrentTimeString().substring(11));
                    detailStatement.executeUpdate();
                    if (i == 0) {
                        RecordSet detailRecordSet = new RecordSet();
                        detailRecordSet.executeSql("select IDENT_CURRENT('uf_khyhb') id");
                        if (detailRecordSet.next()) {
                            baseBean.writeLog("detailRecordSet.next() 执行=============");
                            detailMaxId = detailRecordSet.getInt("id");
                        }
                        i++;
                    }
                    baseBean.writeLog("当前id： " + detailMaxId);
                    detailModeRightInfo.editModeDataShare(1, 16, ++detailMaxId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入客户1-银行N信息出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新客户1-银行N信息异常");
        } finally {
            mainStatement.close();
            detailStatement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easSupplierAndBank(String json) {
        baseBean.writeLog("供应商1 - 银行N表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement mainStatement = new ConnStatement();
        ConnStatement detailStatement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();

        try {
            String mainInsertSql = "INSERT INTO uf_gysb (my_id, eas_id, deleted, name, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";
            String mainUpdateSql = "UPDATE uf_gysb SET deleted = ?, name = ?  WHERE MY_ID = ?";
            String detailInsertSql = "INSERT INTO uf_gysyhb (MY_ID, name, account, bank, supplier_id, deleted, formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime) " +
                    "VALUES (?,?,?,?,?,?, ?,?,?,?,?)";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_gysb where my_id = '" + id + "'");

            ModeRightInfo modeRightInfo = new ModeRightInfo();
            modeRightInfo.setNewRight(true);
            if (!existSet.next()) {
                //插入主表
                mainStatement.setStatementSql(mainInsertSql);
                mainStatement.setString(1, id);
                mainStatement.setString(2, eas_id);
                mainStatement.setString(3, deleted);
                mainStatement.setString(4, name);

                mainStatement.setString(5, "17");//模块id
                mainStatement.setString(6, "1");//创建人id
                mainStatement.setString(7, "0");//一个默认值0
                mainStatement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                mainStatement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                mainStatement.executeUpdate();
                //设置主表权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_gysb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                modeRightInfo.editModeDataShare(1, 17, maxId);
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入供应商1 - 银行N信息成功");
            } else {
                //更新主表
                mainStatement.setStatementSql(mainUpdateSql);
                mainStatement.setString(1, deleted);
                mainStatement.setString(2, name);
                mainStatement.setString(3, id);
                mainStatement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新供应商1 - 银行N信息成功");
            }
            //删除明细表信息
            RecordSet deleteDetailRecordSet = new RecordSet();
            deleteDetailRecordSet.execute("delete from uf_gysyhb where supplier_id = '" + eas_id + "'");
            //插入明细表
            int i = 0;
            int detailMaxId = 0;
            detailStatement.setStatementSql(detailInsertSql);
            RecordSet detailRecordSet = new RecordSet();
            if (object.get("bankList").isJsonArray()) {
                //明细表
                JsonArray bankList = object.get("bankList").getAsJsonArray();
                for (JsonElement je : bankList) {
                    JsonObject jb = je.getAsJsonObject();

                    detailStatement.setString(1, jb.get("id").isJsonNull() ? "" : jb.get("id").getAsString());
                    detailStatement.setString(2, jb.get("name").isJsonNull() ? "" : jb.get("name").getAsString());
                    detailStatement.setString(3, jb.get("account").isJsonNull() ? "" : jb.get("account").getAsString());
                    detailStatement.setString(4, jb.get("bank").isJsonNull() ? "" : jb.get("bank").getAsString());
                    detailStatement.setString(5, jb.get("supplier_id").isJsonNull() ? "" : jb.get("supplier_id").getAsString());
                    detailStatement.setString(6, jb.get("deleted").isJsonNull() ? "" : jb.get("deleted").getAsString());

                    detailStatement.setString(7, "18");//模块id
                    detailStatement.setString(8, "1");//创建人id
                    detailStatement.setString(9, "0");//一个默认值0
                    detailStatement.setString(10, TimeUtil.getCurrentTimeString().substring(0, 10));
                    detailStatement.setString(11, TimeUtil.getCurrentTimeString().substring(11));
                    detailStatement.executeUpdate();
                    if (i == 0) {
                        detailRecordSet.executeSql("select max(id) id from uf_gysyhb");
                        if (detailRecordSet.next()) {
                            detailMaxId = detailRecordSet.getInt("id");
                        }
                        i++;
                    }
                    modeRightInfo.editModeDataShare(1, 18, detailMaxId++);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入供应商1-银行N信息出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新供应商1 - 银行N信息异常");
        } finally {
            mainStatement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easAccount(String json) {
        baseBean.writeLog("科目表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();

        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String assistant_id = object.get("assistant_id").isJsonNull() ? "" : object.get("assistant_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_kmb (my_id, name, assistant_id, deleted,formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " VALUES (?,?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_kmb SET name = ?, assistant_id = ?, deleted = ? WHERE MY_ID = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_kmb where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, assistant_id);
                statement.setString(4, deleted);

                statement.setString(5, "19");//模块id
                statement.setString(6, "1");//创建人id
                statement.setString(7, "0");//一个默认值0
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();

                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_kmb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入科目信息成功");

                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 19, maxId);//创建人id， 模块id， 该条数据id
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, assistant_id);
                statement.setString(3, deleted);
                statement.setString(4, id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新科目信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入科目信息出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新科目信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easExpenseCategory(String json) {
        baseBean.writeLog("业务类别表接收到的json-> " + json);

        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_ywlbb (my_id, eas_id, name, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "VALUES (?,?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_ywlbb SET name = ?, deleted = ? WHERE my_id = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_ywlbb where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, eas_id);
                statement.setString(3, name);
                statement.setString(4, deleted);

                statement.setString(5, "20");//模块id
                statement.setString(6, "1");//创建人id
                statement.setString(7, "0");//一个默认值0
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入业务类别信息成功");
                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_ywlbb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 20, maxId);//创建人id， 模块id， 该条数据id
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新业务类别信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入业务类别出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新业务类别信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easExpenseType(String json) {
        baseBean.writeLog("费用类型表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String category_id = object.get("category_id").isJsonNull() ? "" : object.get("category_id").getAsString();
        String parent_id = object.get("parent_id").isJsonNull() ? "" : object.get("parent_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_fylxb (my_id, name, category_id, parent_id, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "VALUES (?,?,?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_fylxb SET name = ?, category_id = ?, parent_id = ?, deleted = ? WHERE my_id = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_fylxb where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, category_id);
                statement.setString(4, parent_id);
                statement.setString(5, deleted);

                statement.setString(6, "21");//模块id
                statement.setString(7, "1");//创建人id
                statement.setString(8, "0");//一个默认值0
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(10, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();

                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_fylxb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入费用类型信息成功");
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 21, maxId);//创建人id， 模块id， 该条数据id
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, category_id);
                statement.setString(3, parent_id);
                statement.setString(4, deleted);
                statement.setString(5, id);
                statement.executeUpdate();

                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新费用类型信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入费用类型出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新费用类型信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easPayType(String json) {
        baseBean.writeLog("支付方式表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();

        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_zffsb (my_id, name, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " VALUES (?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_zffsb SET name = ?, deleted = ? WHERE my_id = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_zffsb where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, deleted);

                statement.setString(4, "22");//模块id
                statement.setString(5, "1");//创建人id
                statement.setString(6, "0");//一个默认值0
                statement.setString(7, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入支付方式信息成功");

                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_zffsb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 22, maxId);//创建人id， 模块id， 该条数据id
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新支付方式信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入支付方式出错-> " + e);

            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新支付方式信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easAssistant(String json) {
        baseBean.writeLog("核算项目类别表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String type = object.get("type").isJsonNull() ? "" : object.get("type").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_hsxmlb (my_id, name, type, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " VALUES (?,?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_hsxmlb SET name = ?, type = ?, deleted = ? WHERE my_id = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_hsxmlb where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, type);
                statement.setString(4, deleted);

                statement.setString(5, "23");//模块id
                statement.setString(6, "1");//创建人id
                statement.setString(7, "0");//一个默认值0
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();

                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入核算项目信息成功");

                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_hsxmlb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 23, maxId);//创建人id， 模块id， 该条数据id
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, type);
                statement.setString(3, deleted);
                statement.setString(4, id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新核算项目信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入核算项目类别出错-> " + e);

            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新核算项目信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easProduct(String json) {
        baseBean.writeLog("产品档案表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_cpda (my_id, name, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "VALUES (?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_cpda SET name = ?, deleted = ? WHERE my_id = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_cpda where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, deleted);

                statement.setString(4, "24");//模块id
                statement.setString(5, "1");//创建人id
                statement.setString(6, "0");//一个默认值0
                statement.setString(7, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入产品档案信息成功");
                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_cpda");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 24, maxId);//创建人id， 模块id， 该条数据id
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, id);
                statement.executeUpdate();

                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新产品档案信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入产品档案出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新产品档案信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easReceiptsType(String json) {
        baseBean.writeLog("收付类别表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_sflb (my_id, name, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) " +
                    "VALUES (?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_sflb SET name = ?, deleted = ? WHERE my_id = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_sflb where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, deleted);

                statement.setString(4, "25");//模块id
                statement.setString(5, "1");//创建人id
                statement.setString(6, "0");//一个默认值0
                statement.setString(7, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入收付类别信息成功");
                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_sflb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 25, maxId);//创建人id， 模块id， 该条数据id
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新收付类别信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入收付类别出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新收付类别信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easTaxRate(String json) {
        baseBean.writeLog("税目税率表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_smslb (my_id, name, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " VALUES (?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_smslb SET name = ?, deleted = ? WHERE my_id = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_smslb where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, deleted);

                statement.setString(4, "26");//模块id
                statement.setString(5, "1");//创建人id
                statement.setString(6, "0");//一个默认值0
                statement.setString(7, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入税目税率信息成功");
                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_smslb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 26, maxId);//创建人id， 模块id， 该条数据id
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新税目税率信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插入税目税率表出错-> " + e);

            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新税目税率信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

    @Override
    public String easTaxCount(String json) {
        baseBean.writeLog("计税方法表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();

        try {
            String insertSql = "INSERT INTO uf_jsffb (my_id, name, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " VALUES (?,?,?, ?,?,?,?,?)";
            String updateSql = "UPDATE uf_jsffb SET name = ?, deleted = ? WHERE my_id = ?";

            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_jsffb where my_id = '" + id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, deleted);

                statement.setString(4, "27");//模块id
                statement.setString(5, "1");//创建人id
                statement.setString(6, "0");//一个默认值0
                statement.setString(7, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入计税方法信息成功");
                //设置权限
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_jsffb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 27, maxId);//创建人id， 模块id， 该条数据id
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, id);
                statement.executeUpdate();

                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新计税方法信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseBean.writeLog("插计税方法表出错-> " + e);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新计税方法信息异常");
        } finally {
            statement.close();
        }
        return returnObject.toString();
    }

}
