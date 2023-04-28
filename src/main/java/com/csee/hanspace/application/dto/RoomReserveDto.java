package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.RoomReserveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomReserveDto {
    private String groupName;
    private String groupPurpose;
    private String name;
    private String number;
    private String answer1;
    private String answer2;
    private String reserveDate;
    private List<String> reserveTime;

    private String roomName;

    private int regular;
    private boolean reserveOne;
    static public RoomReserveDto from (RoomReserveRequest request) {
        return new RoomReserveDto(request.getGroupName(), request.getGroupPurpose(), request.getName(), request.getNumber()
        ,request.getAnswer1(), request.getAnswer2(), request.getReserveDate(), request.getReserveTime(), request.getRoomName(), request.getRegular(), request.isReserveOne());
    }
}
