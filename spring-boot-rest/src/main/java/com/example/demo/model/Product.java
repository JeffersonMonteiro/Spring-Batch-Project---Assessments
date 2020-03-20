package com.example.demo.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduct;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Product(Long idProduct, String name){
        this.idProduct = idProduct;
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
