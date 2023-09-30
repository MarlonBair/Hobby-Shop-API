package com.hobbyshop.api.service.impl;

import org.springframework.stereotype.Service;

import com.hobbyshop.api.exception.ResourceNotFoundException;
import com.hobbyshop.api.model.User;
import com.hobbyshop.api.repository.UserRepository;
import com.hobbyshop.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserID(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> 
            new ResourceNotFoundException("User", "ID", userId));

    }

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

    @Override
    public void deleteUser(long userId) {
        
        userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "ID", userId));
        
        userRepository.deleteById(userId);;
    }

    
}
