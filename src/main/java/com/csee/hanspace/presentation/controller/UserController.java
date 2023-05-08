package com.csee.hanspace.presentation.controller;


import com.csee.hanspace.application.dto.UserListDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.UserService;
import com.csee.hanspace.domain.entity.SavedUserInfo;
import com.csee.hanspace.domain.entity.User;
import com.csee.hanspace.presentation.request.RoomReserveRequest;
import com.csee.hanspace.presentation.request.UserEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private final UserService userService;



//    @PostMapping(value="/signup")
//    public ResponseEntity<User> signup(@RequestBody UserEmailRequest request){
//        User res = userService.signup(request);
//        return ResponseEntity.ok(res);
//    }

    @PostMapping(value="/findElseSignUp")
    public ResponseEntity<User> findElseSignUp(@RequestBody UserEmailRequest request){
        User res = userService.findElseSignUp(request);
        return ResponseEntity.ok(res);
    }





}
