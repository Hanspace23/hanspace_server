package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.SiteEditRequest;
import com.csee.hanspace.presentation.request.SiteInfoRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SiteEditDto {

    private Long siteId;
    private String siteName;
    private String companyName;
    private String description;
    private int restriction;
    private String logo;
    private String availableDays;
    private String timeUnit;
    private String totalTime;
    private String link;
    private String extraQuestion1;
    private String extraQuestion2;
    private List<String> hashTags;

    public static SiteEditDto from(SiteEditRequest request) {
        return new SiteEditDto(request.getId(), request.getName(), request.getCompany(),request.getDescription(), request.getRestriction(), request.getLogo()
                , request.getMaxDate(), request.getTimeUnit(), request.getMaxTime(), request.getLink(), request.getQuestion1()
                , request.getQuestion2(), request.getTagList());
    }
}
