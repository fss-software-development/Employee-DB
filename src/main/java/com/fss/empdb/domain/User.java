package com.fss.empdb.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    //@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_SQID")
    Long userSqId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String userPassword;

    @Column(name = "EMAIL")
    private String email;

    private String userJwt;

    @Column(name = "IS_RESET_REQUIRED")
    private String isResetRequired;

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;*/

    /*@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ROLE_ID")
    private String role;*/

}
