package com.weavernorth.ddyy;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weaver.general.Util;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;
/**
* @Description:    OA资产报废单
* @Author:         chchq
* @CreateDate:     2019/4/24 9:51
* @Version:        1.0
*/
public class AssetsScrapAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        //查询数据结果集
        RecordSet rs = new RecordSet();
        //查询部门code
        RecordSet code = new RecordSet();
        String requestid = Util.null2String(requestInfo.getRequestid());
        String workflowid = Util.null2String(requestInfo.getWorkflowid());
        String tablename = Util.null2String(requestInfo.getRequestManager().getBillTableName());
        String src = requestInfo.getRequestManager().getSrc();
        //获取主表id
        String id = "";
        //获取主表报废单号
        String bfdh = "";
        //获取主表清理日期
        String rq = "";
        //创建json数据
        JsonObject json = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        //预计处置方式
        String type = "";
        try {
            this.writeLog("----->>>>>进入Action  requestId="+requestid+"  workflowid="+workflowid+"  tablename="+tablename+"  时间="+System.currentTimeMillis());
            rs.execute(" select * from "+tablename+" where requestId = " + requestid);
            if(rs.next()){
                id = Util.null2String(rs.getString("id"));
                bfdh = Util.null2String(rs.getString("bfdh"));
                rq = Util.null2String(rs.getString("rq"));
            }
            json.addProperty("number",bfdh);
            json.addProperty("bizdate",rq);
            if(!"".equals(id)){
                rs.execute(" select b.jdbm,a.* from (select * from "+tablename+"_dt1 where mainid = "+id+" ) a left join uf_zcmk b on a.zcbm = b.zcbh ");
                while(rs.next()){
                    //对比资产变动的前后值
                    JsonObject jsonr = new JsonObject();
                    //金蝶编码
                    jsonr.addProperty("cardNo",Util.null2String(rs.getString("jdbm")));
                    //预计处置方式
                    if("0".equals(Util.null2String(rs.getString("yjczfs")))){
                        type = "丢弃";
                    }else if("1".equals(Util.null2String(rs.getString("yjczfs")))){
                        type = "零件备用";
                    }else if("2".equals(Util.null2String(rs.getString("yjczfs")))){
                        type = "融资封存";
                    }else if("3".equals(Util.null2String(rs.getString("yjczfs")))){
                        type = "变卖";
                    }
                    jsonr.addProperty("type",type);
                    //管理公司
                    if(!"".equals(Util.null2String(rs.getString("glgs")))){
                        code.execute(" select code from uf_zzb where id = " + Util.null2String(rs.getString("glgs")));
                        if(code.next()){
                            jsonr.addProperty("company",Util.null2String(code.getString("code")));
                        }
                    }
                    //摘要
                    jsonr.addProperty("abstract",Util.null2String(rs.getString("bfyy")));
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
