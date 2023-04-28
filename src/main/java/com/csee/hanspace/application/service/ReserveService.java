package com.csee.hanspace.application.service;


//import com.csee.hanspace.application.dto.ReserveDto;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;


//    @Transactional
//    public List<ReserveDto> getMyReservations(Long savedUserInfoId) {
//        List<ReserveRecord> reserves = reserveRepository.findAllBySavedUserInfoId(savedUserInfoId);
//        return reserves.stream()
//                .map(ReserveDto::new)
//                .collect(Collectors.toList());
//    }

}
