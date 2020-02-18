package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Long userId, Product product){
        productRepository.save(product);
        User user = userService.findById(userId);
        user.getProducts().add(product);
        userService.updateUser(user);
        return product;
    }

}
