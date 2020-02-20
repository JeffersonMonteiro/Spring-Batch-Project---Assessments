package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("User/{idUser}/Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@PathVariable Long idUser, @RequestBody Product product){
        return productService.addProduct(idUser, product);
    }

    @PutMapping("/update/{idProduct}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long idProduct){
        return productService.updateProduct(product, idProduct);
    }

}
