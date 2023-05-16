//package com.csee.hanspace.application.dto;
//
//import com.csee.hanspace.domain.entity.Room;
//import com.csee.hanspace.domain.entity.RoomTags;
//import com.csee.hanspace.domain.entity.Tag;
//import lombok.*;
//
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class RoomReadDto {
//    private Long roomId;
//    private String roomName;
//    private Integer capacity;
//    private String description;
//    private Integer startTime;
//    private Integer endTime;
//    private boolean available;
//    private String image;
//    private Integer reserveCnt;
//    private List<Tag> tagsList;
//
//    public RoomReadDto(Room room, List<Tag> tags) {
//        this.roomId = room.getId();
//        this.roomName = room.getName();
//        this.capacity = room.getCapacity();
//        this.description = room.getDescription();
//        this.startTime = room.getStartTime();
//        this.endTime = room.getEndTime();
//        this.available = room.isAvailable();
//        this.image = room.getImage();
//        this.reserveCnt = room.getReserveCnt();
//        this.tagsList = tags;
//    }
//
//    public RoomReadResponse toRes() {
//        return new RoomReadResponse(roomId, roomName, capacity, description, startTime, endTime, available, image, reserveCnt, tagsList);
//    }
//}
