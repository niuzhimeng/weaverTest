package com.weavernorth.ddyy;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weaver.general.Util;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;
/**
* @Description:    OA资产变动单/差异调整单
* @Author:         chchq
* @CreateDate:     2019/4/24 9:50
* @Version:        1.0
*/
public class AssetsUpdateAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        //查询数据结果集
        RecordSet rs = new RecordSet();
        //查询code
        RecordSet code = new RecordSet();
        String requestid = Util.null2String(requestInfo.getRequestid());
        String workflowid = Util.null2String(requestInfo.getWorkflowid());
        String tablename = Util.null2String(requestInfo.getRequestManager().getBillTableName());
        String src = requestInfo.getRequestManager().getSrc();
        //获取主表id
        String id = "";
        //获取主表单号
        String dh = "";
        //创建json数据
        JsonObject json = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        try {
            this.writeLog("----->>>>>进入Action  requestId="+requestid+"  workflowid="+workflowid+"  tablename="+tablename+"  时间="+System.currentTimeMillis());
            rs.execute(" select * from "+tablename+" where requestId = " + requestid);
            if(rs.next()){
                id = Util.null2String(rs.getString("id"));
                dh = Util.null2String(rs.getString("dh"));
            }
            json.addProperty("number",dh);
            if("" != id){
                rs.execute(" select a.zcbm,b.jdbm,a.cfwz,a.bdhzcwz,a.zcxz,a.bdhzcxz,a.zczt,a.bdhzczt,a.zcsl,a.bdhzcsl,a.syr,a.bdhsyr,a.sybm,a.bdhsybm " +
                        "from (select * from "+tablename+"_dt1 " +
                        "where mainid = "+id+") a " +
                        "left join uf_zcmk b " +
                        "on a.zcbm = b.zcbh ");
                while(rs.next()){
                    //对比资产变动的前后值
                    JsonObject jsonr = new JsonObject();
                    //金蝶编码
                    jsonr.addProperty("cardNo",Util.null2String(rs.getString("jdbm")));
                    //资产存放位置
                    if(!Util.null2String(rs.getString("cfwz")).equals(Util.null2String(rs.getString("bdhzcwz")))){
                        code.execute(" select wzmc from uf_zccfwz where id = " + Util.null2String(rs.getString("bdhzcwz")));
                        if(code.next()){
                            jsonr.addProperty("position",Util.null2String(code.getString("wzmc")));
                        }
                    }
                    //资产性质
                    if(!Util.null2String(rs.getString("zcxz")).equals(Util.null2String(rs.getString("bdhzcxz")))){
                        jsonr.addProperty("type",Util.null2String(rs.getString("bdhzcxz")));
                    }
                    //资产状态
                    if(!Util.null2String(rs.getString("zczt")).equals(Util.null2String(rs.getString("bdhzczt")))){
                        code.execute(" select zczt from uf_zczt where id = " + Util.null2String(rs.getString("bdhzczt")));
                        if(code.next()){
                            jsonr.addProperty("status",Util.null2String(code.getString("zczt")));
                        }
                    }
                    //资产数量
                    if(!Util.null2String(rs.getString("zcsl")).equals(Util.null2String(rs.getString("bdhzcsl")))){
                        jsonr.addProperty("qty",Util.null2String(rs.getString("bdhzcsl")));
                    }
                    //使用人
                    if(!Util.null2String(rs.getString("syr")).equals(Util.null2String(rs.getString("bdhsyr")))){
                        code.execute(" select email from HrmResource where id = " + Util.null2String(rs.getString("bdhsyr")));
                        if(code.next()){
                            jsonr.addProperty("user",Util.null2String(code.getString("email")));
                        }
                    }
                    //使用部门
                    if(!Util.null2String(rs.getString("sybm")).equals(Util.null2String(rs.getString("bdhsybm")))){
                        code.execute(" select b.code from ( select * from uf_bmdzb where oabm = " + Util.null2String(rs.getString("bdhsybm")) + " ) a left join uf_zzb b on a.easbm = b.id ");
                        if(code.next()){
                            jsonr.addProperty("userDept",Util.null2String(code.getString("code")));
                        }
                    }
                    //使用公司
                    if(!Util.null2String(rs.getString("sygsdc")).equals(Util.null2String(rs.getString("sygs")))){
                        code.execute(" select code from uf_zzb where id = " + Util.null2String(rs.getString("sygs")));
                        if(code.next()){
                            jsonr.addProperty("userCompany",Util.null2String(code.getString("code")));
                        }
                    }
                    jsonArray.add(jsonr);
                }
                json.addProperty("entry",jsonArray.toString());
                //调用金蝶接口
                //json.toString()
            }
            this.writeLog("<<<<<-----结束Action  requestid="+requestid+"  workflowid="+workflowid+"  tablename="+tablename+"  时间="+System.currentTimeMillis());
        }catch (Exception e){
            //调用异常 返回错误信息
            requestInfo.getRequestManager().setMessageid("11111"+requestid+"55555");
            requestInfo.getRequestManager().setMessagecontent("异常："+e.getMessage());
            writeLog("<<<<<--错误-->>>>>"+e.toString());
            return "0";
        }
        return "1";
    }
}
