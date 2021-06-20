package org.bkcloud.fleet.workflow.process.handler.loanGranting;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * handler for requestRejecter service task
 */
@Component
@ExternalTaskSubscription("requestRejecter")
public class RequestRejecterHandler implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        String customID = externalTask.getVariable("customID");
        int creditScore = externalTask.getVariable("creditScore");

        externalTaskService.complete(externalTask);

        String msg = MessageFormat.format("request rejected for custom {0} with score {1}", customID, creditScore);
        System.out.println(msg);
    }
}
