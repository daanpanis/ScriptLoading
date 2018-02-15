package com.daan.scripting.impl;

import com.daan.scripting.ScriptConstructor;
import com.daan.scripting.ScriptTask;
import com.daan.scripting.constructable.Constructable;
import com.daan.scripting.exception.ScriptException;

import java.lang.reflect.Constructor;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class ScriptConstructorImpl<T, C extends Constructable> implements ScriptConstructor<T, C> {

    private final ScriptTask<Class<T>> scriptTask;
    private final Class<?>[] parameters;
    private final Function<Constructor<T>, C> convert;
    private C result;

    public ScriptConstructorImpl(ScriptTask<Class<T>> scriptTask, Class<?>[] parameters, Function<Constructor<T>, C> convert) {
        this.scriptTask = scriptTask;
        this.parameters = parameters;
        this.convert = convert;
    }

    @Override
    public C convert(Class<T> value) throws ScriptException {
        if (result == null) {
            try {
                Constructor<T> constructor = value.getConstructor(parameters);
                constructor.setAccessible(true);
                result = convert.apply(constructor);
            } catch (NoSuchMethodException e) {
                throw new ScriptException(e);
            }
        }
        return result;
    }

    @Override
    public CompletableFuture<C> getAsync() {
        return scriptTask.getAsync().thenApply((cls) -> {
            try {
                return convert(cls);
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
