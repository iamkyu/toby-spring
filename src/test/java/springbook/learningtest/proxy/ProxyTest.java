package springbook.learningtest.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-06-07
 */
public class ProxyTest {
    @Test
    public void simpleProxy() {
        Hello hello = new HelloTarget();
        assertThat(hello.sayHello("Kyu"), is("Hello Kyu"));
        assertThat(hello.sayHi("Kyu"), is("Hi Kyu"));
        assertThat(hello.sayThankYou("Kyu"), is("Thank You Kyu"));
    }

    @Test
    public void upperProxy() {
        Hello hello = new HelloUppercase(new HelloTarget());
        assertThat(hello.sayHello("Kyu"), is("HELLO KYU"));
        assertThat(hello.sayHi("Kyu"), is("HI KYU"));
        assertThat(hello.sayThankYou("Kyu"), is("THANK YOU KYU"));
    }

    @Test
    public void dynamicProxy() {
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{ Hello.class },
                new UppercaseHandler(new HelloTarget())
        );

        assertThat(proxiedHello.sayHello("Kyu"), is("HELLO KYU"));
        assertThat(proxiedHello.sayHi("Kyu"), is("HI KYU"));
        assertThat(proxiedHello.sayThankYou("Kyu"), is("THANK YOU KYU"));
    }
}
