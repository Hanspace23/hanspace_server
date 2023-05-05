package com.csee.hanspace.presentation.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangeUserStatusRequest {
    private Long siteId;
    private Integer statusId;
    private Long userId;
}
