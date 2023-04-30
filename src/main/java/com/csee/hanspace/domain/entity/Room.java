package com.csee.hanspace.domain.entity;


import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.tomcat.util.http.parser.Authorization;
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
@SQLDelete(sql = "UPDATE room SET deleted = true WHERE id = ?")
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private Integer capacity;

    private String description;

    private boolean available;

    private Integer startTime;
    private Integer endTime;

    private Integer reserveCnt;



    @Builder.Default
    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<ReserveRecord> reserveRecordList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST)
    private List<RoomTags> roomTagsList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<TimeRecord> timeRecordList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Site site;

}
