package com.daan.scripting.constructable;

import com.daan.scripting.exception.ScriptException;

public interface Constructable4<T, P1, P2, P3, P4> extends Constructable {

    T instance(P1 param1, P2 param2, P3 param3, P4 param4) throws ScriptException;

}
