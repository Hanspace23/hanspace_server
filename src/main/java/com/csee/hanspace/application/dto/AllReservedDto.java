package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.ReserveRecord;
//import com.csee.hanspace.domain.entity.TimeRecord;
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
    private String weekdays;
    private String time;
    private String applicant;
    private String purpose;
    private String status;
    private boolean regular;


    static public AllReservedDto of (ReserveRecord record) {
        String fullUseDate = record.getDate().getYear() + "-" + record.getDate().getMonthValue() + "-" + record.getDate().getDayOfMonth();
        String fullApplyDate = record.getDate().getYear() + "-" + record.getDate().getMonthValue() + "-" + record.getDate().getDayOfMonth();

        return new AllReservedDto(record.getId(), record.getRoom().getName(), fullUseDate, fullApplyDate,
                record.getReserveTime(), record.getReservation(), record.getPurpose(), record.getStatus() == 1 ? "대기" : record.getStatus() == 2 ? "승인" : "거절", record.getWeekdays(), record.isRegular());
    }

}
