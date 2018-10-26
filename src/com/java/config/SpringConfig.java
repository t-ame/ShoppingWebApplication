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


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.java.components","com.java.controller","com.java.repository","com.java.service"})
public class SpringConfig {
	public SpringConfig() {
		super();
	}


}
