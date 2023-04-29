package com.csee.hanspace.presentation.controller;


import com.csee.hanspace.application.service.UserService;
import com.csee.hanspace.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private final UserService userService;



    @GetMapping(value="/{email}}")
    public ResponseEntity<User> findByEmail(@PathVariable String email){
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }


}
