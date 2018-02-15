package com.daan.scripting.exception;

public class ScriptInterruptedException extends ScriptException {

    public ScriptInterruptedException(InterruptedException exception) {
        super(exception);
    }

}
