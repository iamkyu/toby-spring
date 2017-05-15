package springbook.learningtest.junit;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * @author Kj Nam
 * @since 2017-05-15
 */
public class JunitTest {
    static Set<JunitTest> testObjects = new HashSet<>();

    @Test
    public void test1() {
        assertThat(this, not((Matcher)hasItem(this)));
        testObjects.add(this);
    }

    @Test
    public void test2() {
        assertThat(this, not((Matcher)hasItem(this)));
        testObjects.add(this);
    }

    @Test
    public void test3() {
        assertThat(this, not((Matcher)hasItem(this)));
        testObjects.add(this);
    }
}
