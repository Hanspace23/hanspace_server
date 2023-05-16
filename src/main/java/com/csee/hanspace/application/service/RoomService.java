package com.csee.hanspace.application.service;

import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.domain.entity.Room;
//import com.csee.hanspace.domain.entity.TimeRecord;
import com.csee.hanspace.domain.entity.RoomTags;
import com.csee.hanspace.domain.entity.Site;
import com.csee.hanspace.domain.entity.Tag;
import com.csee.hanspace.domain.repository.*;
import com.csee.hanspace.presentation.response.RoomFilterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    @Autowired
    private final RoomRepository roomRepository;

    @Autowired
    private final TagRepository tagRepository;

    @Autowired
    private final SiteRepository siteRepository;

    private final RoomTagRepository roomTagRepository;

    @Transactional
    public Room findByName(String roomName){
        return roomRepository.findByName(roomName);
    }

    public Room findById(Long id){
        return roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such room"));
    }

//    @Transactional
//    public RoomReadDto findRoomById(Long id){
//        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such room"));
//        List<Tag> tags = tagRepository.findAllByRoomId(id);
//        RoomReadDto roomReadDto = new RoomReadDto(room, tags);
//        return roomReadDto;
//    }

    @Transactional
    public void save(Room room){
        roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public List<RoomFilterResponse> findAllBySiteId(Long sid) {

        List<RoomFilterResponse> ret = roomRepository.findAllBySiteId(sid).stream().map(RoomFilterResponse::from).collect(Collectors.toList());
        return ret;
    }

    @Transactional
    public List<RoomsReadDto> findRooms(Long siteId) {
        List<Room> rooms = roomRepository.findAllBySiteId(siteId);
        return rooms.stream().map(RoomsReadDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
        return roomId;
    }

    @Transactional
    public RoomDto create(RoomCUDto roomCUDto, Long siteId) {
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("no such room"));
        Room newRoom = new Room(roomCUDto, site);
        Room savedRoom = roomRepository.save(newRoom);
        return savedRoom.toCreateDto();
    }

    @Transactional
    public RoomDto updateRoom(RoomCUDto roomCUDto, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("no such room"));
        room.update(roomCUDto);
        return room.toUpdateDto();
    }

//    @Transactional
//    public RoomDto updateRoomAvailable(RoomAvailableDto roomAvailableDto, Long roomId) {
//        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("no such room"));
//        room.updateAvailable(roomAvailableDto);
//        return room.toUpdateDto();
//    }

//  available update
    @Transactional
    public RoomDto updateRoomAvailable(Long roomId, RoomAvailableDto roomAvailableDto) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("no such room"));
        room.updateAvailable(roomAvailableDto);
        return room.toUpdateDto();
    }





//    public List<Room> findAllRoomForCalendar(Long sid) {
////        List<Room> ret = roomRepository.findAllRoomForCalendar(sid);
//    }
}
