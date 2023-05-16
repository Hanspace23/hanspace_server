package com.csee.hanspace.presentation.response;

import com.csee.hanspace.application.dto.TimeDto;
import com.csee.hanspace.domain.entity.ReserveRecord;
//import com.csee.hanspace.domain.entity.TimeRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveCalResponse {
    private Long id;
    private Long roomId;
    private String roomName;
    private String groupName;

    @Nullable
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate start;
    private String purpose;
    private String reservationName;
    private String contact;
    private int status;
    private String content;
    private Boolean regular;
    private List<TimeDto> timeRecord;

    public static ReserveCalResponse from (ReserveRecord record) {
        return ReserveCalResponse.builder()
                .id(record.getId())
                .roomId(record.getRoom().getId())
                .roomName(record.getRoom().getName())
                .groupName(record.getGroupName())
                .start(record.getDate())
                .purpose(record.getPurpose())
                .reservationName(record.getReservation())
                .contact(record.getContact())
                .status(record.getStatus())
                .content(record.getAnswer1())
                .regular(record.isRegular())
                .timeRecord(record.retListOfTimeRecord())
                .build();
    }
}
