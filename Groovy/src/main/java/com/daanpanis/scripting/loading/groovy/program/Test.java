package com.daanpanis.scripting.loading.groovy.program;

import com.daan.scripting.loading.impl.ScriptLoaderImpl;
import com.daanpanis.scripting.loading.api.ScriptLoader;
import com.daanpanis.scripting.loading.api.exception.ScriptException;
import com.daanpanis.scripting.loading.groovy.GroovyCompiler;

import java.util.Scanner;

public class Test {

    static Runnable runnable;

    public static void main(String[] args) throws Exception {
        ScriptLoader loader = new ScriptLoaderImpl(new GroovyCompiler());
        runnable = loader.stream(Test.class.getResourceAsStream("/Test.groovy")).expectClass().inherits(Runnable.class).cast(Runnable.class)
                .constructor().get().instance().object();
        runnable.run();
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String line;
            while ((line = scanner.nextLine()) != null) {
                if (line.equalsIgnoreCase("reload")) {
                    try {
                        runnable = loader.stream(Test.class.getResourceAsStream("/Test.groovy")).expectClass().inherits(Runnable.class)
                                .cast(Runnable.class).constructor().get().instance().object();
                        runnable.run();
                    } catch (ScriptException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
