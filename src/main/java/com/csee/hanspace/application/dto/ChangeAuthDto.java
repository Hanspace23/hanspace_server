package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.ChangeUserAuthRequest;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ChangeAuthDto {
    private Long siteId;
    private Long userId;
    private int auth;

    public static ChangeAuthDto from(ChangeUserAuthRequest request) {
        return new ChangeAuthDto(request.getSiteId(), request.getUserId(), request.getAuth());
    }
}
