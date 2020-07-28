package com.lym.myblog.mapper;

import com.lym.myblog.bean.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-28 10:25
 * @Version 1.0
 */
@Mapper
public interface ArticleMapper
{
    /**
     * 添加新活动
     * @param article
     * @return
     */
    int addNewArticle(Article article);

    /**
     * 根性活动
     * @param article
     * @return
     */
    int updateArticle(Article article);




}
