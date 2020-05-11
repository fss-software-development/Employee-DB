package com.fss.empdb.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_PERMISSION_ID")
    Long userPermissionId;

    @Column(name = "USER_PERMISSION_NAME", nullable = false)
    String userPermissionName;
}
