package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.TimeRecord;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AllReservedDto {
    private Long id;
    private String place;
    private String useDate;
    private String applyDate;
    private String time;
    private String applicant;
    private String purpose;
    private String status;
    private String weekdays;

//    static public AllReservedDto toAllReserveList (ReserveRecord record, TimeRecord time) {
//        String fullTime = (time.getStartTime()/30)/2 + ':' + (time.getStartTime()/30)%2 + " ~ " + (time.getEndTime()/30)/2 + ":" + (time.getEndTime()/30)%2;
//        String fullUseDate = time.getStartDate().getYear() + "-" + time.getStartDate().getMonth() + "-" + time.getStartDate().getDayOfMonth();
//        String fullApplyDate = record.getRegDate().getYear() + "-" + time.getRegDate().getMonth() + "-" + time.getRegDate().getDayOfMonth();
//
//        return new AllReservedDto(record.getId(), record.getRoom().getName(), fullUseDate,
//                fullApplyDate, fullTime, record.getReservation(), record.getPurpose(), record.getStatus() == 1 ? "대기" : record.getStatus() == 2 ? "승인" : "거절", record.getWeekdays());
//    }

}
