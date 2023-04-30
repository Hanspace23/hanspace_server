package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.SiteService;
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



}
