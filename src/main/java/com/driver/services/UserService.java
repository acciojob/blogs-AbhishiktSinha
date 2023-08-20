package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password)throws Exception {
        if(username == null || password == null)
            throw new Exception("Essential parameter absent");
        if(username.equals("") || password.equals(""))
            throw new Exception("Empty username or password given");

        User user = new User(username, password);
        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId)throws Exception {
        Optional<User> optionalUser = userRepository3.findById(userId);
        if(!optionalUser.isPresent()) {
            throw new Exception("User not present");
        }
        else {
            userRepository3.deleteById(userId);
        }

    }

    public User updateUser(Integer id, String password)throws Exception{
        Optional<User> optionalUser = userRepository3.findById(id);
        if(!optionalUser.isPresent()) {
            throw new Exception("User not found");
        }
        else {
            User user = optionalUser.get();
            user.setPassword(password);

            userRepository3.save(user);

            return user;
        }
    }
}
