package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.presentation.request.ChangeMStatusRequest;
import com.csee.hanspace.presentation.request.ChangeStatusRequest;
import com.csee.hanspace.presentation.request.RegularReserveRequest;
import com.csee.hanspace.presentation.request.RoomReserveRequest;
import com.csee.hanspace.presentation.response.AllReservedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reserve")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReserveController {

    private final ReserveService reserveService;

    @PostMapping("/reserveRoom")
    public ResponseEntity<Long> save(@RequestParam String email, @RequestParam Long siteId, @RequestBody RoomReserveRequest request) {
        System.out.println("request = " + request);
        Long savedId = reserveService.save(email, siteId, OneReserveDto.from(request));
        return ResponseEntity.ok(savedId);
    }

    @PostMapping("/reserveRegularRoom")
    public ResponseEntity<Long> save(@RequestParam String email, @RequestParam Long siteId, @RequestBody RegularReserveRequest request) {
        System.out.println("request = " + request);
        Long savedId = reserveService.saveRegular(email,siteId, RegularReserveDto.from(request));
        return ResponseEntity.ok(savedId);
    }

    @GetMapping("/allReservedList")
    public ResponseEntity<List<AllReservedResponse>> findAllReserved(@RequestParam Long siteId) {
        List<AllReservedResponse> responses = reserveService.readAllReserveList(siteId).stream()
                .map(AllReservedResponse::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/eachReservedList")
    public ResponseEntity<List<AllReservedResponse>> findAllReserved(@RequestParam Long siteId, @RequestParam Long regularId) {
        List<AllReservedResponse> responses = reserveService.readEachReserveList(siteId, regularId).stream()
                .map(AllReservedResponse::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }


    @PutMapping("/changeStatus")
    public ResponseEntity<Integer> changeStatus (@RequestBody ChangeStatusRequest request) {
        int result = reserveService.changeStatus(ChangeRequestDto.from(request));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/changeRegularStatus")
    public ResponseEntity<Void> changeRegularStatus (@RequestBody ChangeStatusRequest request) {
        reserveService.changeRegularStatus(ChangeRequestDto.from(request));
        return ResponseEntity.ok(null);
    }

    @PutMapping("/changeMultiStatus")
    public ResponseEntity<Void> changeMultiStatus (@RequestBody ChangeMStatusRequest request) {
        System.out.println("request = " + request);
        System.out.println("request.getRecordList() = " + request.getRecordList());
        reserveService.changeMultiStatus(ChangeMRequestDto.from(request));
        return ResponseEntity.ok(null);
    }

    @PutMapping("/changeMRegularStatus")
    public ResponseEntity<Void> changeMRegularStatus (@RequestBody ChangeMStatusRequest request) {
        reserveService.changeMultiStatus(ChangeMRequestDto.from(request));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/allRegularReserve")
    public ResponseEntity



}
