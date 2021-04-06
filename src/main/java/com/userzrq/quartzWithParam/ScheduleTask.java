package com.userzrq.quartzWithParam;


import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;



@Component
@Configuration
@EnableScheduling
@DisallowConcurrentExecution    // 禁止并发执行
public class ScheduleTask extends QuartzJobBean {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);


    /**
     *
     * @param context 参数上下文对象,从中接受参数
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("execute activity");
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    }
}
