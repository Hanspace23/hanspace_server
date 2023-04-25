package com.csee.hanspace.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyReserveResponse {
    private Long reserveId;

    private String groupName;

    private String purpose;

    private String reservationUser;

    private String contact;

    private Integer status;

    private String roomName;

    private String roomImage;

    private LocalDateTime startDate;
}
