package com.hobbyshop.api.service;

import java.util.List;

import com.hobbyshop.api.model.Purchase;
import com.hobbyshop.api.model.User;

public interface UserService {

    User saveUser(User user);

    User getUserByUserId(Long userId);

    List<Purchase> getAllPurchasesByUserId(Long userId);

    List<User> listAllUsers();

    User updateUser(User newUserData, Long userId);

    void deleteUser(Long userId);
}
