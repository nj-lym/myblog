package com.lym.myblog.config;

import com.lym.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description 自定义配置类
 * @Auther lym
 * @Date 2020-07-23 15:10
 * @Version 1.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    UserService userService;
    /**
     * AuthenticationManagerBuilder用来配置全局的认证相关的信息，
     * 其实就是AuthenticationProvider和UserDetailsService，
     * 前者是认证服务提供者，后者是认证用户（及其权限）。
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //验证身份
        auth.userDetailsService(userService);
    }

    /**
     * HttpSecurity 具体的权限控制规则配置。一个这个配置相当于xml配置中的一个标签。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/admin/category/all").authenticated()
                /*
                admin/**的URL都需要有超级管理员角色，如果使用.hasAuthority()方法来配置，
                需要在参数中加上ROLE_,如下.hasAuthority("ROLE_超级管理员")
                 */
                .antMatchers("/admin/**","/reg").hasRole("超级管理员")
                .anyRequest().authenticated()//其他的路径都是登录后即可访问
                .and().formLogin().loginPage("/login_page").successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws
                    IOException, ServletException
            {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
                out.flush();
                out.close();
            }
        })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
                        out.flush();
                        out.close();
                    }
                }).loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password").permitAll()
                .and().logout().permitAll().and().csrf().disable().exceptionHandling().accessDeniedHandler(getAccessDeniedHandler());

    }
    /**
     * WebSecurity 全局请求忽略规则配置（比如说静态文件，比如说注册页面）
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/blogimg/**","/index.html","/static/**");
    }

    @Bean
    AccessDeniedHandler getAccessDeniedHandler() {
        return new AuthenticationAccessDeniedHandler();
    }
}
