package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.entity.Room;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room findByName(String roomName){
        return roomRepository.findByName(roomName);
    }



}
