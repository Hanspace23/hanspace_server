package com.csee.hanspace.domain.entity;

import com.csee.hanspace.domain.entity.common.BaseEntity;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE saved_user_info SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Component("savedUserInfo")
public class SavedUserInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    private String purpose;

    private String reservation;

    private String contact;

    private int authority;

    private boolean approve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Site site;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "savedUserInfo", cascade = CascadeType.PERSIST)
    private List<ReserveRecord> reserveRecordList = new ArrayList<>();

}
