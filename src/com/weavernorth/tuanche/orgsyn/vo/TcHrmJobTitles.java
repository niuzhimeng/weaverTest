package com.weavernorth.tuanche.orgsyn.vo;

import com.google.gson.annotations.SerializedName;
import weaver.general.BaseBean;

/**
 * 岗位
 */
public class TcHrmJobTitles extends BaseBean {

    private String id;
    @SerializedName("jobTitleCode")
    private String jobtitlecode;
    @SerializedName("jobTitleName")
    private String jobtitlename;
    private String jobtitlemark;
    private String jobactivityid;
    private String jobactivitiecode;
    private String jobdepartmentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobtitlename() {
        return jobtitlename;
    }

    public void setJobtitlename(String jobtitlename) {
        this.jobtitlename = jobtitlename;
    }

    public String getJobtitlemark() {
        return jobtitlemark;
    }

    public void setJobtitlemark(String jobtitlemark) {
        this.jobtitlemark = jobtitlemark;
    }

    public String getJobactivityid() {
        return jobactivityid;
    }

    public void setJobactivityid(String jobactivityid) {
        this.jobactivityid = jobactivityid;
    }

    public String getJobdepartmentid() {
        return jobdepartmentid;
    }

    public void setJobdepartmentid(String jobdepartmentid) {
        this.jobdepartmentid = jobdepartmentid;
    }

    public String getJobtitlecode() {
        return jobtitlecode;
    }

    public void setJobtitlecode(String jobtitlecode) {
        this.jobtitlecode = jobtitlecode;
    }


    public String getJobactivitiecode() {
        return jobactivitiecode;
    }

    public void setJobactivitiecode(String jobactivitiecode) {
        this.jobactivitiecode = jobactivitiecode;
    }

}
