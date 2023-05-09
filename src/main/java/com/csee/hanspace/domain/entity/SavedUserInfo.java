package com.csee.hanspace.domain.entity;

import com.csee.hanspace.application.dto.CreateSavedDto;
import com.csee.hanspace.domain.entity.common.BaseEntity;
import com.csee.hanspace.presentation.request.SavedUserInfoEditRequest;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
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

    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Site site;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "savedUserInfo", cascade = CascadeType.PERSIST)
    private List<ReserveRecord> reserveRecordList = new ArrayList<>();


    public void update (SavedUserInfoEditRequest request){
        this.groupName = request.getGroupName();
        this.reservation = request.getReservation();
        this.contact = request.getContact();
        this.purpose = request.getPurpose();
    }

    public static SavedUserInfo from (User user, Site site){
        return SavedUserInfo.builder()
                .user(user)
                .site(site)
                .groupName(null)
                .reservation(null)
                .contact(null)
                .purpose(null)
                .reserveRecordList(new ArrayList<>())
                .authority(3)
                .status(1)
                .build();
    }

    public SavedUserInfo(User user, Site site) {
        this.user = user;
        this.site = site;
    }

    public CreateSavedDto toCreateDto() {
        return CreateSavedDto.builder()
                .savedId(this.id)
                .userId(this.user.getId())
                .siteId(this.site.getId())
                .build();
    }

}
