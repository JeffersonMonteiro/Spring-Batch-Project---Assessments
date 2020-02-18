package com.example.batchprocessing;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Person {

    // attributes
    @Id
    private Long id;

    private String name;

    private String department;

    private String numberAccount;

    public Person() {
    }


    //constructor
    public Person(Long userId, String userName, String dept, String account) {
        this.id = userId;
        this.name = userName;
        this.department = dept;
        this.numberAccount = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name + ", Department: " + department + ", Account: " + numberAccount;
    }

}