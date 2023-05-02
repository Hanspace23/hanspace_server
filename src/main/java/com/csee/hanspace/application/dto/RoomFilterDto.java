//package com.csee.hanspace.application.dto;
//
//import com.csee.hanspace.domain.entity.ReserveRecord;
//import com.csee.hanspace.domain.entity.Room;
//import com.csee.hanspace.domain.entity.RoomTags;
////import com.csee.hanspace.domain.entity.TimeRecord;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class RoomFilterDto {
//
//    private Long id;
//    private String roomName;
//    private List<RoomTags> tags;
//    private int availableStart;
//    private int availableEnd;
//    private String img;
//    private String detail;
//    private List<TimeRecordDto> reservedList;
//
//    public static RoomFilterDto from(Room room){
//        return RoomFilterDto.builder()
//                .id(room.getId())
//                .roomName(room.getName())
//                .tags(room.getRoomTagsList())
//                .availableStart(room.getStartTime())
//                .availableEnd(room.getEndTime())
//                .img(room.getImage())
//                .detail(room.getDescription())
//                .reservedList(room.retListOfTimeRecord())
//                .build();
//    }
//
//
//
//}
