package com.lym.myblog.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description 解决认证过的用户访问无权限资源时的异常
 * @Auther lym
 * @Date 2020-07-23 14:21
 * @Version 1.0
 */

public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws
            IOException, ServletException
    {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.write("用户权限不足，请联系管理员");
        out.flush();
        out.close();

    }
}
