package com.electric.entities;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

@Entity
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meterId;

    @Min(value = 0, message = "loadb must be a positive number")
    private int loadb;

    @Min(value = 0, message = "Minimum bill amount must be a positive number")
    private int minimumBillAmount;
    
    @OneToMany(mappedBy = "meter")
    private List<Customer> customers;

    public Meter() {
    }

    public Meter(int loadb, int minimumBillAmount) {
        this.loadb = loadb;
        this.minimumBillAmount = minimumBillAmount;
    }

    public Meter(int i) {
		// TODO Auto-generated constructor stub
	}

	public Long getMeterId() {
        return meterId;
    }

    public void setMeterId(Long meterId) {
        this.meterId = meterId;
    }

    public int getloadb() {
        return loadb;
    }

    public void setloadb(int loadb) {
        this.loadb = loadb;
    }

    public int getMinimumBillAmount() {
        return minimumBillAmount;
    }

    public void setMinimumBillAmount(int minimumBillAmount) {
        this.minimumBillAmount = minimumBillAmount;
    }
}
