package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.RoomTags;
import com.csee.hanspace.domain.entity.Site;
import com.csee.hanspace.domain.entity.Tag;
import com.csee.hanspace.presentation.response.CreateRoomResponse;
import com.csee.hanspace.presentation.response.UpdateRoomAvailResponse;
import com.csee.hanspace.presentation.response.UpdateRoomResponse;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {
    private Long roomId;
    private Site site;
    private String roomName;
    private Integer capacity;
    private String description;
    private Integer startTime;
    private Integer endTime;
    private boolean available;
    private String image;
    private Integer reserveCnt;
    private List<RoomTags> roomTags;

    public CreateRoomResponse createRoomResponse() {
        return new CreateRoomResponse(roomId, site.getId(), roomName, capacity, description, startTime, endTime, available, image, reserveCnt, roomTags);
    }

    public UpdateRoomResponse updateRoomResponse() {
        return new UpdateRoomResponse(roomId, site.getId(), roomName, capacity, description, startTime, endTime, available, image, reserveCnt, roomTags);
    }

    public UpdateRoomAvailResponse updateRoomAvailResponse() {
        return new UpdateRoomAvailResponse(roomId, roomName, available);
    }
}
