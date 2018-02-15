package com.daan.scripting.impl.constructable;

import com.daan.scripting.constructable.Constructable3;
import com.daan.scripting.exception.ScriptException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructable3Impl<T, P1, P2, P3> extends AbstractConstructable<T> implements Constructable3<T, P1, P2, P3> {

    public Constructable3Impl(Constructor<T> constructor) {
        super(constructor);
    }

    @Override
    public T instance(P1 param1, P2 param2, P3 param3) throws ScriptException {
        try {
            return constructor.newInstance(param1, param2, param3);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ScriptException(e);
        }
    }
}
