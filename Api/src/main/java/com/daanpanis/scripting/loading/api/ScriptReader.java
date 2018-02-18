package com.daanpanis.scripting.loading.api;

import java.io.Reader;

public interface ScriptReader extends ScriptTask<Object>, ScriptConverter<Reader, Object> {

    ScriptClass<?> expectClass();

}
