package com.electric.entities;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Customer name must be at most 100 characters")
    private String customerName;

    @NotBlank(message = "Customer address is required")
    @Size(max = 200, message = "Customer address must be at most 200 characters")
    private String customerAddress;

    @NotNull(message = "Connection date is required")
    private LocalDate connectionDate;

    @Min(value = 0, message = "Last reading must be a non-negative number")
    private int lastReading;

    @Min(value = 0, message = "Current reading must be a non-negative number")
    private int currentReading;

    @Min(value = 0, message = "Bill amount must be a non-negative number")
    private double billAmount;

    @ManyToOne
    @JoinColumn(name = "meter_id")
    @NotNull(message = "Meter ID is required")
    private Meter meter;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @NotNull(message = "Supplier ID is required")
    private Supplier supplier;

    public Customer() {
    }

    public Customer(String customerName, String customerAddress, LocalDate connectionDate, int lastReading,
                    int currentReading, double billAmount, Meter meter, Supplier supplier) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.connectionDate = connectionDate;
        this.lastReading = lastReading;
        this.currentReading = currentReading;
        this.billAmount = billAmount;
        this.meter = meter;
        this.supplier = supplier;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public LocalDate getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(LocalDate connectionDate) {
        this.connectionDate = connectionDate;
    }

    public int getLastReading() {
        return lastReading;
    }

    public void setLastReading(int lastReading) {
        this.lastReading = lastReading;
    }

    public int getCurrentReading() {
        return currentReading;
    }

    public void setCurrentReading(int currentReading) {
        this.currentReading = currentReading;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public Meter getMeter() {
        return meter;
    }

    public void setMeter(Meter meter) {
        this.meter = meter;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
