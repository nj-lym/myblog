package com.lym.myblog.service.impl;

import com.lym.myblog.bean.Article;
import com.lym.myblog.mapper.ArticleMapper;
import com.lym.myblog.mapper.TagsMapper;
import com.lym.myblog.service.ArticleService;
import com.lym.myblog.utils.GetCurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-28 9:50
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService
{

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagsMapper tagsMapper;

    @Override
    public int addNewArticle(Article article)
    {
        //处理文章摘要
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            String stripthtml = striptHtml(article.getHtmlContent());
            article.setSummary(stripthtml.substring(0, stripthtml.length() > 50 ? 50 : stripthtml.length()));
        }
        //添加操作
        if (article.getId() == -1) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                article.setPublishDate(timestamp);
            }
            article.setEditTime(timestamp);
            //设置当前用户
            article.setUid(GetCurrentUserUtil.getUser().getId());
            int i = articleMapper.addNewArticle(article);
            //打标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        }
        //修改
        else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            //更新
            article.setEditTime(new Timestamp(System.currentTimeMillis()));
            int i = articleMapper.updateArticle(article);
            //修改标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
        }
    }

    /**
     * 分割html(对内容进行处理)
     *
     * @param content
     * @return
     */
    private String striptHtml(String content)
    {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    /**
     * 添加标签
     *
     * @param dynamicTags
     * @param aid         文章id
     * @return
     */
    private int addTagsToArticle(String[] dynamicTags, Long aid)
    {
        //1,删除文章目前所有的标签
        tagsMapper.deleteTagsByAid(aid);
        //2,将上传的标签全部存入数据库
        tagsMapper.saveTags(dynamicTags);
        //3,查询这些标签的id
        List<Long> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);
        //4,重新给文章设置标签
        int i = tagsMapper.saveTags2ArticleTags(tIds, aid);
        return i == dynamicTags.length ? i : -1;
    }

    @Override
    public Integer getArticleCountByState(Integer state, Long uid, String keywords)
    {
        return articleMapper.getArticleCountByState(state, uid, keywords);
    }

    @Override
    public List<Article> getArticleByState(Integer state, Integer page, Integer count, String keywords)
    {
        int start = (page - 1) * count;
        Long uid = GetCurrentUserUtil.getUser().getId();
        return articleMapper.getArticleByState(state, start, count, uid, keywords);
    }

    @Override
    public Article getArticleById(Long aid)
    {
        Article article = articleMapper.getArticleById(aid);
        articleMapper.pvIncrement(aid);
        return article;
    }

    @Override
    public int updateArticleState(Long[] aids, Integer state)
    {
        //状态等于2 删除
        if (state == 2) {
            return articleMapper.deleteArticleById(aids);
        } else {
            return articleMapper.updateArticleState(aids, 2);//放入到回收站中
        }
    }
}
