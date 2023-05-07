package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.Room;
import com.csee.hanspace.presentation.response.RoomsReadResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomsReadDto {
    private Long roomId;
    private String roomName;
    private Integer capacity;
    private String description;
    private Integer startTime;
    private Integer endTime;
    private boolean available;
    private String image;
    private Integer reserveCnt;

    public RoomsReadDto(Room room) {
        this.roomId = room.getId();
        this.roomName = room.getName();
        this.capacity = room.getCapacity();
        this.description = room.getDescription();
        this.startTime = room.getStartTime();
        this.endTime = room.getEndTime();
        this.available = room.isAvailable();
        this.image = room.getImage();
        this.reserveCnt = room.getReserveCnt();
    }

    public RoomsReadResponse toRes() {
        return new RoomsReadResponse(roomId, roomName, capacity, description, startTime, endTime, available, image, reserveCnt);
    }
}
