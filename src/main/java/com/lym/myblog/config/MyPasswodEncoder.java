package com.lym.myblog.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/** PasswordEncoder spring提供的密码加密接口
 * @Description 自定义密码加密
 * @Auther lym
 * @Date 2020-07-23 14:42
 * @Version 1.0
 */
@Component
public class MyPasswodEncoder implements PasswordEncoder
{
    /**
     *
     * @param sequence CharSequence是一个描述字符串结构的接口
     * @return
     */
    @Override
    public String encode(CharSequence sequence)
    {
        //MD5方式加密
        return DigestUtils.md5DigestAsHex(sequence.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence sequence, String encodedPassword)
    {
        //将未经过加密的密码和已经过加密的密码进行比较是否相同
        return encodedPassword.equals(DigestUtils.md5DigestAsHex(sequence.toString().getBytes()));
    }
}
