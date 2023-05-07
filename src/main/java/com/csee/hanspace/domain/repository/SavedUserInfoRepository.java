package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.SavedUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SavedUserInfoRepository extends JpaRepository<SavedUserInfo, Long> {
    List<SavedUserInfo> findSavedUserInfoBySiteId(Long siteId);

    SavedUserInfo findSavedUserInfoBySiteIdAndUserId(Long siteId, Long userId);

    Long deleteSavedUserInfoBySiteIdAndUserId(Long siteId, Long userId);

    @Query("select s from SavedUserInfo s where s.user.id = :userId and s.site.id = :siteId")
    SavedUserInfo findSiteUserInfo(Long userId, Long siteId);

    @Query("select s from SavedUserInfo s where s.site.id = :sid and s.authority = 1 ")
    SavedUserInfo findCreator(Long sid);

    @Query("select count(s) from SavedUserInfo s where s.site.id = :siteId and s.user.id = :userId ")
    Integer checkInfo(Long userId, Long siteId);
}