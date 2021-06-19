package org.bkcloud.fleet.workflow.job;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class LoanGranting implements Job {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("loan_process");
        if (instance == null) {
            throw new JobExecutionException();
        }
    }
}
