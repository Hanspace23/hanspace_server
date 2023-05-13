package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.SavedUserInfoService;
import com.csee.hanspace.application.service.SiteService;
import com.csee.hanspace.application.service.TagService;
import com.csee.hanspace.domain.entity.SavedUserInfo;
import com.csee.hanspace.domain.entity.User;
import com.csee.hanspace.domain.repository.SiteRepository;
import com.csee.hanspace.presentation.request.CreateSiteRequest;
import com.csee.hanspace.presentation.request.SiteEditRequest;
import com.csee.hanspace.presentation.request.SiteInfoRequest;
import com.csee.hanspace.presentation.response.CreateSiteResponse;
import com.csee.hanspace.presentation.response.SiteByLinkResponse;
import com.csee.hanspace.presentation.response.SiteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import com.csee.hanspace.domain.entity.Site;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/site")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private SavedUserInfoService savedUserInfoService;

//    private static SavedUserInfoService savedUserInfoService;

    @GetMapping("/getSiteByLink/{link}")
    public ResponseEntity<SiteByLinkResponse> findByLink(@PathVariable String link){
        SiteByLinkResponse res = siteService.findByLink(link);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/saveSiteInfo")
    public ResponseEntity<Long> saveSiteInfo (@RequestBody SiteInfoRequest request) {
        Long siteId = siteService.saveSiteInfo(SiteInfoDto.from(request));
        return ResponseEntity.ok(siteId);
    }

    @PutMapping("/editSiteInfo")
    public ResponseEntity<Long> editSiteInfo (@RequestBody SiteEditRequest request) {
        Long siteId = siteService.editSiteInfo(SiteEditDto.from(request));
        return ResponseEntity.ok(siteId);
    }


     @GetMapping("/all-sites")
     public ResponseEntity<List<SiteResponse>> getAllSites() {
         List<SiteDto> sites = siteService.getAllSites();
         List<SiteResponse> response = sites.stream()
                 .map(SiteDto::siteResponse)
                 .collect(Collectors.toList());
         return ResponseEntity.ok(response);
     }


     @PostMapping("create-site")
    public ResponseEntity<CreateSiteResponse> createSite(@RequestBody CreateSiteRequest request) {
        SiteBodyDto siteBodyDto = siteService.create(request.siteCUDto());
        CreateSavedDto createSavedDto = savedUserInfoService.createSavedUserInfo(request.getUserId(), siteBodyDto.getSiteId());

         CreateSiteResponse response = new CreateSiteResponse(siteBodyDto.getSiteId(), siteBodyDto.getSiteName(), siteBodyDto.getDescription(), siteBodyDto.getLogo(), siteBodyDto.getLink(), siteBodyDto.getCompany(), siteBodyDto.getMaxDate(), siteBodyDto.getMaxTime(), siteBodyDto.getTimeUnit(), siteBodyDto.getQuestion1(), siteBodyDto.getQuestion2(), siteBodyDto.getRestriction(), createSavedDto.getSavedId(), createSavedDto.getUserId(), createSavedDto.getAuthority());
        return ResponseEntity.ok(response);
     }

}
