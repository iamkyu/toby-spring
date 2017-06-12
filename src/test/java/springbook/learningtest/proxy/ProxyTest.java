package springbook.learningtest.proxy;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

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

    @Test
    public void pointcutAdvisor() {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");

        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));

        Hello proxiedHello = (Hello) pfBean.getObject();
        assertThat(proxiedHello.sayHello("Kyu"), is("HELLO KYU"));
        assertThat(proxiedHello.sayHi("Kyu"), is("HI KYU"));
        assertThat(proxiedHello.sayThankYou("Kyu"), is("Thank You Kyu"));
    }

    @Test
    public void classNamePointcutAdvisor() {
        NameMatchMethodPointcut classMethodPointcut = new NameMatchMethodPointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return aClass -> aClass.getSimpleName().startsWith("HelloT");
            }
        };

        classMethodPointcut.setMappedName("sayH*");
        checkAdvice(new HelloTarget(), classMethodPointcut, true);

        class HelloWorld extends HelloTarget {}
        checkAdvice(new HelloWorld(), classMethodPointcut, false);

        class HelloT extends HelloTarget {}
        checkAdvice(new HelloT(), classMethodPointcut, true);
    }

    private void checkAdvice(Object target, Pointcut pointcut, boolean adviced) {
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(target);
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
        Hello proxiedHello = (Hello) pfBean.getObject();

        if (adviced) {
            assertThat(proxiedHello.sayHello("Kyu"), is("HELLO KYU"));
            assertThat(proxiedHello.sayHi("Kyu"), is("HI KYU"));
            assertThat(proxiedHello.sayThankYou("Kyu"), is("Thank You Kyu"));
        } else {
            assertThat(proxiedHello.sayHello("Kyu"), is("Hello Kyu"));
            assertThat(proxiedHello.sayHi("Kyu"), is("Hi Kyu"));
            assertThat(proxiedHello.sayThankYou("Kyu"), is("Thank You Kyu"));
        }
    }

    static class UppercaseAdvice implements org.aopalliance.intercept.MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            String ret = (String) methodInvocation.proceed();
            return ret.toUpperCase();
        }
    }
}
