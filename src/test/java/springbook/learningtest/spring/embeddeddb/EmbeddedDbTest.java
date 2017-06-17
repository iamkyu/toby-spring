package springbook.learningtest.spring.embeddeddb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * @author Kj Nam
 * @since 2017-06-17
 */
public class EmbeddedDbTest {
    EmbeddedDatabase db;
    JdbcTemplate template;

    @Before
    public void setUp() {
        db = new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScript("classpath:test-schema.sql")
                .addScript("classpath:test-data.sql")
                .build();

        template = new JdbcTemplate(db);
    }

    @After
    public void tearDown() {
        db.shutdown();
    }

    @Test
    public void initData() {
        assertThat(template.queryForObject("SELECT count(*) FROM SQLMAP", Integer.class), is(2));

        List<Map<String, Object>> list = template.queryForList("SELECT * FROM SQLMAP ORDER BY KEY_");
        assertThat(list.get(0).get("key_"), is("KEY1"));
        assertThat(list.get(0).get("sql_"), is("SQL1"));
        assertThat(list.get(1).get("key_"), is("KEY2"));
        assertThat(list.get(1).get("sql_"), is("SQL2"));
    }

    @Test
    public void insert() {
        template.update("INSERT INTO SQLMAP(KEY_, SQL_) VALUES(?, ?)", "KEY3", "SQL3");
        assertThat(template.queryForObject("SELECT count(*) FROM SQLMAP", Integer.class), is(3));
    }
}
