package com.java.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.java.filter.ErrorHandlerFilter;
import com.java.filter.LoginFilter;

public class MyWebAppInit implements WebApplicationInitializer {

//	extends AbstractAnnotationConfigDispatcherServletInitializer 

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(new Class[] { RootConfig.class });
		rootContext.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		FilterRegistration.Dynamic filter = servletContext.addFilter( "loginFilter", new LoginFilter());
		FilterRegistration.Dynamic filter2 = servletContext.addFilter( "errorHandlerFilter", new ErrorHandlerFilter());
		filter.addMappingForUrlPatterns(null, true, "/proceedToCheckout","/checkout", "/profile", "/updateProfile");
//		,"/checkout"
		filter2.addMappingForUrlPatterns(null, true, "/*");
		
		Dynamic servletOne = servletContext.addServlet("myServlet", new DispatcherServlet());
		servletOne.addMapping("/");
		servletOne.setAsyncSupported(true);
		servletOne.setInitParameter("contextConfigLocation", "com.java.config.SpringConfig");
		servletOne.setInitParameter("contextClass",
				"org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
		servletOne.setLoadOnStartup(1);
	}

//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return new Class<?>[] {RootConfig.class};
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return new Class<?>[] {SpringConfig.class};
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		return new String[]{"/"};
//	}
//
//	@Override
//	protected Filter[] getServletFilters() {
//		return new Filter[] {
//	            new ShallowEtagHeaderFilter()};
//	}
//
//	@Override
//	protected boolean isAsyncSupported() {
//		return true;
//	}

}
