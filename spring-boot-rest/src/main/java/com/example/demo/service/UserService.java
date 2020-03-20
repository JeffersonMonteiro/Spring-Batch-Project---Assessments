package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void removeById(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user){
        Optional<User> OptUser = userRepository.findById(id);
        if(OptUser.isPresent()){
            user.setIdUser(id);
            return userRepository.save(user);
        }
        return userRepository.save(user);
    }

    public List<User> findByName(String name){
        List<User> users = this.getUsers();
        List<User> usersFound = new ArrayList<>();
        for (User user : users){
            if(user.getName().contains(name)){
                usersFound.add(user);
            }
        }
        System.out.println(users);
        return usersFound;
    }

}
