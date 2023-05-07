package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.SavedUserInfo;
import com.csee.hanspace.domain.entity.Site;
import com.csee.hanspace.presentation.response.SiteResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteDto {
    private Long siteId;
    private String name;
    private String description;
    private String logo;

    public SiteDto(Site site) {
        this.siteId = site.getId();
        this.name = site.getName();
        this.description = site.getDescription();
        this.logo = site.getLogo();
    }

    public SiteDto(SavedUserInfo savedUserInfo) {
        this.siteId = savedUserInfo.getSite().getId();
        this.name = savedUserInfo.getSite().getName();
        this.description = savedUserInfo.getSite().getDescription();
        this.logo = savedUserInfo.getSite().getLogo();
    }

    public SiteResponse siteResponse() {
        return new SiteResponse(siteId, name, description, logo);
    }
}
