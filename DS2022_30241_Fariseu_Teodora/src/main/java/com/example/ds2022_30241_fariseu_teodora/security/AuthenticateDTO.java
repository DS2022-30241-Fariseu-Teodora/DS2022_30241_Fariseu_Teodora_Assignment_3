package com.example.ds2022_30241_fariseu_teodora.security;

import com.example.ds2022_30241_fariseu_teodora.entity.Role;
import com.example.ds2022_30241_fariseu_teodora.entity.SiteUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class AuthenticateDTO implements UserDetails {
    private SiteUser user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Role getRole() {
        return user.getRole();
    }

    public String getId() {
        return user.getId();
    }
}
