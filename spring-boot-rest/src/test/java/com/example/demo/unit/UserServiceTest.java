package com.example.demo.unit;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
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
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    private User user1;
    private User user2;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(userRepository);
        user1 = new User((long) 1, "Ana", "add", 21);
        user2 = new User((long) 2, "Maria", "add", 21);
    }

    @Test
    public void GetUsersTest() {
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(userRepository.findAll()).thenReturn(users);
        Assert.assertEquals(users, userService.getUsers());
    }

    @Test
    public void GetUsersTest_ReturnNull() {
        when(userRepository.findAll()).thenReturn(null);
        Assert.assertEquals(null, userService.getUsers());
    }

    @Test
    public void AddUser_GivenAUser_ShouldReturnAUser() {
        when(userRepository.save(user1)).thenReturn(user1);
        Assert.assertEquals(user1, userService.addUser(user1));
    }

    @Test
    public void AddUser_GivenAUserNull_ShouldReturnNull() {
        when(userRepository.save(null)).thenReturn(null);
        Assert.assertEquals(null, userService.addUser(null));
    }

    @Test
    public void FindById_GivenAIdUser_ShouldReturnAUser() {
        when(userRepository.findById(user1.getIdUser())).thenReturn(Optional.of(user1));
        Assert.assertEquals(user1, userService.findById(user1.getIdUser()));
    }

    @Test
    public void RemoveById_GivenAIdUser_ShouldVerifyIfTheUserWasDeleted() {
        userService.removeById(user1.getIdUser());
        verify(userRepository).deleteById(user1.getIdUser());
    }

    @Test
    public void UpdateUser_GivenAUser_ShouldReturnAUserUpdated() {
        when(userRepository.save(user1)).thenReturn(user1);
        Assert.assertEquals(user1, userService.updateUser(user1.getIdUser(), user1));
    }

    @Test
    public void FindUserByName_GivenAName_ShouldReturnAListWithUsers() {
        List<User> users = new ArrayList<>();
        users.add(user1);
        when(userService.findByName("Ana")).thenReturn(users);
        Assert.assertEquals(users, userService.findByName("Ana"));
    }
}
