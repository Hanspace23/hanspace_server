package com.csee.hanspace.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllReservedResponse {
    private String place;
    private String useDate;
    private String applyDate;
    private String time;
    private String applicant;
    private String purpose;
    private String status;
}
