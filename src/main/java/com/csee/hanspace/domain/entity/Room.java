package com.csee.hanspace.domain.entity;


import com.csee.hanspace.domain.entity.Authorization;
import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.vm.ci.meta.Local;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private Integer capacity;

    private String description;

    private boolean available;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    private Integer reserveCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Authorization authorization;

    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST)
    private List<ReserveRecord> reserveRecordList = new ArrayList<>();
}
