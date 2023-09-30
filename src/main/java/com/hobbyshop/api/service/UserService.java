package com.hobbyshop.api.service;

import com.hobbyshop.api.model.User;

public interface UserService {

    User saveUser(User user);

    User updateUser(User user, long userId);

    User getUserByUserID(long userId);

    void deleteUser(long userId);
}
