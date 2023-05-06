package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.response.RegularReservationHistoryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegularReservationHistoryDto {

    private String groupName;

    private String purpose;

    private String userName;

    private String contact;

    private String status;

    private Long regularId;

    private String answer1;

    private String answer2;

    private String roomName;

    private String roomImg;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime regDate;

    public RegularReservationHistoryResponse reserveResponse() {

        return new RegularReservationHistoryResponse(groupName, purpose, userName, contact, status, regularId, answer1, answer2, roomName, roomImg, startDate, endDate, regDate);
    }

}
