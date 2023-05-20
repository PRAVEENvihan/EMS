package com.electric.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electric.entities.Customer;
import com.electric.entities.Supplier;
import com.electric.service.CustomerService;
import com.electric.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
	@Autowired SupplierService supplierService;
	@Autowired CustomerService customerService;
	 @PostMapping
	    public Supplier createSupplier(@Valid @RequestBody Supplier supplier) {
	        return supplierService.saveSupplier(supplier);
	    }
	 
	 @PatchMapping("/{supplierId}")
	 public ResponseEntity<Supplier> updateSupplier(@PathVariable Long supplierId, @RequestBody Supplier updatedSupplier) {
	     supplierService.updateSupplier(supplierId, updatedSupplier);
	     return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
	 }
	 
	 
	 
	 @GetMapping("/{supplierId}")
	 public ResponseEntity<List<Customer>> getCustomersBySupplierId(@PathVariable Long supplierId) {
	     List<Customer> customers = customerService.getCustomersBySupplierId(supplierId);
	     if (!customers.isEmpty()) {
	         return new ResponseEntity<>(customers, HttpStatus.OK);
	     }
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }




	   
	
	
}
