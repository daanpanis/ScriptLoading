package com.daanpanis.scripting.loading.groovy.program;

import com.daan.scripting.loading.impl.ScriptLoaderImpl;
import com.daanpanis.utils.reflection.ReflectionInstance;
import com.daanpanis.utils.reflection.method.MethodUnknown;
import com.daanpanis.scripting.loading.api.ScriptLoader;
import com.daanpanis.scripting.loading.groovy.ClassFilter;
import com.daanpanis.scripting.loading.groovy.GroovyCompiler;
import me.ecminer.benchmark.Benchmark;
import me.ecminer.benchmark.Benchmarks;

import javax.script.*;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {

    static final int BENCH_REPEATS = 40;
    static final Logger logger = Logger.getLogger(Program.class.getSimpleName());


    public static void main(String[] args) throws Exception {
        ScriptLoader loader = new ScriptLoaderImpl(new GroovyCompiler(ClassFilter.STRICT));

        ReflectionInstance<?> obj = loader.stream(Program.class.getResourceAsStream("/methods.groovy")).expectClass().constructor().get().instance();

        Benchmark<MethodUnknown> scriptLoaderBench = new Benchmark<MethodUnknown>(BENCH_REPEATS).supplier(() -> obj.method("bench"))
                .action(m -> m.invoke());

        Benchmark<Invocable> nashornCompiled = new Benchmark<Invocable>(BENCH_REPEATS).supplier(() -> {
            try {
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("Nashorn");
                CompiledScript cscript = ((Compilable) engine).compile(new InputStreamReader(Program.class.getResourceAsStream("/test.js")));
                cscript.eval(engine.getBindings(ScriptContext.ENGINE_SCOPE));
                return (Invocable) cscript.getEngine();
            } catch (ScriptException e) {
                logger.severe(e.getMessage());
                return null;
            }
        }).action(invocable -> {
            try {
                invocable.invokeFunction("bench");
            } catch (ScriptException | NoSuchMethodException e) {
                logger.severe(e.getMessage());
            }
        });


        Benchmark<?> java = new Benchmark<>(BENCH_REPEATS).action(Program::bench);


        if (logger.isLoggable(Level.INFO)) {
            logger.info(Benchmarks.resultsAndGraphToString("Script Loader groovy", scriptLoaderBench.execute(), TimeUnit.MILLISECONDS));
            logger.info(Benchmarks.resultsAndGraphToString("Nashorn Compiled", nashornCompiled.execute(), TimeUnit.MILLISECONDS));
            logger.info(Benchmarks.resultsAndGraphToString("Java", java.execute(), TimeUnit.MILLISECONDS));
        }
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    static void bench() {
        for (int i = 0; i < 100000; i++) {
            Math.sqrt((double) (i * i * i));
        }
    }
}
