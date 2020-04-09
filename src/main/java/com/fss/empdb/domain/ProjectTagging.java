package com.fss.empdb.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "project_tagging")
public class ProjectTagging {
    @Id
    @Column(name = "PROJECT_TAGGING_ID")
    Long projectTaggingId;

    public ProjectTagging() {

    }

    @Column(name = "PROJECT_TAGGING", nullable = false)
    String projectTaggingName;
}


