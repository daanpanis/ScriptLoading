package com.daan.scripting.impl.constructable;

import com.daan.scripting.constructable.Constructable6;
import com.daan.scripting.exception.ScriptException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructable6Impl<T, P1, P2, P3, P4, P5, P6> extends AbstractConstructable<T> implements Constructable6<T, P1, P2, P3, P4, P5, P6> {

    public Constructable6Impl(Constructor<T> constructor) {
        super(constructor);
    }

    @Override
    public T instance(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5, P6 param6) throws ScriptException {
        try {
            return constructor.newInstance(param1, param2, param3, param4, param5, param6);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ScriptException(e);
        }
    }
}
