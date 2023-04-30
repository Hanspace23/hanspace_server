package com.csee.hanspace.application.dto;


import com.csee.hanspace.presentation.request.ChangeMStatusRequest;
import com.csee.hanspace.presentation.request.ChangeStatusRequest;
import com.csee.hanspace.presentation.request.RoomReserveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMRequestDto {
    private Long siteId;
    private Integer statusId;
    private List<Long> recordList;

    static public ChangeMRequestDto from (ChangeMStatusRequest request) {
        System.out.println("request.getRecordList() = " + request.getRecordList());
        return new ChangeMRequestDto(request.getSiteId(), request.getStatusId(), request.getRecordList());
    }
}
