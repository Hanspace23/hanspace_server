package com.csee.hanspace.domain.entity;

import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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

    public static ReserveRecord onetimeReserve (SavedUserInfo savedUserInfo, Site site, Room room, OneReserveDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        LocalDate date = LocalDate.parse(dto.getReserveDate(), formatter);
        String fullReserveTime = String.join(" , " , dto.getReserveTime());


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
                    .date(date)
                    .site(site)
                    .room(room)
                    .savedUserInfo(savedUserInfo)
                    .reserveTime(fullReserveTime)
                    .build();


    }


    public static ReserveRecord regularReserve (SavedUserInfo savedUserInfo, Site site, Room room, OneReserveDto dto, Long curId, LocalDate date, String weekdays){
        String fullReserveTime = String.join(" , " , dto.getReserveTime());


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
                .date(date)
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

    public List<TimeDto> retListOfTimeRecord(){
        List<TimeDto> ret = new ArrayList<>();
        String[] temp = this.reserveTime.split(" , ");
        for(int j=0; j<temp.length; j++){
            String[] partTime = temp[j].split(" ~ ");
            String start = partTime[0].trim();
            String end = partTime[1].trim();
            String[] startT = start.split(":");
            String[] endT = end.split(":");
            int startTime = 0;
            if(!startT[0].equals("00")) startTime = startTime + Integer.parseInt(startT[0].trim()) * 60;
            if(!startT[1].equals("00")) startTime = startTime + Integer.parseInt(startT[1].trim());
            int endTime = 0;
            if(!endT[0].equals("00")) endTime = endTime + Integer.parseInt(endT[0].trim()) * 60;
            if(!endT[1].equals("00")) endTime = endTime + Integer.parseInt(endT[1].trim());
            ret.add(new TimeDto(startTime, endTime));
        }

//        System.out.println(ret);
        return ret;
    }

    public static List<LocalDate> getDatesForDayOfWeek(LocalDate startDate, LocalDate endDate, List<DayOfWeek> targetDaysOfWeek) {
        List<LocalDate> dates = new ArrayList<>();

        for (DayOfWeek targetDayOfWeek : targetDaysOfWeek) {
            LocalDate nextDate = startDate.with(TemporalAdjusters.nextOrSame(targetDayOfWeek));

            while (nextDate.isBefore(endDate) || nextDate.isEqual(endDate)) {
                dates.add(nextDate);
                nextDate = nextDate.with(TemporalAdjusters.next(targetDayOfWeek));
            }
        }

        return dates;
    }

    public static int calculateDuration(String startTime, String endTime) {
        String[] startParts = startTime.split(":");
        int startHour = Integer.parseInt(startParts[0]);
        int startMinute = Integer.parseInt(startParts[1]);

        String[] endParts = endTime.split(":");
        int endHour = Integer.parseInt(endParts[0]);
        int endMinute = Integer.parseInt(endParts[1]);

        int start = startHour * 60 + startMinute;
        int end = endHour * 60 + endMinute;

        return end-start;
    }
}
