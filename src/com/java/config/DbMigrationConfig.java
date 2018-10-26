package com.java.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class DbMigrationConfig {
	
	@Autowired DataSource ds;

	@Bean(name="flyway", initMethod="migrate")
	@Conditional(DataSourceMigration.class)
	public Flyway getFlyway() {
		Flyway flyway= new Flyway() ;
		flyway.setDataSource(ds);
		flyway.setBaselineOnMigrate(true);
		flyway.setLocations("classpath:/com/db/migrate");
		return flyway;
	}

}

class DataSourceMigration implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String migrationNeeded= context.getEnvironment().getProperty("shouldMigrate","false");
		return Boolean.parseBoolean(migrationNeeded);
	}
	
}