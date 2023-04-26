package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.SavedUserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedUserInfoService {
    private final SavedUserInfoRepository savedUserInfoRepository;


}
