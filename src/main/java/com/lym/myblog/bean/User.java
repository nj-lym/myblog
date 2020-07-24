package com.lym.myblog.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * user
 * @author 
 */
@Data
public class User implements UserDetails, Serializable
{
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private boolean enabled;
    private List<Roles> roles;
    private String email;
    private String userface;
    private Timestamp regTime;

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    @Override
    @JsonIgnore
    public List<GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }
}


