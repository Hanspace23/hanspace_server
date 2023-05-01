package com.csee.hanspace.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteResponse {
    private Long siteId;
    private String name;
    private String description;
    private String logo;
}
