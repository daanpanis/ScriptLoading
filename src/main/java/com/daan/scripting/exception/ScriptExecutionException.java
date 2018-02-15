package com.daan.scripting.exception;

import java.util.concurrent.ExecutionException;

public class ScriptExecutionException extends ScriptException {

    public ScriptExecutionException(ExecutionException exception) {
        super(exception);
    }
}
