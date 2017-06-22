package springbook.learningtest.spring.oxm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import springbook.user.sqlservice.jaxb.SqlType;
import springbook.user.sqlservice.jaxb.Sqlmap;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Kj Nam
 * @since 2017-06-15
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:oxmtest-context.xml")
public class OxmTest {
    @Autowired Unmarshaller unmarshaller;

    @Test
    public void readSqlMap() throws JAXBException, IOException {
        Source xmlSource = new StreamSource(this.getClass().getClassLoader().getResourceAsStream("test-sqlmap.xml"));

        Sqlmap sqlmap = (Sqlmap) this.unmarshaller.unmarshal(xmlSource);
        List<SqlType> sqlList = sqlmap.getSql();

        assertThat(sqlList.size(), is(3));

        assertThat(sqlList.get(0).getKey(), is("add"));
        assertThat(sqlList.get(0).getValue(), is("insert"));

        assertThat(sqlList.get(1).getKey(), is("get"));
        assertThat(sqlList.get(1).getValue(), is("select"));

        assertThat(sqlList.get(2).getKey(), is("delete"));
        assertThat(sqlList.get(2).getValue(), is("delete"));
    }
}

