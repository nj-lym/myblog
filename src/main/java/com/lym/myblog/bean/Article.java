package com.lym.myblog.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * article
 *
 * @author
 */
@Data
public class Article implements Serializable
{
    private Long id;
    private String title;
    /**
     * md文件源码
     */
    private String mdContent;
    /**
     * html源码
     */
    private String htmlContent;
    private String summary;
    private Long cid;
    private Long uid;
    private Timestamp publishDate;
    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     */
    private Integer state;
    private Integer pageView;
    private Timestamp editTime;
    private String[] dynamicTags;
    private String nickname;
    private String cateName;
    private List<Tags> tags;
    private String stateStr;
    private static final long serialVersionUID = 1L;
}