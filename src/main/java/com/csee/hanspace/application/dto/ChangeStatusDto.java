package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.ChangeUserStatusRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusDto {
    private Long siteId;
    private Integer statusId;
    private Long userId;

    public static ChangeStatusDto from(ChangeUserStatusRequest request) {
        return new ChangeStatusDto(request.getSiteId(), request.getStatusId(), request.getUserId());
    }
}
