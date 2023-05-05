package com.csee.hanspace.presentation.response;

import com.csee.hanspace.application.dto.AllReservedDto;
import com.csee.hanspace.application.dto.UserListDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListResponse {
    private Long userId;
    private String userName;
    private String authority;
    private String email;
    private int status;

    static public UserListResponse toResponse (UserListDto dto) {
        return UserListResponse.builder()
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .authority(dto.getAuthority())
                .email(dto.getEmail())
                .status(dto.getStatus())
                .build();
    }
}
