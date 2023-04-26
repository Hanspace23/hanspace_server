package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.SavedUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedUserInfoRepository extends JpaRepository<SavedUserInfo, Long> {
}
