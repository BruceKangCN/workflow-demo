package org.bkcloud.fleet.workflow.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * a controller for query process information
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    /**
     * Camunda runtime service for query
     */
    @Autowired
    private RuntimeService runtimeService;

    /**
     * @return the process instance query created by runtime service
     */
    protected ProcessInstanceQuery getProcessInstanceQuery() {
        return runtimeService.createProcessInstanceQuery();
    }

    /**
     * @return count of all process instances
     */
    @GetMapping("/count")
    public long instanceCount() {
        return getProcessInstanceQuery().count();
    }

    /**
     * @return a list of all process instances
     */
    @GetMapping("/list")
    public List<ProcessInstanceDto> instanceList() {
        List<ProcessInstance> processInstances = getProcessInstanceQuery().list();
        List<ProcessInstanceDto> processInstanceDtoList = new ArrayList<>();
        for (ProcessInstance instance : processInstances) { // convert to DTO so they can be transferred
            processInstanceDtoList.add(new ProcessInstanceDto(instance));
        }
        return processInstanceDtoList;
    }
}
