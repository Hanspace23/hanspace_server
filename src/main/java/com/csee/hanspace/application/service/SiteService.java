package com.csee.hanspace.application.service;

import com.csee.hanspace.application.dto.SiteDto;
import com.csee.hanspace.application.dto.SiteEditDto;
import com.csee.hanspace.application.dto.SiteInfoDto;
import com.csee.hanspace.domain.entity.*;
import com.csee.hanspace.domain.repository.*;
import com.csee.hanspace.presentation.response.SiteByLinkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;
    private final SavedUserInfoRepository savedUserInfoRepository;
    private final TagRepository tagRepository;

    private final RoomTagRepository roomTagRepository;

    @Transactional
    public List<SiteDto> getAllSites() {
        List<Site> sites = siteRepository.findAll();
        return sites.stream()
                .map(SiteDto::new)
                .collect(Collectors.toList());
    }

     @Transactional
     public List<SiteDto> getMySites(Long savedUserInfoId) {
         List<Site> sites = siteRepository.findAllBySavedUserInfoIdAndCreator(savedUserInfoId);
         for(Site site : sites) {
             System.out.println("name: " + site.getName());
         }
         return sites.stream()
                 .map(SiteDto::new)
                 .collect(Collectors.toList());
     }

    @Transactional
    public List<SiteDto> getManagingSites(Long savedUserInfoId) {
        List<Site> sites = siteRepository.findAllBySavedUserInfoIdAndAdmin(savedUserInfoId);
        for(Site site : sites) {
            System.out.println("name: " + site.getName());
        }
        return sites.stream()
                .map(SiteDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SiteDto> getSubscribedSites(Long savedUserInfoId) {
        List<Site> sites = siteRepository.findAllBySavedUserInfoIdAndUser(savedUserInfoId);
        for(Site site : sites) {
            System.out.println("name: " + site.getName());
        }
        return sites.stream()
                .map(SiteDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Site findById(Long siteId) {
        return siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("no such site"));
    }

    @Transactional
    public Long saveSiteInfo(SiteInfoDto dto) {
        Site site = siteRepository.save(Site.from(dto));
        List<String> tags = dto.getHashTags();
        for(String tag : tags) {
            Tag newTag = Tag.from(tag, site);
            tagRepository.save(newTag);
        }
        return site.getId();
    }

    @Transactional
    public Site editSiteInfo(SiteEditDto dto) {
        Site site = siteRepository.findById(dto.getSiteId()).orElseThrow(()->new IllegalArgumentException("no such site"));
        System.out.println("dto = " + dto);
        Site editSite = siteRepository.save(site.from(site, dto));
        List<String> tags = dto.getHashTags();
        roomTagRepository.hardDeleteAll();
        tagRepository.hardDeleteAll();
        for(String tag : tags) {
            if(tag != null) {
                Tag newTag = Tag.from(tag, editSite);
                tagRepository.save(newTag);
            }
        }
        return editSite;
    }

    @Transactional
    public void save(Site site) {
        siteRepository.save(site);
    }

    @Transactional(readOnly = true)
    public SiteByLinkResponse findByLink(String link) {
        Site site = siteRepository.findByLink(link);
        SavedUserInfo temp = savedUserInfoRepository.findCreator(site.getId());
        String domain = temp.getUser().getEmail().split("@")[1];
        SiteByLinkResponse ret = SiteByLinkResponse.from(site, domain);
        return ret;
    }
}
