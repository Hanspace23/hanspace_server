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
        return new SiteEditDto(request.getSiteId(), request.getSiteName(), request.getCompanyName(),request.getDescription(), request.getRestriction(), request.getLogo()
                , request.getAvailableDays(), request.getTimeUnit(), request.getTotalTime(), request.getLink(), request.getExtraQuestion1()
                , request.getExtraQuestion2(), request.getHashTags());
    }
}
