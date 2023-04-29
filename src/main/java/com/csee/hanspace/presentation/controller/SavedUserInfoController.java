package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.SavedUserInfoService;
import com.csee.hanspace.domain.entity.SavedUserInfo;
import com.csee.hanspace.presentation.request.UserEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saved")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SavedUserInfoController {

    @Autowired
    private final SavedUserInfoService savedUserInfoService;

    @GetMapping(value="/findByEmail")
    public ResponseEntity<SavedUserInfo> findByEmailSiteId(@RequestBody UserEmailRequest request){
        SavedUserInfo res = savedUserInfoService.findByEmailSiteId(request);
        return ResponseEntity.ok(res);
    }


}
