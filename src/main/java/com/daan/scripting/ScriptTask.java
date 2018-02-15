package com.daan.scripting;

import com.daan.scripting.exception.ScriptException;
import com.daan.scripting.exception.ScriptExecutionException;
import com.daan.scripting.exception.ScriptInterruptedException;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface ScriptTask<T> {

    default T get() throws ScriptException {
        try {
            return getAsync().get();
        } catch (InterruptedException e) {
            throw new ScriptInterruptedException(e);
        } catch (ExecutionException e) {
            throw new ScriptExecutionException(e);
        }
    }

    CompletableFuture<T> getAsync();

}
