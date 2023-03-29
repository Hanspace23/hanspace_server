package com.csee.hanspace.user.model;

import com.csee.hanspace.common.BaseEntity;
import com.csee.hanspace.reserve.model.EtcAnswer;
import com.csee.hanspace.reserve.model.ReserveRecord;
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
@SQLDelete(sql = "UPDATE user SET deleted = true Where id = ?")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy="user")
    private List<Authorization> authorization = new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<EtcAnswer> etcAnswer = new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<ReserveRecord> reserveRecord = new ArrayList<>();
}
