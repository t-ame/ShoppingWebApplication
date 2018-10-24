package com.java.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.Product;
import com.java.components.ProductDetailGroup;
import com.java.service.ProductServiceImpl;

@Controller
@SessionAttributes(names = { "cart", "user" })
public class CartController {

	@Autowired
	@Qualifier("productservice")
	ProductServiceImpl productService;

	@ModelAttribute
	public void init() {

	}

	@RequestMapping(value = "/showCart")
	public ModelAndView showCart() {

		return null;
	}

	@RequestMapping(value = "/addToCart/{id}", method = RequestMethod.POST)
	public ModelAndView addToCart(@PathVariable("id") long id, HttpServletRequest req, HttpServletResponse resp) {

		ModelAndView mv = new ModelAndView("displayCart");

		Product product = productService.getProduct(id);
		HttpSession session = req.getSession(true);
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			cart.setCartEntries(new ArrayList<>());
		}
		CartEntry entry = new CartEntry();

		if (product != null) {
			List<ProductDetailGroup> details = product.getProductDetails();
			List<String> pd = new ArrayList<>();

			for (ProductDetailGroup d : details) {
				pd.add((String) req.getParameter(d.getGroupName()));
			}
			entry.setCartEntryDetails(pd);
			entry.setQuantity(Integer.parseInt((String)req.getParameter("quantity")));
			entry.setProduct(product);
			cart.getCartEntries().add(entry);

			session.setAttribute("cart", cart);

		} else {
			mv.addObject("addCartMsg", "Invalid product item.");
		}

		return mv;
	}

	@RequestMapping(value = "/changeProductQuantity", method = RequestMethod.POST)
	public ModelAndView changeProductQuantity() {

		return null;
	}

	@RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public ModelAndView removeFromCart() {

		return null;
	}

}
