package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.RoomReserveDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.presentation.request.RoomReserveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reserve")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReserveController {
    private final ReserveService reserveService;

    @PostMapping("/reserveRoom")
    public ResponseEntity<Long> save(@RequestParam String email, @RequestParam Long siteId, @RequestBody RoomReserveRequest request) {
        Long savedId = reserveService.save(email, siteId, RoomReserveDto.from(request));
        return ResponseEntity.ok(savedId);

    }



}
