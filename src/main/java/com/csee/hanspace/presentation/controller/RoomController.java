package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.dto.RoomAndTagDto;
import com.csee.hanspace.application.dto.RoomDto;
import com.csee.hanspace.application.dto.RoomsReadDto;
import com.csee.hanspace.application.service.ReserveService;
import com.csee.hanspace.application.service.RoomService;
import com.csee.hanspace.domain.entity.Room;
import com.csee.hanspace.presentation.request.*;
import com.csee.hanspace.presentation.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
//    @GetMapping("/find-room")
//    public ResponseEntity<RoomReadResponse> findRoomById(@RequestParam Long roomId){
//        RoomReadDto room = roomService.findRoomById(roomId);
//        RoomReadResponse res = room.toRes();
//        return ResponseEntity.ok(res);
//    }

//    공간 관리 room 전체 read
    @GetMapping("/find-rooms")
    public ResponseEntity<List<RoomsReadResponse>> findRoomsById(@RequestParam Long siteId){
        List<RoomsReadDto> rooms = roomService.findRooms(siteId);
        List<RoomsReadResponse> res = rooms.stream().map(RoomsReadDto::toRes).collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }

//    공간 관리 room 삭제
    @DeleteMapping("/delete-room")
    public ResponseEntity<ReserveIdResponse> deleteRoom(@RequestParam Long roomId) {
        Long deletedRoomId = roomService.deleteRoom(roomId);
        ReserveIdResponse response = new ReserveIdResponse(deletedRoomId);
        return ResponseEntity.ok(response);
    }

//    공간 관리 room 추가
//    @PostMapping("/create-room")
//    public ResponseEntity<CreateRoomResponse> createRoom(@RequestBody CreateRoomRequest request) {
//        RoomDto roomDto = roomService.create(request.roomCUDto(), request.getSiteId());
//        CreateRoomResponse res = roomDto.createRoomResponse();
//        return ResponseEntity.ok(res);
//    }

    //    공간 관리 room 추가, roomtag 생성
    @PostMapping("/create")
    public ResponseEntity<CreateRoomResponse> createRoomAndTag(@RequestBody CreateRoomAndTagRequest request) {
        RoomDto roomDto = roomService.createRoomAndTag(request.roomAndTagCUDto(), request.createTagDto(), request.getSiteId());
        CreateRoomResponse res = roomDto.createRoomResponse();
        return ResponseEntity.ok(res);
    }

//    공간 관리 room 수정
//    @PostMapping("/update-room")
//    public ResponseEntity<UpdateRoomResponse> updateRoom(@RequestBody UpdateRoomRequest request) {
//        RoomDto roomDto = roomService.updateRoom(request.roomCUDto(), request.createTagDto(), request.getRoomId());
//        UpdateRoomResponse res = roomDto.updateRoomResponse();
//        return ResponseEntity.ok(res);
//    }

    //    공간 관리 room 수정
    @PostMapping("/update-room")
    public ResponseEntity<UpdateRoomResponse> updateRoom(@RequestBody UpdateRoomRequest request) {
        RoomDto roomDto = roomService.updateRoom(request.roomCUDto(), request.getRoomId());
        UpdateRoomResponse res = roomDto.updateRoomResponse();
        return ResponseEntity.ok(res);
    }

//    공간 관리 room 사용금지 여부 수정
//    @PostMapping("/update-avail")
//    public ResponseEntity<UpdateRoomAvailResponse> updateRoomAvailable(@RequestBody UpdateRoomAvailRequest request) {
//        RoomDto roomDto = roomService.updateRoomAvailable(request.roomAvailableDto(), request.getRoomId());
//        UpdateRoomAvailResponse res = roomDto.updateRoomAvailResponse();
//        return ResponseEntity.ok(res);
//    }

//    공간 관리 room 사용금지 여부 수정
    @PostMapping("update-avails")
    public ResponseEntity<List<RoomAvailsResponse>> updateRoomAvailables(@RequestBody List<RoomAvailsRequest> requestList) {
        List<RoomDto> roomDtoList = new ArrayList<>();

        int times = 0;
        for(RoomAvailsRequest request : requestList) {
            if(times != 0) {
                RoomDto roomDto = roomService.updateRoomAvailable(request.getRoomId(), request.roomAvailableDto());
                roomDtoList.add(roomDto);
            }
            times++;
        }

        List<RoomAvailsResponse> res = roomDtoList.stream().map(RoomDto::toRes).collect(Collectors.toList());

        return ResponseEntity.ok(res);
    }



}
