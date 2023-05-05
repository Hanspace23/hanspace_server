package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.ChangeMUserStatusRequest;
import com.csee.hanspace.presentation.request.ChangeUserStatusRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMStatusDto {
    private Long siteId;
    private Integer statusId;
    private List<Long> userList;

    public static ChangeMStatusDto from(ChangeMUserStatusRequest request) {
        return new ChangeMStatusDto(request.getSiteId(), request.getStatusId(), request.getUserList());
    }
}
