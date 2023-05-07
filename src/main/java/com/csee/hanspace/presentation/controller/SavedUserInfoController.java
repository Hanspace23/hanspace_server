package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.SavedUserInfoService;
import com.csee.hanspace.domain.entity.SavedUserInfo;
import com.csee.hanspace.domain.entity.User;
import com.csee.hanspace.presentation.request.*;
import com.csee.hanspace.presentation.response.SiteUserResponse;
import com.csee.hanspace.presentation.response.UserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/saved")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SavedUserInfoController {

    @Autowired
    private final SavedUserInfoService savedUserInfoService;

//    @GetMapping(value="/findByEmail")
//    public ResponseEntity<SavedUserInfo> findByEmailSiteId(@RequestBody UserEmailRequest request){
//        SavedUserInfo res = savedUserInfoService.findByEmailSiteId(request);
//        return ResponseEntity.ok(res);
//    }


    @GetMapping(value="/findCreator/{sid}")
    public ResponseEntity<User> findCreator(@PathVariable Long sid){
        User res = savedUserInfoService.findCreator(sid);
        return ResponseEntity.ok(res);
    }


    @PostMapping(value="/findSiteUserInfo")
    public ResponseEntity<SiteUserResponse>  findSiteUserInfo(@RequestBody SavedUserInfoRequest request){
        SiteUserResponse res = savedUserInfoService.findSiteUserInfo(request);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/readUserList/{siteId}")
    public ResponseEntity<List<UserListResponse>> readUserList(@PathVariable Long siteId) {
        List<UserListDto> userList = savedUserInfoService.readUserList(UserListDto.from(siteId));
        List<UserListResponse> list = userList.stream().map(UserListResponse::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/changeUserStatus")
    public ResponseEntity<Integer> changeUserStatus(@RequestBody ChangeUserStatusRequest request) {
        int id = savedUserInfoService.changeUserStatus(ChangeStatusDto.from(request));
        return ResponseEntity.ok(id);
    }

    @PutMapping("/changeMultiUserStatus")
    public ResponseEntity<Void> changeMultiUserStatus(@RequestBody ChangeMUserStatusRequest request) {
        savedUserInfoService.changeMUserStatus(ChangeMStatusDto.from(request));
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/editSavedUserInfo/{id}")
    public ResponseEntity<SiteUserResponse> editSavedUserInfo(@PathVariable Long id, @RequestBody SavedUserInfoEditRequest request) throws Throwable {
        SiteUserResponse res = savedUserInfoService.editSavedUserInfo(id, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Long> deleteUser(@RequestBody DeleteUserRequest request) {
        System.out.println("request = " + request);
        Long id = savedUserInfoService.deleteUser(DeleteUserDto.from(request));
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/deleteMultiUser")
    public ResponseEntity<Void> deleteMUser(@RequestBody DeleteMUserRequest request) {
        savedUserInfoService.deleteMUser(DeleteMUserDto.from(request));
        return ResponseEntity.ok(null);
    }


}
