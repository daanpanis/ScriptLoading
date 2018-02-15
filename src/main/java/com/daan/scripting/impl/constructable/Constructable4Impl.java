package com.daan.scripting.impl.constructable;

import com.daan.scripting.constructable.Constructable4;
import com.daan.scripting.exception.ScriptException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructable4Impl<T, P1, P2, P3, P4> extends AbstractConstructable<T> implements Constructable4<T, P1, P2, P3, P4> {

    public Constructable4Impl(Constructor<T> constructor) {
        super(constructor);
    }

    @Override
    public T instance(P1 param1, P2 param2, P3 param3, P4 param4) throws ScriptException {
        try {
            return constructor.newInstance(param1, param2, param3, param4);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ScriptException(e);
        }
    }
}
