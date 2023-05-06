package com.csee.hanspace.presentation.controller;


import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.application.dto.OneReserveDto;

import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.presentation.request.ChangeMStatusRequest;
import com.csee.hanspace.presentation.request.ChangeStatusRequest;
import com.csee.hanspace.presentation.request.RegularReserveRequest;
import com.csee.hanspace.presentation.request.RoomReserveRequest;
import com.csee.hanspace.presentation.response.*;
import com.csee.hanspace.presentation.request.*;
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
    public ResponseEntity<Long> save(@RequestBody RoomReserveRequest request) {
        System.out.println("request = " + request);
        Long savedId = reserveService.save(OneReserveDto.from(request));
        return ResponseEntity.ok(savedId);
//        return ResponseEntity.ok(request);
    }

    @PostMapping("/reserveRegularRoom")
    public ResponseEntity<Void> saveRegular(@RequestBody RoomReserveRequest request) {
        reserveService.saveRegular(OneReserveDto.from(request));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/allReservedList/{siteId}")
    public ResponseEntity<List<AllReservedResponse>> findAllReserved(@PathVariable Long siteId) {
        System.out.println("siteId = " + siteId);
        List<AllReservedResponse> responses = reserveService.readAllReserveList(siteId).stream()
                .map(AllReservedResponse::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/oneReservedList/{siteId}")
    public ResponseEntity<List<AllReservedResponse>> findOneReserved(@PathVariable Long siteId) {
//        System.out.println("siteId = " + siteId);
        List<AllReservedResponse> responses = reserveService.readOneReserveList(siteId).stream()
                .map(AllReservedResponse::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/allRegularReservedList/{siteId}")
    public ResponseEntity<List<AllReservedResponse>> findAllRegularReserved(@PathVariable Long siteId) {
        List<AllReservedResponse> responses = reserveService.readAllRegularList(siteId).stream()
                .map(AllReservedResponse::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/eachReservedList")
    public ResponseEntity<List<AllReservedResponse>> findAllReserved(@RequestBody EachOfRegularRequest request) {
        List<AllReservedResponse> responses = reserveService.readEachReserveList(request.getSiteId(), request.getRegularId()).stream()
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
        reserveService.changeMultiStatus(ChangeMRequestDto.from(request));
        return ResponseEntity.ok(null);
    }

    @PutMapping("/changeMRegularStatus")
    public ResponseEntity<Void> changeMRegularStatus (@RequestBody ChangeMStatusRequest request) {
        reserveService.changeMultiStatus(ChangeMRequestDto.from(request));
        return ResponseEntity.ok(null);
    }

//    @PutMapping("/changeAllRegularStatus")
//    public ResponseEntity<Void> changeAllRegularStatus (@RequestBody ChangeStatusRequest request) {
//        reserveService.changeMRegularStatus(ChangeRequestDto.from(request));
//        return ResponseEntity.ok(null);
//    }

    @DeleteMapping("deleteReserve")
    public ResponseEntity<Void> deleteReserve (@RequestBody DeleteReserveRequest request) {
        reserveService.deleteReserve(DeleteReserveDto.from(request));
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("deleteRegularReserve")
    public ResponseEntity<Void> deleteRegularReserve (@RequestBody DeleteReserveRequest request) {
        reserveService.deleteRegularReserve(DeleteReserveDto.from(request));
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("deleteMultiReserve")
    public ResponseEntity<Void> deleteMReserve (@RequestBody DeleteMReserveRequest request) {
        System.out.println("request = " + request);
        reserveService.deleteMReserve(DeleteMultiReserveDto.from(request));
        return ResponseEntity.ok(null);
    }





//    @DeleteMapping("deleteMReserve")
//    public ResponseEntity<Void> deleteMRegularReserve (@RequestBody DeleteMRegularRequest request) {
//        reserveService.deleteMultiRegular(DeleteMultiRegularDto.from(request));
//        return ResponseEntity.ok(null);
//    }



    @GetMapping("/calendarList/{siteId}")
    public ResponseEntity<List<ReserveCalResponse>> findAllBySiteId(@PathVariable Long siteId){
        List<ReserveCalResponse> res = reserveService.findAllBySiteId(siteId);
        return ResponseEntity.ok(res);
    }




}
