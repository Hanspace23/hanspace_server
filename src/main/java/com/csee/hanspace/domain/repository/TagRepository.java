package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select t from Tag t left join fetch t.roomTagsList r where r.room.id = :roomId")
    List<Tag> findAllByRoomId(@Param("roomId") Long roomId);
}
