package com.daanpanis.scripting.loading.api.exception;

public class ScriptException extends Exception {

    public ScriptException() {
    }

    public ScriptException(String message) {
        super(message);
    }

    public ScriptException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScriptException(Throwable cause) {
        super(cause);
    }

    public ScriptException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
