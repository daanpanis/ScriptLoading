package com.daan.scripting;

import com.daan.scripting.constructable.Constructable;

public interface ScriptConstructor<T, C extends Constructable> extends ScriptTask<C>, ScriptConverter<Class<T>, C> {

}
