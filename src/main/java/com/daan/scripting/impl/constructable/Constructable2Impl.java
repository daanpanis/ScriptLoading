package com.daan.scripting.impl.constructable;

import com.daan.scripting.constructable.Constructable2;
import com.daan.scripting.exception.ScriptException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructable2Impl<T, P1, P2> extends AbstractConstructable<T> implements Constructable2<T, P1, P2> {

    public Constructable2Impl(Constructor<T> constructor) {
        super(constructor);
    }

    @Override
    public T instance(P1 param1, P2 param2) throws ScriptException {
        try {
            return constructor.newInstance(param1, param2);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ScriptException(e);
        }
    }
}
