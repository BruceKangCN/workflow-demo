package org.bkcloud.fleet.workflow.controller;

import org.bkcloud.fleet.workflow.dto.RunScriptProcessInstanceDto;
import org.bkcloud.fleet.workflow.process.instance.RunScriptProcessInstance;
import org.bkcloud.fleet.workflow.service.IProcessService;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * a controller to control run script process
 */
@RestController
@RequestMapping("/run-script")
public class RunScriptProcessController {

    @Autowired
    @Qualifier("RunScriptProcessService")
    private IProcessService runScriptProcessService;

    private static final Map<UUID, DeploymentBuilder> map;

    static {
        map = new HashMap<>();
    }

    /**
     * create a run script process with the given source
     * and put it into a hashmap
     *
     * @param instance request body with the source code of the script
     * @throws ScriptException exception while evaluate the script source code
     * @throws IOException exception while reading the template
     * @return created process instance wrapped with DTO
     */
    @PostMapping("/create")
    public RunScriptProcessInstanceDto create(@RequestBody RunScriptProcessInstance instance) throws ScriptException, IOException {
        UUID uuid = UUID.randomUUID();
        DeploymentBuilder builder = runScriptProcessService.create(instance.getSource());
        map.put(uuid, builder);
        instance.setId(uuid);
        return new RunScriptProcessInstanceDto(0, "success", instance);
    }

    /**
     * deploy a process with the builder specified by uuid
     *
     * @param instance request body with the uuid of the deployment builder
     */
    @PostMapping("/deploy")
    public void deploy(@RequestBody RunScriptProcessInstance instance) {
        runScriptProcessService.deploy(map.get(instance.getId()));
    }

    /**
     * start a run script process instance
     */
    @PostMapping("/start")
    public void start() {
        runScriptProcessService.start();
    }
}
