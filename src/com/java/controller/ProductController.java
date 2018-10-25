package com.java.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.components.Books;
import com.java.components.Clothing;
import com.java.components.Electronics;
import com.java.components.Outdoors;
import com.java.components.Product;
import com.java.components.ProductDetail;
import com.java.components.ProductDetailGroup;
import com.java.exception.MyCustomException;
import com.java.service.ProductServiceImpl;

@Controller
public class ProductController {

	@Autowired
	@Qualifier("productservice")
	ProductServiceImpl productService;

//	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/searchProduct")
	public ModelAndView searchProduct(HttpServletRequest req) throws MyCustomException {

		ModelAndView mv = new ModelAndView("displaySearchedProducts");
		String keys = (String) req.getParameter("searchKeys");
		String className = (String) req.getParameter("className");
		List<Product> products = null;
		if (className != null) {
			if (className.equalsIgnoreCase("all")) {
				products = productService.getProductsWithName(keys, 0);
			} else {
				products = productService.getProductsCategoryWithName(className, keys, 0);
			}
			mv.addObject("products", products);
		}
		mv.addObject("pageUrl", "/searchProduct/");
		mv.addObject("currPage", 0);

		if (products == null || products.size() <= 0) {
			mv.addObject("searchMsg", "Search did not yield any results.");
		}
		return mv;
	}

	@RequestMapping(value = "/categoryProducts/{className}")
	public ModelAndView categoryProduct(@PathVariable("className") String className, HttpServletRequest req)
			throws MyCustomException {

		ModelAndView mv = new ModelAndView("displaySearchedProducts");
		List<Product> products = null;
		if (className != null) {
			products = productService.getProductsFromCategory(className, 0);
			mv.addObject("products", products);
		}
		mv.addObject("pageUrl", "/categoryProducts/" + className + "/");
		mv.addObject("currPage", 0);

		if (products == null || products.size() <= 0) {
			mv.addObject("searchMsg", "Search did not yield any results.");
		}

		return mv;
	}

	@RequestMapping(value = "/displayProduct/{id}")
	public ModelAndView displayProduct(@PathVariable("id") long id) {

		ModelAndView mv = new ModelAndView("displayProduct");
		Product product = productService.getProduct(id);

		System.out.println(product);

		mv.addObject("product", product);

		if (product == null) {
			mv.addObject("displayMsg", "No product found.");
		}

		return mv;
	}

	@RequestMapping(value = "/addProduct")
	public void addProduct() {

		for (Product p : addBooks()) {
			productService.addProduct(p);
		}
		for (Product p : addClothing()) {
			productService.addProduct(p);
		}
		for (Product p : addOutdoors()) {
			productService.addProduct(p);
		}
		for (Product p : addElectronics()) {
			productService.addProduct(p);
		}

	}

