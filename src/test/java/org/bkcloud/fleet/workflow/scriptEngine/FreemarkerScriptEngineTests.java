package org.bkcloud.fleet.workflow.scriptEngine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.Bindings;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class FreemarkerScriptEngineTests {

    @Autowired
    private FreemarkerScriptEngine engine;

    @Test
    public void canEvaluate() throws ScriptException {
        Bindings bindings = engine.createBindings();

        String template = "Hello ${name}!";
        Map<String, String> map = new HashMap<>();
        map.put("name", "world");
        bindings.putAll(map);

        Assertions.assertEquals("Hello world!", engine.eval(template, bindings));
    }
}
