package com.csee.hanspace.site.model;

import com.csee.hanspace.common.BaseEntity;
import com.csee.hanspace.reserve.model.ReserveRecord;
import com.csee.hanspace.room.model.Room;
import com.csee.hanspace.user.model.Authorization;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE site SET deleted = true Where id = ?")
public class Site extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String logo;

    private String color;

    private String link;

    private String company;

    private String maxDate;

    private String description;

    private int restriction;

    @OneToMany(mappedBy="site")
    private List<Authorization> authorization = new ArrayList<>();

    @OneToMany(mappedBy="site")
    private List<ReserveRecord> reserveRecord = new ArrayList<>();

    @OneToMany(mappedBy="site")
    private List<SiteEtc> siteEtc = new ArrayList<>();

    @OneToMany(mappedBy="site")
    private List<Room> room = new ArrayList<>();

}
