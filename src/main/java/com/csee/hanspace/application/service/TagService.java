package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.entity.Room;
import com.csee.hanspace.domain.entity.RoomTags;
import com.csee.hanspace.domain.entity.Site;
import com.csee.hanspace.domain.entity.Tag;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.RoomTagRepository;
import com.csee.hanspace.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    private final RoomTagRepository roomTagRepository;

    @Transactional
    public void saveRoomTags(RoomTags roomTags){
        roomTagRepository.save(roomTags);
    }

    @Transactional
    public void saveTag(Tag tag){
        tagRepository.save(tag);
    }

    @Transactional
    public Tag findById(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such roomTags"));
    }

}
