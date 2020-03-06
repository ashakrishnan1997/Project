package com.customer.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.bean.Customer;
import com.customer.dao.CustomerDao;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	// create object of customerdao
	CustomerDao custmerDao;

	// insert details
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public void addCustomerDetails(@RequestBody Customer customer) {
		// calling method in customerdao

		custmerDao.addDetails(customer);

	}

	// list details

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public List<Customer> ListCustomers(Customer customer) {

		// calling display method in customerdao
		List<Customer> customerlist = custmerDao.getDetails();

		return customerlist;

	}

	// search using id
	@RequestMapping(value = "/customers/{custId}", method = RequestMethod.GET)
	public Customer getEmployeeDetails(@PathVariable("custId") int id) {

		// call the method for search using id

		Customer customer = custmerDao.searchById(id);

		return customer;

	}

	// search using id
	@RequestMapping(value = "/customersSearch/{custName}", method = RequestMethod.GET)
	public Customer getEmployeeDetails(@PathVariable("custName") String custName) {

		// call the method for search using id

		Customer customer = custmerDao.searchByName(custName);

		return customer;

	}

	// method for update
	// update details
	// update customer details
	@RequestMapping(value = "/customersUpdate/{custId}", method = RequestMethod.PUT)
	public void updateDetails(@RequestBody Customer customer,
			@PathVariable("custId") int id) {
		// calling update method
		custmerDao.UpdateCustomer(customer, id);

	}

	// delete or disable employee
	@RequestMapping(value = "/customerDel/{custId}", method = RequestMethod.PUT)
	public void disableEmployee(@PathVariable("custId") int id) {

		custmerDao.deleteCustomer(id);

	}

}
