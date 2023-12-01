package com.yt.backend.service;

import com.yt.backend.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUserByuserId(long userId);
    List<User> getUsers();
    void deleteUser(long userId);
    User updateUser(User user);

    boolean validateUser(String username, String password);
}