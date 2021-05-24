package org.bkcloud.fleet.workflow.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * a job class which will be triggered by quartz scheduler
 */
public class Greet implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hello world!");
    }
}
