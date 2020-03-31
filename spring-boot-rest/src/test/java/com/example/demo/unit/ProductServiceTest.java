package com.example.demo.unit;

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
import java.util.Optional;

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

    @InjectMocks
    ProductService productService;

    @Before
    public void setUp() {
        List<Product> products = new ArrayList<>();
        user = new User(1L, "Ana", "add", 21);
        user.setProducts(products);
        product = new Product(2L, "Dress");
    }

    @Test
    public void AddProduct_GivenAProductWithIdUser_ShouldReturnAProductWithAllFields() {
        List<Product> products = new ArrayList<>();
        user.setProducts(products);
        when(userService.findById(user.getIdUser())).thenReturn(user);
        when(productRepository.save(product)).thenReturn(product);
        Product productSaved = productService.addProduct(user.getIdUser(), product);
        Assert.assertTrue(productSaved.getIdProduct() == 2L);
    }

    @Test
    public void RemoveById_GivenAProductAndIdUser_ShouldVerifyIfProductWasDeleted() {
        when(userService.findById(user.getIdUser())).thenReturn(user);
        when(productRepository.findById(product.getIdProduct())).thenReturn(Optional.of(product));

        productService.removeById(user.getIdUser(), product.getIdProduct());
        verify(productRepository).deleteById(product.getIdProduct());
    }
}