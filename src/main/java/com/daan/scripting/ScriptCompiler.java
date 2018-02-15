package com.daan.scripting;

import com.daan.scripting.exception.ScriptException;

import java.io.Reader;

public interface ScriptCompiler {

    Object compile(Reader reader) throws ScriptException;

}
