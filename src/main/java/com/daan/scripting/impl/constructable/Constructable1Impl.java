package com.daan.scripting.impl.constructable;

import com.daan.scripting.constructable.Constructable1;
import com.daan.scripting.exception.ScriptException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructable1Impl<T, P1> extends AbstractConstructable<T> implements Constructable1<T, P1> {

    public Constructable1Impl(Constructor<T> constructor) {
        super(constructor);
    }

    @Override
    public T instance(P1 parameter) throws ScriptException {
        try {
            return constructor.newInstance(parameter);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ScriptException(e);
        }
    }
}
