package com.daanpanis.scripting.loading.api;

import com.daanpanis.utils.reflection.constructor.Constructor;

public interface ScriptConstructor<T, C extends Constructor<T>> extends ScriptTask<C>, ScriptConverter<Class<T>, C> {

}
