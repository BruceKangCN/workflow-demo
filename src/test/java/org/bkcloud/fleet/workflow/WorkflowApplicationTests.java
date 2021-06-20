package org.bkcloud.fleet.workflow;

import org.bkcloud.fleet.workflow.process.DeploymentTests;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DeploymentTests.class,
})
class WorkflowApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertAll(() -> {});
    }

}
