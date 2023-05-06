package com.csee.hanspace.application.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveDetailDto {
    private Long reserveId;

    private String groupName;

    private String purpose;

    private String userName;

    private String contact;

    private Integer status;

    private String answer1;

    private String answer2;

    private String roomName;

    private String reserveTime;

    private LocalDateTime regDate;

    private LocalDate reservationDate;
}
