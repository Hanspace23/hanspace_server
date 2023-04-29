package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.entity.Site;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;

    @Transactional
    public Site findById(Long siteId) {
        return siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("no such site"));
    }

    @Transactional
    public void save(Site site) {
        siteRepository.save(site);
    }

    @Transactional(readOnly = true)
    public Site findByLink(String link) {
        Site ret = siteRepository.findByLink(link);
        return ret;
    }
}
