package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SiteRepository extends JpaRepository<Site, Long> {

    @Query("select s from Site s where s.link=:link")
    Site findByLink(String link);
}
