package com.daan.scripting.program;

import com.daan.scripting.ScriptLoader;
import com.daan.scripting.impl.ScriptLoaderImpl;
import com.daan.scripting.impl.groovy.ClassFilter;
import com.daan.scripting.impl.groovy.GroovyCompiler;
import me.ecminer.benchmark.Benchmark;
import me.ecminer.benchmark.Benchmarks;

import javax.script.*;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Program {

    static final int BENCH_REPEATS = 40;

    public static void main(String[] args) throws Exception {
        ScriptLoader loader = new ScriptLoaderImpl(new GroovyCompiler(ClassFilter.STRICT));

        Object obj = loader.stream(Program.class.getResourceAsStream("/methods.groovy")).expectClass().constructor().get().instance();
        Method m = obj.getClass().getDeclaredMethod("bench");
        Benchmark<?> scriptLoaderBench = new Benchmark<>(BENCH_REPEATS).action(() -> {
            try {
                m.invoke(obj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        Benchmark<Invocable> nashornCompiled = new Benchmark<Invocable>(BENCH_REPEATS).supplier(() -> {
            try {
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("Nashorn");
                CompiledScript cscript = ((Compilable) engine).compile(new InputStreamReader(Program.class.getResourceAsStream("/test.js")));
                cscript.eval(engine.getBindings(ScriptContext.ENGINE_SCOPE));
                return (Invocable) cscript.getEngine();
            } catch (ScriptException e) {
                e.printStackTrace();
                return null;
            }
        }).action((invocable) -> {
            try {
                invocable.invokeFunction("bench");
            } catch (ScriptException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });


        Benchmark<?> java = new Benchmark<>(BENCH_REPEATS).action(Program::bench);

        System.out.println(Benchmarks.resultsAndGraphToString("Script Loader groovy", scriptLoaderBench.execute(), TimeUnit.MILLISECONDS));
        System.out.println(Benchmarks.resultsAndGraphToString("Nashorn Compiled", nashornCompiled.execute(), TimeUnit.MILLISECONDS));
        System.out.println(Benchmarks.resultsAndGraphToString("Java", java.execute(), TimeUnit.MILLISECONDS));
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    static void bench() {
        for (int i = 0; i < 100000; i++) {
            double b = Math.sqrt((double) (i * i * i));
        }
    }
}
