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
    private String link;

    public SiteDto(Site site) {
        this.siteId = site.getId();
        this.name = site.getName();
        this.description = site.getDescription();
        this.logo = site.getLogo();
        this.link = site.getLink();
    }

    public SiteDto(SavedUserInfo savedUserInfo) {
        this.siteId = savedUserInfo.getSite().getId();
        this.name = savedUserInfo.getSite().getName();
        this.description = savedUserInfo.getSite().getDescription();
        this.logo = savedUserInfo.getSite().getLogo();
        this.link = savedUserInfo.getSite().getLink();
    }

    public SiteResponse siteResponse() {
        return new SiteResponse(siteId, name, description, logo, link);
    }
}
