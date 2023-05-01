package com.csee.hanspace.application.dto;


import com.csee.hanspace.presentation.request.ChangeStatusRequest;
import com.csee.hanspace.presentation.request.RoomReserveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRequestDto {
    private Long siteId;
    private Integer statusId;
    private Long recordId;

    static public ChangeRequestDto from (ChangeStatusRequest request) {
        return new ChangeRequestDto(request.getSiteId(), request.getStatusId(), request.getRecordId());
    }
}
