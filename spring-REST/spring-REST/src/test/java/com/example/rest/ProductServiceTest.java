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
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {

    Person person1;
    Product product1;

    @Mock
    private PersonService personService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Product> products = new ArrayList<>();
        this.person1 = new Person("Joao", 21, 1);
        personService.createPerson(person1);
        person1.setProducts(products);
        this.product1 = new Product("Jaqueta");
    }

    @Test
    public void createProductTest(){
        person1.getProducts().add(product1);
        personService.updatePerson(person1, person1.getId());
        when(personService.getById(person1.getId())).thenReturn(person1);
        when(personService.updatePerson(person1, person1.getId())).thenReturn(person1);
        Assert.assertEquals(product1, productService.createProduct(person1.getId(),product1));
    }

    @Test
    public void removeProductTest(){

        when(personService.getById(person1.getId())).thenReturn(person1);
        when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));

        productService.removeProduct(person1.getId(), product1.getId());
        verify(productRepository).deleteById(product1.getId());
    }
}
