package com.csee.hanspace.presentation.request;

import com.csee.hanspace.application.dto.RoomAvailableDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomAvailsRequest {
    private Long roomId;
    private boolean available;

    public RoomAvailableDto roomAvailableDto() {
        return new RoomAvailableDto(available);
    }
}
