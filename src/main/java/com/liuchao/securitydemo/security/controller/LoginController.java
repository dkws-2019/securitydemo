package com.liuchao.securitydemo.security.controller;

import com.liuchao.securitydemo.security.entity.User;
import com.liuchao.securitydemo.security.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public String login(){
        return "login.html";
    }

    @RequestMapping("/userLogin")
    public String userLogin(@RequestParam("username")String userName,
                            @RequestParam("password")String password,
                            HttpServletRequest request) throws MyException {
        if(StringUtils.isEmpty(userName)){
            throw new MyException("没有输入用户名");
        }
        if(StringUtils.isEmpty(password)){
            throw new MyException("没有输入密码");
        }
        User user=new User();
        user.setUsername(userName);
        user.setPassword(password);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userName,password);
        try{
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:login-error?error=2";
        }
        return "redirect:index";
    }
}
