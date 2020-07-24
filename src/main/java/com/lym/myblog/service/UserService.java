package com.lym.myblog.service;

import com.lym.myblog.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-23 15:25
 * @Version 1.0
 */

public interface UserService extends UserDetailsService
{
    /**
     * 添加用户(注册)
     * @param user
     * @return 0-注册成功 1-用户名重复 2-添加失败
     */
    int addUser(User user);
}
