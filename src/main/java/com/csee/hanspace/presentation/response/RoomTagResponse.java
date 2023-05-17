package com.csee.hanspace.presentation.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTagResponse {
    private Long roomTagId;
    private String tagName;
    private Long roomId;
}
