package com.csee.hanspace.application.service;

import com.csee.hanspace.application.dto.RoomTagDto;
import com.csee.hanspace.application.dto.SiteDto;
import com.csee.hanspace.application.dto.TagDto;
import com.csee.hanspace.domain.entity.*;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.RoomTagRepository;
import com.csee.hanspace.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    //    사이트 태그 리스트
    @Transactional
    public List<TagDto> getSiteTags(Long siteId) {
        List<Tag> tagList = tagRepository.findAllBySiteId(siteId);
        return tagList.stream().map(TagDto::new).collect(Collectors.toList());
    }

    //    룸 태그 리스트
    @Transactional
    public List<RoomTagDto> getRoomTags(Long roomId) {
        List<RoomTags> roomTags = roomTagRepository.findAllByRoomId(roomId);
        return roomTags.stream().map(RoomTagDto::new).collect(Collectors.toList());
    }

}
