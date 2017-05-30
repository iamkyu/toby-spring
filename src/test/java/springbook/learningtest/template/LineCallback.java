package springbook.learningtest.template;

/**
 * @author Kj Nam
 * @since 2017-05-31
 */
public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
