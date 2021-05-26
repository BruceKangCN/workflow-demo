package org.bkcloud.fleet.workflow.process;

import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptException;
import java.io.IOException;

@SpringBootTest
public class RunScriptProcessTests {

    @Autowired
    private RunScriptProcess runScriptProcess;

    @Test
    public void allTests() throws ScriptException, IOException {
        DeploymentBuilder builder = runScriptProcess.create("println \"hello\"");
        Assertions.assertNotNull(builder);
        Assertions.assertAll(() -> runScriptProcess.deploy(builder));
        Assertions.assertNotNull(runScriptProcess.start());
    }
}
