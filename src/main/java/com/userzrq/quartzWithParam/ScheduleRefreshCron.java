package com.userzrq.quartzWithParam;

import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Configuration
//@EnableScheduling
//@Component
public class ScheduleRefreshCron {

    private static Logger logger = LoggerFactory.getLogger(ScheduleRefreshCron.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

//    @Scheduled(fixedRate = 5000) // 每隔5s查库
//    public void scheduleUpdateCronTrigger() throws FailException, SchedulerException {
//        try{
//            logger.info("----- scheduling job --------");
//
//            String searchCron = "*/5 * * * * ?";// 从数据库查询出来的
//
//            // 获取一个调度器
////            SchedulerFactory factory = new StdSchedulerFactory();
////            Scheduler scheduler = factory.getScheduler();
//            Scheduler scheduler = schedulerFactoryBean.getScheduler();
//
//            // 创建一项作业
//            JobDetail job = JobBuilder.newJob(ScheduleTask.class)
//                    .withIdentity("marketingActivityJob", "marketingActivityGroup")
//                    .build();
//
//            // 设置参数
//            MarketingActivitiesVO marketingActivitiesVO = new MarketingActivitiesVO();
//            marketingActivitiesVO.setId(DSHUtils.generateUUID());
//            job.getJobDataMap().put("marketingActivitiesVO", marketingActivitiesVO);
//
//            // 作业的执行时间(当前时间的下一分钟)
//            Date runTime = DateBuilder.evenMinuteDate(new Date());
//
//            // 创建一个触发器
//            CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger()
//                    .withIdentity("marketingActivityTrigger", "marketingActivityGroup")
//                    .startAt(runTime)  //该触发器将在runTime时执行作业
//                    .endAt(new Date(System.currentTimeMillis()+5*1000+60*1000)) //该触发器将在runTime时结束作业
//                    .withSchedule(CronScheduleBuilder.cronSchedule(searchCron))  // 具体执行时间
//                    .build();
//
//            // 告诉调度器使用该触发器来安排作业
//            scheduler.scheduleJob(job, trigger);
//            // 启动调度器
//            scheduler.start();
//            logger.info("------ started scheduler -------");
//
//            logger.info("------ waiting 2 minutes ------");
//            Thread.sleep(2*60*1000);
//
//            logger.info("------- shutting down ------");
//            // 关闭调度器
//            scheduler.shutdown(true);
//            logger.info("------- shutdown complete -------");
//        }catch(Exception e){
//            logger.error(DSHUtils.formatException(e));
//            throw new FailException(e.getMessage());
//        }
//    }
}
