package com.csee.hanspace.presentation.response;

import com.csee.hanspace.domain.entity.RoomTags;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomsReadResponse {
    private Long roomId;
    private String roomName;
    private Integer capacity;
    private String description;
    private Integer startTime;
    private Integer endTime;
    private boolean available;
    private String image;
    private Integer reserveCnt;
//    private List<RoomTags> tagsList;
}
