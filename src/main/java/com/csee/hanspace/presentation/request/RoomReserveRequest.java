package com.csee.hanspace.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

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


    @Nullable
    private String answer1;


    @Nullable
    private String answer2;



    private String reserveStartDate;


    private List<String> reserveTime;


    private String roomName;


}
