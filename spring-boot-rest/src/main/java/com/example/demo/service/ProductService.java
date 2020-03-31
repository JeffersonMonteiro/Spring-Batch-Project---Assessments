package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private UserService userService;

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, UserService userService){
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public Product addProduct(Long userId, Product product){
        System.out.println(product);
        productRepository.save(product);
        User user = userService.findById(userId);
        System.out.println(user);
        user.getProducts().add(product);
        userService.updateUser(userId, user);
        return product;
    }

    public void removeById(Long idUser, Long id){
        Optional<Product> product = productRepository.findById(id);
        System.out.println(product);
        product.ifPresent(prd -> {
            userService.deleteProductFromUser(prd, idUser);
            productRepository.deleteById(id);
        });
    }
}
