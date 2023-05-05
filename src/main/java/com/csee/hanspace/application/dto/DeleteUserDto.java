package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.DeleteUserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserDto {
    private Long siteId;
    private Long userId;

    public static DeleteUserDto from(DeleteUserRequest request) {
        return new DeleteUserDto(request.getSiteId(), request.getUserId());
    }
}
