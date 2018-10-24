package com.java.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.java.components.User;
import com.java.util.LastState;

@Component
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession(true);
//		req.getContextPath().indexOf('/', 1);
		User user = (User) session.getAttribute("user");
		if (user == null || user.getUserEmail() == null) {
			LastState st = new LastState();
			int i = req.getContextPath().indexOf('/', 1);
			System.out.println(i);
			System.out.println(req.getContextPath());
			System.out.println(req.getRequestURI());
			if(i == -1) {
				st.setLastUri(req.getRequestURI());
			} else {
				st.setLastUri(req.getRequestURI().substring(i));
			}
			System.out.println(st);
			session.setAttribute("laststate", st);
			req.getRequestDispatcher("/login").forward(req, resp);
		}

		chain.doFilter(request, response);
	}

}
