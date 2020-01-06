package com.liuchao.securitydemo.security.config;

import com.liuchao.securitydemo.security.entity.Menu;
import com.liuchao.securitydemo.security.entity.User;
import com.liuchao.securitydemo.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByName(s);
        if(StringUtils.isEmpty(user)){
            throw new UsernameNotFoundException("账号不存在");
        }

        List<Menu> menuList = userMapper.findByUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for(Menu menu: menuList){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getUrl());
                //此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);

           }
        user.setAuthorities(grantedAuthorities);

        return user;
    }
}
