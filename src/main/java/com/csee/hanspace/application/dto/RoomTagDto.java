package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.RoomTags;
import com.csee.hanspace.presentation.response.RoomTagResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTagDto {
    private Long roomTagId;
    private String tagName;
    private Long roomId;

    public RoomTagDto(RoomTags roomTags) {
        this.roomTagId = roomTags.getId();
        this.tagName = roomTags.getTag().getName();
        this.roomId = roomTags.getRoom().getId();
    }

    public RoomTagResponse roomTagResponse() {
        return new RoomTagResponse(roomTagId, tagName, roomId);
    }
}
