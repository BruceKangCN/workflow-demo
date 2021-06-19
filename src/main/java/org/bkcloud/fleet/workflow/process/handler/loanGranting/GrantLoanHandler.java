package org.bkcloud.fleet.workflow.process.handler.loanGranting;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@ExternalTaskSubscription("loanGranter")
public class GrantLoanHandler implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        String customID = externalTask.getVariable("customID");
        int creditScore = externalTask.getVariable("creditScore");

        externalTaskService.complete(externalTask);

        String msg = "loan granted.";
        Logger.getLogger("LoanGranter").warning(msg);
    }
}
