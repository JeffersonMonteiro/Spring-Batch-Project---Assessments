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
    private Long userId;

    private String userName;

    private String dept;

    private BigDecimal account;

    public Person() {
    }

    //constructor
    public Person(Long userId, String userName, String dept, BigDecimal account) {
        this.userId = userId;
        this.userName = userName;
        this.dept = dept;
        this.account = account;
    }

    //getters and setters
    public void setId(Long userId) {
        this.userId = userId;
    }
    public Long getId() {
        return userId;
    }

    public String getName() {
        return userName;
    }
    public void setName(String userName) {
        this.userName = userName;
    }

    public void setDept(Long setDept) { this.dept = dept; }
    public String getDept() { return dept; }

    public void setAccount(BigDecimal account) { this.account = account; }
    public BigDecimal getAccount() { return account; }

    @Override
    public String toString() {
        return "Id: " + userId + ", Name: " + userName + ", Department: " + dept + ", Account: " + account;
    }

}