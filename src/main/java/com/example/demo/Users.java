package com.example.demo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Users {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String name;
    private String dept;
    private BigDecimal account;

    public Users(Long userId, String name, String dept, BigDecimal account) {
        this.userId = userId;
        this.name = name;
        this.dept = dept;
        this.account = account;
    }

    public Users() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", account=" + account +
                '}';
    }
}
