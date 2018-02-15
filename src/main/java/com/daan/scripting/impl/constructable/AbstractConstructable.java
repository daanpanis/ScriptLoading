package com.daan.scripting.impl.constructable;

import java.lang.reflect.Constructor;

public class AbstractConstructable<T> {

    protected final Constructor<T> constructor;

    public AbstractConstructable(Constructor<T> constructor) {
        this.constructor = constructor;
    }
}
