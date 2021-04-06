package com.userzrq.quartz.config;

import com.userzrq.quartz.task.ScheduleTask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Quartz 配置类
 */
//@Configuration
public class QuartzConfiguration {

//    /**
//     * 配置定时任务
//     *
//     * @param task 参数为需要被执行的具体任务
//     * @return
//     */
//    @Bean(name = "marketingActivityJobDetail")
//    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task) {
//        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
//        /**
//         * 是否并发执行
//         * 并发执行：任务执行间隔到达就再次执行
//         * 非并发执行：任务间隔到达 且 上次任务执行结束才执行下一次
//         */
//        jobDetail.setConcurrent(false);
//
//        // 设置任务的名字和分组
//        jobDetail.setName("marketing_activity");
//        jobDetail.setGroup("marketing_activity");
//
//        // 确认执行实体类对应的对象 和 对象中的方法
//        jobDetail.setTargetObject(task);
//        jobDetail.setTargetMethod("marketingActivity");
//        return jobDetail;
//    }
//
//    /**
//     * CronJobTrigger 定时任务的触发器
//     *
//     * @param jobDetail
//     * @return
//     */
//    @Bean(name = "marketingActivityJobTrigger")
//    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
//        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
//        trigger.setJobDetail(jobDetail.getObject());
//        trigger.setCronExpression("0 0 1 * * ?");
//        trigger.setName("marketing_activity_trigger");
//        return trigger;
//    }
//
//    @Bean(name = "marketingActivityScheduler")
//    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
//        SchedulerFactoryBean bean = new SchedulerFactoryBean();
//
//        bean.setOverwriteExistingJobs(true);
//        // 延时启动，应用启动1秒后
//        bean.setStartupDelay(1);
//        // 注册触发器
//        bean.setTriggers(cronJobTrigger);
//        return bean;
//    }
//

}
