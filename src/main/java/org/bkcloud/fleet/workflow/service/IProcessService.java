package org.bkcloud.fleet.workflow.service;

import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import javax.script.ScriptException;
import java.io.IOException;

/**
 * process service interface
 */
public interface IProcessService {

    DeploymentBuilder create(String source) throws ScriptException, IOException;
    void deploy(DeploymentBuilder builder);
    ProcessInstance start();
}
