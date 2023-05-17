package com.csee.hanspace.presentation.request;

import com.csee.hanspace.application.dto.CreateTagDto;
import com.csee.hanspace.application.dto.RoomAndTagCUDto;
import com.csee.hanspace.application.dto.RoomCUDto;
import com.csee.hanspace.domain.entity.RoomTags;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoomAndTagRequest {
    private Long siteId;
    private String roomName;
    private Integer capacity;
    private String description;
    private Integer startTime;
    private Integer endTime;
    private boolean available;
    private String image;
    private Long tag1;
    private Long tag2;
    private Long tag3;

    public RoomAndTagCUDto roomAndTagCUDto() {
        return new RoomAndTagCUDto(roomName, capacity, description, startTime, endTime, available, image);
    }

    public CreateTagDto createTagDto() {
        return new CreateTagDto(tag1, tag2, tag3);
    }


}
