package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduct;

    private String name;

    private Long idUser;

    public Product(String name, User user, Long idUser) {
        this.name = name;
        this.idUser = idUser;
    }

    public Product(){}

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
