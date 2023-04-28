package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByName(String roomName);
}
