package com.lym.myblog.mapper;

import com.lym.myblog.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


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
     * @param username
     * @return
     */
    User loadUserByUsername(@Param("username") String username);

    Long addUser(User user);


}
