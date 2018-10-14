package com.java.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.java.util.MyDataSource;

@Import(MyDataSource.class)
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.java")
public class SpringConfig {
	@Autowired
	BasicDataSource ds;

//	@Bean("ps")
//	public static PropertySourcesPlaceholderConfigurer getConfigurer() {
//		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
//		cfg.setLocation(new ClassPathResource("database.properties"));
//		return cfg;
//	}

	@Bean(initMethod = "migrate", value = "flyway")
	public Flyway getFlyway() {
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate(true);
		flyway.setValidateOnMigrate(true);
		flyway.setLocations("classpath:/migration");
		flyway.setDataSource(ds);
		return flyway;
	}

	@Bean
	public ViewResolver getResolver() {
		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	}
	
	
	

}
