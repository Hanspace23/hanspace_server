package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.TimeRecord;
import lombok.*;

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

    private boolean regular;

    private Long regularId;

    private String answer1;

    private String answer2;

    private String roomName;

    private String roomImg;

    private List<TimeRecord> timeRecord;

    private LocalDateTime regDate;
}
