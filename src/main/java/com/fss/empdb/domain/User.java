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

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User  implements Serializable {
    //@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_SQID")
    Long userSqId;

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ROLE_ID")
    private UserRole userRole;


    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String userPassword;

/*  //  @JsonIgnore
    private String userOldPassword;

  //  @JsonIgnore
    private String userNewPassword;

   // @JsonIgnore
    private String userConfirmPassword;*/

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USER_JWT")
    private String userJwt;

    @Column(name = "IS_RESET_REQUIRED")
    private String isResetRequired;

    /*public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserRole().getUserPermission().stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getUserPermissionName()))
                .collect(Collectors.toList());
    }*/

}
