package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.RoomDto;
import com.csee.hanspace.application.dto.RoomReadDto;
import com.csee.hanspace.application.dto.RoomsReadDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.RoomService;
import com.csee.hanspace.domain.entity.Room;
import com.csee.hanspace.presentation.request.CreateRoomRequest;
import com.csee.hanspace.presentation.request.UpdateRoomAvailRequest;
import com.csee.hanspace.presentation.request.UpdateRoomRequest;
import com.csee.hanspace.presentation.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoomController {
    @Autowired
    private final RoomService roomService;

//    @GetMapping(value ="/calendarEvents/{sid}")
//    public ResponseEntity<List<Room>> findAllRoomForCalendar(@PathVariable Long sid){
//        List<Room> res = roomService.findAllRoomForCalendar(sid);
//        return ResponseEntity.ok(res);
//    }

    @GetMapping(value="/roomList/{sid}")
    public ResponseEntity<List<RoomFilterResponse>> findAllBySiteId(@PathVariable Long sid){
        List<RoomFilterResponse> res = roomService.findAllBySiteId(sid);
        return ResponseEntity.ok(res);
    }

//    공간 관리 room read
    @GetMapping("/find-room")
    public ResponseEntity<RoomReadResponse> findRoomById(@RequestParam Long roomId){
        RoomReadDto room = roomService.findRoomById(roomId);
        RoomReadResponse res = room.toRes();
        return ResponseEntity.ok(res);
    }

//    공간 관리 room 전체 read
    @GetMapping("/find-rooms")
    public ResponseEntity<List<RoomsReadResponse>> findRoomsById(@RequestParam Long siteId){
        List<RoomsReadDto> rooms = roomService.findRooms(siteId);
        List<RoomsReadResponse> res = rooms.stream().map(RoomsReadDto::toRes).collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }

//    공간 관리 room 삭제
    @PostMapping("/delete-room")
    public ResponseEntity<ReserveIdResponse> deleteRoom(@RequestParam Long roomId) {
        Long deletedRoomId = roomService.deleteRoom(roomId);
        ReserveIdResponse response = new ReserveIdResponse(deletedRoomId);
        return ResponseEntity.ok(response);
    }

//    공간 관리 room 추가
    @PostMapping("/create-room")
    public ResponseEntity<CreateRoomResponse> createRoom(@RequestBody CreateRoomRequest request) {
        RoomDto roomDto = roomService.create(request.roomCUDto(), request.getSiteId());
        CreateRoomResponse res = roomDto.createRoomResponse();
        return ResponseEntity.ok(res);
    }

//    공간 관리 room 수정
    @PostMapping("/update-room")
    public ResponseEntity<UpdateRoomResponse> updateRoom(@RequestBody UpdateRoomRequest request) {
        RoomDto roomDto = roomService.updateRoom(request.roomCUDto(), request.getRoomId());
        UpdateRoomResponse res = roomDto.updateRoomResponse();
        return ResponseEntity.ok(res);
    }

//    공간 관리 room 사용금지 여부 수정
    @PostMapping("/update-room")
    public ResponseEntity<UpdateRoomAvailResponse> updateRoomAvailable(@RequestBody UpdateRoomAvailRequest request) {
        RoomDto roomDto = roomService.updateRoomAvailable(request.roomAvailableDto(), request.getRoomId());
        UpdateRoomAvailResponse res = roomDto.updateRoomAvailResponse();
        return ResponseEntity.ok(res);
    }



}
