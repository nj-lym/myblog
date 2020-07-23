package com.lym.myblog.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * tags
 * @author 
 */
@Data
public class Tags implements Serializable {
    private Integer id;

    private String tagname;

    private static final long serialVersionUID = 1L;
}