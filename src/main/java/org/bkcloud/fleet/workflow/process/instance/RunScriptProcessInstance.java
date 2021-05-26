package org.bkcloud.fleet.workflow.process.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * a class to define run script process instance
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunScriptProcessInstance {

    private UUID id;
    private String source;

}
