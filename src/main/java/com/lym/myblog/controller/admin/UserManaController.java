package com.lym.myblog.controller.admin;

import com.lym.myblog.bean.RespBean;
import com.lym.myblog.bean.Roles;
import com.lym.myblog.bean.User;
import com.lym.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-31 17:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class UserManaController
{
    @Autowired
    UserService userService;

    /**
     * 根据昵称获取用户
     *
     * @param nickname
     * @return
     */
    @GetMapping("/user")
    public List<User> getUserByNickname(String nickname)
    {
        return userService.getUserByNickname(nickname);
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}}")
    public User getUserById(@PathVariable Long id)
    {
        return userService.getUserByid(id);
    }

    /**
     * 获取所有角色信息
     *
     * @return
     */
    @GetMapping("/roles")
    public List<Roles> getAllRole()
    {
        return userService.getAllRoles();
    }

    /**
     * 跟新用户是否可用
     *
     * @param enabled
     * @param uid
     * @return
     */
    @PutMapping("/user/enabled")
    public RespBean updateUserEnabled(Boolean enabled, Long uid)
    {
        if (userService.updateUserEnabled(enabled, uid) == 1) {
            return new RespBean("success", "更新成功!");
        }
        else {
            return new RespBean("error", "更新失败!");
        }
    }

    /**
     * 根据id删除用户
     *
     * @param uid
     * @return
     */
    @DeleteMapping("/user/{uid}")
    public RespBean deleteUserById(@PathVariable Long uid)
    {
        if (userService.deleteUserById(uid) == 1) {
            return new RespBean("success", "删除成功!");
        }
        else {
            return new RespBean("error", "删除失败!");
        }
    }

    /**
     * 更新用户角色
     *
     * @param rids
     * @param id
     * @return
     */
    @PutMapping("/user/role")
    public RespBean updateUserRoles(Long[] rids, Long id)
    {
        if (userService.updateUserRoles(rids, id) == rids.length) {
            return new RespBean("success", "更新成功!");
        }
        else {
            return new RespBean("error", "更新失败!");
        }
    }

}
