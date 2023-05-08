package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.presentation.response.RegularReservationHistoryResponse;
import com.csee.hanspace.presentation.response.RegularResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegularResponseDto {
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

    private String detail;

    public static RegularResponseDto of (ReserveRecord record, String status, LocalDate now, LocalDate startDate, LocalDate endDate, String question1, String question2) {
        String detail = "";
        String answer1 = record.getAnswer1();
        String answer2 = record.getAnswer2();

        if(answer1 != null && answer2 != null) {
            detail = question1 + " : " +  answer1 + "\n" +
                    question2 + " : " + answer2;
        }
        else if(answer1 != null && answer2 == null){
            detail = question1 + " : " + answer1;
        }
        else if(answer1 == null && answer2 != null){
            detail = question2 + " : " + answer2;
        }
        return RegularResponseDto.builder()
                .groupName(record.getGroupName())
                .purpose(record.getPurpose())
                .userName(record.getReservation())
                .contact(record.getContact())
                .status(status)
                .regularId(record.getRegularId())
                .answer1(record.getAnswer1())
                .answer2(record.getAnswer2())
                .roomName(record.getRoom().getName())
                .roomImg(record.getRoom().getImage())
                .startDate(startDate)
                .endDate(endDate)
                .regDate(record.getRegDate())
                .reserveTime(record.getReserveTime())
                .weekdays(record.getWeekdays())
                .detail(detail)
                .build();


    }

    public RegularResponse reserveResponse() {

        return new RegularResponse(groupName, purpose, userName, contact, status, regularId, answer1, answer2, roomName, roomImg, startDate, endDate, regDate, reserveTime, deadline, weekdays, detail);
    }

}
