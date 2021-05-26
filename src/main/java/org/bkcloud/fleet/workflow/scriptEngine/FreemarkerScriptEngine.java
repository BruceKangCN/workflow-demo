package org.bkcloud.fleet.workflow.scriptEngine;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.camunda.templateengines.FreeMarkerCompiledScript;
import org.springframework.stereotype.Component;

import javax.script.*;
import java.io.*;

/**
 * a script engine based on freemarker to compile/evaluate scripts
 */
@Component
public class FreemarkerScriptEngine extends AbstractScriptEngine implements Compilable {

    private final ScriptEngineFactory factory;
    private final Configuration configuration;

    public FreemarkerScriptEngine(ScriptEngineFactory factory) {
        this.factory = factory;
        this.configuration = new Configuration(new Version(factory.getEngineVersion()));
    }

    @Override
    public CompiledScript compile(String script) throws ScriptException {
        return compile(new StringReader(script));
    }

    @Override
    public CompiledScript compile(Reader script) throws ScriptException {
        return new FreeMarkerCompiledScript(this, script, configuration);
    }

    @Override
    public Object eval(String script, ScriptContext context) throws ScriptException {
        return eval(new StringReader(script), context);
    }

    /**
     * @param reader a reader to read script
     * @param context script context
     * @return an Object that can be converted to String which contains template processing result
     * @throws ScriptException template processing exception
     */
    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        String filename = (String) context.getAttribute(ScriptEngine.FILENAME); // get script filename
        Writer writer = new StringWriter(); // to write the result to String
        Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE); // get bindings from context

        try {
            Template template = new Template(filename, reader, configuration);
            template.process(bindings, writer);
            writer.flush();
        } catch (IOException | TemplateException e) {
            throw new ScriptException(e);
        }

        return writer.toString();
    }

    @Override
    public Bindings createBindings() {
        return new SimpleBindings();
    }

    @Override
    public ScriptEngineFactory getFactory() {
        return this.factory;
    }
}
