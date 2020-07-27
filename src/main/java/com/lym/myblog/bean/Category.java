package com.lym.myblog.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * category
 * @author 
 */
@Data
public class Category implements Serializable {
    private Long id;

    private String catename;

    private Timestamp date;

    private static final long serialVersionUID = 1L;
}