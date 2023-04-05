package com.csee.hanspace.reserve.service;

import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.reserve.application.dto.MyReserveDto;
import com.csee.hanspace.reserve.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;

    @Transactional
    public List<MyReserveDto> getMyReservations(Long userId){
        List<ReserveRecord> reservations = reserveRepository.findByUserId(userId);
        return reservations.stream().map(MyReserveDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long delete(Long reserveId) {
        reserveRepository.deleteById(reserveId);
        return reserveId;
    }
}
