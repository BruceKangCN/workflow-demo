package org.bkcloud.fleet.workflow.service.impl;

import org.bkcloud.fleet.workflow.process.RunScriptProcess;
import org.bkcloud.fleet.workflow.service.IProcessService;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.script.ScriptException;
import java.io.IOException;

/**
 * RunScriptProcess service implementation class
 */
@Service
@Qualifier("RunScriptProcessService")
public class RunScriptProcessServiceImpl implements IProcessService {

    @Autowired
    private RunScriptProcess runScriptProcess;

    @Override
    public DeploymentBuilder create(String source) throws ScriptException, IOException {
        return runScriptProcess.create(source);
    }

    @Override
    public void deploy(DeploymentBuilder builder) {
        runScriptProcess.deploy(builder);
    }

    @Override
    public ProcessInstance start() {
        return runScriptProcess.start();
    }
}
