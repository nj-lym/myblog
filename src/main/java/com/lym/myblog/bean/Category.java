package com.lym.myblog.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * category
 * @author 
 */
@Data
public class Category implements Serializable {
    private Integer id;

    private String catename;

    private Date date;

    private static final long serialVersionUID = 1L;
}