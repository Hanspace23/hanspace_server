package com.csee.hanspace.presentation.controller;


import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.application.dto.OneReserveDto;
import com.csee.hanspace.application.dto.RegularReserveDto;
import com.csee.hanspace.application.dto.RoomReserveDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.presentation.request.ChangeMStatusRequest;
import com.csee.hanspace.presentation.request.ChangeStatusRequest;
import com.csee.hanspace.presentation.request.RegularReserveRequest;
import com.csee.hanspace.presentation.request.RoomReserveRequest;
import com.csee.hanspace.presentation.response.AllReservedResponse;
import com.csee.hanspace.presentation.response.ReserveCalResponse;

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
//
//    @GetMapping("/reservations")
//    public ResponseEntity<List<ReserveResponse>> getMyReservations(@RequestParam Long savedUserInfoId) {
//        List<ReserveDto> reserves = reserveService.getMyReservations(savedUserInfoId);
//        List<ReserveResponse> response = reserves.stream()
//                .map(ReserveDto::reserveResponse)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping
//    public ResponseEntity<ReserveDetailDto> getOneReservation(@RequestParam Long reservationId) {
//        ReserveDetailDto reserveDetailDto = reserveService.find(reservationId);
//        return ResponseEntity.ok(reserveDetailDto);
//    }
//
//    @PostMapping("/delete")
//    public ResponseEntity<ReservationIdResponse> deleteReservation(@RequestParam Long reservationId) {
//        Long deletedReservationId = reserveService.delete(reservationId);
//        ReservationIdResponse response = new ReservationIdResponse(deletedReservationId);
//        return ResponseEntity.ok(response);
//    }

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

//    @GetMapping("/allReservedList")
//    public ResponseEntity<List<AllReservedResponse>> findAllReserved(@RequestParam Long siteId) {
//        List<AllReservedResponse> responses = reserveService.readAllReserveList(siteId).stream()
//                .map(AllReservedResponse::toResponse)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responses);
//    }

//    @GetMapping("/eachReservedList")
//    public ResponseEntity<List<AllReservedResponse>> findAllReserved(@RequestParam Long siteId, @RequestParam Long regularId) {
//        List<AllReservedResponse> responses = reserveService.readEachReserveList(siteId, regularId).stream()
//                .map(AllReservedResponse::toResponse)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responses);
//    }


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

//    @GetMapping("/allRegularReserve")
//    public ResponseEntity

    @GetMapping("/calendarList/{siteId}")
    public ResponseEntity<List<ReserveCalResponse>> findAllBySiteId(@PathVariable Long siteId){
        List<ReserveCalResponse> res = reserveService.findAllBySiteId(siteId);
        return ResponseEntity.ok(res);
    }


}
