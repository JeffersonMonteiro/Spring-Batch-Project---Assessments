package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long userId;

    private String name;

    private String address;

    private int age;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public User(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public User(Long userId, String name, String address, int age) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public User(){}

    public Long getIdUser() {
        return userId;
    }

    public void setIdUser(Long idUser) {
        this.userId = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
