package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.DeleteMUserRequest;
import com.csee.hanspace.presentation.request.DeleteUserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMUserDto {
    private Long siteId;
    private List<Long> userList;

    public static DeleteMUserDto from(DeleteMUserRequest request) {
        return new DeleteMUserDto(request.getSiteId(), request.getUserList());
    }
}
