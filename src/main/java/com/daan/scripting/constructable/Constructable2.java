package com.daan.scripting.constructable;

import com.daan.scripting.exception.ScriptException;

public interface Constructable2<T, P1, P2> extends Constructable {

    T instance(P1 param1, P2 param2) throws ScriptException;

}
