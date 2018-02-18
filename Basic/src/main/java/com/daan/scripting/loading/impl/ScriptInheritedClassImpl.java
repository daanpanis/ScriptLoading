package com.daan.scripting.loading.impl;

import com.daanpanis.scripting.loading.api.ScriptInheritedClass;
import com.daanpanis.scripting.loading.api.ScriptTask;
import com.daanpanis.scripting.loading.api.exception.ScriptException;

public class ScriptInheritedClassImpl<T> extends ScriptClassImpl<T> implements ScriptInheritedClass<T> {

    private final Class<?>[] inheritsClasses;

    public ScriptInheritedClassImpl(ScriptTask<?> scriptClass, Class<?>[] inheritsClasses) {
        super(scriptClass);
        this.inheritsClasses = inheritsClasses;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<T> convert(Object value) throws ScriptException {
        if (value instanceof Class) {
            Class<T> cls = (Class<T>) value;
            for (Class<?> superClass : inheritsClasses) {
                if (!superClass.isAssignableFrom(cls))
                    throw new ScriptException("Class doesn't inherit " + superClass);
            }
            return cls;
        }
        throw new ScriptException("Value isn't a class!");
    }
}
