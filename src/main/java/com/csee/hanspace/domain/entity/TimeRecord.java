package com.csee.hanspace.domain.entity;

import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE time_record SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")

public class TimeRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDate startDate;

    private int startTime;

    private int endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private  Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReserveRecord reserveRecord;



    public static TimeRecord from (LocalDate date, int startTime, int endTime, ReserveRecord record, Room room) {
        return TimeRecord.builder()
                .startDate(date)
                .startTime(startTime)
                .endTime(endTime)
                .room(room)
                .reserveRecord(record)
                .build();
    }


}
