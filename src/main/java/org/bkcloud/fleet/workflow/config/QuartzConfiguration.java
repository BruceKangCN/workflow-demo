package org.bkcloud.fleet.workflow.config;

import org.bkcloud.fleet.workflow.job.LoanGranting;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * quartz jobs and schedulers configuration
 */
@Configuration
public class QuartzConfiguration {

    /**
     * job configuration for loan granting
     *
     * @return job detail
     */
    @Bean
    public JobDetail loanGrantingDetail() {
        return JobBuilder.newJob(LoanGranting.class).withIdentity("loan granting job").storeDurably().build();
    }

    /**
     * trigger configuration
     *
     * @param loanGrantingDetail job detail for loan granting
     * @return a scheduled trigger
     */
    @Bean
    public Trigger loanGrantingTrigger(JobDetail loanGrantingDetail) {
        return TriggerBuilder.newTrigger().forJob(loanGrantingDetail).withIdentity("loan granting trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * ? * * *")).build();
    }
}
