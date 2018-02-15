package com.daan.scripting.constructable;

import com.daan.scripting.exception.ScriptException;

import java.lang.reflect.InvocationTargetException;

public interface Constructable1<T, P> extends Constructable {

    T instance(P parameter) throws ScriptException;

}
