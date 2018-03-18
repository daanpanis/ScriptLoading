package com.daan.scripting.loading.test;

import com.daanpanis.scripting.loading.api.ScriptCompiler;
import com.daanpanis.scripting.loading.api.exception.ScriptException;

import java.io.Reader;

public class TestCompiler implements ScriptCompiler {

    private final Class<?> toReturn;

    public TestCompiler(Class<?> toReturn) {
        this.toReturn = toReturn;
    }

    @Override
    public Object compile(Reader reader) throws ScriptException {
        return toReturn;
    }
}
