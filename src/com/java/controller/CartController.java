package com.java.controller;

import java.sql.Date;
import java.time.LocalDate;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.components.Address;
import com.java.components.Card;
import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.Order;
import com.java.components.Product;
import com.java.components.ProductDetailGroup;
import com.java.components.User;
import com.java.components.UserDetails;
import com.java.exception.MyCustomException;
import com.java.service.ProductServiceImpl;
import com.java.service.UserServiceImpl;
import com.java.util.OrderSorter;

@Controller
@SessionAttributes(names = { "cart", "user" })
public class CartController {

	@Autowired
	@Qualifier("productservice")
	ProductServiceImpl productService;

	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;
	
	@RequestMapping(value="/addToCart/displaycart")
	public ModelAndView addToCart(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView("displayCart");
		
		mv.addObject("cart", req.getSession().getAttribute("cart"));
		
		return mv;
	}

	@RequestMapping(value = "/addToCart/{id}")
	public ModelAndView addToCart(@PathVariable("id") long id, HttpServletRequest req, HttpServletResponse resp) {

		ModelAndView mv = new ModelAndView("redirect:displaycart");

		Product product = productService.getProduct(id);
		HttpSession session = req.getSession(true);
		Cart cart = (Cart) session.getAttribute("cart");
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
			cart.getCartEntries().add(entry);

			session.setAttribute("cart", cart);

		} else {
			mv.addObject("addCartMsg", "Invalid product item.");
		}

		return mv;
	}

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public ModelAndView payForProducts(@ModelAttribute Card card, @ModelAttribute Address address, HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("displayCart");

		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("cart") != null && session.getAttribute("user") != null
				&& ((User) session.getAttribute("user")).getUserEmail() != null) {
			User user = (User) session.getAttribute("user");
			UserDetails details = (UserDetails) session.getAttribute("userdetails");
			Cart cart = (Cart) session.getAttribute("cart");
			List<CartEntry> entries = cart.getCartEntries();
			List<Order> orders = new ArrayList<>();
			Order order = null;

			for (CartEntry entry : entries) {
				if (productService.purchaseProduct(entry.getProduct(), entry.getQuantity())) {
					order = new Order();
					order.setComplete(false);
					order.setOrderDate(Date.valueOf(LocalDate.now()));
					order.setProduct(entry.getProduct());
					order.setQuantity(entry.getQuantity());
					order.setProductDetails(entry.getCartEntryDetails());
					orders.add(order);
				}
			}
			if (details == null) {
				throw new MyCustomException("User not logged in or no products in the cart");
			}
			user.setUserDetails(details);
			userService.updateOrders(user, orders);
			cart.getCartEntries().clear();
			session.setAttribute("cart", cart);
			
			mv.addObject("paymentSuccess", "Successfully purchased products.");
		} else {
			throw new MyCustomException("User not logged in or no products in the cart");
		}

		return mv;
	}

	@RequestMapping(value = "/removeFromCart/{index}")
	public ModelAndView removeFromCart(@PathVariable("index") int index, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("redirect:displaycart");
		HttpSession session = req.getSession();

		if (session != null) {
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart != null && cart.getCartEntries() != null && cart.getCartEntries().size() > index) {
				cart.getCartEntries().remove(index);
				session.setAttribute("cart", cart);
			}
		}

		return mv;
	}

	@RequestMapping(value = "/clearCart")
	public ModelAndView clearCart(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("displayCart");
		HttpSession session = req.getSession();
		if (session != null) {
			session.setAttribute("cart", new Cart());
		}
		return mv;
	}

	@RequestMapping(value = "/proceedToCheckout")
	public ModelAndView checkoutCart(HttpServletRequest req) throws MyCustomException {
		ModelAndView mv = new ModelAndView("paymentPage");
		HttpSession session = req.getSession();

		if (session != null && session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");

			// FETCH CARD DETAILS

		} else {
			throw new MyCustomException("User not logged in, cannot proceed to payment");
		}

		// DO MORE WITH CARDS AND ADDRESSES

		return mv;
	}



	@RequestMapping(value = "/displayHistory")
	public ModelAndView displayHistory(HttpServletRequest req) throws MyCustomException {
		ModelAndView mv = new ModelAndView("orderHistory");
		HttpSession session = req.getSession();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			user = userService.getUser(user.getUserEmail());
			UserDetails details = user.getUserDetails();
			Set<Order> orders = null;
			System.out.println(details);
			if(details != null) {
				orders = details.getOrders();
				System.out.println(orders);
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

	@RequestMapping(value="/updateCartItem")
	public ModelAndView changeCart(@RequestParam("index") int index, @RequestParam("quantity") String qty, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("redirect:addToCart/displaycart");
		HttpSession session = req.getSession();

		int quantity  = 0;
		
		System.out.println(index + " "+ qty);
		
		if(qty.matches(".*\\D+.*")) {
			mv.addObject("errorMsg", "Quantity must be numeric");
			return mv;
		} else {
			quantity = Integer.parseInt(qty);
		}
		
		if (session != null) {
			
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart != null && cart.getCartEntries() != null && cart.getCartEntries().size() > index) {
				if(quantity <= 0) {
					cart.getCartEntries().remove(index);
				} else {
					CartEntry entry = cart.getCartEntries().get(index);
					cart.getCartEntries().remove(index);
					entry.setQuantity(quantity);
					cart.getCartEntries().add(index, entry);
				}
				session.setAttribute("cart", cart);
			}
		}
		
		return mv;
	}
	
}
















