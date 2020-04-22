package com.project.spring_rest_project.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    private String customerName;

    private String customerMail;

    private String customerPhoneNumber;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Event> events;

    public Customer() { }

    public Customer(long customerId, String customerName, String customerMail, String customerPhoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerMail = customerMail;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public Customer(String customerName, String customerMail, String customerPhoneNumber) {
        this.customerName = customerName;
        this.customerMail = customerMail;
        this.customerPhoneNumber = customerPhoneNumber;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
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

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }


}
