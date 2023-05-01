package com.csee.hanspace.application.service;

import com.csee.hanspace.application.dto.ReserveDto;
import com.csee.hanspace.application.dto.SiteDto;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.Site;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;

    @Transactional
    public List<SiteDto> getAllSites() {
        List<Site> sites = siteRepository.findAll();
        return sites.stream()
                .map(SiteDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SiteDto> getMySites(Long savedUserInfoId, int creator, int admin) {
        List<Site> sites = siteRepository.findAllBySavedUserInfoIdAndCreatorAndAdmin(savedUserInfoId, creator, admin);
        return sites.stream()
                .map(SiteDto::new)
                .collect(Collectors.toList());
    }

}
