package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.ReserveDto;
import com.csee.hanspace.application.dto.SiteDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.SiteService;
import com.csee.hanspace.presentation.response.ReserveResponse;
import com.csee.hanspace.presentation.response.SiteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/site")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SiteController {
    private final SiteService siteService;


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
        List<SiteDto> sites = siteService.getMySites(savedUserInfoId, 1, 2);
        List<SiteResponse> response = sites.stream()
                .map(SiteDto::siteResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

}
