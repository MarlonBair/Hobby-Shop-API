package com.hobbyshop.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobbyshop.api.model.User;
import com.hobbyshop.api.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserByUserID(@PathVariable("id") long userId) {
        return new ResponseEntity<User>(userService.getUserByUserID(userId), HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUser(user, userId), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User deleted successfully.", HttpStatus.OK);
    }


    
}
