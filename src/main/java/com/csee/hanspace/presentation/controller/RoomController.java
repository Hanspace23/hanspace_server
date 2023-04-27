package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.RoomService;
import com.csee.hanspace.domain.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoomController {
    private final RoomService roomService;

    @GetMapping(value ="/calendarEvents/{sid}")
    public ResponseEntity<List<Room>> findAllRoomForCalendar(@PathVariable Long sid){
        List<Room> res = roomService.findAllRoomForCalendar(sid);
        return ResponseEntity.ok(res);
    }



}
