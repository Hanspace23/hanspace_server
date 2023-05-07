package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.SavedUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SavedUserInfoRepository extends JpaRepository<SavedUserInfo, Long> {
    List<SavedUserInfo> findSavedUserInfoBySiteId(Long siteId);

    SavedUserInfo findSavedUserInfoBySiteIdAndId(Long siteId, Long userId);

    Long deleteSavedUserInfoBySiteIdAndUserId(Long siteId, Long userId);

    @Query("select s.user.id from SavedUserInfo s where s.id = :savedUserInfoId and s.site.id = :siteId")
    Long findUserId(Long savedUserInfoId, Long siteId);
}