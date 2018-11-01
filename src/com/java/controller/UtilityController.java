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
import com.java.exception.MyCustomException;
import com.java.service.CartServiceImpl;
import com.java.service.UserServiceImpl;

@Controller
@SessionAttributes(names = { "user", "cart" })
public class UtilityController {

	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;

	@Autowired
	private CartServiceImpl cartService;

	@RequestMapping("./error")
	public ModelAndView goToError() {

		ModelAndView mv = new ModelAndView("errorPage");

		mv.addObject("errorMsg", "Error occured!!!");

		return mv;
	}

	@RequestMapping("/logout")
	public ModelAndView doLogout(HttpServletRequest request, HttpServletResponse response, SessionStatus status)
			throws MyCustomException {

		ModelAndView mv = new ModelAndView("redirect:home");
		
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("user") != null) {
//			User user = (User) session.getAttribute("user");
//			Cart cart = (Cart) session.getAttribute("cart");
//			if (user != null && user.getUserEmail() != null) {
////				System.out.println("utility "+user);
//				if (cart != null) {
//					cartService.updateCart(user, cart);
//				}
			session.removeAttribute("user");
			session.removeAttribute("userdetails");
			session.removeAttribute("cart");
			status.setComplete();
//			}
		} else {
			mv.setViewName("errorPage");
			mv.addObject("errorMsg", "Cannot logout when not logged in.");
		}
		return mv;
	}

}
