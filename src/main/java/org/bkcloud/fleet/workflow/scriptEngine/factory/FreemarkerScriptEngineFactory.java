package org.bkcloud.fleet.workflow.scriptEngine.factory;

import org.bkcloud.fleet.workflow.scriptEngine.FreemarkerScriptEngine;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * a factory to create freemarker script engine
 */
@Component
public class FreemarkerScriptEngineFactory implements ScriptEngineFactory {

    public final static String NAME = "freemarker";
    public final static String VERSION = "2.3.31";
    public final static List<String> names;
    public final static List<String> extensions;
    public final static List<String> mimeTypes;

    static {
        names = Collections.unmodifiableList(Arrays.asList(NAME, "Freemarker", "FreeMarker"));
        extensions = Collections.singletonList("ftl");
        mimeTypes = Collections.emptyList();
    }

    @Override
    public String getEngineName() {
        return NAME;
    }

    @Override
    public String getEngineVersion() {
        return VERSION;
    }

    @Override
    public List<String> getExtensions() {
        return extensions;
    }

    @Override
    public List<String> getMimeTypes() {
        return mimeTypes;
    }

    @Override
    public List<String> getNames() {
        return names;
    }

    @Override
    public String getLanguageName() {
        return NAME;
    }

    @Override
    public String getLanguageVersion() {
        return VERSION;
    }

    @Override
    public Object getParameter(String key) {
        // require jdk 14
        // return switch (key) {
        //     case ScriptEngine.NAME, ScriptEngine.LANGUAGE -> getLanguageName();
        //     case ScriptEngine.ENGINE -> getEngineName();
        //     case ScriptEngine.ENGINE_VERSION -> getEngineVersion();
        //     case ScriptEngine.LANGUAGE_VERSION -> getLanguageVersion();
        //     case "THREADING" -> "MULTITHREADING";
        //     default -> null;
        // }
        switch (key) {
            case ScriptEngine.NAME:
            case ScriptEngine.LANGUAGE:
                return getLanguageName();
            case ScriptEngine.ENGINE:
                return getEngineName();
            case ScriptEngine.ENGINE_VERSION:
                return getEngineVersion();
            case ScriptEngine.LANGUAGE_VERSION:
                return getLanguageVersion();
            case "THREADING":
                return "MULTITHREADED";
            default:
                return null;
        }
    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args) {
        String params = args == null ? "" : String.join(", ", args);
        return "${" + obj + "." + m + "(" + params + ")}";
    }

    @Override
    public String getOutputStatement(String toDisplay) {
        return toDisplay;
    }

    @Override
    public String getProgram(String... statements) {
        return statements == null ? null : String.join("\n", statements);
    }

    @Override
    public ScriptEngine getScriptEngine() {
        return new FreemarkerScriptEngine(this);
    }
}
