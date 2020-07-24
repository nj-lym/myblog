package com.lym.myblog.controller;

import com.lym.myblog.bean.RespBean;
import com.lym.myblog.bean.User;
import com.lym.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 登录/注册接口
 * @Auther lym
 * @Date 2020-07-24 9:16
 * @Version 1.0
 */
@RestController
@RequestMapping("")
public class LoginRegController
{
    @Autowired
    private UserService userService;

    @GetMapping("/login_error")
    public RespBean loginError()
    {
        return new RespBean("error", "登录失败");
    }

    @GetMapping("/login_success")
    public RespBean loginSuccess()
    {
        return new RespBean("success", "登录成功");
    }

    @GetMapping("/login_page")
    public RespBean loginPage()
    {
        return new RespBean("error", "用户尚未登录");
    }

    @PostMapping("/resg")
    public RespBean register(User user)
    {
        RespBean respBean = new RespBean();
        int result = userService.addUser(user);

        if (result == 0) {
            respBean.setMsg("注册成功");
            respBean.setStatus("success");
        }
        else if (result == 1) {
            respBean.setMsg("用户名重复");
            respBean.setStatus("error");
        }else {
            respBean.setMsg("注册失败");
            respBean.setStatus("error");
        }
        return respBean;
    }

}
