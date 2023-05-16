package com.csee.hanspace.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserAuthRequest {
    private Long siteId;
    private Long userId;
    private int auth;

}
