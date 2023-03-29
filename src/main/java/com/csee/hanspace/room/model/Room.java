package com.csee.hanspace.room.model;

import com.csee.hanspace.common.BaseEntity;
import com.csee.hanspace.reserve.model.ReserveRecord;
import com.csee.hanspace.site.model.Site;
import com.csee.hanspace.site.model.SiteEtc;
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
@SQLDelete(sql = "UPDATE room SET deleted = true Where id = ?")
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String name;

    private int capacity;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

    private int reserveCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Site site;

    @OneToMany(mappedBy="room")
    private List<ReserveRecord> reserveRecord = new ArrayList<>();

    @OneToMany(mappedBy="room")
    private List<Tag> tag = new ArrayList<>();

    @OneToMany(mappedBy="room")
    private List<NoRegTime> NoRegTime = new ArrayList<>();
}
