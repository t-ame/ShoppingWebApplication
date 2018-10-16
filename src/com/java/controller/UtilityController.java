package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UtilityController {

	@RequestMapping("./loginpage")
	public ModelAndView getLogin() {
		
		return new ModelAndView("loginPage");
	}
	
	@RequestMapping("./error")
	public ModelAndView goToError() {
		
		ModelAndView mv = new ModelAndView("errorPage");
		
		mv.addObject("errMsg", "Error occured!!!");
		
		return mv;
	}
	
	@RequestMapping("./login")
	public ModelAndView doLogin() {
		
		
		return null;
	}
	
//	@RequestMapping("")
	
	
}
