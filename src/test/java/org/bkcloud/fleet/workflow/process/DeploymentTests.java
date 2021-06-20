package org.bkcloud.fleet.workflow.process;

import org.camunda.bpm.engine.RuntimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeploymentTests {

    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void canDeploy() {
        Assertions.assertNotEquals(0, runtimeService.createProcessInstanceQuery().count());
    }
}
