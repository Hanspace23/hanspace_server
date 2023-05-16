package com.csee.hanspace.presentation.response;

import com.csee.hanspace.application.dto.AllReservedDto;
import com.csee.hanspace.domain.entity.ReserveRecord;
//import com.csee.hanspace.domain.entity.TimeRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllReservedResponse {
    private Long id;
    private String place;
    private String useDate;
    private String applyDate;
    private String time;
    private String applicant;
    private String purpose;
    private String status;
    private String weekdays;
    private boolean regular;
    private String detail;
    private String userName;
    static public AllReservedResponse toResponse (AllReservedDto dto) {
        return new AllReservedResponse(dto.getId(), dto.getPlace(), dto.getUseDate() , dto.getApplyDate()
                , dto.getTime(), dto.getApplicant(), dto.getPurpose(), dto.getStatus() , dto.getWeekdays(), dto.isRegular(), dto.getDetail() , dto.getUserName());

    }
}
