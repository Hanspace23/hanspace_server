package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.presentation.response.ReserveResponse;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveDto {
    private Long id;

    private String groupName;

    private String purpose;

    private String userName;

    private String contact;

    private String status;

    private boolean regular;

    private Long regularId;

    private String answer1;

    private String answer2;

    private String roomName;

    private String roomImg;

    private LocalDate reservationDate;

    private LocalDateTime regDate;

    private String reserveTime;

    private String deadline;

//    public ReserveDto(ReserveRecord reserve){
//        this.id = reserve.getId();
//        this.groupName = reserve.getGroupName();
//        this.purpose = reserve.getPurpose();
//        this.userName = reserve.getReservation();
//        this.contact = reserve.getContact();
//        this.status = reserve.getStatus();
//        this.regular = reserve.isRegular();
//        this.regularId = reserve.getRegularId();
//        this.answer1 = reserve.getAnswer1();
//        this.answer2 = reserve.getAnswer2();
//        this.roomName = reserve.getRoom().getName();
//        this.roomImg = reserve.getRoom().getImage();
//        this.reservationDate = reserve.getDate();
//        this.regDate = reserve.getRegDate();
//        this.reserveTime = reserve.getReserveTime();
//    }

    public ReserveResponse reserveResponse() {

        return new ReserveResponse(id, groupName, purpose, userName, contact, status, regular, regularId, answer1, answer2, roomName, roomImg, reservationDate, regDate, reserveTime, deadline);
    }
}
