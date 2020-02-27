package com.example.service;


import com.example.model.Person;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private PersonService personService;

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Integer personId, Product product){

        productRepository.save(product);
        Person person = personService.getById(personId);

        person.getProducts().add(product);
        personService.updatePerson(person, personId);

        return product;
    }

    public Product updateProduct(Product product, int id){

        Optional<Product> OptProduct = productRepository.findById(id);
        if(OptProduct.isPresent()){
            product.setId(id);
            return productRepository.save(product);
        }
        return OptProduct.orElseThrow(() -> new RuntimeException("Product does not exist"));
    }

}
