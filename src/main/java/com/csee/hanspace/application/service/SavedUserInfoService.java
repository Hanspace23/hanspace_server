package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.entity.SavedUserInfo;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.SavedUserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedUserInfoService {
    private final SavedUserInfoRepository savedUserInfoRepository;

    public SavedUserInfo findById(Long userId) {
        return savedUserInfoRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("no savedInfo data"));
    }
}
