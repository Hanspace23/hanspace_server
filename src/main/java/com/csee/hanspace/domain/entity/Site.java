package com.csee.hanspace.domain.entity;

import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.tomcat.util.http.parser.Authorization;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE site SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Site extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String logo;

    @Column(unique = true)
    private String link;
    private String company;

    private int maxDate;
    private int maxTime;

    private int restriction;

    private String question1;

    private String question2;

    private int timeUnit;

    @Builder.Default
    @OneToMany(mappedBy = "site", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<SavedUserInfo> savedUserInfoList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "site", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Tag> tagList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "site", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<ReserveRecord> recordList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "site", cascade = CascadeType.PERSIST)
    private List<Room> roomList = new ArrayList<>();
}
