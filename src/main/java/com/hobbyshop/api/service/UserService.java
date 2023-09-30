package com.hobbyshop.api.service;

import com.hobbyshop.api.model.User;

public interface UserService {

    User saveUser(User user);

    User getUserByUserID(long userId);

    User updateUser(User user, long userId);

    void deleteUser(long userId);
}
