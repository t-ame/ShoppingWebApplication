//package com.java.util;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.flywaydb.core.Flyway;
//
//import com.java.exception.GeneralException;
//
//@WebListener
//public class MyFlyway implements ServletContextListener {
//	
//	BasicDataSource ds;
//
//	@Override
//	public void contextInitialized(ServletContextEvent event) {
//	
//			Flyway flyway = new Flyway();
//
//			flyway.setBaselineOnMigrate(true);
//			flyway.setValidateOnMigrate(true);
//			flyway.setLocations("classpath:/com.db.migrate");
//			flyway.setDataSource(ds);
//
//			flyway.migrate();
//
//	}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent event) {
//
//	}
//
//}
