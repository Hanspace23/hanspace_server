package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.SavedUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SavedUserInfoRepository extends JpaRepository<SavedUserInfo, Long> {
    List<SavedUserInfo> findSavedUserInfoBySiteId(Long siteId);

    @Query("select s from SavedUserInfo s left join fetch s.user u where u.id = :userId and s.authority=3")
    List<SavedUserInfo> findAllByUserIdAndUser(Long userId);

    @Query("select s from SavedUserInfo s left join fetch s.user u where u.id = :userId and s.authority=2")
    List<SavedUserInfo> findAllByUserIdAndAdmin(Long userId);

    @Query("select s from SavedUserInfo s left join fetch s.user u where u.id = :userId and s.authority=1")
    List<SavedUserInfo> findAllByUserIdAndCreator(Long userId);

    SavedUserInfo findSavedUserInfoBySiteIdAndUserId(Long siteId, Long userId);

    SavedUserInfo findSavedUserInfoBySiteIdAndId(Long siteId, Long userId);

    Long deleteSavedUserInfoBySiteIdAndUserId(Long siteId, Long userId);

    @Query("select s from SavedUserInfo s where s.user.id = :userId and s.site.id = :siteId")
    SavedUserInfo findSiteUserInfo(Long userId, Long siteId);

    @Query("select s from SavedUserInfo s where s.site.id = :sid and s.authority = 1 ")
    SavedUserInfo findCreator(Long sid);

    @Query("select count(s) from SavedUserInfo s where s.site.id = :siteId and s.user.id = :userId ")
    Integer checkInfo(Long userId, Long siteId);

    @Query("select s.user.id from SavedUserInfo s where s.id = :userId and s.site.id = :siteId")
    Long findUserId(Long userId, Long siteId);
}