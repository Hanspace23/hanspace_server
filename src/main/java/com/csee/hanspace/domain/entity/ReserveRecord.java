package com.csee.hanspace.domain.entity;

import com.csee.hanspace.application.dto.ReserveDetailDto;
import com.csee.hanspace.domain.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE reserve_record SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
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

    private String answer1;

    private String answer2;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private SavedUserInfo savedUserInfo;

    @OneToMany(mappedBy = "reserveRecord", cascade = CascadeType.PERSIST)
    private List<TimeRecord> timeRecordList = new ArrayList<>();



    public ReserveDetailDto toDetailDto() {
        return ReserveDetailDto.builder()
                .reserveId(this.id)
                .groupName(this.groupName)
                .purpose(this.purpose)
                .userName(this.reservation)
                .contact(this.contact)
                .status(this.status)
                .regular(this.regular)
                .regularId(this.regularId)
                .answer1(this.answer1)
                .answer2(this.answer2)
                .roomName(this.getRoom().getName())
                .roomImg(this.getRoom().getImage())
                .timeRecord(this.timeRecordList)
                .regDate(this.getRegDate())
                .build();
    }


}
