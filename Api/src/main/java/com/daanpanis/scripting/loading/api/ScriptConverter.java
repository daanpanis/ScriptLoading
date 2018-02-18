package com.daanpanis.scripting.loading.api;

import com.daanpanis.scripting.loading.api.exception.ScriptException;

public interface ScriptConverter<T, R> {

    R convert(T value) throws ScriptException;

}
