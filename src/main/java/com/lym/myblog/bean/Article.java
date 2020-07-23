package com.lym.myblog.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * article
 * @author 
 */
@Data
public class Article implements Serializable {
    private Integer id;

    private String title;

    /**
     * md文件源码
     */
    private String mdcontent;

    /**
     * html源码
     */
    private String htmlcontent;

    private String summary;

    private Integer cid;

    private Integer uid;

    private Date publishdate;

    private Date edittime;

    /**
     * 0表示草稿箱，1表示已发表，2表示已删除
     */
    private Integer state;

    private Integer pageview;

    private static final long serialVersionUID = 1L;
}