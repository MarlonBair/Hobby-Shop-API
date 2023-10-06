package com.hobbyshop.api.service;

import com.hobbyshop.api.model.User;

public interface UserService {

    User saveUser(User user);

    User getUserByUserId(long userId);

    User updateUser(User newUserData, long userId);

    void deleteUser(long userId);
}