package com.csee.hanspace.presentation.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomReserveRequest {

    private String email;

    private Long siteId;
    private String groupName;
    private String groupPurpose;

    private String name;

    private String number;

    private String answer1;

    private String answer2;

    private String reserveStartDate;
    private List<String> reserveTime;

    private String roomName;
}
