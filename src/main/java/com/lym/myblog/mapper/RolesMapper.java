package com.lym.myblog.mapper;

import com.lym.myblog.bean.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-24 14:54
 * @Version 1.0
 */
@Mapper
public interface RolesMapper
{
    /**
     * 根据用户id获取角色信息
     * @param uid
     * @return
     */
    List<Roles> getRolesByUid(Long uid);

    /**
     * 添加角色
     * @param roles
     * @param uid
     * @return
     */
    int addRoles(@Param("roles") String[] roles, @Param("uid") Long uid);



}
