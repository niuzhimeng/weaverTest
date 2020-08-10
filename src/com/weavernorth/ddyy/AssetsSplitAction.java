package com.weavernorth.ddyy;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weaver.general.Util;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
* @Description:    OA数量拆分单
* @Author:         chchq
* @CreateDate:     2019/4/24 9:51
* @Version:        1.0
*/
public class AssetsSplitAction extends BaseAction {

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
        String dh = "";
        //获取主表申请拆分日期
        String rq = "";
        //获取主表拆分人
        String sqr = "";
        //获取主表资产的金蝶编码
        String jdbm = "";
        //创建json数据
        JsonObject json = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        try {
            this.writeLog("----->>>>>进入Action  requestId="+requestid+"  workflowid="+workflowid+"  tablename="+tablename+"  时间="+System.currentTimeMillis());
            rs.execute(" select a.*,b.jdbm from "+tablename+" a left join uf_zcmk b on a.zcbh = b.zcbh where a.requestId = " + requestid);
            if(rs.next()){
                id = Util.null2String(rs.getString("id"));
                dh = Util.null2String(rs.getString("dh"));
                rq = Util.null2String(rs.getString("sqrq"));
                sqr = Util.null2String(rs.getString("sqr"));
                jdbm = Util.null2String(rs.getString("jdbm"));
            }
            json.addProperty("number",dh);
            json.addProperty("bizdate",rq);
            if(!"".equals(sqr)){
                code.execute(" select email from HrmResource where id = " + sqr);
                if(code.next()){
                    json.addProperty("person",Util.null2String(code.getString("email")));
                }
            }
            json.addProperty("cardNo",jdbm);
            if(!"".equals(id)){
                rs.execute(" select * from " + tablename + "_dt1 where mainid = " + id );
                while(rs.next()){
                    JsonObject jsonr = new JsonObject();
                    //拆分成的数量
                    jsonr.addProperty("qty",Util.null2String(rs.getString("cfhsl")));
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
