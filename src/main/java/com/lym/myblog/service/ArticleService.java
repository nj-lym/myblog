package com.lym.myblog.service;

import com.lym.myblog.bean.Article;

import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-28 9:47
 * @Version 1.0
 */

public interface ArticleService
{
    /**
     * 添加新的文章
     *
     * @param article
     * @return
     */
    public int addNewArticle(Article article);

    /**
     * 获取文章总数根据文章状态及用户id
     * @param state
     * @param uid
     * @param keywords
     * @return
     */
    public Integer getArticleCountByState(Integer state, Long uid, String keywords);

    /**
     * 根据文章状态获取文章
     * @param state
     * @param page
     * @param count
     * @param keywords
     * @return
     */
    public List<Article> getArticleByState(Integer state, Integer page, Integer count, String keywords);

    /**
     * 查询某个文章
     * @param aid 文章id
     * @return
     */
    public Article getArticleById(Long aid);

    /**
     * 更新文章状态
     * @param aids 文章id
     * @param state
     * @return
     */
    public int updateArticleState(Long[] aids, Integer state);

}
