package com.fss.empdb.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "ROLE_ID")
    Long roleId;

    @Column(name = "ROLE_NAME", nullable = false)
    String roleName;

}



