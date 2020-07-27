package com.lym.myblog.service.impl;

import com.lym.myblog.bean.Category;
import com.lym.myblog.mapper.CategoryMapper;
import com.lym.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-27 15:51
 * @Version 1.0
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories()
    {
        return categoryMapper.getAllCategories();
    }

    @Override
    public Boolean deleteCategoryByIds(String ids)
    {
        String[] split = ids.split(",");
        int result = categoryMapper.deleteCategoryByIds(split);
        return result == split.length;
    }

    @Override
    public int addCategory(Category category)
    {
        category.setDate(new Timestamp(System.currentTimeMillis()));
        return categoryMapper.addCategory(category);
    }

    @Override
    public int updateCategoryById(Category category)
    {
        return categoryMapper.updateCategoryById(category);
    }
}
