package com.java.filter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.CacheControl;
import org.springframework.web.servlet.ModelAndView;

import com.java.exception.MyCustomException;

public class ErrorHandlerFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) arg1;
		try {
			resp.addHeader("cache-control", CacheControl.maxAge(3, TimeUnit.HOURS).cachePublic().getHeaderValue());
			arg2.doFilter(arg0, resp);
		} catch (Exception ex) {
			arg0.setAttribute("errorMsg", ex.getMessage());
			ex.printStackTrace();
			arg0.getRequestDispatcher("/WEB-INF/views/errorPage.jsp").forward(arg0, arg1);
		}
	}

}
