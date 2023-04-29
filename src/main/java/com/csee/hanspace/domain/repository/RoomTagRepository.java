package com.csee.hanspace.domain.repository;

import com.csee.hanspace.domain.entity.RoomTags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTagRepository extends JpaRepository<RoomTags, Long> {
}
