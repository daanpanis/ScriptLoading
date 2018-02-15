package com.daan.scripting;

import com.daan.scripting.exception.ScriptException;

import java.util.Objects;
import java.util.function.Function;

public interface ScriptConverter<T, R> {

    R convert(T value) throws ScriptException;

    default <V> ScriptConverter<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(convert(t));
    }

}
