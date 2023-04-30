package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReserveRepository extends JpaRepository<ReserveRecord, Long> {
    @Query("select r from ReserveRecord r " +
            "left join fetch r.savedUserInfo " +
            "where r.savedUserInfo.id = :savedUserInfoId ")
    List<ReserveRecord> findAllBySavedUserInfoId(@Param("savedUserInfoId") Long savedUserInfoId);

//    @Query("select r from ReserveRecord  r " + "join fetch r.timeRecordList t " + "where r.site.id = :siteId")
//    List<ReserveRecord> findAllReserveBySiteId(Long siteId);
    List<ReserveRecord> findAllReserveBySiteId(Long siteId);

    @Query("select MAX (regularId) from ReserveRecord where id = :siteId")
    Long findCurrentRegularId(Long siteId);

    List<ReserveRecord> findBySiteIdAndRegularId(Long siteId, Long regularId);

    ReserveRecord findBySiteIdAndId(Long siteId, Long reserveId);

}
