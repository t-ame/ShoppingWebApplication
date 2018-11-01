package com.java.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.java.components.Address;
import com.java.components.Card;
import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.Order;
import com.java.components.User;
import com.java.components.UserDetails;
import com.java.exception.MyCustomException;
import com.java.service.CardServiceImpl;
import com.java.service.ProductServiceImpl;
import com.java.service.UserServiceImpl;

@Controller
@SessionAttributes(names = { "address", "card" })
public class CardController {

	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;

	@Autowired
	CardServiceImpl cardService;

	@Autowired
	@Qualifier("productservice")
	ProductServiceImpl productService;

	@ModelAttribute("card")
	public Card initCard() {
		return new Card();
	}

	@ModelAttribute("address")
	public Address initAddress() {
		return new Address();
	}

	@RequestMapping("/showAllCards")
	public ModelAndView showAllCards(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {
		ModelAndView mv = new ModelAndView("displayCards");

		HttpSession session = req.getSession(false);
		if (session != null) {
			Set<Card> cards = null;
			User user = (User) session.getAttribute("user");
			if (user != null && user.getUserEmail() != null) {
				cards = userService.getCards(user);
			} else {
				throw new MyCustomException("Cannot access this page when not logged in.");
			}
			mv.addObject("cards", cards);
		} else {
			throw new MyCustomException("Cannot access this page when not logged in.");
		}

		return mv;
	}

	@RequestMapping("/editCard")
	public ModelAndView editCard(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("editCardPage");

		String cardId = req.getParameter("cardid");

		long id = Long.parseLong(cardId == null ? "0" : cardId);

		HttpSession session = req.getSession(false);
		if (session != null) {
			Card card = null;
			User user = (User) session.getAttribute("user");
			if (user != null && user.getUserEmail() != null) {
				if (id == 0) {
					card = new Card();
				} else {
					card = cardService.getCard(id);
				}
				mv.addObject("card", card);
				mv.addObject("editCard", true);
			} else {
				throw new MyCustomException("Cannot access this page when not logged in.");
			}
		} else {
			throw new MyCustomException("Cannot access this page when not logged in.");
		}
		return mv;
	}

	@RequestMapping(path = "/updateCard", method = RequestMethod.POST)
	public ModelAndView updateCard(@ModelAttribute("card") Card card, @ModelAttribute("address") Address address,
			HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("redirect:showAllCards");

		HttpSession session = req.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null && user.getUserEmail() != null) {
				if (card != null) {
					if (card.getCardId() == 0) {
						if (address == null) {
							address = new Address();
						}
						card.setBillingAddress(address);
						cardService.saveCard(user, card);
					} else {
						cardService.updateCard(card);
					}
				}
			} else {
				throw new MyCustomException("Cannot access this page when not logged in.");
			}
		} else {
			throw new MyCustomException("Cannot access this page when not logged in.");
		}
		return mv;
	}

	@RequestMapping(path = "/deleteCard")
	public ModelAndView deleteCard(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("redirect:showAllCards");

		String cardId = req.getParameter("cardid");

		long id = Long.parseLong(cardId == null ? "0" : cardId);

		HttpSession session = req.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null && user.getUserEmail() != null) {

				// DELETE IS A BIT TRICKY BECAUSE OF DEPENDENCIES... DO LATER

			} else {
				throw new MyCustomException("Cannot access this page when not logged in.");
			}
		} else {
			throw new MyCustomException("Cannot access this page when not logged in.");
		}
		return mv;
	}

	@RequestMapping(path = "/addNewCard")
	public ModelAndView addNewCard(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		System.out.println("Just entered addNewCard");
		
		ModelAndView mv = new ModelAndView("editCardPage");
		
		System.out.println("After setting view");

		HttpSession session = req.getSession(false);
		
		System.out.println("After getting session");
		
		if (session == null || session.getAttribute("user") == null
				|| ((User) session.getAttribute("user")).getUserEmail() == null) {
			throw new MyCustomException("Cannot access this page when not logged in.");
		}
		
		System.out.println("Done with addNewCard");
		
		return mv;
	}

	@RequestMapping(path = "/changeCard")
	public ModelAndView changeCard(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("redirect:showAllCards");

		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null
				|| ((User) session.getAttribute("user")).getUserEmail() == null) {
			throw new MyCustomException("Cannot access this page when not logged in.");
		} else {
			session.setAttribute("changeCard", true);
		}
		
		System.out.println("set changeCard to true");

		return mv;
	}

	@RequestMapping(path = "/selectCard")
	public ModelAndView selectCard(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("paymentPage");

		String cardId = req.getParameter("cardid");
		long id = Long.parseLong(cardId == null ? "0" : cardId);
		Card card = null;
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null
				|| ((User) session.getAttribute("user")).getUserEmail() == null) {
			throw new MyCustomException("Cannot access this page when not logged in.");
		} else {
			card = cardService.getCard(id);
			mv.addObject("card", card);
		}
		return mv;
	}

	@RequestMapping(path = "/proceedToCheckout")
	public ModelAndView checkoutCart(HttpServletRequest req) throws MyCustomException {
		ModelAndView mv = new ModelAndView("paymentPage");
		HttpSession session = req.getSession(false);
		Card card = null;
		if (session != null && session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			Set<Card> cards = userService.getCards(user);
			if (cards != null && cards.size() > 0) {
				card = cards.iterator().next();
			}
			mv.addObject("card", card);
		} else {
			throw new MyCustomException("User not logged in, cannot proceed to payment");
		}
		return mv;
	}

	@RequestMapping(path = "/pay")
	public ModelAndView payForProducts(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("redirect:success");

		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("cart") != null && session.getAttribute("user") != null
				&& ((User) session.getAttribute("user")).getUserEmail() != null) {
			User user = (User) session.getAttribute("user");
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
			userService.updateOrders(user, orders);
			cart.getCartEntries().clear();
			session.setAttribute("cart", cart);

			mv.addObject("successMsg", "Products purchase successful.");
		} else {
			throw new MyCustomException("User not logged in or no products in the cart");
		}

		return mv;
	}

}
