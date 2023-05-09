package com.csee.hanspace.presentation.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSiteRequest {
    private Long siteId;
    private Long savedUserInfoId;
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
