package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("User/{idUser}/Product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Product addProduct(@PathVariable Long idUser, @RequestBody Product product){
        System.out.println("Add Product Controller");
        return productService.addProduct(idUser, product);
    }

    @PutMapping("/update/{idProduct}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long idProduct){
        return productService.updateProduct(product, idProduct);
    }

    @DeleteMapping("/delete/{idProduct}")
    public void deleteProduct(@PathVariable Long idUser, @PathVariable Long idProduct){
        productService.removeById(idUser, idProduct);
    }

}
