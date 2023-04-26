package com.csee.hanspace.domain.entity;

import com.csee.hanspace.domain.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE saved_user_info SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
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
    private Site site;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "savedUserInfo", cascade = CascadeType.PERSIST)
    private List<ReserveRecord> reserveRecordList = new ArrayList<>();

}
