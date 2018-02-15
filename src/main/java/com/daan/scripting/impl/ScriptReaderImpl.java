package com.daan.scripting.impl;

import com.daan.scripting.ScriptClass;
import com.daan.scripting.ScriptCompiler;
import com.daan.scripting.ScriptReader;
import com.daan.scripting.exception.ScriptException;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;

import javax.script.ScriptEngine;
import java.io.Reader;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ScriptReaderImpl implements ScriptReader {

    private final ScriptCompiler compiler;
    private final Supplier<Reader> readerSupplier;
    private Object result;

    public ScriptReaderImpl(ScriptCompiler compiler, Supplier<Reader> readerSupplier) {
        this.compiler = compiler;
        this.readerSupplier = readerSupplier;
    }

    @Override
    public Object convert(Reader value) throws ScriptException {
        if (result == null) {
            result = compiler.compile(readerSupplier.get());
        }
        return result;
    }

    @Override
    public CompletableFuture<Object> getAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return convert(readerSupplier.get());
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public ScriptClass<?> expectClass() {
        return new ScriptClassImpl<>(this);
    }
}
