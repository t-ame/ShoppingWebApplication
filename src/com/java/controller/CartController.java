package com.java.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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

@Controller
@SessionAttributes(names = { "cart", "user" })
public class CartController {

	@Autowired
	@Qualifier("productservice")
	ProductServiceImpl productService;

//	@Autowired
//	@Qualifier("orderservice")
//	OrderServiceImpl orderService;
	
	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;


	@RequestMapping(value = "/addToCart/{id}")
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
	public ModelAndView payForProducts(HttpServletRequest req, HttpServletResponse resp)
			throws MyCustomException {

		ModelAndView mv = new ModelAndView("displayCart");

		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("cart") != null && session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			Cart cart = (Cart) session.getAttribute("cart");
			List<CartEntry> entries = cart.getCartEntries();
			Set<Order> orders = new HashSet<>();
			Order order = new Order();
			
			for(CartEntry entry : entries) {
				order.setComplete(false);
				order.setOrderDate(Date.valueOf(LocalDate.now()));
				order.setProduct(entry.getProduct());
				order.setQuantity(entry.getQuantity());
				order.setProductDetails(entry.getCartEntryDetails());
				orders.add(order);
			}
			
			UserDetails details = user.getUserDetails();
			if(details == null) {
				details=new UserDetails();
			}
			details.setUserId(userService.getUser(user.getUserEmail()).getUserDetails().getUserId());

			details.setOrders(orders);
			user.setUserDetails(details);
			userService.updateUser(user);
			
		} else {
			throw new MyCustomException("User not logged in or no products in the cart");
		}

		return mv;
	}

	@RequestMapping(value = "/removeFromCart/{index}")
	public ModelAndView removeFromCart(@PathVariable("index") int index, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("displayCart");
		HttpSession session = req.getSession();
		
		if(session != null) {
			Cart cart = (Cart)session.getAttribute("cart");
			if(cart != null && cart.getCartEntries() != null && cart.getCartEntries().size()>index) {
				cart.getCartEntries().remove(index);
				session.setAttribute("cart", cart);
			}
		}

		return mv;
	}

}
