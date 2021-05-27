package org.bkcloud.fleet.workflow.job;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GreatTests {

    @Autowired
    private Scheduler scheduler;

    @Test
    public void canExecute() {
        JobDetail jobDetail = JobBuilder.newJob(Greet.class).storeDurably().build();
        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).startNow().build();
        Assertions.assertAll(
                () -> scheduler.scheduleJob(jobDetail, trigger)
        );
    }
}
