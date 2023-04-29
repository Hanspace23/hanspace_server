package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.OneReserveDto;
import com.csee.hanspace.application.dto.RegularReserveDto;
import com.csee.hanspace.application.dto.RoomReserveDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.presentation.request.RegularReserveRequest;
import com.csee.hanspace.presentation.request.RoomReserveRequest;
import com.csee.hanspace.presentation.response.AllReservedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @GetMapping("/allReservedList")
////    public ResponseEntity<List<AllReservedResponse>> findAllReserved(@RequestParam Long siteId) {
//    public ResponseEntity<Long> findAllReserved(@RequestParam Long siteId) {
//        List<ReserveRecord> list = reserveService.readAllReserveList(siteId);
//        return ResponseEntity.ok(2L);
//    }

}
