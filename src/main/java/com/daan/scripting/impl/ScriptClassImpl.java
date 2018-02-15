package com.daan.scripting.impl;

import com.daan.scripting.ScriptClass;
import com.daan.scripting.ScriptConstructor;
import com.daan.scripting.ScriptInheritedClass;
import com.daan.scripting.ScriptTask;
import com.daan.scripting.constructable.*;
import com.daan.scripting.exception.ScriptException;
import com.daan.scripting.impl.constructable.*;

import java.util.concurrent.CompletableFuture;

public class ScriptClassImpl<T> implements ScriptClass<T> {

    private final ScriptTask<?> objectTask;
    private Class<T> result;

    public ScriptClassImpl(ScriptTask<?> objectTask) {
        this.objectTask = objectTask;
    }

    @Override
    public Class<T> convert(Object value) throws ScriptException {
        if (result == null) {
            if (!(value instanceof Class)) throw new ScriptException("Return script value isn't a class!");
            result = (Class<T>) value;
        }
        return result;
    }

    @Override
    public CompletableFuture<Class<T>> getAsync() {
        return objectTask.getAsync().thenApply((object) -> {
            try {
                return convert(object);
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public ScriptClass<T> inherits(Class<?>... classes) {
        return new ScriptInheritedClassImpl<T>(objectTask, classes);
    }

    @Override
    public <P1> ScriptConstructor<T, Constructable1<T, P1>> constructor(Class<P1> param1) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1}, Constructable1Impl::new);
    }

    @Override
    public <P1, P2> ScriptConstructor<T, Constructable2<T, P1, P2>> constructor(Class<P1> param1, Class<P2> param2) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2}, Constructable2Impl::new);
    }

    @Override
    public <P1, P2, P3> ScriptConstructor<T, Constructable3<T, P1, P2, P3>> constructor(Class<P1> param1, Class<P2> param2, Class<P3> param3) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2, param3}, Constructable3Impl::new);
    }

    @Override
    public <P1, P2, P3, P4> ScriptConstructor<T, Constructable4<T, P1, P2, P3, P4>> constructor(Class<P1> param1, Class<P2> param2, Class<P3> param3,
            Class<P4> param4) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2, param3, param4}, Constructable4Impl::new);
    }

    @Override
    public <P1, P2, P3, P4, P5> ScriptConstructor<T, Constructable5<T, P1, P2, P3, P4, P5>> constructor(Class<P1> param1, Class<P2> param2,
            Class<P3> param3, Class<P4> param4, Class<P5> param5) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2, param3, param4, param5}, Constructable5Impl::new);
    }

    @Override
    public <P1, P2, P3, P4, P5, P6> ScriptConstructor<T, Constructable6<T, P1, P2, P3, P4, P5, P6>> constructor(Class<P1> param1, Class<P2> param2,
            Class<P3> param3, Class<P4> param4, Class<P5> param5, Class<P6> param6) {
        return new ScriptConstructorImpl<>(this, new Class<?>[]{param1, param2, param3, param4, param5, param6}, Constructable6Impl::new);
    }

    @Override
    public ScriptConstructor<T, ConstructableUnknown<T>> constructor(Class<?>... cls) {
        return new ScriptConstructorImpl<>(this, cls, ConstructableUnknownImpl::new);
    }

    @Override
    public <C> ScriptClass<C> cast(Class<C> castClass) {
        return new ScriptClassImpl<>(this);
    }
}
