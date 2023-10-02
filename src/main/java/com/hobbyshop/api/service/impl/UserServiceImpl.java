package com.hobbyshop.api.service.impl;

import org.springframework.stereotype.Service;

import com.hobbyshop.api.exception.ResourceNotFoundException;
import com.hobbyshop.api.model.User;
import com.hobbyshop.api.repository.UserRepository;
import com.hobbyshop.api.service.UserService;

/**
 * UserService implementation.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    /**
     * Constructs a new UserServiceImpl instance with the given UserRepository.
     * 
     * @param userRepository The repository for User entities.
     */
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

     /**
     * Saves a given user to database.
     * 
     * @param user The user entity to save.
     * @return The saved user entity.
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a user from database by its id.
     * 
     * @param userId The ID of the user to retrieve.
     * @return The retrieved user entity.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    @Override
    public User getUserByUserId(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> 
            new ResourceNotFoundException("User", "ID", userId));
    }

    /**
     * Updates all user attributes.
     * 
     * @param newUserData The new data for the user.
     * @param userId The ID of the user to update.
     * @return The updated user entity.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    @Override
    public User updateUser(User newUserData, long userId) {
        User currentUser = userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "ID", userId));
        
        currentUser.setName(newUserData.getName());
        currentUser.setEmail(newUserData.getEmail());
        currentUser.setPurchases(newUserData.getPurchases());
       
        userRepository.save(currentUser);

        return currentUser;
    }

    /**
     * Deletes a user by its ID.
     * 
     * @param userId The ID of the user to delete.
     * @throws ResourceNotFoundException If no user with the given ID is found.
     */
    @Override
    public void deleteUser(long userId) {

        userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "ID", userId));
        
        userRepository.deleteById(userId);;
    }

    
}
