
package com.fss.empdb.service;

import com.fss.empdb.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class CustomUserDetails implements UserDetails {

    //private static final long serialVersionUID = 1256711395932122675L;
    private  String userName;
    public CustomUserDetails(String userName){
        log.info("Username ::::"+userName);
        this.userName=userName;
    }

    public User getUser() {
        log.info("get Username ::::"+userName);
        return user;
    }

    public void setUser(User user) {
        log.info("set Username ::::"+userName);
        this.user = user;
    }

    private User user;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("getAuthorities ::--::");
        return user.getUserRole().getUserPermission().stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getUserPermissionName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }


    @Override
    public String getUsername() {
        return user.getUserId();
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
}

