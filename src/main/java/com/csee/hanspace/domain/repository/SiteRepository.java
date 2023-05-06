package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {

     @Query("select distinct s from Site s " +
     "left join fetch s.savedUserInfoList as u " +
     "where u.id = :savedUserInfoId " +
     "and u.authority = :creator " +
     "or u.authority = :admin ")
 List<Site> findAllBySavedUserInfoIdAndCreatorAndAdmin(@Param("savedUserInfoId") Long savedUserInfoId, @Param("creator") int creator, @Param("admin") int admin);

    @Query("select distinct s from Site s " +
            "left join fetch s.savedUserInfoList as u " +
            "where u.id = :savedUserInfoId " +
            "and u.authority = 1 ")
    List<Site> findAllBySavedUserInfoIdAndCreator(@Param("savedUserInfoId") Long savedUserInfoId);

    @Query("select distinct s from Site s " +
            "left join fetch s.savedUserInfoList as u " +
            "where u.id = :savedUserInfoId " +
            "and u.authority = 2 ")
    List<Site> findAllBySavedUserInfoIdAndAdmin(@Param("savedUserInfoId") Long savedUserInfoId);

    @Query("select distinct s from Site s " +
            "left join fetch s.savedUserInfoList as u " +
            "where u.id = :savedUserInfoId " +
            "and u.authority = 3 ")
    List<Site> findAllBySavedUserInfoIdAndUser(@Param("savedUserInfoId") Long savedUserInfoId);


    @Query("select s from Site s where s.link=:link")
    Site findByLink(String link);

}
