package com.hobbyshop.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobbyshop.api.exception.ResourceNotFoundException;
import com.hobbyshop.api.model.Purchase;
import com.hobbyshop.api.model.User;
import com.hobbyshop.api.repository.UserRepository;
import com.hobbyshop.api.service.UserService;

/**
 * Implementation for UserService.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     /**
     * Saves a given User to database.
     * 
     * @param user The User entity to save.
     * @return The saved User entity.
     */
    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a User from database by its ID.
     * 
     * @param userId The ID of the User to retrieve.
     * @return The retrieved User entity.
     * @throws ResourceNotFoundException If no User with the given ID is found.
     */
    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> 
            new ResourceNotFoundException("User", "ID", userId));
    }

    /**
     * Retrieves all Users from the database.
     * 
     * @return List of all Users.
     */
    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves all Purchases made by User.
     * 
     * @param userId ID of target User.
     * @return List of all Purchases made by User.
     * @throws ResourceNotFoundException If no User with the given ID is found.
     */
    @Override
    public List<Purchase> getAllPurchasesByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "ID", userId));

        return user.getPurchases();
    }  
    
    /**
     * Updates User attributes.
     * Purchases must be updated directly, and are not updated by this method.
     * 
     * @param newUserData The new data for the User.
     * @param userId The ID of the User to update.
     * @return The updated User entity.
     * @throws ResourceNotFoundException If no User with the given ID is found.
     */
    @Override
    @Transactional
    public User updateUser(User newUserData, Long userId) {
        User currentUser = userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "ID", userId));
        
        currentUser.setName(newUserData.getName());
        currentUser.setEmail(newUserData.getEmail());
       
        return userRepository.save(currentUser);
    }

    /**
     * Deletes a User by its ID.
     * 
     * @param userId The ID of the User to delete.
     * @throws ResourceNotFoundException If no User with the given ID is found.
     */
    @Override
    @Transactional
    public void deleteUser(Long userId) {

        userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "ID", userId));
        
        userRepository.deleteById(userId);
    } 
    
}
