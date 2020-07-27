package com.lym.myblog.service;

import com.lym.myblog.bean.Roles;
import com.lym.myblog.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

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
     *
     * @param user
     * @return 0-注册成功 1-用户名重复 2-添加失败
     */
    public int addUser(User user);

    /**
     * 更新用户email
     *
     * @param email
     * @return
     */
    public int updateUserEmail(String email);

    /**
     * 根据昵称获取用户信息
     *
     * @param nickName 昵称
     * @return 用户列表
     */
    public List<User> getUserByNickname(String nickName);

    /**
     * 获取所有角色
     *
     * @return
     */
    public List<Roles> getAllRoles();

    /**
     * 更新用户是否可用
     *
     * @param enabled
     * @param uid
     * @return
     */
    public int updateUserEnabled(Boolean enabled, Long uid);

    /**
     * 根据用户id删除用户
     *
     * @param uid
     * @return
     */
    public int deleteUserById(Long uid);

    /**
     * 更新用户角色一个用户可以有多个角色
     * @param rids 角色id
     * @param uid 用户id
     * @return
     */
    public int updateUserRoles(Long[] rids,Long uid);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    public User getUserByid(Long id);

}
