package org.bkcloud.fleet.workflow;

import org.bkcloud.fleet.workflow.controller.CityControllerTests;
import org.bkcloud.fleet.workflow.job.GreatTests;
import org.bkcloud.fleet.workflow.process.DeploymentTests;
import org.bkcloud.fleet.workflow.process.RunScriptProcessTests;
import org.bkcloud.fleet.workflow.scriptEngine.FreemarkerScriptEngineTests;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CityControllerTests.class,
        GreatTests.class,
        DeploymentTests.class,
        RunScriptProcessTests.class,
        FreemarkerScriptEngineTests.class,
})
class WorkflowApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertAll(() -> {});
    }

}
