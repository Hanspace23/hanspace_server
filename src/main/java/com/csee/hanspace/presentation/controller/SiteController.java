package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.SiteDto;
import com.csee.hanspace.application.dto.SiteEditDto;
import com.csee.hanspace.application.dto.SiteInfoDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.SiteService;
import com.csee.hanspace.application.service.TagService;
import com.csee.hanspace.presentation.request.SiteEditRequest;
import com.csee.hanspace.presentation.request.SiteInfoRequest;
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

    @GetMapping("/getSiteByLink/{link}")
    public ResponseEntity<Site> findByLink(@PathVariable String link){
        Site res = siteService.findByLink(link);
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

     @GetMapping("/my-sites")
     public ResponseEntity<List<SiteResponse>> getMySites(@RequestParam Long savedUserInfoId) {
         List<SiteDto> sites = siteService.getMySites(savedUserInfoId);
         List<SiteResponse> response = sites.stream()
                 .map(SiteDto::siteResponse)
                 .collect(Collectors.toList());
         return ResponseEntity.ok(response);
     }

    @GetMapping("/manage-sites")
    public ResponseEntity<List<SiteResponse>> getManagingSites(@RequestParam Long savedUserInfoId) {
        List<SiteDto> sites = siteService.getManagingSites(savedUserInfoId);
        List<SiteResponse> response = sites.stream()
                .map(SiteDto::siteResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/subscribed-sites")
    public ResponseEntity<List<SiteResponse>> getSubscribedSites(@RequestParam Long savedUserInfoId) {
        List<SiteDto> sites = siteService.getSubscribedSites(savedUserInfoId);
        List<SiteResponse> response = sites.stream()
                .map(SiteDto::siteResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}
