package com.lym.myblog.controller.admin;

import com.lym.myblog.bean.Article;
import com.lym.myblog.bean.RespBean;
import com.lym.myblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 超级管理员专属Controller
 *
 * @Description
 * @Auther lym
 * @Date 2020-07-31 16:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    ArticleService articleService;

    /**
     * 管理员获取所有文章信息
     * @param page
     * @param count
     * @param keywords
     * @return
     */
    @GetMapping("/article/all")
    public Map<String, Object> getArticleByStateByAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "count", defaultValue = "6") Integer count,
                                                        String keywords)
    {
        List<Article> articles = articleService.getArticleByState(-2, page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("articles", articles);
        map.put("totalCount", articleService.getArticleCountByState(1, null, keywords));
        return map;
    }

    @RequestMapping(value = "/article/dustbin", method = RequestMethod.PUT)
    public RespBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

}
