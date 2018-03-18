package com.daan.scripting.loading.impl;

import com.daanpanis.scripting.loading.api.ScriptClass;
import com.daanpanis.scripting.loading.api.ScriptCompiler;
import com.daanpanis.scripting.loading.api.ScriptReader;
import com.daanpanis.scripting.loading.api.exception.ScriptException;
import com.daanpanis.scripting.loading.api.exception.ScriptRuntimeException;

import java.io.Reader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
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
//            System.out.println(readerSupplier.get());
//            System.out.println("1");
            try {
                return convert(readerSupplier.get());
            } catch (ScriptException e) {
                throw new ScriptRuntimeException(e);
            }
        }, Executors.newSingleThreadExecutor());
    }

    @Override
    public ScriptClass<?> expectClass() {
        return new ScriptClassImpl<>(this);
    }
}
