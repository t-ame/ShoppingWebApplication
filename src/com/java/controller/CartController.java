package com.java.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.components.Card;
import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.Order;
import com.java.components.Product;
import com.java.components.ProductDetailGroup;
import com.java.components.User;
import com.java.components.UserDetails;
import com.java.exception.MyCustomException;
import com.java.service.CartServiceImpl;
import com.java.service.ProductServiceImpl;
import com.java.service.UserServiceImpl;
import com.java.util.OrderSorter;

@Controller
@SessionAttributes(names = { "cart", "user", "card" })
public class CartController {

	@Autowired
	@Qualifier("productservice")
	ProductServiceImpl productService;

	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;

	@Autowired
	private CartServiceImpl cartService;

	@ModelAttribute("card")
	public Card initCard() {
		return new Card();
	}

	@RequestMapping(value = "/addToCart")
	public ModelAndView addToCart(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("redirect:cart");

		String parameter = req.getParameter("id");

		if (parameter == null || parameter.isEmpty() || parameter.matches("\\D+")) {
			throw new MyCustomException("Malformed Url.");
		}

		Long id = Long.parseLong(req.getParameter("id"));

		Product product = productService.getProduct(id);
		HttpSession session = req.getSession(true);
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		if (cart == null) {
			cart = new Cart();
			cart.setCartEntries(new ArrayList<>());
		}
		CartEntry entry = new CartEntry();

		if (product != null) {
			Set<ProductDetailGroup> details = product.getProductDetails();
			List<String> pd = new ArrayList<>();

			for (ProductDetailGroup d : details) {
				pd.add((String) req.getParameter(d.getGroupName()));
			}
			entry.setCartEntryDetails(pd);
			entry.setQuantity(Integer.parseInt((String) req.getParameter("quantity")));
			entry.setProduct(product);
			if (user != null && user.getUserEmail() != null) {
				entry = cartService.addToCart(user, entry);
			}
			cart.getCartEntries().add(entry);
			session.setAttribute("cart", cart);
		} else {
			throw new MyCustomException("Invalid product item, cannot add to cart");
		}

		return mv;
	}

	@RequestMapping(value = "/removeFromCart")
	public ModelAndView removeFromCart(@RequestParam("index") int index, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("redirect:cart");
		HttpSession session = req.getSession(false);

		if (session != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			User user = (User) session.getAttribute("user");
			if (cart != null && cart.getCartEntries() != null && cart.getCartEntries().size() > index) {
				List<CartEntry> entries = cart.getCartEntries();
				if (user != null && user.getUserEmail() != null) {
					CartEntry entry = entries.get(index);
					cartService.removeEntry(user, entry);
				}
				entries.remove(index);
				session.setAttribute("cart", cart);
			}
		}

		return mv;
	}

	@RequestMapping(value = "/clearCart")
	public ModelAndView clearCart(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("displayCart");
		HttpSession session = req.getSession(false);
		if (session != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			User user = (User) session.getAttribute("user");
			if (user != null && user.getUserEmail() != null) {
				cart = cartService.clearCart(user);
			}
			session.setAttribute("cart", cart);
		}
		return mv;
	}

	@RequestMapping(value = "/displayHistory")
	public ModelAndView displayHistory(HttpServletRequest req) throws MyCustomException {
		ModelAndView mv = new ModelAndView("orderHistory");
		HttpSession session = req.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("user");
			user = userService.getUser(user.getUserEmail());
			UserDetails details = user.getUserDetails();
			Set<Order> orders = null;
			System.out.println(details);
			if (details != null) {
				orders = details.getOrders();
			} else {
				orders = new HashSet<>();
			}
			List<Order> sortedOrders = new ArrayList<>(orders);
			Collections.sort(sortedOrders, new OrderSorter());
			mv.addObject("orders", sortedOrders);
		} else {
			throw new MyCustomException("User not logged in.");
		}
		return mv;
	}

	@RequestMapping(value = "/updateCartItems")
	public ModelAndView changeCart(@RequestParam("index") int index, @RequestParam("quantity") String qty,
			HttpServletRequest req) {
		
		ModelAndView mv = new ModelAndView("redirect:cart");
		HttpSession session = req.getSession(false);
		int quantity = 0;

		if (qty.matches("\\D+")) {
			mv.addObject("errorMsg", "Quantity must be numeric");
			return mv;
		} else {
			quantity = Integer.parseInt(qty);
		}

		if (session != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			User user = (User) session.getAttribute("user");
			if (cart != null && cart.getCartEntries() != null && cart.getCartEntries().size() > index) {
				if (quantity <= 0) {
					mv.setViewName("redirect:removeFromCart?index="+index);
					return mv;
				} else {
					CartEntry entry = cart.getCartEntries().get(index);
					entry.setQuantity(quantity);
					if(user != null && user.getUserEmail() != null) {
						cartService.updateEntry(entry);
					}
				}
				session.setAttribute("cart", cart);
			}
		}

		return mv;
	}

}
