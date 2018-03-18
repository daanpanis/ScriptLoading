package com.daanpanis.scripting.loading.groovy;

import com.daanpanis.scripting.loading.api.ScriptCompiler;
import com.daanpanis.scripting.loading.api.exception.ScriptException;
import groovy.lang.GroovyClassLoader;
import groovy.transform.CompileStatic;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class GroovyCompiler implements ScriptCompiler {

    public static final GroovyClassLoader classLoader;

    static {
        CompilerConfiguration config = new CompilerConfiguration();
        config.addCompilationCustomizers(new ASTTransformationCustomizer(CompileStatic.class));
        classLoader = new FilteredGroovyClassLoader(ClassFilter.STRICT, GroovyCompiler.class.getClassLoader(), config);
    }

    public GroovyCompiler() {
        this(null);
    }

    public GroovyCompiler(ClassFilter filter) {
    }

    @Override
    public Object compile(Reader reader) throws ScriptException {
        try {
            return classLoader.parseClass(readerToString(reader));
        } catch (IOException e) {
            throw new ScriptException(e);
        }
    }

    private String readerToString(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = br.read();
        while (result != -1) {
            buf.write((byte) result);
            result = br.read();
        }
        return buf.toString(StandardCharsets.UTF_8.name());
    }
}
