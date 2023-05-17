package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.RoomTags;
import com.csee.hanspace.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoomTagRepository extends JpaRepository<RoomTags, Long> {
     @Modifying
     @Query("delete from RoomTags t")
     void hardDeleteAll();
}
