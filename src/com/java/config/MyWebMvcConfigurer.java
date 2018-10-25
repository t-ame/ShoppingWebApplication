package com.java.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@EnableWebMvc
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	@Bean("localeResolver")
	public LocaleResolver getLocaleResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}

	@Bean("messageSource")
	public static ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource src = new ResourceBundleMessageSource();
		src.addBasenames("locale");
		src.setDefaultEncoding("UTF-8");
		return src;
	}

	@Bean("localeChangeInterceptor")
	public LocaleChangeInterceptor getLocaleChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("language");
//		System.out.println("locale");
		return interceptor;
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configureAsyncSupport(configurer);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getLocaleChangeInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/style/**").addResourceLocations("/style/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addRedirectViewController("/add", "/addProduct");
		registry.addRedirectViewController("/", "../../index");
		registry.addViewController("/login").setViewName("loginPage");
		registry.addViewController("/register").setViewName("registrationPage");
		registry.addViewController("/home").setViewName("../../index");
//		registry.addViewController("/").setViewName("../../index");
		registry.addViewController("/cart").setViewName("displayCart");
		registry.addViewController("/checkout").setViewName("paymentPage");
		registry.addViewController("/add").setViewName("addProduct");
	}

}
