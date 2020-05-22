package com.pm.service;

import com.pm.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);

    List<User> findAllUser();

    List<User> findAllUserByInput(String input);

    User findById(Long id);

    User deleteUser(Long id);
}
