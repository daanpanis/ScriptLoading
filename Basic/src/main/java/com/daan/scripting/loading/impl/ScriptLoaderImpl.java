package com.daan.scripting.loading.impl;

import com.daanpanis.scripting.loading.api.ScriptCompiler;
import com.daanpanis.scripting.loading.api.ScriptLoader;
import com.daanpanis.scripting.loading.api.ScriptReader;

import java.io.Reader;
import java.util.function.Supplier;

public class ScriptLoaderImpl implements ScriptLoader {

    private final ScriptCompiler compiler;

    public ScriptLoaderImpl(ScriptCompiler compiler) {
        this.compiler = compiler;
    }

    @Override
    public ScriptReader reader(Supplier<Reader> readerSupplier) {
        return new ScriptReaderImpl(compiler, readerSupplier);
    }
}
