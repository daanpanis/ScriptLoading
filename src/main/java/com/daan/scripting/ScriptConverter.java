package com.daan.scripting;

import com.daan.scripting.exception.ScriptException;

public interface ScriptConverter<T, R> {

    R convert(T value) throws ScriptException;

}
