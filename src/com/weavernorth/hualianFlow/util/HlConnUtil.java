package com.weavernorth.hualianFlow.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSet;
import weaver.general.MD5;
import weaver.general.Util;

public class HlConnUtil {

    private static Log log = LogFactory.getLog(HlConnUtil.class);

    /**
     * 校验接口调用权限
     *
     * @param authorization 请求头中的json
     * @param flowKey       接口标识
     */
    public static JSONObject apiCheck(String authorization, String flowKey) {
        try {
            JSONObject authorizationJson = JSONObject.parseObject(authorization);
            String workCode = authorizationJson.getString("workCode");
            String currentTime = authorizationJson.getString("currentTime");
            String token = authorizationJson.getString("token");

            // 时间验证
            long currentTimeMillis = System.currentTimeMillis();
            long longValue = getLongValue(currentTime);
            long result = currentTimeMillis - longValue;
            long min = result / 1000 / 60;
            if (min > 5) {
                return createReturnObj("1", "认证失败-token已过期");
            }

            // 加密方式验证
            String md5ofStr = new MD5().getMD5ofStr(currentTime + flowKey + workCode);
            log.info("oa加密后的token：" + md5ofStr);
            if (!md5ofStr.equals(token)) {
                return createReturnObj("1", "认证失败-token错误");
            }

            // 创建人验证
            RecordSet recordSet = new RecordSet();
            workCode = StringUtils.isBlank(workCode) ? "error" : workCode;
            recordSet.executeQuery("select id from hrmresource where workcode = ?", workCode);
            if (!recordSet.next()) {
                return createReturnObj("1", "创建人工号不存在");
            }
        } catch (Exception e) {
            return createReturnObj("1", "认证失败-解析authorization异常");

        }
        return createReturnObj("0", "认证通过");
    }

    private static long getLongValue(String time) {
        try {
            return Long.parseLong(time);
        } catch (Exception e) {
            return -1;
        }
    }

    public static JSONObject createReturnObj(String status, String message) {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("status", status);
        jsonObject.put("message", message);
        return jsonObject;
    }

    public static String getFlowMes(String returnStr) {
        String errStr = "";
        int returnInt = Util.getIntValue(returnStr);
        if (returnInt > 0) {
            errStr = "流程创建成功";
        } else if (returnInt == -1) {
            errStr = "流程创建失败";
        } else if (returnInt == -2) {
            errStr = "没有创建权限";
        } else if (returnInt == -3) {
            errStr = "流程创建失败";
        } else if (returnInt == -4) {
            errStr = "字段或表名不正确";
        } else if (returnInt == -5) {
            errStr = "更新流程级别失败";
        } else if (returnInt == -6) {
            errStr = "无法创建流程待办任务";
        } else if (returnInt == -7) {
            errStr = "流程下一节点出错，请检查流程的配置，在OA中发起流程进行测试";
        } else if (returnInt == -8) {
            errStr = "流程节点自动赋值操作错误";
        }
        return errStr;
    }
}
