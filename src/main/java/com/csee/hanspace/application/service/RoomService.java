package com.csee.hanspace.application.service;

import com.csee.hanspace.application.dto.RoomFilterDto;
import com.csee.hanspace.domain.entity.Room;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    @Autowired
    private final RoomRepository roomRepository;


    @Transactional(readOnly = true)
    public List<RoomFilterDto> findAllRoomBySiteId(Long sid) {
        List<Room> ret = roomRepository.findAllRoomBySiteId(sid);
        return ret.stream().map(RoomFilterDto::from).collect(Collectors.toList());
    }

    public Room findByName(String roomName){
        return roomRepository.findByName(roomName);
    }



//    public List<Room> findAllRoomForCalendar(Long sid) {
////        List<Room> ret = roomRepository.findAllRoomForCalendar(sid);
//    }
}
