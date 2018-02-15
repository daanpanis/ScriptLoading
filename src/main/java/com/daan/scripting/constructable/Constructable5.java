package com.daan.scripting.constructable;

import com.daan.scripting.exception.ScriptException;

public interface Constructable5<T, P1, P2, P3, P4, P5> extends Constructable {

    T instance(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5) throws ScriptException;

}
