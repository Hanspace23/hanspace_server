package com.csee.hanspace.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomAvailsDto {
    private boolean available;

    public RoomAvailableDto roomAvailableDto() {
        return new RoomAvailableDto(available);
    }
}
