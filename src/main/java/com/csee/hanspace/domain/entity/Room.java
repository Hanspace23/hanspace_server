package com.csee.hanspace.domain.entity;


import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.tomcat.util.http.parser.Authorization;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
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

//    @Builder.Default
//    @OneToMany(mappedBy = "room", cascade = CascadeType.PERSIST)
//    @JsonIgnore
//    private List<TimeRecord> timeRecordList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Site site;

    public Room(RoomCUDto roomCUDto, Site site) {
        this.name = roomCUDto.getRoomName();
        this.image = roomCUDto.getImage();
        this.capacity = roomCUDto.getCapacity();
        this.startTime = roomCUDto.getStartTime();
        this.endTime = roomCUDto.getEndTime();
        this.available = roomCUDto.isAvailable();
        this.description = roomCUDto.getDescription();
        this.site = site;
    }

    public Room(RoomAndTagCUDto roomAndTagCUDto, Site site) {
        this.name = roomAndTagCUDto.getRoomName();
        this.image = roomAndTagCUDto.getImage();
        this.capacity = roomAndTagCUDto.getCapacity();
        this.startTime = roomAndTagCUDto.getStartTime();
        this.endTime = roomAndTagCUDto.getEndTime();
        this.available = roomAndTagCUDto.isAvailable();
        this.description = roomAndTagCUDto.getDescription();
        this.site = site;
    }

    public RoomDto toCreateDto() {
        return RoomDto.builder()
                .roomId(this.id)
                .roomName(this.name)
                .image(this.image)
                .capacity(this.capacity)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .available(this.available)
                .description(this.description)
                .site(this.site)
                .build();
    }

    public RoomDto toUpdateDto() {
        return RoomDto.builder()
                .roomId(this.id)
                .roomName(this.name)
                .image(this.image)
                .capacity(this.capacity)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .available(this.available)
                .description(this.description)
                .site(this.site)
                .build();
    }



    public void update(RoomCUDto roomCUDto) {
        this.name = roomCUDto.getRoomName();
        this.image = roomCUDto.getImage();
        this.capacity = roomCUDto.getCapacity();
        this.startTime = roomCUDto.getStartTime();
        this.endTime = roomCUDto.getEndTime();
        this.available = roomCUDto.isAvailable();
        this.description = roomCUDto.getDescription();
    }

    public void updateAvailable(RoomAvailableDto roomAvailableDto) {
        this.available = roomAvailableDto.isAvailable();
    }

    public List<TimeRecordDto> retListOfTimeRecord(){
        List<TimeRecordDto> ret = new ArrayList<>();
        for(int i=0; i<this.reserveRecordList.size();i++){
            LocalDate d = this.reserveRecordList.get(i).getDate();
            String[] temp = this.reserveRecordList.get(i).getReserveTime().split(" , ");
            for(int j=0; j<temp.length;j++){
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
                ret.add(new TimeRecordDto(d, startTime, endTime));
            }
        }
//        System.out.println(ret);
        return ret;
    }


}
