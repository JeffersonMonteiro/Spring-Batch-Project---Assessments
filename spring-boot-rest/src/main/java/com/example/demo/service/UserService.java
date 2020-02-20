package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
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
        return OptUser.orElseThrow(() -> new RuntimeException("User does not exist"));
    }

}
