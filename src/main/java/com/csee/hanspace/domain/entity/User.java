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
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String email;

    @OneToMany(mappedBy="user", cascade = CascadeType.PERSIST)
    private List<SavedUserInfo> savedUserInfoList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<ReserveRecord> reserveRecordList = new ArrayList<>();

}
