package com.csee.hanspace.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteBodyDto {
    private Long siteId;
    private String siteName;
    private String description;
    private String logo;
    private String link;
    private String company;
    private int maxDate;
    private int maxTime;
    private int timeUnit;
    private String question1;
    private String question2;
    private int restriction;
}
