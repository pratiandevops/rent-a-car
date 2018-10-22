package com.ranko.rent_a_car.service.impl;

import com.ranko.rent_a_car.model.Customer;
import com.ranko.rent_a_car.model.Rental;
import com.ranko.rent_a_car.repository.CustomerRepository;
import com.ranko.rent_a_car.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final RentalServiceImpl rentalService;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, RentalServiceImpl rentalService) {
		this.customerRepository = customerRepository;
		this.rentalService = rentalService;
	}

	@Override
	public Customer findOne(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Collection<Customer> findByLastName(String lastName) {
		return customerRepository.findByLastName(lastName);
	}

	@Override
	public Collection<Customer> findByLastNameAndFirstName(String lastName, String firstName) {
		return customerRepository.findByLastNameAndFirstNameOrderByLastNameAscAllIgnoreCase(lastName, firstName);
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	@Transactional
	public void remove(Long id) throws IllegalArgumentException {
		Customer customer = customerRepository.findOne(id);
		if (customer == null) {
			throw new IllegalArgumentException(String.format(
					"Customer with id=%d does not exist.", id));
		}
		Collection<Rental> rentalsOfThisCustomer = rentalService.findAllByCustomer(customer);
		rentalsOfThisCustomer.forEach(rental -> rentalService.remove(rental.getId()));
		customerRepository.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findOneWithRentals(Long id) {
		return customerRepository.findByIdWithRentals(id);
	}

	@Override
	public Collection<Customer> findByLastNameWithRentals(String lastName) {
		return customerRepository.findByLastNameWithRentals(lastName);
	}
}
