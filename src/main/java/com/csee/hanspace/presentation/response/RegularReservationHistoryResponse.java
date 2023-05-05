package com.csee.hanspace.presentation.response;

import com.csee.hanspace.application.dto.RegularReservationHistoryDto;
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
public class RegularReservationHistoryResponse {
    private String groupName;

    private String purpose;

    private String userName;

    private String contact;

    private Integer status;

    private Long regularId;

    private String answer1;

    private String answer2;

    private String roomName;

    private String roomImg;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime regDate;
}
