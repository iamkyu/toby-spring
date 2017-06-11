package springbook.learningtest.pointcut;

/**
 * @author Kj Nam
 * @since 2017-06-11
 */
interface TargetInterface {
    void hello();

    void hello(String a);

    int minus(int a, int b) throws RuntimeException;

    int plus(int a, int b);
}
