package weaver.interfaces.hrm;

import weaver.general.BaseBean;

public class HrmSyn extends BaseBean implements HrmSynService {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String SynTimingToOASubCompany() {
        return null;
    }

    @Override
    public String SynTimingToOADepartment() {
        return null;
    }

    @Override
    public String SynTimingToOAJobtitle() {
        return null;
    }

    @Override
    public String SynTimingToOAHrmResource() {
        return null;
    }

    @Override
    public void SynTimingFromOASubCompany(SubCompanyBean[] subCompanyBeans) {

    }

    @Override
    public void SynTimingFromOADepartment(DepartmentBean[] departmentBeans) {

    }

    @Override
    public void SynTimingFromOAJobtitle(JobTitleBean[] jobTitleBeans) {

    }

    @Override
    public void SynTimingFromOAHrmResource(UserBean[] userBeans) {
        baseBean.writeLog("批量执行=================================");
    }

    @Override
    public void SynInstantSubCompany(SubCompanyBean subCompanyBean) {

    }

    @Override
    public void SynInstantDepartment(DepartmentBean departmentBean) {

    }

    @Override
    public void SynInstantJobtitle(JobTitleBean jobTitleBean) {

    }

    @Override
    public void SynInstantHrmResource(UserBean userBean) {
        baseBean.writeLog("========================新建人员");
        baseBean.writeLog(userBean.getAction());
        baseBean.writeLog(userBean.getLastname());
        baseBean.writeLog(userBean.getLoginid());
        baseBean.writeLog(userBean.getDepartmentname());
        baseBean.writeLog(userBean.getBirthday());
        baseBean.writeLog(userBean.getEmail());
        baseBean.writeLog(userBean.getManagerid());
        baseBean.writeLog(userBean.getSex());
    }

    @Override
    public boolean SynSendMessage(String s, String s1, String s2, String s3, String s4) {
        return false;
    }
}
