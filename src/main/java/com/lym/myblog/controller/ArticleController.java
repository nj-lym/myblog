package com.lym.myblog.controller;

import com.lym.myblog.bean.Article;
import com.lym.myblog.bean.RespBean;
import com.lym.myblog.service.ArticleService;
import com.lym.myblog.utils.GetCurrentUserUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        }
        else {
            return new RespBean("error", article.getState() == 0 ? "文章保存失败!" : "文章发表失败!");
        }
    }

    /**
     * 上传图片
     * MultipartFile 用来接收 前端传过来的文件(这里是图片)
     *
     * @param req
     * @param image
     * @return
     */
    @PostMapping("/uploadimg")
    public RespBean uoloadImg(HttpServletRequest req, MultipartFile image)
    {
        StringBuffer url = new StringBuffer();
        String filePath = "uploadimg" + sdf.format(new Date());
        //返回一个包含filePath的绝对路径
        String imgFolderPath = req.getServletContext().getRealPath(filePath);
        //创建一个文件对象
        File file = new File(imgFolderPath);
        //文件不存在则创建
        if (!file.exists()) {
            file.mkdirs();
        }
        //req.getScheme()返回当前链接使用的协议；
        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName()) //获取网站域名(ip)
                .append(":")
                .append(req.getServerPort()) //获取端口号
                .append(req.getContextPath()) //返回应用名
                .append(filePath);
        // UUID是通用唯一识别码
        // getOriginalFilename() 获取原来的文件名在客户机的文件系统名称
        //将空格替换为空字符串
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {
            //将图片写入文件
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(file, imgName)));
            //图片路径
            url.append("/").append(imgName);
            return new RespBean("success", url.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new RespBean("error", "上传失败!");
    }

    /**
     * 查询文章列表
     *
     * @param state
     * @param page
     * @param count
     * @param keywords
     * @return
     */
    @GetMapping("/all")
    public Map<String, Object> getArticleByState(@RequestParam(value = "state", defaultValue = "-1") Integer state,
                                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords)
    {

        Integer total = articleService.getArticleCountByState(state, GetCurrentUserUtil.getUser().getId(), keywords);
        List<Article> articles = articleService.getArticleByState(state, page, count, keywords);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", total);
        map.put("articles", articles);
        return map;
    }

    /**
     * 根据文章id查询文章
     *
     * @param aid
     * @return
     */
    @GetMapping("/{aid}")
    public Article getArticleById(@PathVariable Long aid)
    {
        return articleService.getArticleById(aid);
    }

    /**
     * 用来删除文章 状态为2时放入回收站(相当于标识为待删除)
     *
     * @param aids
     * @param state
     * @return
     */
    @PutMapping("/dustbin")
    public RespBean updateArticleState(Long[] aids, Integer state)
    {
        int result = articleService.updateArticleState(aids, state);
        if (result == aids.length) {
            return new RespBean("success", "");
        }
        return new RespBean("error", "删除失败!");
    }


}
