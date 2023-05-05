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
import com.csee.hanspace.presentation.response.*;

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

//    일회대여 리스트
    @GetMapping("/one-reservations")
    public ResponseEntity<List<ReserveResponse>> getMyReservations(@RequestParam Long savedUserInfoId) {
        List<ReserveDto> reserves = reserveService.getMyReservations(savedUserInfoId);
        List<ReserveResponse> response = reserves.stream()
                .map(ReserveDto::reserveResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

//    일회 대여 더보기
    @GetMapping("/one-detail")
    public ResponseEntity<ReserveDetailDto> getOneReservationDetail(@RequestParam Long reservationId) {
        ReserveDetailDto reserveDetailDto = reserveService.findOneReservationDetail(reservationId);
        return ResponseEntity.ok(reserveDetailDto);
    }

    //    일회 대여 삭제
    @PostMapping("/delete")
    public ResponseEntity<ReserveIdResponse> deleteReservation(@RequestParam Long reservationId) {
        Long deletedReservationId = reserveService.delete(reservationId);
        ReserveIdResponse response = new ReserveIdResponse(deletedReservationId);
        return ResponseEntity.ok(response);
    }

//    정기 대여 리스트
    @GetMapping("/regular-reservations")
    public ResponseEntity<List<RegularReservationHistoryResponse>> getMyRegularReservations(@RequestParam Long savedUserInfoId) {
        List<RegularReservationHistoryDto> reserves = reserveService.getMyRegularReservations(savedUserInfoId);
        List<RegularReservationHistoryResponse> response = reserves.stream()
                .map(RegularReservationHistoryDto::reserveResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

//    정기 대여 더보기
    @GetMapping("/regular-detail")
    public ResponseEntity<ReserveDetailDto> getRegularReservationDetail(@RequestParam Long regularId) {
        ReserveDetailDto detailDto = reserveService.findRegularReservationDetail(regularId);
        return ResponseEntity.ok(detailDto);
    }

//    정기 대여 삭제
    @PostMapping("/delete/regular")
    public ResponseEntity<String> deleteAllByRegular(@RequestParam Long regularId) {
        reserveService.deleteAllByRegular(regularId);
        return ResponseEntity.ok("Success");
    }

//    개별 예약 보기
    @GetMapping("/each-reservations")
    public ResponseEntity<List<ReserveResponse>> getEachReservations(@RequestParam Long regularId) {
        List<ReserveDto> reserves = reserveService.getEachReservations(regularId);
        List<ReserveResponse> response = reserves.stream()
            .map(ReserveDto::reserveResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }



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
