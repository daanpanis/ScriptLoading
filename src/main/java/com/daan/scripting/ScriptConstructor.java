package com.daan.scripting;

import com.daanpanis.reflection.constructor.Constructor;

public interface ScriptConstructor<T, C extends Constructor<T>> extends ScriptTask<C>, ScriptConverter<Class<T>, C> {

}
