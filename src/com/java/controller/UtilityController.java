package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.java.components.Cart;
import com.java.components.User;
import com.java.components.UserDetails;
import com.java.exception.MyCustomException;
import com.java.service.UserServiceImpl;

@Controller
@SessionAttributes(names = { "user", "cart" })
public class UtilityController {

	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;

	@RequestMapping("./error")
	public ModelAndView goToError() {

		ModelAndView mv = new ModelAndView("errorPage");

		mv.addObject("errorMsg", "Error occured!!!");

		return mv;
	}

	@RequestMapping("/logout")
	public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response, SessionStatus status) throws MyCustomException {

		HttpSession session = request.getSession();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			Cart cart = (Cart) session.getAttribute("cart");
			if (user != null && user.getUserEmail() != null) {
				UserDetails details = user.getUserDetails();
				
				System.out.println(details);
				
				if (cart != null && cart.getCartEntries().size() > 0) {
					if (details != null) {
						details.setCart(cart);
					} else {
						details = new UserDetails();
						details.setCart(cart);
					}
					user.setUserDetails(details);
				}
				userService.updateUser(user);
				session.setAttribute("user", null);
				session.setAttribute("userdetails", null);
				session.setAttribute("cart", null);

				status.setComplete();
				try {
					request.getRequestDispatcher("/home").forward(request, response);
				} catch (ServletException | IOException e) {
					throw new MyCustomException("Could not redirect to home page.");
				}
			}
		}
		ModelAndView mv = new ModelAndView("errorPage");
		mv.addObject("errorMsg", "Cannot logout when not logged in.");
		return mv;
	}

//	@RequestMapping("")

}
