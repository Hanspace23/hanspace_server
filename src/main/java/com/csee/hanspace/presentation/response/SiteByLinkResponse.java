package com.csee.hanspace.presentation.response;

import com.csee.hanspace.domain.entity.Site;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteByLinkResponse {
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
    private String domain;

    public static SiteByLinkResponse from (Site site, String domain){
        return SiteByLinkResponse.builder()
                .id(site.getId())
                .name(site.getName())
                .description(site.getDescription())
                .logo(site.getLogo())
                .link(site.getLink())
                .company(site.getCompany())
                .maxDate(site.getMaxDate())
                .maxTime(site.getMaxTime())
                .restriction(site.getRestriction())
                .question1(site.getQuestion1())
                .question2(site.getQuestion2())
                .timeUnit(site.getTimeUnit())
                .domain(domain)
                .build();
    }
}
