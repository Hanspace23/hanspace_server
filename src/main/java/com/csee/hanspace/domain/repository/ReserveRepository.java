package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReserveRepository extends JpaRepository<ReserveRecord, Long> {
    @Query("select r from ReserveRecord r " +
            "left join fetch r.savedUserInfo " +
            "where r.savedUserInfo.user.id = :userId ")
    List<ReserveRecord> findByUserId(@Param("userId") Long userId);
}
