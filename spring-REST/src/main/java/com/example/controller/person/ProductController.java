package com.example.controller.person;


import com.example.model.Person;
import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person/{personId}/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@PathVariable Integer personId, @RequestBody Product product){
        return  productService.createProduct(personId, product);
    }

    @PutMapping("/{id}")
    public void updatePerson(@RequestBody Product product, @PathVariable int id){
        productService.updateProduct(product, id);
    }
}
