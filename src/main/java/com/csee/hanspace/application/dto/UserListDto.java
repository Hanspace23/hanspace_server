package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.SavedUserInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListDto {
    private Long siteId;
    private Long userId;
    private String userName;
    private int authority;
    private String email;
    private int status;

    public static UserListDto of(SavedUserInfo userInfo) {
        return UserListDto.builder()
                .userId(userInfo.getId())
                .userName(userInfo.getUser().getName())
                .authority(userInfo.getAuthority())
                .email(userInfo.getUser().getEmail())
                .status(userInfo.getStatus())
                .build();
    }

    public static UserListDto from(Long siteId) {
        return UserListDto.builder()
                .siteId(siteId)
                .build();
    }
}
