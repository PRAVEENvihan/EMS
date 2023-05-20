package com.electric.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electric.entities.Customer;
import com.electric.entities.Meter;
import com.electric.entities.Supplier;
import com.electric.repository.CustomerRepository;
import com.electric.repository.MeterRepository;
import com.electric.repository.SupplierRepository;
import com.electric.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired CustomerRepository customerRepository;
	@Autowired MeterRepository meterRepository;
	@Autowired SupplierRepository supplierRepository;

	 public Customer saveCustomer(@Valid Customer customer) {
		 
		 int load1=customer.getMeter().getloadb();
		 Meter meter1 = meterRepository.findByLoadb(load1);
		    if (meter1 != null) {
		    	customer.setMeter(meter1);
		    } else {
		    	Meter meter = customer.getMeter();
			    if (meter != null && meter.getMeterId() == null) {
			        // If the Meter object does not have an ID, it means it's a new Meter
			    	meter.setloadb(customer.getMeter().getloadb());
			    	meter.setMinimumBillAmount(1250);
			    	
			        meterRepository.save(meter);
			        customer.setMeter(meter);
			    }
		    }
		 
		 
		 
		 
	        return customerRepository.save(customer);
	    }

	    public List<Customer> getAllCustomers() {
	        return customerRepository.findAll();
	    }

	    public Customer getCustomerById(Long customerId) {
	    	
	    	
	    	
	        return customerRepository.findById(customerId)
	                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + customerId));
	    }


	    public void deleteCustomerById(Long customerId) {
	        customerRepository.deleteById(customerId);
	    }

		@Override
		public Customer updateCustomer(Customer customer,long cId) {
			
			Customer dbCustomer = customerRepository.findById(cId).get();
			if(customer.getCurrentReading()!= 0)
				dbCustomer.setCurrentReading(customer.getCurrentReading());
			if(customer.getCustomerAddress()!= null)
				dbCustomer.setCustomerAddress(customer.getCustomerAddress());
			if(customer.getMeter()!=null)
				dbCustomer.setMeter(customer.getMeter());
			if(customer.getCustomerName()!=null)
				dbCustomer.setCustomerName(customer.getCustomerName());
			if(customer.getConnectionDate()!=null)
				dbCustomer.setConnectionDate(customer.getConnectionDate());
			if(customer.getSupplier()!=null)
				dbCustomer.setSupplier(customer.getSupplier());
			
			return customerRepository.save(dbCustomer);
		}
		
		@Override
		public String calculateBillOfCustomer(long cId, int currentReading) {
				
			Customer dbCustomer = customerRepository.findById(cId).get();
			int lastReading = dbCustomer.getCurrentReading();
		int unitsConsumed = currentReading - lastReading;
			double billAmount = 0 ; 
					if (unitsConsumed <= 100) {
	            billAmount = unitsConsumed * 3;
	        } else if (unitsConsumed <= 200) {
	        	 billAmount = 100 * 3 + (unitsConsumed - 100) * 5;
	        } else if (unitsConsumed <= 300) {
	        	 billAmount = 100 * 3 + 100 * 5 + (unitsConsumed - 200) * 6;
	        } else if (unitsConsumed <= 400) {
	        	 billAmount = 100 * 3 + 100 * 5 + 100 * 6 + (unitsConsumed - 300) * 7;
	        } else if (unitsConsumed <= 500) {
	        	 billAmount = 100 * 3 + 100 * 5 + 100 * 6 + 100 * 7 + (unitsConsumed - 400) * 7.5;
	        } else {
	        	 billAmount = 100 * 3 + 100 * 5 + 100 * 6 + 100 * 7 + 100 * 7.5 + (unitsConsumed - 500) * 8;
	        } 
					
				if(	dbCustomer.getMeter().getMinimumBillAmount()> billAmount ) {
					billAmount=dbCustomer.getMeter().getMinimumBillAmount();
				}
				dbCustomer.setLastReading(dbCustomer.getCurrentReading());
				dbCustomer.setCurrentReading(currentReading);
				dbCustomer.setBillAmount(billAmount);
				customerRepository.save(dbCustomer);
			return "the previous reading was:" + lastReading +"\ncurrent reading is " + currentReading +"\ntotal bill amount is "+billAmount;
		}

		

		    public List<Customer> getCustomersBySupplierId(Long supplierId) {
		        Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
		        if (supplier != null) {
		            return customerRepository.findBySupplier(supplier);
		        }
		        return Collections.emptyList();
		    }


}
