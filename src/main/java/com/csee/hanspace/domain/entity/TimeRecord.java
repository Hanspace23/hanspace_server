package com.csee.hanspace.domain.entity;

import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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
    private LocalDateTime startDate;

    private String time;

    @NotNull
    private Long regularId;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReserveRecord reserveRecord;

}
