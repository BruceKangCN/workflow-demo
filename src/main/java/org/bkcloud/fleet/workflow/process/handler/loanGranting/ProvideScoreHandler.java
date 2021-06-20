package org.bkcloud.fleet.workflow.process.handler.loanGranting;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * handler for scoreProvider service task
 */
@Component
@ExternalTaskSubscription("scoreProvider")
public class ProvideScoreHandler implements ExternalTaskHandler {
    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        String customID = "C-" + UUID.randomUUID().toString().substring(32);
        int creditScore = (int) (Math.random() * 11);

        // set variables to use later
        VariableMap map = Variables.createVariables();
        map.put("customID", customID);
        map.put("creditScore", creditScore);

        externalTaskService.complete(externalTask, map);

        String msg = MessageFormat.format("Credit score {0} for custom {1} provided", creditScore, customID);
        System.out.println(msg);
    }
}
