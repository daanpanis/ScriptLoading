package com.daan.scripting.impl;

import com.daan.scripting.ScriptCompiler;
import com.daan.scripting.ScriptLoader;
import com.daan.scripting.ScriptReader;

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
