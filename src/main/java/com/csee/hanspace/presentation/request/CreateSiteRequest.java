package com.csee.hanspace.presentation.request;

import com.csee.hanspace.application.dto.SiteCUDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSiteRequest {
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
    private Long userId;

    public SiteCUDto siteCUDto() {
        return new SiteCUDto(siteName, description, logo, link, company, maxDate, maxTime, timeUnit, question1, question2, restriction);
    }
}
