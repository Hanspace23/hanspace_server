package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.Site;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomAndTagDto {
    private Long roomId;
    private Site site;
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
}
