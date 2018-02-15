import groovy.transform.CompileStatic

import java.util.concurrent.Callable

@CompileStatic
class test implements Runnable, Callable<String> {

    test(int k) {
        println("Int ${k}")
    }

    test(String k) {
        println("String ${k}")
    }

    test(int p1, int p2, int p3, int p4, int p5, int p6, int p7) {
        println("Seven parameters")
    }

    test() {
        println("Empty constructor");
    }

    def kappa() {

    }

    @Override
    void run() {
        println()
        for (int i = 0; i < 10000; i++) {
            int k = i * i;
            print(k);
        }
        println();
    }

    @Override
    String call() throws Exception {
        return "Kappa"
    }
}
