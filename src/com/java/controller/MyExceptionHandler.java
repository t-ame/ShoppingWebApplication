package com.java.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.java.exception.DatabaseException;

@ControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(DatabaseException.class)
	public ModelAndView handleCustomException(DatabaseException ex) {

		ModelAndView model = new ModelAndView("errorPage");
		model.addObject("errorMsg", ex.getMessage());

		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("errorPage");
		model.addObject("errMsg", ex.getMessage());

		return model;

	}

}
