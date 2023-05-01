package com.csee.hanspace.application.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeRecordDto {
    private Long id;
    private LocalDateTime startDate;
    private int startTime;
    private int endTime;
}
