package org.bkcloud.fleet.workflow.config;

import org.bkcloud.fleet.workflow.job.Greet;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quartz jobs and schedulers configuration
 */
@Configuration
public class QuartzConfiguration {

    /**
     * job configuration
     *
     * @return job detail
     */
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(Greet.class).withIdentity("demo job").storeDurably().build();
    }

    /**
     * trigger configuration
     *
     * @param jobDetail job detail
     * @return a scheduled trigger
     */
    @Bean
    public Trigger jobTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity("demo trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * ? * * *")) // triggered per 10 s, same format with crontab
                .build();
    }
}
