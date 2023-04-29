package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.entity.Room;
import com.csee.hanspace.domain.entity.TimeRecord;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
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

//    public List<Room> findAllRoomForCalendar(Long sid) {
////        List<Room> ret = roomRepository.findAllRoomForCalendar(sid);
//    }
}
