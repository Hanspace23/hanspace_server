package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.ReserveRecord;
//import com.csee.hanspace.domain.entity.TimeRecord;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AllReservedDto {
    private Long id;
    private String place;
    private String useDate;
    private String applyDate;

    private String startDate;
    private String endDate;
    private String weekdays;
    private String time;
    private String applicant;
    private String purpose;
    private String status;
    private boolean regular;
    private String detail;


    static public AllReservedDto of (ReserveRecord record, String question1,  String question2) {
        String fullUseDate = record.getDate().getYear() + "-" + record.getDate().getMonthValue() + "-" + record.getDate().getDayOfMonth();
        String fullApplyDate = record.getDate().getYear() + "-" + record.getDate().getMonthValue() + "-" + record.getDate().getDayOfMonth();
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
        System.out.println("detail = " + detail);
        return AllReservedDto.builder()
                .id(record.getId())
                .place(record.getRoom().getName())
                .useDate(fullUseDate)
                .applyDate(fullApplyDate)
                .weekdays(record.getWeekdays())
                .time(record.getReserveTime())
                .applicant(record.getReservation())
                .purpose(record.getPurpose())
                .status(record.getStatus() == 1 ? "대기" : record.getStatus() == 2 ? "승인" : "거절")
                .regular(record.isRegular())
                .detail(detail)
                .build();

    }

//    static public AllReservedDto regularOf (List<ReserveRecord> reservedList) {
//
//        Map<Long, List<ReserveRecord>> dataMap = new HashMap<>();
//        for (ReserveRecord data : reservedList) {
//            Long regularId = data.getRegularId();
//
//            if (!dataMap.containsKey(regularId)) {
//                dataMap.put(regularId, new ArrayList<>());
//            }
//
//            List<ReserveRecord> dataListForId = dataMap.get(regularId);
//            dataListForId.add(data);
//        }
//        for (List<ReserveRecord> dataListForId : dataMap.values()) {
//        }
//        return
//    }
}
