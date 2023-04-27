package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.presentation.request.ReserveDeleteRequest;
import com.csee.hanspace.presentation.response.MyReserveResponse;
import com.csee.hanspace.presentation.response.ReserveDeleteResponse;
import com.csee.hanspace.application.service.ReserveService;
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

//    @GetMapping("/myReserve")
//    public ResponseEntity<List<MyReserveResponse>> getMyReservations(@RequestParam Long userId) {
//        List<MyReserveDto> myReservations = reserveService.getMyReservations(userId);
//        List<MyReserveResponse> response = myReservations.stream()
//                .map(MyReserveDto::myReserveResponse)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/delete")
    public ResponseEntity<ReserveDeleteResponse> deleteReservation(@RequestBody ReserveDeleteRequest request) {
        Long deletedReserveId = reserveService.delete(request.getReserveId());
        ReserveDeleteResponse response = new ReserveDeleteResponse(deletedReserveId);
        return ResponseEntity.ok(response);
    }
}
