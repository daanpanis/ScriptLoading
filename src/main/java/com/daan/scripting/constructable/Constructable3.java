package com.daan.scripting.constructable;

import com.daan.scripting.exception.ScriptException;

public interface Constructable3<T, P1, P2, P3> extends Constructable {

    T instance(P1 param1, P2 param2, P3 param3) throws ScriptException;

}
