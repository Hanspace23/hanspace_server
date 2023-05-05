package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.RoomReserveRequest;
import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OneReserveDto {

    private String email;
    private Long siteId;
    private String groupName;
    private String groupPurpose;
    private String name;
    private String number;

    @Nullable
    private String answer1;
    @Nullable
    private String answer2;
    private String reserveDate;
    private List<String> reserveTime;
    private String roomName;

    static public OneReserveDto from (RoomReserveRequest request) {
        System.out.println("request = " + request);
        return new OneReserveDto(request.getEmail(), request.getSiteId(), request.getGroupName(), request.getGroupPurpose(), request.getName(), request.getNumber()
                ,request.getAnswer1()==null ?"" :  request.getAnswer1(), request.getAnswer1()==null ?"" :  request.getAnswer2(), request.getReserveStartDate(), request.getReserveTime(), request.getRoomName());
    }
}
