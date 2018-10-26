package com.java.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
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
@Import({MyWebMvcConfigurer.class, DbMigrationConfig.class})
@EnableWebMvc
public class RootConfig {

	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;
	@Value("${jdbc.driverName}")
	private String driverName;

	private SessionFactory sessionFactory = null;

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

	private SessionFactory initializeFactory() {
		SessionFactory sf;

		org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration()
				.addPackage("com.java.components"); // package containing the entity classes.
		config.setProperty(Environment.HBM2DDL_AUTO, "update");
		config.addAnnotatedClass(Address.class).addAnnotatedClass(Card.class).addAnnotatedClass(ProductToString.class)
				.addAnnotatedClass(Cart.class).addAnnotatedClass(CartEntry.class).addAnnotatedClass(Electronics.class)
				.addAnnotatedClass(Clothing.class).addAnnotatedClass(Outdoors.class).addAnnotatedClass(Home.class)
				.addAnnotatedClass(Books.class).addAnnotatedClass(ProductDetailGroup.class)
				.addAnnotatedClass(Order.class).addAnnotatedClass(ProductDetail.class).addAnnotatedClass(Product.class)
				.addAnnotatedClass(User.class).addAnnotatedClass(UserDetails.class);
		config.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
		config.setProperty("hibernate.connection.username", username);
		System.out.println(username);
		config.setProperty("hibernate.connection.password", password);
		System.out.println(password);
		config.setProperty("hibernate.connection.url", url);
		System.out.println(url);
		config.setProperty("hibernate.connection.driver_class", driverName);
		System.out.println(driverName);
		config.setProperty(Environment.SHOW_SQL, "true");
		StandardServiceRegistryBuilder rb = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		System.out.println("Service being done.");
		sf = config.buildSessionFactory(rb.build());
		System.out.println("Service done.");

		return sf;
	}

	@Bean
	public SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			return initializeFactory();
		else
			return sessionFactory;
	}

	@Profile("dev")
	@Bean("ps")
	public static PropertySourcesPlaceholderConfigurer getConfigurerDev() {
//		System.out.println("dev ps");
		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource("database-dev.properties"));
		return cfg;
	}

	@Profile("test")
	@Bean("ps")
	public static PropertySourcesPlaceholderConfigurer getConfigurerTest() {
//		System.out.println("test ps");
		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
		cfg.setLocation(new ClassPathResource("database-test.properties"));
		return cfg;
	}
	
	@Profile("prod")
	@Bean("ps")
	public static PropertySourcesPlaceholderConfigurer getConfigurerProd() {
//		System.out.println("prod ps");
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
