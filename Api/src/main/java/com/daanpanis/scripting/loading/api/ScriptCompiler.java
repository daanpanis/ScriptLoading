package com.daanpanis.scripting.loading.api;

import com.daanpanis.scripting.loading.api.exception.ScriptException;

import java.io.Reader;

public interface ScriptCompiler {

    Object compile(Reader reader) throws ScriptException;

}
