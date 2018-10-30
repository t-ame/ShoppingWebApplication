package com.java.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.java.components.Address;
import com.java.components.Books;
import com.java.components.Card;
import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.Clothing;
import com.java.components.Electronics;
import com.java.components.Home;
import com.java.components.Order;
import com.java.components.Outdoors;
import com.java.components.Product;
import com.java.components.ProductDetail;
import com.java.components.ProductDetailGroup;
import com.java.components.ProductToString;
import com.java.components.User;
import com.java.components.UserDetails;

@Configuration("datasource")
@Import({ MyWebMvcConfigurer.class, DbMigrationConfig.class })
@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RootConfig {

	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.driverName}")
	private String driverName;

	public RootConfig() {
		super();
	}

	@Bean("ds")
	public BasicDataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(url);
		ds.setPassword(password);
		ds.setUsername(username);
		ds.setDriverClassName(driverName);
		ds.setMaxIdle(20);
		ds.setMaxConnLifetimeMillis(3000);
		ds.setMaxTotal(100);
		ds.setMaxWaitMillis(3000);
		return ds;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate(@Autowired BasicDataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean
	public DataSource getDs() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(url);
		ds.setPassword(password);
		ds.setUsername(username);
		ds.setDriverClassName(driverName);
		return ds;
	}

	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
		properties.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
		properties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
		properties.put(org.hibernate.cfg.Environment.AUTOCOMMIT, "false");
		return properties;
	}

	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(getDs());
		bean.setHibernateProperties(hibernateProperties());
		bean.setAnnotatedClasses(Address.class, Card.class, ProductToString.class, Cart.class, CartEntry.class,
				Electronics.class, Clothing.class, Outdoors.class, Home.class, Books.class, ProductDetailGroup.class,
				Order.class, ProductDetail.class, Product.class, User.class, UserDetails.class);
		bean.afterPropertiesSet();
		return bean.getObject();
	}

	@Bean("hibtxmanager")
	public HibernateTransactionManager getHibTxManager() throws IOException {
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(sessionFactory());
		return tx;
	}

//	@Bean("jpatxmanager")
//	public JpaTransactionManager getJpaTxManager() {
//		return null;
//		
//	}

	@Profile("dev")
	@Bean("ps")
	public static PropertySourcesPlaceholderConfigurer getConfigurerDev() {
		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource("database-dev.properties"));
		return cfg;
	}

	@Profile("test")
	@Bean("ps")
	public static PropertySourcesPlaceholderConfigurer getConfigurerTest() {
		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource("database-test.properties"));
		return cfg;
	}

	@Profile("prod")
	@Bean("ps")
	public static PropertySourcesPlaceholderConfigurer getConfigurerProd() {
		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource("database-prod.properties"));
		return cfg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

}
