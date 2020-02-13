package com.example.demo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Users {

    @Id
    private Long userId;
    private String name;
    private String dept;
    private BigDecimal account;

    public Users(String name, String dept, BigDecimal account) {
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
