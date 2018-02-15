package com.daan.scripting.impl.groovy;

public interface ClassFilter {

    ClassFilter STRICT = className -> !className.equalsIgnoreCase("java.lang.Thread");

    boolean isAllowed(String className);

}
