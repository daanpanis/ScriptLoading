import groovy.transform.CompileStatic

@CompileStatic
class methods {

    def bench() {
        for (long i = 0; i < 100000; i++) {
            double b = Math.sqrt((double) (i * i * i))
        }
    }
}