package com.csee.hanspace.domain.entity;

import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Site extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String logo;
    private String link;
    private String company;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime maxDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime maxTime;

    private boolean restriction;

    @OneToMany(mappedBy = "site", cascade = CascadeType.PERSIST)
    private List<Authorization> authorizationList = new ArrayList<>();

    @OneToMany(mappedBy = "site", cascade = CascadeType.PERSIST)
    private List<Tag> tagList = new ArrayList<>();

    @OneToMany(mappedBy = "site", cascade = CascadeType.PERSIST)
    private List<SiteEtc> etcList = new ArrayList<>();

}
