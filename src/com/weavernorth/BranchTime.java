package com.weavernorth;

import weaver.general.BaseBean;
import weaver.integration.logging.Logger;
import weaver.integration.logging.LoggerFactory;
import weaver.interfaces.schedule.BaseCronJob;

public class BranchTime extends BaseCronJob {
    public BranchTime() {
    }

    private Logger logger = LoggerFactory.getLogger();

    @Override
    public void execute() {
        new BaseBean().writeLog("定时执行2018-08-27==========");
    }
}
