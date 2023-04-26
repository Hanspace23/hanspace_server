package com.csee.hanspace.application.service;

import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.SiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteRepository siteRepository;


}
