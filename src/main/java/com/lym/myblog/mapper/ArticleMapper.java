package com.lym.myblog.mapper;

import com.lym.myblog.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 添加新文章
     * @param article
     * @return
     */
    int addNewArticle(Article article);

    /**
     * 更新文章
     * @param article
     * @return
     */
    int updateArticle(Article article);

    /**
     * 根据文章状态获取文章
     * @param state 状态
     * @param start 开始序号(分页起始)
     * @param count 每页记录数
     * @param uid 用户id
     * @param keywords 关键字
     * @return
     */
    List<Article> getArticleByState(@Param("state") Integer state, @Param("start") Integer start, @Param("count") Integer count, @Param("uid") Long uid, @Param("keywords") String keywords);

    /**
     * 根据文章状态获取文章数量
     * @param state 状态
     * @param uid 用户id
     * @param keywords
     * @return
     */
    Integer  getArticleCountByState(@Param("state") Integer state, @Param("uid") Long uid, @Param("keywords") String keywords);

    /**
     * 根据id查询文章
     * @param aid
     * @return
     */
    Article getArticleById(Long aid);

    /**
     * 增量
     * @param aid
     */
    void pvIncrement(Long aid);

    /**
     * 删除文章
     * @param aids
     * @return
     */
    int deleteArticleById(@Param("aids") Long[] aids);

    /**
     * 将文章放入回收站中(状态修改为2)
     * @param aids
     * @param state
     * @return
     */
    int updateArticleState(@Param("aids") Long aids[], @Param("state") Integer state);

}
