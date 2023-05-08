package com.csee.hanspace.presentation.response;

import com.csee.hanspace.domain.entity.SavedUserInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteUserResponse {
    private Long id;
    private String groupName;
    private String purpose;
    private String reservation;
    private String contact;
    private int authority;
    private int status;

    public static SiteUserResponse from (SavedUserInfo savedUserInfo){
        return SiteUserResponse.builder()
                .id(savedUserInfo.getId())
                .groupName(savedUserInfo.getGroupName())
                .purpose(savedUserInfo.getPurpose())
                .reservation(savedUserInfo.getReservation())
                .contact(savedUserInfo.getContact())
                .authority(savedUserInfo.getAuthority())
                .status(savedUserInfo.getStatus())
                .build();
    }
}
