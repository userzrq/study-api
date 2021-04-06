package com.userzrq.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Configuration
//@Component
//@EnableScheduling
public class ScheduleRefreshCron {

//    @Resource(name = "marketingActivityJobDetail")
//    private JobDetail jobDetail;
//
//    @Resource(name = "marketingActivityJobTrigger")
//    private CronTrigger cronTrigger;
//
//    @Resource(name = "marketingActivityScheduler")
//    private Scheduler scheduler;

//    @Scheduled(fixedRate = 5000) // 每隔5s查库，并根据查询结果决定是否重新设置定时任务
//    @Scheduled(cron = "0 0 1 * * ?")
//    public void scheduleUpdateCronTrigger()  {
//        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
//        String currentCron = trigger.getCronExpression();// 当前Trigger使用的
//        String searchCron = "0 41 10 ? * *";// 从数据库查询出来的
//        System.out.println(currentCron);
//        System.out.println(searchCron);
//
//        // 如果当前使用的cron表达式和从数据库中查询出来的cron表达式一致，则不刷新任务
//        if (!currentCron.equals(searchCron)) {
//            MarketingActivitiesVO marketingActivitiesVO = new MarketingActivitiesVO();
//            marketingActivitiesVO.setId(DSHUtils.generateUUID());
//
//            // 表达式调度构建器
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
//            // 按新的cronExpression表达式重新构建trigger
//            trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
//            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey())
//                    .withSchedule(scheduleBuilder).build();
//            // 按新的trigger重新设置job执行
//            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
//            currentCron = searchCron;
//        }
//    }
}
