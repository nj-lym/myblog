package com.lym.myblog.mapper;

import com.lym.myblog.bean.Roles;
import com.lym.myblog.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description
 * @Auther lym
 * @Date 2020-07-24 13:41
 * @Version 1.0
 */
@Mapper
public interface UserMapper
{
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User loadUserByUsername(@Param("username") String username);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    Long addUser(User user);

    /**
     * 更新用户email
     *
     * @param email
     * @param id    用户唯一标识id
     * @return
     */
    int updateUserEmail(@Param("email") String email, @Param("id") Long id);

    /**
     * 根据用户名获取用户信息
     *
     * @param nickName
     * @return
     */
    List<User> getUserByNickname(@Param("nickname") String nickName);

    /**
     * 获取所有角色信息
     *
     * @return
     */
    List<Roles> getAllRole();

    /**
     * 更新用户状态(是否可用)
     *
     * @param enabled
     * @param uid     用户id
     * @return
     */
    int updateUserEnabled(Boolean enabled, Long uid);

    /**
     * 根据用户id删除用户
     *
     * @param uid
     * @return
     */
    public int deleteUserById(Long uid);

    /** 根据用户id删除原来的角色
     * @param id 用户id
     * @return
     */
    public int deleteUserRolesByUid(Long id);

    /**
     * 给用户设置角色
     * @param rids 角色id
     * @param id 用户id
     * @return
     */
    int setUserRoles(@Param("rids") Long[] rids, @Param("id") Long id);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    public User getUserByid(Long id);

}
