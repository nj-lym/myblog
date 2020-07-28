package com.lym.myblog.service;

import com.lym.myblog.bean.Article;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-28 9:47
 * @Version 1.0
 */

public interface ArticleService
{
    /**
     * 添加新的活动
     * @param article
     * @return
     */
    public int addNewArticle(Article article);



}
