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
    private Long id;
    private String userName;
    private String authorization;
    private String email;
    private String status;

    static public UserListResponse toResponse (UserListDto dto) {
        System.out.println("dto.getAuthority() = " + dto.getAuthority());
        return UserListResponse.builder()
                .id(dto.getUserId())
                .userName(dto.getUserName())
                .authorization(dto.getAuthority() == 1 ? "creator" : dto.getAuthority() == 2 ? "admin" : dto.getAuthority() == 3 ? "user" : "blacklist")
                .email(dto.getEmail())
                .status(dto.getStatus() == 1 ? "대기" : dto.getStatus() == 2 ? "승인" : "거절")
                .build();
    }
}
