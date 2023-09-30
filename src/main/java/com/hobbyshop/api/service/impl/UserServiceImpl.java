package com.hobbyshop.api.service.impl;

import com.hobbyshop.api.model.User;
import com.hobbyshop.api.repository.UserRepository;
import com.hobbyshop.api.service.UserService;

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
    public User updateUser(User user, long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public User getUserByUserID(long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByUserID'");
    }

    @Override
    public void deleteUser(long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    
}
