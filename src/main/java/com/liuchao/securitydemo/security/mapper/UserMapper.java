package com.liuchao.securitydemo.security.mapper;

import com.liuchao.securitydemo.security.entity.Menu;
import com.liuchao.securitydemo.security.entity.User;

import java.util.List;

public interface UserMapper {

    public User findByName(String name);

    public List<Menu> findByUserId(int userId);

    public List<User> getAllUsers();

    public List<Menu> findAllMenu();
}
