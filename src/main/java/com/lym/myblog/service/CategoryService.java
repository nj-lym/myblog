package com.lym.myblog.service;

import com.lym.myblog.bean.Category;

import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-27 15:51
 * @Version 1.0
 */

public interface CategoryService
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
    Boolean deleteCategoryByIds(String ids);

    /**
     * 添加类别
     * @param category
     * @return
     */
    public int addCategory(Category category);

    /**
     * 根据id更新类别
     * @param category
     * @return
     */
    public int updateCategoryById(Category category);
}
