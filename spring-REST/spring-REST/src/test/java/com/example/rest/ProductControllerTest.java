package com.example.rest;

import com.example.controller.person.PersonController;
import com.example.controller.person.ProductController;
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
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

    Product product;
    Person person;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    ProductService productService = new ProductService(productRepository);

    @InjectMocks
    ProductController productController = new ProductController(productService);

    @InjectMocks
    PersonService personService = new PersonService(personRepository);

    @InjectMocks
    PersonController personController = new PersonController(personService);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Product> products = new ArrayList<>();
        this.person = new Person("Joao", 21, 1);
        personService.createPerson(person);
        person.setProducts(products);
        this.product = new Product("Jaqueta");
    }

    @Test
    public void createProductTest(){
        when(productService.createProduct(person.getId(),product)).thenReturn(product);
        Assert.assertEquals(product, productController.createProduct(person.getId(), product));
    }

    @Test
    public void removeProductTest(){
        productController.removeProduct(product.getId(), person.getId());
        verify(productRepository).deleteById(product.getId());
    }
}
