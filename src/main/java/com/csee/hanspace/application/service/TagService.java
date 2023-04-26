package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;


}
