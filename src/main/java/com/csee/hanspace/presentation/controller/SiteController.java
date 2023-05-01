package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.SiteDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.SiteService;
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



    // @GetMapping("/all-sites")
    // public ResponseEntity<List<SiteResponse>> getAllSites() {
    //     List<SiteDto> sites = siteService.getAllSites();
    //     List<SiteResponse> response = sites.stream()
    //             .map(SiteDto::siteResponse)
    //             .collect(Collectors.toList());
    //     return ResponseEntity.ok(response);
    // }

    // @GetMapping("/my-sites")
    // public ResponseEntity<List<SiteResponse>> getMySites(@RequestParam Long savedUserInfoId) {
    //     List<SiteDto> sites = siteService.getMySites(savedUserInfoId, 1, 2);
    //     List<SiteResponse> response = sites.stream()
    //             .map(SiteDto::siteResponse)
    //             .collect(Collectors.toList());
    //     return ResponseEntity.ok(response);
    

}
