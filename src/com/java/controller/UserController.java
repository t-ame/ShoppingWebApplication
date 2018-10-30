package com.java.controller;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.components.Address;
import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.User;
import com.java.components.UserDetails;
import com.java.exception.MyCustomException;
import com.java.service.UserServiceImpl;
import com.java.util.LastState;

@Controller
@SessionAttributes(names = { "user", "login", "userdetails", "address", "cart" })
public class UserController {

	@ModelAttribute("user")
	public User initUser() {
		return new User();
	}

	@ModelAttribute("userdetails")
	public UserDetails initUserDetails() {
		return new UserDetails();
	}

	@ModelAttribute("address")
	public Address initAddress() {
		return new Address();
	}

	@ModelAttribute("cart")
	public Cart initCart() {
		return new Cart();
	}

	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;
	

	@RequestMapping(path = "/loggedin")
	public ModelAndView loggedIn(@ModelAttribute("user") User user, HttpServletRequest req, HttpServletResponse resp) {
//		ModelAndView mv = new ModelAndView("../../index");
		
		return new ModelAndView("../../index");
	}
			
	@RequestMapping(path = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(@ModelAttribute("user") User user, HttpServletRequest req, HttpServletResponse resp)
			throws MyCustomException {

		ModelAndView mv = new ModelAndView("errorPage");
		HttpSession session = req.getSession();
		LastState ls = (LastState) session.getAttribute("laststate");

		String email = user.getUserEmail();
		String password = user.getUserPassword();
		user = userService.getUser(email);
		if (user != null && user.getUserEmail() != null && user.getUserPassword().equals(password)) {
			System.out.println("login  " + user);
			session.setAttribute("userdetails", user.getUserDetails());

			Cart cart = (Cart) session.getAttribute("cart");
			if (cart != null&& user.getUserDetails() != null && user.getUserDetails().getCart() != null) {
				cart.addEntries(user.getUserDetails().getCart().getCartEntries());
			} else {
				if(user.getUserDetails() != null ) {
					cart = user.getUserDetails().getCart();
				}
				if(cart == null) {
					cart = new Cart();
				}
			}
			for (CartEntry entry : cart.getCartEntries()) {
				entry.setCartEntryId(0);
			}
			session.setAttribute("cart", cart);

			try {
				if (ls != null) {
					req.getRequestDispatcher(ls.getLastUri()).forward(req, resp);
				} else {
					mv.setViewName("redirect:loggedin");
				}
			} catch (ServletException | IOException e) {
				throw new MyCustomException(e.getMessage());
			}
		} else {
			req.getSession().removeAttribute("user");
			mv.addObject("errorMsg", "Invalid username or password");
			mv.setViewName("loginPage");
		}

		return mv;
	}

	@RequestMapping(path = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("user") User user,
			@ModelAttribute("userdetails") UserDetails details) {
		ModelAndView mv = new ModelAndView("redirect:login");
		user.setUserDetails(details);
		userService.addUser(user);
		return mv;
	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(@ModelAttribute("user") User user, HttpServletRequest req, HttpServletResponse resp)
			throws MyCustomException {
		ModelAndView mv = new ModelAndView("redirect:loggedin");
		userService.deleteUser(user);

		req.getSession().removeAttribute("user");
		
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

	@RequestMapping(value = "/profile")
	public ModelAndView userAccount(HttpServletRequest req) {

		ModelAndView mv = new ModelAndView("profilePage");

		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("user") != null
				&& ((User) session.getAttribute("user")).getUserEmail() != null) {
			User user = (User) session.getAttribute("user");

			if (user.getUserDetails() == null || user.getUserDetails().getUserId() <= 0) {
				user = userService.getUser(user.getUserEmail());
			}
			mv.addObject("user", user);
		} else {
			mv.addObject("errorMsg", "Cannot access profile when not logged in.");
			mv.setViewName("errorPage");
		}

		return mv;
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ModelAndView updateProfile(@ModelAttribute("user") User user,
			@Valid @ModelAttribute("userdetails") UserDetails details, @ModelAttribute("address") Address address,
			HttpServletRequest req, HttpServletResponse resp, BindingResult result) throws MyCustomException {

		if (result.hasErrors()) {
			req.setAttribute("formError", result.getAllErrors());
			try {
				req.getRequestDispatcher("/profile").forward(req, resp);
			} catch (ServletException | IOException e) {
				throw new MyCustomException(e.getMessage());
			}
		}

		String gender = req.getParameter("genders");
		if (user != null && user.getUserEmail() != null && user.getUserPassword() != null && user.getUserEmail() != ""
				&& user.getUserPassword() != "" && details != null) {
			details.setAddresses(new HashSet<>());
			details.addAddress(address);
			if (gender.equalsIgnoreCase("female")) {
				details.setGender(UserDetails.Gender.FEMALE);
			} else {
				details.setGender(UserDetails.Gender.MALE);
			}
			user.setUserDetails(details);
			userService.updateUser(user, address);
		}
		return new ModelAndView("redirect:profile");
	}

}
