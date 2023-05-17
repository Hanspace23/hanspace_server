package com.csee.hanspace.presentation.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SiteEditRequest {

    private String company;

    private String description;
    private Long id;
    private String link;

    private String logo;
    private String maxDate;

    private String maxTime;
    private String name;

    private String question1;
    private String question2;
    private int restriction;

    private List<String> tagList;
    private String timeUnit;

}

