package com.fss.empdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@ToString
public class ChangeUserPassword {

    Long userSqId;

    private String userId;

    private String userName;

    private String userPassword;

    private String userOldPassword;

    private String userNewPassword;

    public Long getUserSqId() {
        return userSqId;
    }

    public void setUserSqId(Long userSqId) {
        this.userSqId = userSqId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserOldPassword() {
        return userOldPassword;
    }

    public void setUserOldPassword(String userOldPassword) {
        this.userOldPassword = userOldPassword;
    }

    public String getUserNewPassword() {
        return userNewPassword;
    }

    public void setUserNewPassword(String userNewPassword) {
        this.userNewPassword = userNewPassword;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
    }

    String userConfirmPassword;

}
