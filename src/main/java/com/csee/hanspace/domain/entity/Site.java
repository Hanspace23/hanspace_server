package com.csee.hanspace.domain.entity;

import com.csee.hanspace.application.dto.SiteEditDto;
import com.csee.hanspace.application.dto.SiteInfoDto;
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

    public static Site from(SiteInfoDto dto) {
        return Site.builder()
                .name(dto.getSiteName())
                .description(dto.getDescription())
                .logo(dto.getLogo())
                .link(dto.getLink())
                .company(dto.getCompanyName())
                .maxDate(Integer.parseInt(dto.getAvailableDays()))
                .maxTime(Integer.parseInt(dto.getTotalTime()))
                .restriction(dto.getRestriction())
                .question1(dto.getExtraQuestion1())
                .question2(dto.getExtraQuestion2())
                .timeUnit(Integer.parseInt(dto.getTimeUnit()))
                .build();
    }

    public static Site from(Site site , SiteEditDto dto) {
        site.setId(dto.getSiteId());
        site.setName(dto.getSiteName());
        site.setDescription(dto.getDescription());
        site.setLogo(dto.getLogo());
        site.setLink(dto.getLink());
        site.setCompany(dto.getCompanyName());
        site.setMaxDate(Integer.parseInt(dto.getAvailableDays()));
        site.setMaxTime(Integer.parseInt(dto.getTotalTime()));
        site.setRestriction(dto.getRestriction());
        site.setQuestion1(dto.getExtraQuestion1());
        site.setQuestion2(dto.getExtraQuestion2());
        site.setTimeUnit(Integer.parseInt(dto.getTimeUnit()));

        return site;
    }


}
