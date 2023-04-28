package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.entity.Site;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;


    public Site findById(Long siteId) {
        return siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("no such site"));
    }
}
