package com.ranko.rent_a_car.web;

import com.ranko.rent_a_car.model.Customer;
import com.ranko.rent_a_car.model.Rental;
import com.ranko.rent_a_car.model.Vehicle;
import com.ranko.rent_a_car.service.CustomerService;
import com.ranko.rent_a_car.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RentalService rentalService;

	@ModelAttribute("rentals")
	public Collection<Rental> populateRentals() {
		return this.rentalService.findAll();
	}

	@RequestMapping(method= RequestMethod.GET)
	public String getCustomers(@RequestParam(value="lastName", required=false) String lastName, Model model) {
		Collection<Customer> customers = (lastName == null || "".equals(lastName) ? customerService.findAll() : customerService.findByLastName(lastName));
		model.addAttribute("customers", customers);

		return "customers";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewCustomer(@PathVariable("id") Long id, Model model) {

		logger.debug("showCustomer() id: {}", id);

		Customer customer = customerService.findOne(id);
		if (customer == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "customer not found");
		}
		model.addAttribute("customer", customer);

		return "showCustomer";

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String removeCustomer(@PathVariable Long id) {
		customerService.remove(id);
		return "customers";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String saveCustomer(Customer customer, Model model, final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("css", "success");

		redirectAttributes.addFlashAttribute("msg", "Customer saved successfully!");
		customerService.save(customer);

		return "redirect:/customers/" + customer.getId();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editCustomer(@PathVariable Long id, Model model) {
		Customer customer = customerService.findOne(id);
		model.addAttribute("customer", customer);
		return "addEditCustomer";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "addEditCustomer";
	}
}
