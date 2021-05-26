package org.bkcloud.fleet.workflow.process;

import org.bkcloud.fleet.workflow.scriptEngine.FreemarkerScriptEngine;
import org.bkcloud.fleet.workflow.scriptEngine.factory.FreemarkerScriptEngineFactory;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.Bindings;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * a class to create, deploy, start RunScript process
 */
@Component
public class RunScriptProcess {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * create a BPMN process with the given source
     *
     * @param source script source code
     * @return a deploy builder with the BPMN content
     * @throws IOException exception while reading the template
     * @throws ScriptException exception while evaluate the script source code
     */
    public DeploymentBuilder create(String source) throws IOException, ScriptException {
        FreemarkerScriptEngine scriptEngine = (FreemarkerScriptEngine) new FreemarkerScriptEngineFactory().getScriptEngine();

        String template = scriptEngine.getConfiguration().getTemplate("runScript.ftl").toString(); // get the template as String
        Bindings bindings = scriptEngine.createBindings();
        bindings.put("source", source); // bind ${source} from parameter
        String content = (String) scriptEngine.eval(template, bindings); // evaluate the template to BPMN content

        return repositoryService.createDeployment().addString("runScript.bpmn", content);
    }

    public void deploy(DeploymentBuilder deploymentBuilder) {
        deploymentBuilder.deploy();
    }

    public ProcessInstance start() {
        return runtimeService.startProcessInstanceByKey("runScript");
    }
}
