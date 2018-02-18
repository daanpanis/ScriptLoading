package com.daan.scripting.impl.groovy;

import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.control.CompilerConfiguration;

public class FilteredGroovyClassLoader extends GroovyClassLoader {

    private final ClassFilter filter;

    public FilteredGroovyClassLoader(ClassFilter filter, ClassLoader loader, CompilerConfiguration config) {
        super(loader, config);
        this.filter = filter;
    }

    @Override
    public Class loadClass(String name, boolean lookupScriptFiles, boolean preferClassOverScript, boolean resolve) throws ClassNotFoundException {
        if (filter != null && !filter.isAllowed(name))
            return null;
        return super.loadClass(name, lookupScriptFiles, preferClassOverScript, resolve);
    }
}
