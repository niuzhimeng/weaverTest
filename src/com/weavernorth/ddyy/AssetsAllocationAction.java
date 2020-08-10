package com.weavernorth.ddyy;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weaver.general.Util;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
* @Description:    OA资产调拨单
* @Author:         chchq
* @CreateDate:     2019/4/24 9:51
* @Version:        1.0
*/
public class AssetsAllocationAction extends BaseAction {

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
        //获取主表调拨单号
        String dbdh = "";
        //获取主表申请调拨日期
        String rq = "";
        //调出公司
        String dcdwrzgszy = "";
        //调出负责人
        String sqr = "";
        //调入公司
        String drdwrzgszy = "";
        //调入负责人
        String drdwfzr = "";
        //创建json数据
        JsonObject json = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        try {
            this.writeLog("----->>>>>进入Action  requestId="+requestid+"  workflowid="+workflowid+"  tablename="+tablename+"  时间="+System.currentTimeMillis());
            rs.execute(" select * from "+tablename+" where requestId = " + requestid);
            if(rs.next()){
                id = Util.null2String(rs.getString("id"));
                dbdh = Util.null2String(rs.getString("dbdh"));
                rq = Util.null2String(rs.getString("rq"));
                dcdwrzgszy = Util.null2String(rs.getString("dcdwrzgszy"));
                sqr = Util.null2String(rs.getString("sqr"));
                drdwrzgszy = Util.null2String(rs.getString("drdwrzgszy"));
                drdwfzr = Util.null2String(rs.getString("drdwfzr"));
            }
            json.addProperty("number",dbdh);
            json.addProperty("bizdate",rq);
            json.addProperty("companyOut",dcdwrzgszy);
            //调出公司负责人转成邮箱
            if(!"".equals(sqr)){
                code.execute(" select email from HrmResource where id = " + sqr);
                if(code.next()){
                    json.addProperty("personOut",Util.null2String(code.getString("email")));
                }
            }
            json.addProperty("companyIn",drdwrzgszy);
            //调入公司负责人转成邮箱
            if(!"".equals(drdwfzr)){
                code.execute(" select email from HrmResource where id = " + drdwfzr);
                if(code.next()){
                    json.addProperty("personIn",Util.null2String(code.getString("email")));
                }
            }
            if(!"".equals(id)){
                rs.execute(" select b.jdbm,a.* from (select * from "+tablename+"_dt1 where mainid = "+id+" ) a left join uf_zcmk b on a.zcbm = b.zcbh ");
                while(rs.next()){
                    JsonObject jsonr = new JsonObject();
                    //金蝶编码
                    jsonr.addProperty("cardNo",Util.null2String(rs.getString("jdbm")));
                    //调拨原因
                    jsonr.addProperty("reason",Util.null2String(rs.getString("dbyy")));
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
