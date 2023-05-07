package com.csee.hanspace.presentation.request;

import com.csee.hanspace.application.dto.RoomAvailableDto;
import com.csee.hanspace.application.dto.RoomCUDto;
import com.csee.hanspace.domain.entity.Room;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoomAvailRequest {
    private Long roomId;
    private boolean available;

    public RoomAvailableDto roomAvailableDto() {
        return new RoomAvailableDto(available);
    }
}
