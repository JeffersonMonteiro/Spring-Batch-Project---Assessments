package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    private User user;

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController = new UserController(userService);

    @Before
    public void setUp() {
        user = new User((long) 1, "Ana", "add", 21);
    }

    @Test
    public void FindAllUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userService.getUsers()).thenReturn(users);
        Assert.assertEquals(users, userController.getUsers());
    }

    @Test
    public void AddUserTest() {
        when(userService.addUser(user)).thenReturn(user);
        Assert.assertEquals(user, userController.addUser(user));
    }

    @Test
    public void DeleteUserTest() {
        userController.deleteUser(user.getIdUser());
        verify(userService).removeById(user.getIdUser());
    }

    @Test
    public void GetUserByIdTest() {
        when(userService.findById(user.getIdUser())).thenReturn(user);
        Assert.assertEquals(user, userController.getUserById(user.getIdUser()));
    }

    @Test
    public void UpdatePersonTest() {
        when(userService.updateUser(user.getIdUser(), user)).thenReturn(user);
        Assert.assertEquals(user, userController.updateUser(user.getIdUser(), user));
    }

    @Test
    public void FindUserByName() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userService.findByName("Ana")).thenReturn(users);
        Assert.assertEquals(users, userController.findByName("Ana"));
    }
}
