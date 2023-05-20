package com.electric.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.electric.entities.Supplier;
import com.electric.repository.SupplierRepository;
import com.electric.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{
	@Autowired SupplierRepository supplierRepository;

	 public Supplier saveSupplier(@Valid Supplier supplier) {
	        return supplierRepository.save(supplier);
	    }

	    public List<Supplier> getAllSuppliers() {
	        return supplierRepository.findAll();
	    }

	    public Supplier getSupplierById(Long supplierId) {
	        return supplierRepository.findById(supplierId)
	                .orElseThrow(() -> new NoSuchElementException("Supplier not found with ID: " + supplierId));
	    }


	    public void deleteSupplierById(Long supplierId) {
	        supplierRepository.deleteById(supplierId);
	    }
	    
	    public Supplier updateSupplier(Long supplierId, Supplier updatedSupplier) {
	        Supplier existingSupplier = supplierRepository.findById(supplierId).orElse(null);
	        if (existingSupplier != null) {
	            
	            existingSupplier.setSupplierName(updatedSupplier.getSupplierName());
	            existingSupplier.setUrbanRural(updatedSupplier.getUrbanRural());

	            return supplierRepository.save(existingSupplier);
	        }
			return existingSupplier; 
	    }

		

}
