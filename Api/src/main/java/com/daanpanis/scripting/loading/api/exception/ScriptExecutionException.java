package com.daanpanis.scripting.loading.api.exception;

import java.util.concurrent.ExecutionException;

public class ScriptExecutionException extends ScriptException {

    public ScriptExecutionException(ExecutionException exception) {
        super(exception);
    }
}
