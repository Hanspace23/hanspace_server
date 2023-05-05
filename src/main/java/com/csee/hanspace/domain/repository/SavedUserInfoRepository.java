package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.SavedUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedUserInfoRepository extends JpaRepository<SavedUserInfo, Long> {
    List<SavedUserInfo> findSavedUserInfoBySiteId(Long siteId);

    SavedUserInfo findSavedUserInfoBySiteIdAndUserId(Long siteId, Long userId);

    Long deleteSavedUserInfoBySiteIdAndUserId(Long siteId, Long userId);
}