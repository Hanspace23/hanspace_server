package com.csee.hanspace.domain.entity;

import com.csee.hanspace.domain.entity.common.BaseEntity;
//import com.sun.tools.javac.jvm.Gen;
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
@SQLDelete(sql = "UPDATE tag SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Site site;

    @Builder.Default
    @OneToMany(mappedBy = "tag", cascade = CascadeType.PERSIST)
    private List<RoomTags> roomTagsList = new ArrayList<>();
}
