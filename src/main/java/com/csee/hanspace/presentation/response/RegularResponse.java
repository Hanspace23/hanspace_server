package com.csee.hanspace.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegularResponse {
    private String groupName;

    private String purpose;

    private String userName;

    private String contact;

    private String status;

    private Long id;

    private String answer1;

    private String answer2;

    private String roomName;

    private String roomImg;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime regDate;

    private String reserveTime;

    private String deadline;

    private String weekdays;
    private String detail;
}

