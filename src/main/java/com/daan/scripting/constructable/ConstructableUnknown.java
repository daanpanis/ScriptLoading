package com.daan.scripting.constructable;

import com.daan.scripting.exception.ScriptException;

public interface ConstructableUnknown<T> extends Constructable {

    T instance(Object... parameters) throws ScriptException;

}
