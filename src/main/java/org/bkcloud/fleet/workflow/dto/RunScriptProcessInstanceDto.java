package org.bkcloud.fleet.workflow.dto;

import org.bkcloud.fleet.workflow.process.instance.RunScriptProcessInstance;

public class RunScriptProcessInstanceDto extends AbstractDto<RunScriptProcessInstance> {

    public RunScriptProcessInstanceDto() {
        super();
    }

    public RunScriptProcessInstanceDto(int code, String msg) {
        super(code, msg);
    }

    public RunScriptProcessInstanceDto(int code, String msg, RunScriptProcessInstance data) {
        super(code, msg, data);
    }
}
