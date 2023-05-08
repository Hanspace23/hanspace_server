package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.RoomReserveRequest;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.DayOfWeek;
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

    static public DayOfWeek parseDayOfWeek(String dayString) {
        switch (dayString) {
            case "월":
                return DayOfWeek.MONDAY;
            case "화":
                return DayOfWeek.TUESDAY;
            case "수":
                return DayOfWeek.WEDNESDAY;
            case "목":
                return DayOfWeek.THURSDAY;
            case "금":
                return DayOfWeek.FRIDAY;
            case "토":
                return DayOfWeek.SATURDAY;
            case "일":
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Invalid day of week string: " + dayString);
        }
    }
}
