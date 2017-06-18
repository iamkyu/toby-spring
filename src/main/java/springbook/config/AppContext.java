package springbook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Kj Nam
 * @since 2017-06-17
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "springbook.user")
@Import(SqlServiceContext.class)
public class AppContext {
}
