package com.fss.empdb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user_role")
public class UserRole  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID")
    Long userRoleId;

    @Column(name = "USER_ROLE_NAME", nullable = false)
    String userRoleName;

    @ManyToMany
    @JoinTable(name = "USER_ROLE_PERMISSION", joinColumns = @JoinColumn(name = "USER_ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "USER_PERMISSION_ID"))
    private Collection<UserPermission> userPermission;
}
