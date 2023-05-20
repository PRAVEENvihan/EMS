package com.electric.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.electric.entities.Customer;
import com.electric.service.CustomerService;

@RestController
//@RequestMapping("/customers")
public class CustomerController {

	@Autowired CustomerService customerService;

	
	@PostMapping("/customers")
    public long createCustomer(@Valid @RequestBody Customer customer) {
         customerService.saveCustomer(customer);
         return customer.getCustomerId();
	}
	@GetMapping("/customers/{cId}")
	public Customer getCustomer(@PathVariable long cId) {
		return customerService.getCustomerById(cId);				
	}

   @PatchMapping("/customers/{cId}")
   public Customer updateCustomer(@RequestBody Customer customer, @PathVariable long cId) {
	   return customerService.updateCustomer(customer,cId);
   }
   @GetMapping("customerBill/{cId}/{currentReading}")
   public String calculateBill(@PathVariable long cId,@PathVariable int currentReading) {
	   return customerService.calculateBillOfCustomer(cId,currentReading);
   }
}
