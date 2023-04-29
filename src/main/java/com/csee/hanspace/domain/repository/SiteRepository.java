package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {

}
