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
    ProductService productService;

    @InjectMocks
    ProductController productController = new ProductController(productService);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Product> products = new ArrayList<>();
        this.person = new Person("Joao", 21, 1);
        person.setProducts(products);
        this.product = new Product("Jaqueta");
        product.setId(1);
    }

    @Test
    public void createProductTest(){
        when(productService.createProduct(person.getId(),product)).thenReturn(product);
        Assert.assertEquals(product, productController.createProduct(1, product));
    }

    /*@Test
    public void updateProductTest(){
        when(productService.updateProduct(product, 1)).thenReturn(product);
        Assert.assertEquals(product, productController.updateProduct(product,1));
    }*/

    @Test
    public void removeProductTest(){
        productController.removeProduct(product.getId(), person.getId());
        verify(productService).removeProduct(product.getId(), 1);
    }
}
