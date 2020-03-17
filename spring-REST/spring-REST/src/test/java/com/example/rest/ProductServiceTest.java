package com.example.rest;


import com.example.model.Person;
import com.example.model.Product;
import com.example.repository.PersonRepository;
import com.example.repository.ProductRepository;
import com.example.service.PersonService;
import com.example.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

    Person person1;
    Person person2;
    Product product1;

    @Mock
    private PersonRepository personRepository;
    @Mock
    private ProductRepository productRepository;


    @InjectMocks
    PersonService personService = new PersonService(personRepository);
    @InjectMocks
    ProductService productService = new ProductService(productRepository);



    @Before
    public void setUp() {
        person1 = new Person("Joao", 21, 1);
        person2 = new Person("Vitor", 22, 2);
        product1 = new Product("Jaqueta");
    }

    @Test
    public void createProductTest(){
        when(productRepository.save(product1)).thenReturn(product1);
        Assert.assertEquals(product1, productService.createProduct(person1.getId(),product1));
    }
}
