package com.lym.myblog.service.impl;

import com.lym.myblog.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-23 15:29
 * @Version 1.0
 */

public class UserServiceImpl implements UserService
{
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        return null;
    }
}
