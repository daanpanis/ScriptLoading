package com.daan.scripting;

import com.daanpanis.reflection.constructor.*;

public interface ScriptClass<T> extends ScriptTask<Class<T>>, ScriptConverter<Object, Class<T>> {

    ScriptClass<T> inherits(Class<?>... classes);

    <P1> ScriptConstructor<T, Constructor1<T, P1>> constructor(Class<P1> param1);

    <P1, P2> ScriptConstructor<T, Constructor2<T, P1, P2>> constructor(Class<P1> param1, Class<P2> param2);

    <P1, P2, P3> ScriptConstructor<T, Constructor3<T, P1, P2, P3>> constructor(Class<P1> param1, Class<P2> param2, Class<P3> param3);

    <P1, P2, P3, P4> ScriptConstructor<T, Constructor4<T, P1, P2, P3, P4>> constructor(Class<P1> param1, Class<P2> param2, Class<P3> param3,
            Class<P4> param4);

    <P1, P2, P3, P4, P5> ScriptConstructor<T, Constructor5<T, P1, P2, P3, P4, P5>> constructor(Class<P1> param1, Class<P2> param2, Class<P3> param3,
            Class<P4> param4, Class<P5> param5);

    <P1, P2, P3, P4, P5, P6> ScriptConstructor<T, Constructor6<T, P1, P2, P3, P4, P5, P6>> constructor(Class<P1> param1, Class<P2> param2,
            Class<P3> param3, Class<P4> param4, Class<P5> param5, Class<P6> param6);

    ScriptConstructor<T, ConstructorUnknown<T>> constructor(Class<?>... cls);

    <C> ScriptClass<C> cast(Class<C> castClass);
}
