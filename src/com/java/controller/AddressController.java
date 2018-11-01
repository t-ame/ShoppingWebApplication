package com.java.controller;

import java.util.Optional;
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
import com.java.components.User;
import com.java.exception.MyCustomException;
import com.java.service.AddressServiceImpl;
import com.java.service.UserServiceImpl;

@Controller
@SessionAttributes(names = { "address", "card" })
public class AddressController {

	@Autowired
	@Qualifier("userservice")
	private UserServiceImpl userService;

	@Autowired
	AddressServiceImpl addService;

	@ModelAttribute("card")
	public Card initCard() {
		return new Card();
	}

	@ModelAttribute("address")
	public Address initAddress() {
		return new Address();
	}

	@RequestMapping("/showAllAddresses")
	public ModelAndView showAllAddress(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {
		ModelAndView mv = new ModelAndView("displayAddresses");

		HttpSession session = req.getSession(false);
		if (session != null) {
			Set<Address> addresses = null;
			User user = (User) session.getAttribute("user");
			if (user != null && user.getUserEmail() != null) {
				addresses = userService.getAddresses(user);
			} else {
				throw new MyCustomException("Cannot access this page when not logged in.");
			}
			mv.addObject("addresses", addresses);
		} else {
			throw new MyCustomException("Cannot access this page when not logged in.");
		}

		return mv;
	}

	@RequestMapping(value = "/editAddress")
	public ModelAndView editAddress(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("editAddressPage");

		String addressId = req.getParameter("addressId");

		long id = Long.parseLong(addressId == null ? "0" : addressId);

		HttpSession session = req.getSession();
		if (session != null) {
			Address address = null;
			User user = (User) session.getAttribute("user");
			if (user != null && user.getUserEmail() != null) {
				if (id == 0) {
					address = new Address();
				} else {
					address = addService.getAddress(id);
				}
				mv.addObject("address", address);
			} else {
				throw new MyCustomException("Cannot access this page when not logged in.");
			}
		} else {
			throw new MyCustomException("Cannot access this page when not logged in.");
		}
		return mv;
	}

	@RequestMapping(value = "/addAddress")
	public ModelAndView addAddress(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("editAddressPage");

		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null
				|| ((User) session.getAttribute("user")).getUserEmail() == null) {
			throw new MyCustomException("Cannot access this page when not logged in.");
		}

		return mv;
	}

	@RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
	public ModelAndView updateCard(@ModelAttribute("address") Address address, HttpServletRequest req,
			HttpServletResponse resp) throws MyCustomException {

		ModelAndView mv = new ModelAndView("redirect:showAllAddresses");

		HttpSession session = req.getSession();
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null && user.getUserEmail() != null) {
				if (address != null) {
					if (address.getAddressId() == 0) {
						addService.saveAddress(user, address);
					} else {
						addService.updateAddress(address);
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

}
