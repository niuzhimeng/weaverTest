//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

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

    public FinanceBaseImpl() {
    }

    public String easOrg(String json) {
        this.baseBean.writeLog("组织表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String corpId = object.get("corp").isJsonNull() ? "" : object.get("corp").getAsString();
        String parent_id = object.get("parent_id").isJsonNull() ? "" : object.get("parent_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codeOrg = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();
        //新增
        String gsid = object.get("orgcode").isJsonNull() ? "" : object.get("orgcode").getAsString();
        String level = object.get("level").isJsonNull() ? "" : object.get("level").getAsString();

        String corp = "";
        if (corpId.equals("false")) {
            corp = "0";
        } else {
            corp = "1";
        }

        try {
            String insertSql = "INSERT INTO uf_zzb (MY_ID, eas_id, NAME, corp, parent_id,code, deleted, gsid, level, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?,?,?,?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_zzb SET NAME = ?, corp = ?, parent_id = ?, deleted = ? ,code= ? , gsid= ? , level= ? WHERE eas_id collate Chinese_PRC_CS_AS = ?";
            RecordSet recordSet = new RecordSet();
            recordSet.execute("select * from uf_zzb where eas_id collate Chinese_PRC_CS_AS = '" + eas_id + "'");
            if (!recordSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, eas_id);
                statement.setString(3, name);
                statement.setString(4, corp);
                statement.setString(5, parent_id);
                statement.setString(6, codeOrg);
                statement.setString(7, deleted);
                statement.setString(8, gsid);
                statement.setString(9, level);
                statement.setString(10, "28");
                statement.setString(11, "1");
                statement.setString(12, "0");
                statement.setString(13, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(14, TimeUtil.getCurrentTimeString().substring(11));
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
                statement.setString(5, codeOrg);
                statement.setString(6, gsid);
                statement.setString(7, level);
                statement.setString(8, eas_id);
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

            ModeRightInfo ModeRightInfo = new ModeRightInfo();
            ModeRightInfo.setNewRight(true);
            ModeRightInfo.editModeDataShare(1, 28, maxId);
        } catch (Exception var21) {
            var21.printStackTrace();
            this.baseBean.writeLog("插入组织信息出错-> " + var21);
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
        this.baseBean.writeLog("职员表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String deptId = object.get("deptId").isJsonNull() ? "" : object.get("deptId").getAsString();
        String email = object.get("email").isJsonNull() ? "" : object.get("email").getAsString();
        //String codeUser ="";
        String codeUser = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();

        try {
            String insertSql = "INSERT INTO uf_zyb (MY_ID, eas_id, NAME, EMAIL, dept_id, code,deleted,formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?,?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_zyb SET NAME = ?, EMAIL = ?, dept_id = ?, code= ?,deleted = ? WHERE eas_id collate Chinese_PRC_CS_AS = ?";
            RecordSet recordSet = new RecordSet();
            recordSet.execute("select * from uf_zyb where eas_id collate Chinese_PRC_CS_AS = '" + eas_id + "'");
            if (!recordSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, eas_id);
                statement.setString(3, name);
                statement.setString(4, email);
                statement.setString(5, deptId);
                statement.setString(6, codeUser);
                statement.setString(7, deleted);
                statement.setString(8, "13");
                statement.setString(9, "1");
                statement.setString(10, "0");
                statement.setString(11, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(12, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入职员信息成功!");
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, deptId);
                statement.setString(4, codeUser);
                statement.setString(5, deleted);
                statement.setString(6, eas_id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新职员信息成功!");
            }

            RecordSet maxSet = new RecordSet();
            maxSet.executeSql("select max(id) id from uf_zyb");
            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }

            ModeRightInfo ModeRightInfo = new ModeRightInfo();
            ModeRightInfo.setNewRight(true);
            ModeRightInfo.editModeDataShare(1, 13, maxId);
        } catch (Exception var21) {
            var21.printStackTrace();
            this.baseBean.writeLog("插入职员信息出错-> " + var21);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新职员信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easPersonBank(String json) {
        this.baseBean.writeLog("职员银行表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String account = object.get("account").isJsonNull() ? "" : object.get("account").getAsString();
        String bank = object.get("bank").isJsonNull() ? "" : object.get("bank").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String userId = object.get("userId").isJsonNull() ? "" : object.get("userId").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String codePersonBank = "";
        baseBean.writeLog("userId--->" + userId);
        //根据userId查询职员表里的email
        RecordSet emailQuery = new RecordSet();
        String emailSql = "select EMAIL from uf_zyb where eas_id collate Chinese_PRC_CS_AS ='" + eas_id + "'";
        baseBean.writeLog("邮箱--->" + "select EMAIL from uf_zyb where eas_id collate Chinese_PRC_CS_AS='" + eas_id + "'");
        emailQuery.execute(emailSql);
        String email = emailQuery.getString("EMAIL");
        baseBean.writeLog("邮箱--->" + email);
        //根据邮箱在hrmresource查询人员id
        RecordSet peopleQuery = new RecordSet();
        String peopleSql = "select id from HrmResource where email='" + email + "'";
        peopleQuery.execute(peopleSql);
        peopleQuery.next();
        String nameId = peopleQuery.getString("id");

        //String codePersonBank = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String insertSql = "INSERT INTO uf_zyyh (MY_ID, account, bank, deleted, name, user_id, code,eas_id, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?,?,?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_zyyh SET name = ?, account = ?, bank = ?, code= ?,user_id = ? , deleted = ?,eas_id=? WHERE eas_id collate Chinese_PRC_CS_AS = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_zyyh where eas_id collate Chinese_PRC_CS_AS = '" + eas_id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, account);
                statement.setString(3, bank);
                statement.setString(4, deleted);
                statement.setString(5, name);
                statement.setString(6, userId);
                statement.setString(7, codePersonBank);
                statement.setString(8, eas_id);
                statement.setString(9, "14");
                statement.setString(10, "1");
                statement.setString(11, "0");
                statement.setString(12, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(13, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入职员银行信息成功!");
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
                statement.setString(4, codePersonBank);
                statement.setString(5, userId);
                statement.setString(6, deleted);
                statement.setString(7, eas_id);
                statement.setString(8, eas_id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新职员银行信息成功!");
            }
        } catch (Exception var21) {
            var21.printStackTrace();
            this.baseBean.writeLog("插入职员银行信息出错-> " + var21);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新职员银行信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easCustomerAndBank(String json) {
        this.baseBean.writeLog("客户1 - 银行N表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement mainStatement = new ConnStatement();
        ConnStatement detailStatement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codeCustomerAndBank = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String mainInsertSql = "INSERT INTO uf_khb (my_id, eas_id, name, deleted,code, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?,?, ?,?,?,?,?,?)";
            String mainUpdateSql = "UPDATE uf_khb SET name = ?, deleted = ? ,code = ? WHERE eas_id collate Chinese_PRC_CS_AS = ?";
            String detailInsertSql = "INSERT INTO uf_khyhb (MY_ID, account, bank, customer_id, deleted, code , name, eas_id, formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime) VALUES (?,?,?,?,?,?,?, ?,?,?,?,?,?)";
            String detailsUpdateSql = "UPDATE uf_khyhb SET MY_ID = ?,account = ? ,bank = ? ,customer_id = ? , deleted = ? ,code = ? ,name = ? WHERE eas_id collate Chinese_PRC_CS_AS = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_khb where eas_id collate Chinese_PRC_CS_AS = '" + eas_id + "'");
            ModeRightInfo modeRightInfo = new ModeRightInfo();
            modeRightInfo.setNewRight(true);
            RecordSet recordSet;
            int i;
            if (!existSet.next()) {
                mainStatement.setStatementSql(mainInsertSql);
                mainStatement.setString(1, id);
                mainStatement.setString(2, eas_id);
                mainStatement.setString(3, name);
                mainStatement.setString(4, deleted);
                mainStatement.setString(5, codeCustomerAndBank);
                mainStatement.setString(6, "15");
                mainStatement.setString(7, "1");
                mainStatement.setString(8, "0");
                mainStatement.setString(9, TimeUtil.getCurrentTimeString().substring(0, 10));
                mainStatement.setString(10, TimeUtil.getCurrentTimeString().substring(11));
                mainStatement.executeUpdate();
                recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_khb");
                i = 0;
                if (recordSet.next()) {
                    i = recordSet.getInt("id");
                }

                modeRightInfo.editModeDataShare(1, 15, i);
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入客户1-银行N信息成功");
            } else {
                mainStatement.setStatementSql(mainUpdateSql);
                mainStatement.setString(1, name);
                mainStatement.setString(2, deleted);
                mainStatement.setString(3, codeCustomerAndBank);
                mainStatement.setString(4, eas_id);
                mainStatement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新客户1-银行N信息成功!");
            }

//            recordSet = new RecordSet();
//            recordSet.execute("delete from uf_khyhb where customer_id = '" + eas_id + "'");
//            detailStatement.setStatementSql(detailInsertSql);

            i = 0;
            int detailMaxId = 0;
            ModeRightInfo detailModeRightInfo = new ModeRightInfo();
            detailModeRightInfo.setNewRight(true);
            if (object.get("bankList").isJsonArray()) {
                JsonArray bankList = object.get("bankList").getAsJsonArray();
                for (JsonElement je : bankList) {
                    JsonObject jb = je.getAsJsonObject();
                    String detailsEasId = jb.get("eas_id").isJsonNull() ? "" : jb.get("eas_id").getAsString();
                    this.baseBean.writeLog("银行的easId为：--->" + detailsEasId);
                    //查询客户银行表是否存在对应数据
                    RecordSet detailsExistSet = new RecordSet();
                    detailsExistSet.execute("select * from uf_khyhb where eas_id collate Chinese_PRC_CS_AS = '" + detailsEasId + "'");
                    //如果存在则更新，不存在则插入
                    if (!detailsExistSet.next()) {//插入
                        detailStatement.setStatementSql(detailInsertSql);
                        detailStatement.setString(1, jb.get("id").isJsonNull() ? "" : jb.get("id").getAsString());
                        detailStatement.setString(2, jb.get("account").isJsonNull() ? "" : jb.get("account").getAsString());
                        detailStatement.setString(3, jb.get("bank").isJsonNull() ? "" : jb.get("bank").getAsString());
                        detailStatement.setString(4, jb.get("customerId").isJsonNull() ? "" : jb.get("customerId").getAsString());
                        detailStatement.setString(5, jb.get("deleted").isJsonNull() ? "" : jb.get("deleted").getAsString());
                        detailStatement.setString(6, "");
                        //jb.get("code").isJsonNull() ? "" : jb.get("code").getAsString()
                        detailStatement.setString(7, jb.get("name").isJsonNull() ? "" : jb.get("name").getAsString());
                        detailStatement.setString(8, detailsEasId);
                        detailStatement.setString(9, "16");
                        detailStatement.setString(10, "1");
                        detailStatement.setString(11, "0");
                        detailStatement.setString(12, TimeUtil.getCurrentTimeString().substring(0, 10));
                        detailStatement.setString(13, TimeUtil.getCurrentTimeString().substring(11));
                        detailStatement.executeUpdate();
                        if (i == 0) {
                            RecordSet detailRecordSet = new RecordSet();
                            detailRecordSet.executeSql("select IDENT_CURRENT('uf_khyhb') id");
                            if (detailRecordSet.next()) {
                                this.baseBean.writeLog("detailRecordSet.next() 执行=============");
                                detailMaxId = detailRecordSet.getInt("id");
                            }

                            ++i;
                        }
                        this.baseBean.writeLog("当前id： " + detailMaxId);
                        ++detailMaxId;
                        detailModeRightInfo.editModeDataShare(1, 16, detailMaxId);
                        returnObject.addProperty("success", "true");
                        returnObject.addProperty("code", "200");
                        returnObject.addProperty("message", "插入客户1-银行N信息成功");
                    } else {//更新
                        detailStatement.setStatementSql(detailsUpdateSql);
                        detailStatement.setString(1, jb.get("id").isJsonNull() ? "" : jb.get("id").getAsString());
                        detailStatement.setString(2, jb.get("account").isJsonNull() ? "" : jb.get("account").getAsString());
                        detailStatement.setString(3, jb.get("bank").isJsonNull() ? "" : jb.get("bank").getAsString());
                        detailStatement.setString(4, jb.get("customerId").isJsonNull() ? "" : jb.get("customerId").getAsString());
                        detailStatement.setString(5, jb.get("deleted").isJsonNull() ? "" : jb.get("deleted").getAsString());
                        detailStatement.setString(6, "");
                        detailStatement.setString(7, jb.get("name").isJsonNull() ? "" : jb.get("name").getAsString());
                        detailStatement.setString(8, detailsEasId);
                        detailStatement.executeUpdate();
                        returnObject.addProperty("success", "true");
                        returnObject.addProperty("code", "200");
                        returnObject.addProperty("message", "更新客户1-银行N信息成功!");
                    }

                }
            }
        } catch (Exception var28) {
            var28.printStackTrace();
            this.baseBean.writeLog("插入客户1-银行N信息出错-> " + var28);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新客户1-银行N信息异常");
        } finally {
            mainStatement.close();
            detailStatement.close();
        }

        return returnObject.toString();
    }

    public String easSupplierAndBank(String json) {
        this.baseBean.writeLog("供应商1 - 银行N表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement mainStatement = new ConnStatement();
        ConnStatement detailStatement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String codeSupplierAndBank = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String mainInsertSql = "INSERT INTO uf_gysb (my_id, eas_id, deleted, name,code, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?,?,?,?,?,?,?,?)";
            String mainUpdateSql = "UPDATE uf_gysb SET deleted = ?, name = ? , code= ? WHERE eas_id collate Chinese_PRC_CS_AS = ?";
            String detailInsertSql = "INSERT INTO uf_gysyhb (MY_ID, name, account, bank, supplier_id, deleted, code,eas_id, formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String detailsUpdateSql = "UPDATE uf_gysyhb SET MY_ID = ?,account = ? ,bank = ? ,supplier_id = ? , deleted = ? ,code = ? ,name = ? WHERE eas_id collate Chinese_PRC_CS_AS = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_gysb where eas_id collate Chinese_PRC_CS_AS = '" + eas_id + "'");
            ModeRightInfo modeRightInfo = new ModeRightInfo();
            modeRightInfo.setNewRight(true);
            RecordSet recordSet;
            int i;
            if (!existSet.next()) {
                mainStatement.setStatementSql(mainInsertSql);
                mainStatement.setString(1, id);
                mainStatement.setString(2, eas_id);
                mainStatement.setString(3, deleted);
                mainStatement.setString(4, name);
                mainStatement.setString(5, codeSupplierAndBank);
                mainStatement.setString(6, "17");
                mainStatement.setString(7, "1");
                mainStatement.setString(8, "0");
                mainStatement.setString(9, TimeUtil.getCurrentTimeString().substring(0, 10));
                mainStatement.setString(10, TimeUtil.getCurrentTimeString().substring(11));
                mainStatement.executeUpdate();
                recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_gysb");
                i = 0;
                if (recordSet.next()) {
                    i = recordSet.getInt("id");
                }

                modeRightInfo.editModeDataShare(1, 17, i);
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入供应商1 - 银行N信息成功");
            } else {
                mainStatement.setStatementSql(mainUpdateSql);
                mainStatement.setString(1, deleted);
                mainStatement.setString(2, name);
                mainStatement.setString(3, codeSupplierAndBank);
                mainStatement.setString(4, eas_id);
                mainStatement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新供应商1 - 银行N信息成功!");
            }
//            recordSet = new RecordSet();
//            recordSet.execute("delete from uf_gysyhb where supplier_id = '" + eas_id + "'");
//            detailStatement.setStatementSql(detailInsertSql);
            i = 0;
            int detailMaxId = 0;
            ModeRightInfo detailModeRightInfo = new ModeRightInfo();
            detailModeRightInfo.setNewRight(true);

            if (object.get("bankList").isJsonArray()) {
                JsonArray bankList = object.get("bankList").getAsJsonArray();
                for (JsonElement je : bankList) {
                    JsonObject jb = je.getAsJsonObject();
                    String detailsEasId = jb.get("eas_id").isJsonNull() ? "" : jb.get("eas_id").getAsString();
                    this.baseBean.writeLog("供应商银行的easId为--->" + detailsEasId);
                    //查询供应商银行表是否存在对应数据
                    RecordSet detailsExistSet = new RecordSet();
                    detailsExistSet.execute("select * from uf_gysyhb where eas_id collate Chinese_PRC_CS_AS = '" + detailsEasId + "'");

                    //如果存在则更新，不存在则插入
                    if (!detailsExistSet.next()) {//插入
                        detailStatement.setStatementSql(detailInsertSql);
                        detailStatement.setString(1, jb.get("id").isJsonNull() ? "" : jb.get("id").getAsString());
                        detailStatement.setString(2, jb.get("name").isJsonNull() ? "" : jb.get("name").getAsString());
                        detailStatement.setString(3, jb.get("account").isJsonNull() ? "" : jb.get("account").getAsString());
                        detailStatement.setString(4, jb.get("bank").isJsonNull() ? "" : jb.get("bank").getAsString());
                        detailStatement.setString(5, jb.get("supplierId").isJsonNull() ? "" : jb.get("supplierId").getAsString());
                        detailStatement.setString(6, jb.get("deleted").isJsonNull() ? "" : jb.get("deleted").getAsString());
                        detailStatement.setString(7, "");
                        detailStatement.setString(8, detailsEasId);
                        //jb.get("code").isJsonNull() ? "" : jb.get("code").getAsString()
                        detailStatement.setString(9, "18");
                        detailStatement.setString(10, "1");
                        detailStatement.setString(11, "0");
                        detailStatement.setString(12, TimeUtil.getCurrentTimeString().substring(0, 10));
                        detailStatement.setString(13, TimeUtil.getCurrentTimeString().substring(11));
                        detailStatement.executeUpdate();

                        if (i == 0) {
                            RecordSet detailRecordSet = new RecordSet();
                            detailRecordSet.executeSql("select IDENT_CURRENT('uf_gysyhb') id");
                            if (detailRecordSet.next()) {
                                this.baseBean.writeLog("detailRecordSet.next() 执行=============");
                                detailMaxId = detailRecordSet.getInt("id");
                            }

                            ++i;
                        }

                        this.baseBean.writeLog("当前id： " + detailMaxId);
                        ++detailMaxId;
                        detailModeRightInfo.editModeDataShare(1, 18, detailMaxId);
                        returnObject.addProperty("success", "true");
                        returnObject.addProperty("code", "200");
                        returnObject.addProperty("message", "插入供应商1 - 银行N信息成功");
                    } else {//更新
                        detailStatement.setStatementSql(detailsUpdateSql);
                        detailStatement.setString(1, jb.get("id").isJsonNull() ? "" : jb.get("id").getAsString());
                        detailStatement.setString(2, jb.get("account").isJsonNull() ? "" : jb.get("account").getAsString());
                        detailStatement.setString(3, jb.get("bank").isJsonNull() ? "" : jb.get("bank").getAsString());
                        detailStatement.setString(4, jb.get("supplierId").isJsonNull() ? "" : jb.get("supplierId").getAsString());
                        detailStatement.setString(5, jb.get("deleted").isJsonNull() ? "" : jb.get("deleted").getAsString());
                        detailStatement.setString(6, "");
                        detailStatement.setString(7, jb.get("name").isJsonNull() ? "" : jb.get("name").getAsString());
                        detailStatement.setString(8, detailsEasId);
                        detailStatement.executeUpdate();
                        returnObject.addProperty("success", "true");
                        returnObject.addProperty("code", "200");
                        returnObject.addProperty("message", "更新供应商1 - 银行N信息成功!");
                    }

                }
            }
        } catch (Exception var27) {
            var27.printStackTrace();
            this.baseBean.writeLog("插入供应商1-银行N信息出错-> " + var27);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新供应商1 - 银行N信息异常");
        } finally {
            mainStatement.close();
            detailStatement.close();
        }

        return returnObject.toString();
    }

    public String easAccount(String json) {
        this.baseBean.writeLog("科目表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String assistant_id = object.get("assistant_id").isJsonNull() ? "" : object.get("assistant_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codeAccount = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();
        this.baseBean.writeLog("是不是------>" + codeAccount);

        try {
            String insertSql = "INSERT INTO uf_kmb (my_id, name, assistant_id,deleted, code, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime ) VALUES (?,?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_kmb SET name = ?, assistant_id = ?, deleted = ?, code = ? WHERE code = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_kmb where code collate Chinese_PRC_CS_AS = '" + codeAccount + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, assistant_id);
                statement.setString(4, deleted);
                statement.setString(5, codeAccount);
                statement.setString(6, "19");
                statement.setString(7, "1");
                statement.setString(8, "0");
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(0, 10));
                this.baseBean.writeLog("时间----->" + TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(10, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
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
                ModeRightInfo.editModeDataShare(1, 19, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, assistant_id);
                statement.setString(3, deleted);
                statement.setString(4, codeAccount);
                statement.setString(5, codeAccount);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新科目信息成功!");
            }
        } catch (Exception var19) {
            var19.printStackTrace();
            this.baseBean.writeLog("插入科目信息出错-> " + var19);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新科目信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easExpenseCategory(String json) {
        this.baseBean.writeLog("业务类别表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String eas_id = object.get("eas_id").isJsonNull() ? "" : object.get("eas_id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codeExpenseCategory = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String insertSql = "INSERT INTO uf_ywlbb (my_id, eas_id, name, deleted, code ,formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_ywlbb SET name = ?, deleted = ? , code= ? WHERE eas_id collate Chinese_PRC_CS_AS = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_ywlbb where eas_id collate Chinese_PRC_CS_AS = '" + eas_id + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, eas_id);
                statement.setString(3, name);
                statement.setString(4, deleted);
                statement.setString(5, codeExpenseCategory);
                statement.setString(6, "20");
                statement.setString(7, "1");
                statement.setString(8, "0");
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(10, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入业务类别信息成功!");
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_ywlbb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 20, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, codeExpenseCategory);
                statement.setString(4, eas_id);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新业务类别信息成功!");
            }
        } catch (Exception var19) {
            var19.printStackTrace();
            this.baseBean.writeLog("插入业务类别出错-> " + var19);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新业务类别信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easExpenseType(String json) {
        this.baseBean.writeLog("费用类型表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String category_id = object.get("category_id").isJsonNull() ? "" : object.get("category_id").getAsString();
        String parent_id = object.get("parent_id").isJsonNull() ? "" : object.get("parent_id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        this.baseBean.writeLog("接收到的deleted--->" + deleted);
        String codeExpenseType = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();
        //新增
        String isleaf = object.get("isleaf").isJsonNull() ? "" : object.get("isleaf").getAsString();

        try {
            String insertSql = "INSERT INTO uf_fylxb (my_id, name, category_id, code ,parent_id, deleted, isleaf, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?,?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_fylxb SET name = ?, category_id = ?, parent_id = ?, my_id = ? ,deleted = ? ,isleaf = ? WHERE code = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_fylxb where code collate Chinese_PRC_CS_AS = '" + codeExpenseType + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, category_id);
                statement.setString(4, codeExpenseType);
                statement.setString(5, parent_id);
                statement.setString(6, deleted);
                //新增
                statement.setString(7, isleaf);
                statement.setString(8, "21");
                statement.setString(9, "1");
                statement.setString(10, "0");
                statement.setString(11, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(12, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_fylxb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入费用类型信息成功!");
                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 21, maxId);
            } else {
                statement.setStatementSql(updateSql);
                this.baseBean.writeLog("更新语句---》" + updateSql);
                statement.setString(1, name);
                statement.setString(2, category_id);
                statement.setString(3, parent_id);
                statement.setString(4, id);
                statement.setString(5, deleted);
                statement.setString(6, isleaf);
                statement.setString(7, codeExpenseType);
                this.baseBean.writeLog("my_id--->" + id);
                this.baseBean.writeLog("查看deleted-->" + deleted);
                int sun = statement.executeUpdate();
                this.baseBean.writeLog("执行----》" + sun);
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新费用类型信息成功!");//out/production/YiLi/com/weavernorth/financeBaseInfo/FinanceBaseImpl.class
            }
        } catch (Exception var20) {
            var20.printStackTrace();
            this.baseBean.writeLog("插入费用类型出错-> " + var20);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新费用类型信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easPayType(String json) {
        this.baseBean.writeLog("支付方式表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codePayType = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String insertSql = "INSERT INTO uf_zffsb (my_id, name, code, deleted, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_zffsb SET name = ?, deleted = ? ,code = ? WHERE code = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_zffsb where code = '" + codePayType + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, codePayType);
                statement.setString(4, deleted);
                statement.setString(5, "22");
                statement.setString(6, "1");
                statement.setString(7, "0");
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入支付方式信息成功!");
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_zffsb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 22, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, codePayType);
                statement.setString(4, codePayType);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新支付方式信息成功!");
            }
        } catch (Exception var18) {
            var18.printStackTrace();
            this.baseBean.writeLog("插入支付方式出错-> " + var18);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新支付方式信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easAssistant(String json) {
        this.baseBean.writeLog("核算项目类别表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String type = object.get("type").isJsonNull() ? "" : object.get("type").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codeAssistant = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String insertSql = "INSERT INTO uf_hsxmlb (my_id, name, type, deleted, code, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_hsxmlb SET name = ?, type = ?, deleted = ? ,code = ? WHERE code = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_hsxmlb where code = '" + codeAssistant + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, type);
                statement.setString(4, deleted);
                statement.setString(5, codeAssistant);
                statement.setString(6, "23");
                statement.setString(7, "1");
                statement.setString(8, "0");
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(10, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入核算项目信息成功!");
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_hsxmlb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 23, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, type);
                statement.setString(3, deleted);
                statement.setString(4, codeAssistant);
                statement.setString(5, codeAssistant);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新核算项目信息成功!");
            }
        } catch (Exception var19) {
            var19.printStackTrace();
            this.baseBean.writeLog("插入核算项目类别出错-> " + var19);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新核算项目信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easProduct(String json) {
        this.baseBean.writeLog("产品档案表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codeProduct = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String insertSql = "INSERT INTO uf_cpda (my_id, name, deleted, code ,formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_cpda SET name = ?, deleted = ? ,code= ? WHERE code = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_cpda where code = '" + codeProduct + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, deleted);
                statement.setString(4, codeProduct);
                statement.setString(5, "24");
                statement.setString(6, "1");
                statement.setString(7, "0");
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入产品档案信息成功!");
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_cpda");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 24, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, codeProduct);
                statement.setString(4, codeProduct);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新产品档案信息成功!");
            }
        } catch (Exception var18) {
            var18.printStackTrace();
            this.baseBean.writeLog("插入产品档案出错-> " + var18);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新产品档案信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easReceiptsType(String json) {
        this.baseBean.writeLog("收付类别表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codeReceiptsType = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String insertSql = "INSERT INTO uf_sflb (my_id, name, deleted, code, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_sflb SET name = ?, deleted = ? ,code = ? WHERE code = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_sflb where code = '" + codeReceiptsType + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, deleted);
                statement.setString(4, codeReceiptsType);
                statement.setString(5, "25");
                statement.setString(6, "1");
                statement.setString(7, "0");
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入收付类别信息成功!");
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_sflb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 25, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, codeReceiptsType);
                statement.setString(4, codeReceiptsType);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新收付类别信息成功!");
            }
        } catch (Exception var18) {
            var18.printStackTrace();
            this.baseBean.writeLog("插入收付类别出错-> " + var18);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新收付类别信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easTaxRate(String json) {
        this.baseBean.writeLog("税目税率表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codeTaxRate = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String insertSql = "INSERT INTO uf_smslb (my_id, name, deleted, code, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_smslb SET name = ?, deleted = ? ,code = ? WHERE code = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_smslb where code = '" + codeTaxRate + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, deleted);
                statement.setString(4, codeTaxRate);
                statement.setString(5, "26");
                statement.setString(6, "1");
                statement.setString(7, "0");
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入税目税率信息成功!");
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_smslb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 26, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, codeTaxRate);
                statement.setString(4, codeTaxRate);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新税目税率信息成功!");
            }
        } catch (Exception var18) {
            var18.printStackTrace();
            this.baseBean.writeLog("插入税目税率表出错-> " + var18);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新税目税率信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    public String easTaxCount(String json) {
        this.baseBean.writeLog("计税方法表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String codeTaxCount = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();

        try {
            String insertSql = "INSERT INTO uf_jsffb (my_id, name, deleted,code, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_jsffb SET name = ?, deleted = ? ,code = ? WHERE code = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_jsffb where code = '" + codeTaxCount + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, deleted);
                statement.setString(4, codeTaxCount);
                statement.setString(5, "27");
                statement.setString(6, "1");
                statement.setString(7, "0");
                statement.setString(8, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(9, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入计税方法信息成功!");
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_jsffb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 27, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, name);
                statement.setString(2, deleted);
                statement.setString(3, codeTaxCount);
                statement.setString(4, codeTaxCount);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新计税方法信息成功");
            }
        } catch (Exception var19) {
            var19.printStackTrace();
            this.baseBean.writeLog("插计税方法表出错-> " + var19);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新计税方法信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }

    //新增项目核算明细表
    public String easAssistantDetail(String json) {
        this.baseBean.writeLog("核算项目类别明细表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonObject object = (new JsonParser()).parse(json).getAsJsonObject();
        String type = object.get("assistant").isJsonNull() ? "" : object.get("assistant").getAsString();
        String easId = object.get("easId").isJsonNull() ? "" : object.get("easId").getAsString();
        String codeAssistantDetail = object.get("code").isJsonNull() ? "" : object.get("code").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String leaf = object.get("leaf").isJsonNull() ? "" : object.get("leaf").getAsString();
        //新增
        String longnumber = object.get("longnumber").isJsonNull() ? "" : object.get("longnumber").getAsString();
        try {
            String insertSql = "INSERT INTO uf_hsxmlbmxb (eas_id, name, type, deleted, code, leaf, longnumber, formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) VALUES (?,?,?,?,?,?, ?,?,?,?,?,?)";
            String updateSql = "UPDATE uf_hsxmlbmxb SET eas_id =? ,name = ?, type = ?, deleted = ? ,code = ? ,leaf= ? ,longnumber =? WHERE eas_id collate Chinese_PRC_CS_AS = ?";
            RecordSet existSet = new RecordSet();
            existSet.execute("select * from uf_hsxmlbmxb where eas_id collate Chinese_PRC_CS_AS = '" + easId + "'");
            if (!existSet.next()) {
                statement.setStatementSql(insertSql);
                statement.setString(1, easId);
                statement.setString(2, name);
                statement.setString(3, type);
                statement.setString(4, deleted);
                statement.setString(5, codeAssistantDetail);
                statement.setString(6, leaf);
                statement.setString(7, longnumber);
                statement.setString(8, "59");
                statement.setString(9, "1");
                statement.setString(10, "0");
                statement.setString(11, TimeUtil.getCurrentTimeString().substring(0, 10));
                statement.setString(12, TimeUtil.getCurrentTimeString().substring(11));
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "插入核算项目信息成功");
                RecordSet recordSet = new RecordSet();
                recordSet.executeSql("select max(id) id from uf_hsxmlbmxb");
                int maxId = 0;
                if (recordSet.next()) {
                    maxId = recordSet.getInt("id");
                }

                ModeRightInfo ModeRightInfo = new ModeRightInfo();
                ModeRightInfo.setNewRight(true);
                ModeRightInfo.editModeDataShare(1, 23, maxId);
            } else {
                statement.setStatementSql(updateSql);
                statement.setString(1, easId);
                statement.setString(2, name);
                statement.setString(3, type);
                statement.setString(4, deleted);
                statement.setString(5, codeAssistantDetail);
                statement.setString(6, leaf);
                statement.setString(7, longnumber);
                statement.setString(8, easId);
                statement.executeUpdate();
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "更新核算项目明细信息成功");
            }
        } catch (Exception var19) {
            var19.printStackTrace();
            this.baseBean.writeLog("插入核算项目类别明细出错-> " + var19);
            returnObject.addProperty("success", "false");
            returnObject.addProperty("code", "201");
            returnObject.addProperty("message", "插入或更新核算项目信息异常");
        } finally {
            statement.close();
        }

        return returnObject.toString();
    }
}
