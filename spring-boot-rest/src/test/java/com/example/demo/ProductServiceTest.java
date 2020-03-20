package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
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
public class ProductServiceTest {

    private User user;
    private Product product;

    @Mock
    ProductRepository productRepository;

    @Mock
    UserService userService;

    @Mock
    ProductService productService = new ProductService(productRepository, userService);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Product> products = new ArrayList<>();
        user = new User((long) 1, "Ana", "add", 21);
        user.setProducts(products);
        product = new Product((long) 2, "Dress");
    }

    @Test
    public void AddProductTest() {
        when(productRepository.save(product)).thenReturn(product);
        Assert.assertEquals(product, productService.addProduct(user.getIdUser(), product));
        System.out.println(product);
    }

    @Test
    public void RemoveProductById() {
        productService.removeById(user.getIdUser(), product.getIdProduct());
        verify(productRepository).deleteById(product.getIdProduct());
    }

}
