//package com.csee.hanspace.application.dto;
//
//import com.csee.hanspace.presentation.request.RegularReserveRequest;
//import com.csee.hanspace.presentation.request.RoomReserveRequest;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.springframework.lang.Nullable;
//
//import java.util.List;
//
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class RegularReserveDto {
//    private String groupName;
//    private String groupPurpose;
//    private String name;
//    private String number;
//    @Nullable
//    private String answer1;
//    @Nullable
//    private String answer2;
//    private String reserveDate;
//    private List<String> reserveTime;
//
//    private String roomName;
//
//    private int regular;
//    private boolean reserveOne;
//
//    private String weekdays;
//    static public RegularReserveDto from (RegularReserveRequest request) {
//        return new RegularReserveDto(request.getGroupName(), request.getGroupPurpose(), request.getName(), request.getNumber()
//                ,request.getAnswer1(), request.getAnswer2(), request.getReserveDate(), request.getReserveTime(), request.getRoomName(), request.getRegular(), request.isReserveOne(), request.getWeekdays());
//    }
//}
