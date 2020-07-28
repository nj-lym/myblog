package com.lym.myblog.controller;

import com.lym.myblog.bean.Article;
import com.lym.myblog.bean.RespBean;
import com.lym.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-28 9:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/acticle")
public class ArticleController
{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    ArticleService articleService;

    @PostMapping("/")
    public RespBean addNewArticle(Article article)
    {
        int result = articleService.addNewArticle(article);
        if (result == 1) {
            return new RespBean("success", article.getId() + "");
        }else {
            return new RespBean("error", article.getState() == 0 ? "文章保存失败!" : "文章发表失败!");
        }
    }


}
