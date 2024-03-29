package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

//    @Query("select r from Room r where r.site.id = :sid ")
//    List<Room> findAllRoomBySiteId(Long sid);

    Room findByName(String roomName);

    List<Room> findAllBySiteId(Long sid);

    @Modifying
    @Query("Update Room r set r.deleted = true where r.id = :id AND r.deleted = false")
    void deleteById(Long id);

}
