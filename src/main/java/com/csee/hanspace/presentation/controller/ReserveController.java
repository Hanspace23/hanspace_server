package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reserve")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReserveController {
    private final ReserveService reserveService;


}
