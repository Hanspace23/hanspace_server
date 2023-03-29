package com.csee.hanspace.room.model;

import com.csee.hanspace.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE no_reg_time SET deleted = true Where id = ?")
public class NoRegTime extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime consecutiveTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
}
