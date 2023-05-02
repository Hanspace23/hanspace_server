package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.entity.Room;
//import com.csee.hanspace.domain.entity.TimeRecord;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.RoomRepository;
import com.csee.hanspace.presentation.response.RoomFilterResponse;
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

    @Transactional
    public Room findByName(String roomName){
        return roomRepository.findByName(roomName);
    }

    public Room findById(Long id){
        return roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such room"));
    }

    @Transactional
    public void save(Room room){
        roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public List<RoomFilterResponse> findAllBySiteId(Long sid) {

        List<RoomFilterResponse> ret = roomRepository.findAllBySiteId(sid).stream().map(RoomFilterResponse::from).collect(Collectors.toList());
        return ret;
    }




//    public List<Room> findAllRoomForCalendar(Long sid) {
////        List<Room> ret = roomRepository.findAllRoomForCalendar(sid);
//    }
}
