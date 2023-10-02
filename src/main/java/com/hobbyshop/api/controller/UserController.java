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

/**
 * Controller for endpoints for User CRUD operations
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private UserService userService;

    /**
     * Constructs a new UserController instance.
     * 
     * @param userService The service for User operations.
     */
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    /**
     * Saves a new User to the database.
     * 
     * @param user The User entity to be saved.
     * @return ResponseEntity containing the saved User entity and the HTTP status.
     */
    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * Retrieves a User by its ID.
     * 
     * @param userId The ID of the User to retrieve.
     * @return ResponseEntity containing the retrieved User entity and the HTTP status.
     */
    @GetMapping("{id}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("id") long userId) {
        return new ResponseEntity<User>(userService.getUserByUserId(userId), HttpStatus.OK);

    }

     /**
     * Updates an existing User by its ID.
     * 
     * @param userId The ID of the User to update.
     * @param user The updated User entity.
     * @return ResponseEntity containing the updated User entity and the HTTP status.
     */
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUser(user, userId), HttpStatus.OK);
    }

    /**
     * Deletes a User by its ID.
     * 
     * @param userId The ID of the User to delete.
     * @return ResponseEntity with a confirmation message and the HTTP status.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User deleted successfully.", HttpStatus.OK);
    }


    
}
