package com.liuchao.securitydemo.security.controller;

import com.liuchao.securitydemo.security.entity.User;
import com.liuchao.securitydemo.security.mapper.UserMapper;
import com.liuchao.securitydemo.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/list")
    @PreAuthorize("hasAuthority('/user/list')")
    @ResponseBody
    public List login() {
        List<User> list =  userMapper.getAllUsers();
        return list;
    }

    @RequestMapping(value = "/update")
    @PreAuthorize("hasAuthority('/user/update')")
    @ResponseBody
    public HashMap<String, Object> update() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("state","success");
        return map;
    }


    @RequestMapping(value = "/del")
    @PreAuthorize("hasAuthority('/user/del')")
    @ResponseBody
    public HashMap<String, Object> del() {
        int i=1/0;
        HashMap<String, Object> map = new HashMap<>();
        map.put("state","success");
        return map;
    }
    @RequestMapping(value = "/add")
    @PreAuthorize("hasAuthority('/user/add')")
    @ResponseBody
    public HashMap<String, Object> add() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("state","success");
        return map;
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        SecurityUtils.logout();
        return "redirect:login";
    }

    @RequestMapping(value = "/info")
    @ResponseBody
    public User info() {
        return SecurityUtils.getUser();
    }
}
