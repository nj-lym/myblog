package com.lym.myblog.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * roles
 * @author 
 */
@Data
public class Roles implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}