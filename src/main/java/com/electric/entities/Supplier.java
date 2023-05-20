package com.electric.entities;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @NotBlank(message = "Supplier name is required")
    @Size(max = 100, message = "Supplier name must be at most 100 characters")
    private String supplierName;

    @NotBlank(message = "Urban/Rural is required")
    @Size(max = 50, message = "Urban/Rural must be at most 50 characters")
    private String urbanRural;
    
    @OneToMany(mappedBy = "supplier")
    private List<Customer> customers;

    public Supplier() {
    }

    public Supplier(String supplierName, String urbanRural) {
        this.supplierName = supplierName;
        this.urbanRural = urbanRural;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getUrbanRural() {
        return urbanRural;
    }

    public void setUrbanRural(String urbanRural) {
        this.urbanRural = urbanRural;
    }
}
