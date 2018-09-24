package com.junwan.demo.service;

import com.junwan.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User createUser(User user);
    User findById(Integer id);
    void deleteById(Integer id);
    List<User> findByName(String name);
}
