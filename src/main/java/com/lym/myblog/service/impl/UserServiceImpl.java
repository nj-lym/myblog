package com.lym.myblog.service.impl;

import com.lym.myblog.bean.Roles;
import com.lym.myblog.bean.User;
import com.lym.myblog.mapper.RolesMapper;
import com.lym.myblog.mapper.UserMapper;
import com.lym.myblog.service.UserService;
import com.lym.myblog.utils.GetCurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-23 15:29
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    UserMapper userMapper;

    @Autowired
    RolesMapper rolesMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            return new User();
        }
        //查询用户的角色信息，并返回存入user中
        List<Roles> roles = rolesMapper.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    /**
     * 0-注册成功
     * 1-用户名重复
     * 3-注册失败
     *
     * @param user
     * @return
     */
    @Override
    public int addUser(User user)
    {
        User loadUser = userMapper.loadUserByUsername(user.getUsername());
        if (loadUser == null) {
            return 1;
        }
        //添加用户,添加用户前对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true); //用户可用
        Long result=userMapper.addUser(user);
        //配置用户信息,默认为普通用户
        String[]roles=new String[]{"2"};
        int i = rolesMapper.addRoles(roles, user.getId());
        boolean b = i == roles.length && result == 1;
        if (b) {
            return 0;
        } else {
            return 2;
        }
    }

    @Override
    public int updateUserEmail(String email)
    {
        return userMapper.updateUserEmail(email, GetCurrentUserUtil.getUser().getId());
    }

    @Override
    public List<User> getUserByNickname(String nickName)
    {
        return userMapper.getUserByNickname(nickName);
    }

    @Override
    public List<Roles> getAllRoles()
    {
        return userMapper.getAllRole();
    }

    @Override
    public int updateUserEnabled(Boolean enabled, Long uid)
    {
        return userMapper.updateUserEnabled(enabled,uid);
    }

    @Override
    public int deleteUserById(Long uid)
    {
        return userMapper.deleteUserById(uid);
    }

    @Override
    public int updateUserRoles(Long[] rids, Long uid)
    {
        int i =userMapper.deleteUserRolesByUid(uid);
        return userMapper.setUserRoles(rids,uid);
    }

    @Override
    public User getUserByid(Long id)
    {
        return userMapper.getUserByid(id);
    }
}
