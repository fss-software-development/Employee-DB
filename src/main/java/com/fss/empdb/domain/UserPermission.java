package com.fss.empdb.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user_permission")
public class UserPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_PERMISSION_ID")
    Long userPermissionId;

    @Column(name = "USER_PERMISSION_NAME", nullable = false)
    String userPermissionName;


}
