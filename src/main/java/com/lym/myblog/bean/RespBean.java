package com.lym.myblog.bean;

/**
 * 通用返回类
 */
public class RespBean
{
    /**
     * 状态码
     */
    private String status;
    /**
     * 信息
     */
    private String msg;

    public RespBean() {
    }

    public RespBean(String status, String msg) {

        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
