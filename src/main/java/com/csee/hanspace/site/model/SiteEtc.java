package com.csee.hanspace.site.model;

import com.csee.hanspace.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE site_etc SET deleted = true Where id = ?")
public class SiteEtc extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    private int type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Site site;
}
