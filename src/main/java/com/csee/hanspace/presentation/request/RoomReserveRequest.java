package com.csee.hanspace.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomReserveRequest {
    private String groupName;
    private String groupPurpose;

    private String name;

    private String number;

    private String answer1;

    private String answer2;

    private String reserveDate;
    private List<String> reserveTime;

    private String roomName;

    private int regular;
    private boolean reserveOne;
}
