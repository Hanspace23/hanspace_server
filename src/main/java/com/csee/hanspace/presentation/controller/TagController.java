package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TagController {
    private final TagService tagService;


}
