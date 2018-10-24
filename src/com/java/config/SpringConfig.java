package com.java.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.java.components.Address;
import com.java.components.Card;
import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.Order;
import com.java.components.Product;
import com.java.components.ProductCategory;
import com.java.components.ProductDetail;
import com.java.components.ProductDetailGroup;
import com.java.components.UserDetails;

//@Import(MyWebMvcConfigurer.class)
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.java.components","com.java.controller","com.java.repository","com.java.service"})
public class SpringConfig {
	public SpringConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

//	@Autowired
//	static BasicDataSource ds;
//	
//	private static String url = "jdbc:mysql://localhost:3306/Connections?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//	private static String username = "root";
//	private static String password = "Special.girl1";
//	private static String driver_class = "com.mysql.jdbc.Driver";

	

//	@Bean("ps")
//	public static PropertySourcesPlaceholderConfigurer getConfigurer() {
//		PropertySourcesPlaceholderConfigurer cfg = new PropertySourcesPlaceholderConfigurer();
//		cfg.setLocation(new ClassPathResource("database.properties"));
//		return cfg;
//	}

//	@Bean
//	public ViewResolver getResolver() {
//		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
//	}

//	@Bean
////	@DependsOn("ds")
//	public static SessionFactory getSessionFactory() {
//		SessionFactory sf;
//
//		org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration()
//				.addPackage("com.java.components"); // package containing the entity class.
//		config.setProperty(Environment.HBM2DDL_AUTO, "create");
//		config.addAnnotatedClass(Address.class).addAnnotatedClass(Card.class).addAnnotatedClass(ProductCategory.class)
//				.addAnnotatedClass(ProductDetail.class).addAnnotatedClass(ProductDetailGroup.class)
//				.addAnnotatedClass(Product.class).addAnnotatedClass(CartEntry.class).addAnnotatedClass(Cart.class)
//				.addAnnotatedClass(UserDetails.class).addAnnotatedClass(OrderDetail.class).addAnnotatedClass(Order.class);
//		config.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//		config.setProperty("hibernate.connection.username", username);
//		config.setProperty("hibernate.connection.password", password);
//		config.setProperty("hibernate.connection.url", url);
//		config.setProperty("hibernate.connection.driver_class", driver_class);
//		config.setProperty(Environment.SHOW_SQL, "true");
//		StandardServiceRegistryBuilder rb = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
//		sf = config.buildSessionFactory(rb.build());
//
//		return sf;
//	}

}
