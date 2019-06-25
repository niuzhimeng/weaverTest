package com.weavernorth.zhouji;

import com.weavernorth.zhouji.util.ZjCommUtil;
import com.weavernorth.zhouji.vo.ProjectVo;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class ProjectAmountCollection extends BaseAction {

    private String dtName;

    /**
     * 模块id
     */
    private static final Integer ONE_MODE_ID = 8;
    private static final Integer TWO_MODE_ID = 9;
    private static final Integer THREE_MODE_ID = 10;

    @Override
    public String execute(RequestInfo requestInfo) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("费用归集action Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表id
            recordSet.executeQuery("select id from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            int mainId = recordSet.getInt("id");
            recordSet.executeQuery("select * from " + tableName + dtName + " where mainid = " + mainId);
            while (recordSet.next()) {
                // 核算主体（项目编码）
                String hszt = recordSet.getString("hszt").substring(2);
                // 根据编码查询全部信息
                ProjectVo projectVo = getInfoByNo(hszt);

                // 归集科目编码
                String xmkm = recordSet.getString("xmkm");
                // 归集科目名称
                String kmmc = getNameByCode(xmkm);
                // 金额
                String je = recordSet.getString("je");

                projectVo.setKmbm(xmkm);
                projectVo.setKmmc(kmmc);
                projectVo.setJe(je);
                this.writeLog("页面数据： " + projectVo.toString());

                // 项目层级
                int xmcj = projectVo.getXmcj();
                if (xmcj == 1) {
                    projectVo.setModeId(String.valueOf(ONE_MODE_ID));
                    projectVo.setTableName("uf_xm_one");
                    ZjCommUtil.oneProject(projectVo);
                } else if (xmcj == 2) {
                    projectVo.setModeId(String.valueOf(TWO_MODE_ID));
                    projectVo.setTableName("uf_xm_two");
                    ZjCommUtil.twoProject();
                } else if (xmcj == 3) {
                    projectVo.setModeId(String.valueOf(THREE_MODE_ID));
                    projectVo.setTableName("uf_xm_three");
                    ZjCommUtil.threeProject();
                }
            }
            this.writeLog("三张表授权===========");
            ZjCommUtil.rebuildModeDataShare("uf_xm_one", currentTimeString, ONE_MODE_ID);
            ZjCommUtil.rebuildModeDataShare("uf_xm_two", currentTimeString, TWO_MODE_ID);
            ZjCommUtil.rebuildModeDataShare("uf_xm_three", currentTimeString, THREE_MODE_ID);
            this.writeLog("费用归集action End");
        } catch (Exception e) {
            this.writeLog("ProjectAmountCollection 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("费用报销 异常： " + e);
            return "0";
        }
        return "1";
    }

    /**
     * 根据项目编码获取项目全部信息
     */
    private ProjectVo getInfoByNo(String code) {
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from uf_project where xmbh = '" + code + "'");
        recordSet.next();

        ProjectVo projectVo = new ProjectVo();
        projectVo.setXmbm(code);
        projectVo.setXmmc(recordSet.getString("xmmc"));
        projectVo.setHth(recordSet.getString("cwhsdy"));
        projectVo.setXmcj(recordSet.getInt("xmcj"));
        return projectVo;
    }

    /**
     * 根据编码获取科目名称
     */
    private String getNameByCode(String code) {
        String kmmc;
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select xmmc from uf_xmkm where kmbm = '" + code + "'");
        recordSet.next();
        kmmc = recordSet.getString("xmmc");

        return kmmc;
    }

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName;
    }
}
