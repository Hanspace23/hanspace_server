package com.csee.hanspace.reserve.application.dto;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.reserve.application.response.MyReserveResponse;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyReserveDto {
    private Long reserveId;
    private String groupName;
    private String purpose;
    private String reservationUser;
    private String contact;
    private Integer status;
    private String roomName;
    private String roomImage;
    private LocalDateTime startDate;

//    private Integer time;

    public MyReserveDto(ReserveRecord reserveRecord) {
        this.reserveId = reserveRecord.getId();
        this.groupName = reserveRecord.getGroupName();
        this.purpose = reserveRecord.getPurpose();
        this.reservationUser = reserveRecord.getReservation();
        this.contact = reserveRecord.getContact();
        this.status = reserveRecord.getStatus();
        this.roomName = reserveRecord.getRoom().getName();
        this.roomImage = reserveRecord.getRoom().getImage();
        this.startDate = reserveRecord.getRegDate();
    }

    public MyReserveResponse myReserveResponse() {
        return new MyReserveResponse(reserveId, groupName, purpose, reservationUser, contact, status, roomName, roomImage, startDate);
    }
}
