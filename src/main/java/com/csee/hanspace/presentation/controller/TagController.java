package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.SiteDto;
import com.csee.hanspace.application.dto.TagDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.TagService;
import com.csee.hanspace.presentation.response.SiteResponse;
import com.csee.hanspace.presentation.response.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TagController {
    private final TagService tagService;

//    태그 리스트
    @GetMapping("/site-tags")
    public ResponseEntity<List<TagResponse>> getSiteTags(@RequestParam Long siteId) {
        List<TagDto> tags = tagService.getSiteTags(siteId);
        List<TagResponse> response = tags.stream()
                .map(TagDto::tagResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


}
