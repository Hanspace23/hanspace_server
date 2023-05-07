package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.RoomTags;
import com.csee.hanspace.domain.entity.Tag;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomCUDto {
    private String roomName;
    private Integer capacity;
    private String description;
    private Integer startTime;
    private Integer endTime;
    private boolean available;
    private String image;
    private List<RoomTags> roomTags;

}
