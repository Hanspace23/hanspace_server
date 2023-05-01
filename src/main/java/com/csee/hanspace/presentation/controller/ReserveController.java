package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.ReserveDetailDto;
import com.csee.hanspace.application.dto.ReserveDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.presentation.request.ReservationIdRequest;
import com.csee.hanspace.presentation.response.ReservationIdResponse;
import com.csee.hanspace.presentation.response.ReserveResponse;
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

    @GetMapping("/reservations")
    public ResponseEntity<List<ReserveResponse>> getMyReservations(@RequestParam Long savedUserInfoId) {
        List<ReserveDto> reserves = reserveService.getMyReservations(savedUserInfoId);
        List<ReserveResponse> response = reserves.stream()
                .map(ReserveDto::reserveResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ReserveDetailDto> getOneReservation(@RequestParam Long reservationId) {
        ReserveDetailDto reserveDetailDto = reserveService.find(reservationId);
        return ResponseEntity.ok(reserveDetailDto);
    }

    @PostMapping("/delete")
    public ResponseEntity<ReservationIdResponse> deleteReservation(@RequestParam Long reservationId) {
        Long deletedReservationId = reserveService.delete(reservationId);
        ReservationIdResponse response = new ReservationIdResponse(deletedReservationId);
        return ResponseEntity.ok(response);
    }


}
