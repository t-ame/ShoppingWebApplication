package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(names= {"cart", "user"})
public class CartController {

	@ModelAttribute
	public void init() {
		
	}
	

	@RequestMapping(value="/showCart", method=RequestMethod.POST)
	public ModelAndView showCart() {
		
		
		
		return null;
	}
	

	@RequestMapping(value="/addToCart", method=RequestMethod.POST)
	public ModelAndView addToCart() {
		
		return null;
	}
	
	@RequestMapping(value="/changeProductQuantity", method=RequestMethod.POST)
	public ModelAndView changeProductQuantity() {
		
		return null;
	}
	
	@RequestMapping(value="/removeFromCart", method=RequestMethod.POST)
	public ModelAndView removeFromCart() {
		
		return null;
	}
	
	
	
}
