package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.ReserveRecord;
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

    private String reserveTime;

    private String deadline;

    private String weekdays;


    public RegularReservationHistoryDto (ReserveRecord tmp, String status, LocalDate now, LocalDate startDate, LocalDate endDate) {

        this.groupName = tmp.getGroupName();
        this.purpose =  tmp.getPurpose();
        this.userName = tmp.getReservation();
        this.contact = tmp.getContact();
        this.status = status;
        this.regularId = tmp.getRegularId();
        this.answer1 = tmp.getAnswer1();
        this.answer2 = tmp.getAnswer2();
        this.roomName = tmp.getRoom().getName();
        this.roomImg = tmp.getRoom().getImage();
        this.startDate = startDate;
        this.endDate = endDate;
        this.regDate = tmp.getRegDate();
        this.reserveTime = tmp.getReserveTime();
        this.weekdays = tmp.getWeekdays();

        String deadline = "마감됨";
        if(endDate.isAfter(now)) {
            deadline = "마감되지 않음";
        }

        this.deadline = deadline;

    }

    public RegularReservationHistoryResponse reserveResponse() {

        return new RegularReservationHistoryResponse(regularId, groupName, purpose, userName, contact, status, answer1, answer2, roomName, roomImg, startDate, endDate, regDate, reserveTime, deadline, weekdays);
    }

}
