package com.lym.myblog.utils;

import com.lym.myblog.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Description 获取当前用户工具类
 * @Auther lym
 * @Date 2020-07-24 9:13
 * @Version 1.0
 */

public class GetCurrentUserUtil
{
public static User getUser(){
    //获取当前用户信息
    User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user;
}
}
