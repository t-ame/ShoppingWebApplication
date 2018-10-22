package com.java.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.components.User;
import com.java.components.UserDetails;
import com.java.exception.MyCustomException;

@Controller
@ControllerAdvice
@SessionAttributes(names = { "user", "userdetails" })
public class MyMasterController {

	@ModelAttribute("user")
	public User initUser() {
		return new User();
	}

	@ModelAttribute("userdetails")
	public UserDetails initUserDetails() {
		return new UserDetails();
	}

	@ExceptionHandler(MyCustomException.class)
	public ModelAndView handleCustomException(MyCustomException ex) {

		ModelAndView model = new ModelAndView("errorPage");
		model.addObject("errorMsg", ex.getMessage());

		return model;
	}

	@ExceptionHandler(DataAccessException.class) // thrown by JdbcTemplate
	public ModelAndView handleDataAccessException(DataAccessException ex) {

		ModelAndView model = new ModelAndView("errorPage");
		model.addObject("errMsg", ex.getMessage());

		return model;

	}

//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleAllException(Exception ex) {
//
//		ModelAndView model = new ModelAndView("errorPage");
//		model.addObject("errMsg", ex.getMessage());
//
//		return model;
//
//	}

}
