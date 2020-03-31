package com.example.demo.unit;

import com.example.demo.controller.ProductController;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

    private Product product;
    private User user;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setUp() {
        user = new User((long) 1, "Ana", "add", 21);
        product = new Product((long) 2, "Camisa");
    }

    @Test
    public void AddProduct_GivenAProductWithIdUser_ShouldReturnAProductWithAllFields() {
        when(productService.addProduct(user.getIdUser(), product)).thenReturn(product);
        Assert.assertEquals(product, productController.addProduct(user.getIdUser(), product));
    }

    @Test
    public void DeleteProduct_GivenAProductAndIdUser_ShouldVerifyIfProductWasDeleted() {
        productController.deleteProduct(user.getIdUser(), product.getIdProduct());
        verify(productService).removeById(user.getIdUser(), product.getIdProduct());
    }

}
