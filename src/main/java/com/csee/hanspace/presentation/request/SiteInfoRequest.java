package com.csee.hanspace.presentation.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SiteInfoRequest {

    private String siteName;
    private String companyName;

    private String description;
    private String logo;
    private String availableDays;
    private String timeUnit;
    private String totalTime;
    private String link;
    private String extraQuestion1;
    private String extraQuestion2;
    private List<String> hashTags;

    private int restriction;

}

