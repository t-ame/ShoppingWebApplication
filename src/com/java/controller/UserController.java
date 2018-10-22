package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.components.User;
import com.java.components.UserDetails;
import com.java.exception.MyCustomException;
import com.java.service.UserServiceImpl;
import com.java.util.LastState;

@Controller
@SessionAttributes(names = { "user", "login", "userdetails"})
public class UserController {

	
	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;
	
	@RequestMapping(path = "/loginRed")
	public ModelAndView loginRedirect(HttpServletRequest req) throws MyCustomException {

		ModelAndView mv = new ModelAndView("loginPage");
		LastState laststate = new LastState();
		laststate.setLastUri((String) req.getAttribute("lasturi"));
		req.getSession(true).setAttribute("laststate", laststate);
		
		return mv;
	}

	@RequestMapping(path = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(@ModelAttribute("user") User user, HttpServletRequest req) throws MyCustomException {

		ModelAndView mv = new ModelAndView("errorPage");
		
		user = userService.getUser(user.getUserEmail());
		if(user.getUserEmail() != null) {
			try {
				req.getRequestDispatcher(((LastState)req.getAttribute("laststate")).getLastUri()).forward(req, null);
			} catch (ServletException | IOException e) {
				throw new MyCustomException("Cannot forward request to previous page.");
			}
		} else {
			mv.addObject("errorMsg", "Invalid username or password");
			mv.setViewName("loginPage");
		}
		
		return mv;
	}

	@RequestMapping(path = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("user") User user, @ModelAttribute("userdetails") UserDetails details) {
		ModelAndView mv = new ModelAndView("loginPage");
		user.setUserDetails(details);
		userService.addUser(user);
		return mv;
	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(@ModelAttribute("user") User user, HttpServletRequest req) throws MyCustomException {

		userService.deleteUser(user);
		try {
			req.getRequestDispatcher("/home").forward(null, null);
		} catch (ServletException | IOException e) {
			throw new MyCustomException("Couldn't redirect to home page.");
		}
		
		return null;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("user") User user, @ModelAttribute("userdetails") UserDetails details) {

		user.setUserDetails(details);
		userService.updateUser(user);
		
		//NOT COMPLETE!!!
		
		return null;
	}

}
