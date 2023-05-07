package com.csee.hanspace.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveResponse {
    private Long id;

    private String groupName;

    private String purpose;

    private String userName;

    private String contact;

    private String status;

    private boolean regular;

    private Long regularId;

    private String answer1;

    private String answer2;

    private String roomName;

    private String roomImg;

    private LocalDate reservationDate;

    private LocalDateTime regDate;

    private String reserveTime;

    private String deadline;
}
