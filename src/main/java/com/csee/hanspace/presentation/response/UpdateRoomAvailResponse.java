package com.csee.hanspace.presentation.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoomAvailResponse {
    private Long roomId;
    private String roomName;
    private boolean available;
}
