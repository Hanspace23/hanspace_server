package com.csee.hanspace.presentation.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangeMUserStatusRequest {

    private Long siteId;
    private Integer statusId;
    private List<Long> userList;
}
