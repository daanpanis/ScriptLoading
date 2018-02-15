package com.daan.scripting.impl.constructable;

import com.daan.scripting.constructable.ConstructableUnknown;
import com.daan.scripting.exception.ScriptException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructableUnknownImpl<T> extends AbstractConstructable<T> implements ConstructableUnknown<T> {

    public ConstructableUnknownImpl(Constructor<T> constructor) {
        super(constructor);
    }

    @Override
    public T instance(Object... parameters) throws ScriptException {
        try {
            return constructor.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ScriptException(e);
        }
    }
}
