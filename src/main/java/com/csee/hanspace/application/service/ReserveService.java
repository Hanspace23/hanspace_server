package com.csee.hanspace.application.service;


import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.application.dto.RoomReserveDto;
import com.csee.hanspace.domain.entity.*;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.TimeRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;

    private final UserService userService;

    private final SiteService siteService;

    private final SavedUserInfoService userInfoService;

    private final RoomService roomService;

    private final TimeRecordRepository timeRecordRepository;


    @Transactional
    public Long save(String email, Long siteId ,RoomReserveDto dto) {
        // 일회 대여
        User user = userService.findByEmail(email);
        SavedUserInfo savedUserInfo = userInfoService.findById(user.getId());
        Site site = siteService.findById(siteId);
        Room room = roomService.findByName(dto.getRoomName());
        if(dto.isReserveOne()) {
            ReserveRecord savedRecord = reserveRepository.save(reserveRepository.save(ReserveRecord.onetimeReserve(savedUserInfo, site, room,  dto)));
            saveOneTimeRecord(dto.getReserveDate(), dto.getReserveTime(), savedRecord);
            return savedRecord.getId();
        }
        // 정기 대여
        else {
            ReserveRecord savedRecord = reserveRepository.save(reserveRepository.save(ReserveRecord.regularReserve(savedUserInfo, site, room, dto)));
            return savedRecord.getId();
        }
    }

    public Long saveOneTimeRecord(String reserveDate, List<String> reserveTime, ReserveRecord record) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(reserveDate, formatter);
//        Pattern pattern = Pattern.compile("(\\d{2}):\\d{2} ~ (\\d{2}):\\d{2}");

            List<Integer> hours = new ArrayList<>();
            for (String time : reserveTime) {
                String[] parts = time.split(" ~ ");
                String start = parts[0];
                String end = parts[1];

                int startHour = Integer.parseInt(start.split(":")[0]);
                int endHour = Integer.parseInt(end.split(":")[0]);

                TimeRecord timeRecord = TimeRecord.of(date, startHour, endHour, record);
                TimeRecord savedRecord = timeRecordRepository.save(timeRecord);
            }

        return 1L;
    }


//    @Transactional
//    public List<ReserveDto> getMyReservations(Long savedUserInfoId) {
//        List<ReserveRecord> reserves = reserveRepository.findAllBySavedUserInfoId(savedUserInfoId);
//        return reserves.stream()
//                .map(ReserveDto::new)
//                .collect(Collectors.toList());
//    }

}
