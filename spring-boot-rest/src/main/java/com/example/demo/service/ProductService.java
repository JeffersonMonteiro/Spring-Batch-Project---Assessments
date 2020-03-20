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
        productRepository.save(product);
        User user = userService.findById(userId);
        user.getProducts().add(product);
        userService.updateUser(userId, user);
        return product;
    }

    public Product updateProduct(Product product, Long id){
        Optional<Product> OptProduct = productRepository.findById(id);
        if(OptProduct.isPresent()){
            product.setIdProduct(id);
            return productRepository.save(product);
        }
        return OptProduct.orElseThrow(() -> new RuntimeException("Product does not exist"));
    }

    public void removeById(Long idUser, Long id){
        User user = userService.findById(idUser);

        Optional<Product> product = productRepository.findById(id);

        product.ifPresent(prd -> {
            user.getProducts().remove(prd);
            userService.updateUser(idUser, user);
        });
    }
}
