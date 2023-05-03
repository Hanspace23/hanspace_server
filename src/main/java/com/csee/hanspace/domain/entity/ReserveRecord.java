package com.csee.hanspace.domain.entity;

// import com.csee.hanspace.application.dto.ReserveDetailDto;
import com.csee.hanspace.application.dto.OneReserveDto;
import com.csee.hanspace.application.dto.RegularReserveDto;
import com.csee.hanspace.application.dto.ReserveDetailDto;
import com.csee.hanspace.application.dto.RoomReserveDto;
import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE reserve_record SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@ToString
public class ReserveRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    private String purpose;

    private String reservation;

    private String contact;

    private Integer status;

    private boolean regular;

    private Long regularId;
    @Nullable
    private String answer1;
    @Nullable
    private String answer2;
    @Nullable
    private String weekdays;

    private String reserveTime;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Site site;

     public ReserveDetailDto toDetailDto() {
         return ReserveDetailDto.builder()
                 .reserveId(this.id)
                 .groupName(this.groupName)
                 .purpose(this.purpose)
                 .userName(this.reservation)
                 .contact(this.contact)
                 .status(this.status)
                 .answer1(this.answer1)
                 .answer2(this.answer2)
                 .roomName(this.getRoom().getName())
                 .reserveTime(this.reserveTime)
                 .regDate(this.getRegDate())
                 .reservationDate(this.date)
                 .build();
     }
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private SavedUserInfo savedUserInfo;

//    @Builder.Default
//    @OneToMany(mappedBy = "reserveRecord", cascade = CascadeType.PERSIST)
//    private List<TimeRecord> timeRecordList = new ArrayList<>();

    public static ReserveRecord onetimeReserve (SavedUserInfo savedUserInfo, Site site, Room room, OneReserveDto dto, String fullReserveTime) {
        if (site.getRestriction() == 1) {
            return ReserveRecord.builder()
                    .groupName(dto.getGroupName())
                    .purpose(dto.getGroupPurpose())
                    .reservation(dto.getName())
                    .contact(dto.getNumber())
                    .status(1)
                    .regular(false)
                    .regularId(0L)
                    .answer1(dto.getAnswer1())
                    .answer2(dto.getAnswer2())
                    .site(site)
                    .room(room)
                    .savedUserInfo(savedUserInfo)
                    .reserveTime(fullReserveTime)
                    .build();
        }
        return null;

    }


    public static ReserveRecord regularReserve (SavedUserInfo savedUserInfo, Site site, Room room, RegularReserveDto dto, String fullReserveTime, Long curId, String weekdays){

        return ReserveRecord.builder()
                .groupName(dto.getGroupName())
                .purpose(dto.getGroupPurpose())
                .reservation(dto.getName())
                .contact(dto.getNumber())
                .status(1)
                .regular(true)
                .regularId(curId)
                .answer1(dto.getAnswer1())
                .answer2(dto.getAnswer2())
                .weekdays(weekdays)
                .site(site)
                .room(room)
                .savedUserInfo(savedUserInfo)
                .reserveTime(fullReserveTime)
                .build();
    }

    @Override
    public LocalDateTime getModifiedDate() {
        return super.getModifiedDate();
    }

    @Override
    public LocalDateTime getRegDate() {
        return super.getRegDate();
    }
}
