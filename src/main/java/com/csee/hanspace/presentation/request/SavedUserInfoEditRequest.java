package com.csee.hanspace.presentation.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SavedUserInfoEditRequest {
    private String groupName;
    private String purpose;
    private String reservation;
    private String contact;
}
