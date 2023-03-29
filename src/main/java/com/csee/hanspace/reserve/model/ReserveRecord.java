package com.csee.hanspace.reserve.model;

import com.csee.hanspace.common.BaseEntity;
import com.csee.hanspace.room.model.Room;
import com.csee.hanspace.site.model.Site;
import com.csee.hanspace.user.model.User;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE reserve_record SET deleted = true Where id = ?")
public class ReserveRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String contact;

    private String email;

    private String purpose;

    private boolean regularReserve;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String dayOfWeek;

    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy="reserveRecord")
    private List<EtcAnswer> etcAnswer = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Site site;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
}
