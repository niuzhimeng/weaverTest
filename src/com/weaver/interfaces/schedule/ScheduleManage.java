package com.weaver.interfaces.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import weaver.general.StaticObj;
import weaver.integration.logging.Logger;
import weaver.integration.logging.LoggerFactory;
import weaver.interfaces.schedule.CronJob;
import weaver.interfaces.schedule.IntervalJob;
import weaver.interfaces.schedule.WeaverJob;


import java.util.List;

/**
 * User: xiaofeng.zhang
 * Date: 2007-10-12
 * Time: 10:05:45
 * To change this template use File | Settings | File Templates.
 */
public class ScheduleManage {
    private static Logger log = LoggerFactory.getLogger(ScheduleManage.class);
    private static Scheduler scheduler;

    private ScheduleManage() {
    }
    private static Scheduler getSchedulerInstance() {
        try {
            if (scheduler == null) {
                synchronized (ScheduleManage.class) {
                    if (scheduler == null) {
                        SchedulerFactory sf = new StdSchedulerFactory();
                        scheduler = sf.getScheduler();
                    }
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error("==============getSchedulerInstance error occured!!!", e);
        }

        return scheduler;
    }

    public static void start(){
        try {
            //SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = getSchedulerInstance();
            log.info("=======================scheduler hashCode:"+scheduler.hashCode());
            log.info("=======================schedule cron job now:");
            //cron job
            List l= StaticObj.getServiceIds(CronJob.class);
            for(int i=0;i<l.size();i++){
                String jobname = (String) l.get(i);
                log.info("================= jobname:"+jobname);
                CronJob job=(CronJob)StaticObj.getServiceByFullname(jobname,CronJob.class);
                JobDetail jobDetail=new JobDetail(jobname,"cron", WeaverJob.class);
                CronTrigger cronTrigger = new CronTrigger("crontrigger_"+i, "tgroup1");
                String cronExpr=job.getCronExpr();
                CronExpression cexp = new CronExpression(cronExpr);
                cronTrigger.setCronExpression(cexp);
                scheduler.scheduleJob(jobDetail, cronTrigger);
            }
            log.info("=======================schedule cron job end:");
            //internal job
            l= StaticObj.getServiceIds(IntervalJob.class);
            log.info("=======================schedule interval job now:");
            for(int i=0;i<l.size();i++){
                IntervalJob job=(IntervalJob)StaticObj.getServiceByFullname((String)l.get(i), IntervalJob.class);
                JobDetail jobDetail=new JobDetail((String)l.get(i),"interval",WeaverJob.class);
                int interval=job.getSecond();
                Trigger internalTrigger =TriggerUtils.makeSecondlyTrigger(interval);
                internalTrigger.setName("intervaltrigger_"+i);
                internalTrigger.setGroup("tgroup2");
                scheduler.scheduleJob(jobDetail, internalTrigger);
            }
            log.info("=======================schedule interval job end:");
            String[] groupnames=scheduler.getJobGroupNames();
            if(groupnames.length>0){
            scheduler.start();
            }
        } catch (Throwable e) {
            log.error(e);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
