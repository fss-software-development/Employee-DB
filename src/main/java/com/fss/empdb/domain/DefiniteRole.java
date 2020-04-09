package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "definite_role")
public class DefiniteRole {
    @Id
    @Column(name = "DEFINITE_ROLE_ID")
    Long definiteRoleId;

    public DefiniteRole() {

    }

    @Column(name = "DEFINITE_ROLE_NAME", nullable = false)
    String definiteRoleName;
}



