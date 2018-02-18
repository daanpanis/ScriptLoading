package com.daan.scripting.loading.impl;

import com.daanpanis.scripting.loading.api.ScriptClass;
import com.daanpanis.scripting.loading.api.ScriptConstructor;
import com.daanpanis.scripting.loading.api.ScriptTask;
import com.daanpanis.scripting.loading.api.exception.ScriptException;
import com.daanpanis.scripting.loading.api.exception.ScriptRuntimeException;
import com.daanpanis.reflection.constructor.*;
import com.daanpanis.reflection.impl.constructor.*;

import java.util.concurrent.CompletableFuture;

public class ScriptClassImpl<T> implements ScriptClass<T> {

    private final ScriptTask<?> objectTask;
    private Class<T> result;

    public ScriptClassImpl(ScriptTask<?> objectTask) {
        this.objectTask = objectTask;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<T> convert(Object value) throws ScriptException {
        if (result == null) {
            if (!(value instanceof Class))
                throw new ScriptException("Return script value isn't a class!");
            result = (Class<T>) value;
        }
        return result;
    }

    @Override
    public CompletableFuture<Class<T>> getAsync() {
        return objectTask.getAsync().thenApply(object -> {
            try {
                return convert(object);
            } catch (ScriptException e) {
                throw new ScriptRuntimeException(e);
            }
        });
    }

    @Override
    public ScriptClass<T> inherits(Class<?>... classes) {
        return new ScriptInheritedClassImpl<>(objectTask, classes);
    }

    @Override
    public <P1> ScriptConstructor<T, Constructor1<T, P1>> constructor(Class<P1> param1) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1}, Constructor1Impl::new);
    }

    @Override
    public <P1, P2> ScriptConstructor<T, Constructor2<T, P1, P2>> constructor(Class<P1> param1, Class<P2> param2) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2}, Constructor2Impl::new);
    }

    @Override
    public <P1, P2, P3> ScriptConstructor<T, Constructor3<T, P1, P2, P3>> constructor(Class<P1> param1, Class<P2> param2, Class<P3> param3) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2, param3}, Constructor3Impl::new);
    }

    @Override
    public <P1, P2, P3, P4> ScriptConstructor<T, Constructor4<T, P1, P2, P3, P4>> constructor(Class<P1> param1, Class<P2> param2, Class<P3> param3,
            Class<P4> param4) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2, param3, param4}, Constructor4Impl::new);
    }

    @Override
    public <P1, P2, P3, P4, P5> ScriptConstructor<T, Constructor5<T, P1, P2, P3, P4, P5>> constructor(Class<P1> param1, Class<P2> param2,
            Class<P3> param3, Class<P4> param4, Class<P5> param5) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2, param3, param4, param5}, Constructor5Impl::new);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6> ScriptConstructor<T, Constructor6<T, P1, P2, P3, P4, P5, P6>> constructor(Class<P1> param1, Class<P2> param2,
            Class<P3> param3, Class<P4> param4, Class<P5> param5, Class<P6> param6) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2, param3, param4, param5, param6}, Constructor6Impl::new);
    }

    @Override
    public ScriptConstructor<T, ConstructorUnknown<T>> constructor(Class<?>... cls) {
        return new ScriptConstructorImpl<>(this, cls, ConstructorUnknownImpl::new);
    }

    @Override
    public <C> ScriptClass<C> cast(Class<C> castClass) {
        return new ScriptClassImpl<>(this);
    }
}
