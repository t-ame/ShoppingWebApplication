package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.components.Product;
import com.java.exception.MyCustomException;
import com.java.service.ProductServiceImpl;

@Controller
public class ProductController {

	@Autowired
	@Qualifier("productservice")
	ProductServiceImpl productService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchProduct")
	public ModelAndView searchProduct(HttpServletRequest req) throws MyCustomException {

//		System.out.println("Inside searchProduct");

		ModelAndView mv = new ModelAndView("displaySearchedProducts");
		String keys = (String) req.getParameter("searchKeys");
		String className = (String) req.getParameter("className");
		List<Product> products = null;
		if (className != null && keys != null) {
			if (className.equalsIgnoreCase("all")) {
				products = productService.getProductsWithName(keys);
				mv.addObject("products", products);
			} else {
				try {
					Class<? extends Product> c = (Class<? extends Product>) Class.forName("com.java.components."+className);
					products = productService
							.getProductsCategoryWithName(c, keys);
				} catch (ClassNotFoundException e) {
					throw new MyCustomException(e.getMessage());
				}
			}
		}
		
		System.out.println(products);
		
		if (products == null || products.size()<=0) {
			mv.addObject("searchMsg", "Search did not yield any results.");
		}
		// IMPLEMENT PAGINATION...

		return mv;
	}

	@RequestMapping(value = "/categoryProducts/{className}", method = RequestMethod.POST)
	public ModelAndView categoryProduct(@PathVariable("className") String className) throws MyCustomException {

		ModelAndView mv = new ModelAndView("displaySearchedProducts");
		List<Product> products;
		try {
			products = productService.getProductsFromCategory((Class<? extends Product>) Class.forName(className));
		} catch (ClassNotFoundException e) {
			throw new MyCustomException("Could not display products for category " + className + " " + e.getMessage());
		}

		mv.addObject("products", products);

		// IMPLEMENT PAGINATION...

		return mv;
	}

	@RequestMapping(value = "/displayProduct/{id}")
	public ModelAndView displayProduct(@PathVariable("id") long id) {

		ModelAndView mv = new ModelAndView("displayProduct");
		Product product = productService.getProduct(id);
		mv.addObject("product", product);

		if (product == null) {
			mv.addObject("displayMsg", "No product found.");
		}

		return mv;
	}

}
