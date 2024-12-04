package com.codewitharjun.fullstackbackend.controller;

import com.codewitharjun.fullstackbackend.exception.UserNotFoundException;
import com.codewitharjun.fullstackbackend.model.User;
import com.codewitharjun.fullstackbackend.repository.UserRepository;
import com.codewitharjun.fullstackbackend.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/* Created by Arjun Gautam */
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private userservice userservice;



    @GetMapping("/user")
    List<User> getAllUsers() {
        return userservice.getAllUsers();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userservice.getUserById(id);
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userservice.updateUser(newUser, id);
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        return userservice.deleteUser(id);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createeUser(@RequestBody User newwUser,
                                        @RequestBody MultipartFile imageFile) {
        try{
        User user1=userservice.createeUser(newwUser,imageFile);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
        catch(Exception e)
        {
              return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
}
    }