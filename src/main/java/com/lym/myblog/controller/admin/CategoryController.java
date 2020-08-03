package com.lym.myblog.controller.admin;

import com.lym.myblog.bean.Category;
import com.lym.myblog.bean.RespBean;
import com.lym.myblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 超级管理员专属Controller
 *
 * @Description
 * @Auther lym
 * @Date 2020-07-27 15:49
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController
{

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    /**
     * 注解@PathVariable: 映射 URL 绑定的占位符
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public RespBean deleteByid(@PathVariable String ids)
    {
        boolean result = categoryService.deleteCategoryByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewCate(Category category) {

        if ("".equals(category.getCatename()) || category.getCatename() == null) {
            return new RespBean("error", "请输入栏目名称!");
        }

        int result = categoryService.addCategory(category);

        if (result == 1) {
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateCate(Category category) {
        int i = categoryService.updateCategoryById(category);
        if (i == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }


}
