package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@SessionAttributes(names = { "user", "login", "userdetails" })
public class UserController {
	
	@ModelAttribute("user")
	public User initUser() {
		return new User();
	}

	@ModelAttribute("userdetails")
	public UserDetails initUserDetails() {
		return new UserDetails();
	}

	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;

	@RequestMapping(path = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(@ModelAttribute("user") User user, HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("redirect:errorPage");
		LastState ls = (LastState) req.getSession().getAttribute("laststate");

		String email = user.getUserEmail();
		String password= user.getUserPassword();
		user = userService.getUser(email);
		if (user != null && user.getUserEmail() != null && user.getUserPassword().equals(password)) {
//			System.out.println(user);
			try {
				if (ls != null) {
					System.out.println(ls.getLastUri());
					req.getRequestDispatcher(ls.getLastUri()).forward(req, resp);
				} else {
					mv.setViewName("redirect:../../index");
				}
			} catch (ServletException | IOException e) {
				throw new MyCustomException(e.getMessage());
			}
		} else {
			req.getSession().removeAttribute("user");
			mv.addObject("errorMsg", "Invalid username or password");
			mv.setViewName("redirect:loginPage");
		}

		return mv;
	}

	@RequestMapping(path = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("user") User user,
			@ModelAttribute("userdetails") UserDetails details) {
		ModelAndView mv = new ModelAndView("redirect:loginPage");
		user.setUserDetails(details);
		userService.addUser(user);
		return mv;
	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(@ModelAttribute("user") User user, HttpServletRequest req) throws MyCustomException {
		ModelAndView mv = new ModelAndView("redirect:../../index");
		userService.deleteUser(user);
		try {
			req.getRequestDispatcher("/home").forward(null, null);
		} catch (ServletException | IOException e) {
			throw new MyCustomException(e.getMessage());
		}

		return mv;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("user") User user,
			@ModelAttribute("userdetails") UserDetails details) {

		user.setUserDetails(details);
		userService.updateUser(user);

		// NOT COMPLETE!!!

		return null;
	}
	
	@RequestMapping(value = "/myAccount/{id}", method = RequestMethod.POST)
	public ModelAndView userAccount(@ModelAttribute("user") User user,
			@ModelAttribute("userdetails") UserDetails details, @PathVariable("id") long id) {

//		user.setUserDetails(details);
//		userService.updateUser(user);
		
		

		// NOT COMPLETE!!!

		return null;
	}
	

}
