package com.daan.scripting;

import java.io.Reader;

public interface ScriptReader extends ScriptTask<Object>, ScriptConverter<Reader, Object> {

    ScriptClass<?> expectClass();

}
