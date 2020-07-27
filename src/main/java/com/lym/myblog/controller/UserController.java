package com.lym.myblog.controller;

import com.lym.myblog.bean.RespBean;
import com.lym.myblog.service.UserService;
import com.lym.myblog.utils.GetCurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-27 11:18
 * @Version 1.0
 */
@RestController
public class UserController
{
    @Autowired
    UserService userService;

    @RequestMapping("/currentUserName")
    public String currentUserName()
    {
        return GetCurrentUserUtil.getUser().getUsername();
    }

    @RequestMapping("/currentUserId")
    public Long currentUserId()
    {
        return GetCurrentUserUtil.getUser().getId();
    }

    @RequestMapping("/currentUserEmail")
    public String currentUserEmail()
    {
        return GetCurrentUserUtil.getUser().getEmail();
    }

    @RequestMapping("/isAdmin")
    public Boolean isAdmin()
    {
        //获取当前用户的所有权限,判断是不是超级管理员
        List<GrantedAuthority> authorities = GetCurrentUserUtil.getUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 修改用户email
     * @param email
     * @return
     */
    @RequestMapping(value = "/updateUserEmail",method = RequestMethod.PUT)
    public RespBean updateUserEmail(String email) {
        if (userService.updateUserEmail(email) == 1) {
            return new RespBean("success", "开启成功!");
        }
        return new RespBean("error", "开启失败!");
    }
}
