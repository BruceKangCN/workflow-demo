package org.bkcloud.fleet.workflow.process;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoanApproval {

    @Autowired
    private RuntimeService runtimeService; // camunda runtime service

    /**
     * auto start the process instance after deployment
     * the key name is pre-defined in bpmn file
     *
     * @param event
     */
    @EventListener
    public void processPostDeploy(PostDeployEvent event) {
        runtimeService.startProcessInstanceByKey("loanApproval");
    }
}
