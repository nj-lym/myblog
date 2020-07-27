package com.lym.myblog.mapper;

import com.lym.myblog.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-27 15:56
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper
{
    /**
     * 获取所有类别
     * @return
     */
    List<Category> getAllCategories();

    /**
     * 根据id删除类别
     * @param ids
     * @return
     */
    int deleteCategoryByIds(@Param("ids") String[] ids);

    /**
     * 根据id更新类别
     * @param category
     * @return
     */
    int updateCategoryById(Category category);

    /**
     * 添加类别
     * @param category
     * @return
     */
    int addCategory(Category category);
}