	@RequestMapping(value = "/addelect")
	public List<Product> addElectronics() {
		Product prod = null;

		List<Product> products = new ArrayList<>();

		Set<ProductDetailGroup> pg = null;
		Set<ProductDetail> pgd = null;

		ProductDetailGroup pd = null;

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Red"));
		pgd.add(new ProductDetail("Silver"));
		pgd.add(new ProductDetail("Gold"));
		pd = new ProductDetailGroup("color", pgd);
		pg.add(pd);

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("16GB"));
		pgd.add(new ProductDetail("32GB"));
		pgd.add(new ProductDetail("64GB"));
		pgd.add(new ProductDetail("128GB"));
		pd = new ProductDetailGroup("Memory size", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Electronics");
		prod.setImageUrl("images/iphone.jpeg");
		prod.setProductFrequency(20);
		prod.setProductName("iPhone 6s");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Red"));
		pgd.add(new ProductDetail("Silver"));
		pgd.add(new ProductDetail("Dark Grey"));
		pd = new ProductDetailGroup("color", pgd);
		pg.add(pd);

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("256GB"));
		pgd.add(new ProductDetail("500GB"));
		pgd.add(new ProductDetail("1TB"));
		pd = new ProductDetailGroup("Memory size", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Electronics");
		prod.setImageUrl("images/iphone.jpeg");
		prod.setProductFrequency(20);
		prod.setProductName("Macbook Pro");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Red"));
		pgd.add(new ProductDetail("Silver"));
		pgd.add(new ProductDetail("black"));
		pd = new ProductDetailGroup("color", pgd);
		pg.add(pd);

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("16GB"));
		pgd.add(new ProductDetail("32GB"));
		pgd.add(new ProductDetail("64GB"));
		pgd.add(new ProductDetail("128GB"));
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Electronics");
		prod.setImageUrl("images/iphone.jpeg");
		prod.setProductFrequency(20);
		prod.setProductName("Samsung s6. with earphones and charger");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		return products;
	}

	@RequestMapping(value = "/addcloth")
	public List<Product> addClothing() {

		Product prod = null;

		List<Product> products = new ArrayList<>();

		Set<ProductDetailGroup> pg = null;
		Set<ProductDetail> pgd = null;

		ProductDetailGroup pd = null;

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Red"));
		pgd.add(new ProductDetail("Blue"));
		pgd.add(new ProductDetail("Green"));
		pd = new ProductDetailGroup("color", pgd);
		pg.add(pd);

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("S"));
		pgd.add(new ProductDetail("M"));
		pgd.add(new ProductDetail("L"));
		pgd.add(new ProductDetail("XL"));
		pd = new ProductDetailGroup("Size", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Clothing");
		prod.setImageUrl("images/booties.jpeg");
		prod.setProductFrequency(20);
		prod.setProductName("Chunky heeled Booties with chains");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Checked"));
		pgd.add(new ProductDetail("spotted"));
		pgd.add(new ProductDetail("striped"));
		pd = new ProductDetailGroup("pattern", pgd);
		pg.add(pd);

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("S"));
		pgd.add(new ProductDetail("M"));
		pgd.add(new ProductDetail("L"));
		pgd.add(new ProductDetail("XL"));
		pd = new ProductDetailGroup("Size", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Clothing");
		prod.setImageUrl("images/booties.jpeg");
		prod.setProductFrequency(20);
		prod.setProductName("Patterned shirt with huge buckle");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("straight"));
		pgd.add(new ProductDetail("half cut"));
		pgd.add(new ProductDetail("pencil"));
		pd = new ProductDetailGroup("style", pgd);
		pg.add(pd);

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("S"));
		pgd.add(new ProductDetail("M"));
		pgd.add(new ProductDetail("XL"));
		pgd.add(new ProductDetail("XXL"));
		pd = new ProductDetailGroup("Size", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Clothing");
		prod.setImageUrl("images/booties.jpeg");
		prod.setProductFrequency(20);
		prod.setProductName("Levi Denim pants with huge zippers");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		return products;
	}

	@RequestMapping(value = "/addbook")
	public List<Product> addBooks() {
		Product prod = null;

		List<Product> products = new ArrayList<>();

		Set<ProductDetailGroup> pg = null;
		Set<ProductDetail> pgd = null;

		ProductDetailGroup pd = null;

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Hard cover"));
		pgd.add(new ProductDetail("Paper back"));
		pd = new ProductDetailGroup("Cover", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Books");
		prod.setImageUrl("images/javascriptbook.jpg");
		prod.setProductFrequency(20);
		prod.setProductName("A Summer Night by J.P. Schuler");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Hard cover"));
		pgd.add(new ProductDetail("Paper back"));
		pd = new ProductDetailGroup("Cover", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Books");
		prod.setImageUrl("images/javascriptbook.jpg");
		prod.setProductFrequency(20);
		prod.setProductName("The Woman by the Window by R.T. Jar");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Hard cover"));
		pgd.add(new ProductDetail("Paper back"));
		pd = new ProductDetailGroup("Cover", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Books");
		prod.setImageUrl("images/javascriptbook.jpg");
		prod.setProductFrequency(20);
		prod.setProductName("Jump into your job with a lot of gusto by Mi Yun");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		return products;
	}

	@RequestMapping(value = "/addout")
	public List<Product> addOutdoors() {
		Product prod = null;

		List<Product> products = new ArrayList<>();

		Set<ProductDetailGroup> pg = null;
		Set<ProductDetail> pgd = null;

		ProductDetailGroup pd = null;

		pg = new HashSet<>();

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Outdoors");
		prod.setImageUrl("images/mountaingear.jpg");
		prod.setProductFrequency(20);
		prod.setProductName("Thick rope for mountain climbing");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Small"));
		pgd.add(new ProductDetail("Medium"));
		pgd.add(new ProductDetail("Large"));
		pd = new ProductDetailGroup("size", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Outdoors");
		prod.setImageUrl("images/mountaingear.jpg");
		prod.setProductFrequency(20);
		prod.setProductName("Gardening shears.");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		pg = new HashSet<>();

		pgd = new HashSet<>();
		pgd.add(new ProductDetail("Thick"));
		pgd.add(new ProductDetail("Light"));
		pd = new ProductDetailGroup("cover", pgd);
		pg.add(pd);

		prod = new Product();
		prod.setProductDetails(pg);
		prod.setCatName("Outdoors");
		prod.setImageUrl("images/mountaingear.jpg");
		prod.setProductFrequency(20);
		prod.setProductName("Winter jacket with extra removable cover layer");
		prod.setProductRating(5);
		prod.setStockQuantity(200);
		prod.setUnitPrice(100);
		prod.setProductDescription("Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet. Lorem ipsum dolor sit amet, consectetur\n"
				+ "									adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor\n"
				+ "									sit amet.");

		products.add(prod);

		return products;
	}

}