package com.lym.myblog.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private Boolean enabled;

    private String email;

    private String userface;

    private Date regtime;

    private static final long serialVersionUID = 1L;
}