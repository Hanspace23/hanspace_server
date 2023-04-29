package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.RoomReserveDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.UserService;
import com.csee.hanspace.presentation.request.RoomReserveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;



}
