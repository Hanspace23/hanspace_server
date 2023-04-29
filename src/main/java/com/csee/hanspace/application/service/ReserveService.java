package com.csee.hanspace.application.service;


import com.csee.hanspace.application.dto.AllReservedDto;
import com.csee.hanspace.application.dto.OneReserveDto;
import com.csee.hanspace.application.dto.RegularReserveDto;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.application.dto.RoomReserveDto;
import com.csee.hanspace.domain.entity.*;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.TimeRecordRepository;
import com.csee.hanspace.presentation.response.ReserveCalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;

    @Autowired
    private final UserService userService;

    @Autowired
    private final SiteService siteService;

    @Autowired
    private final SavedUserInfoService userInfoService;

    @Autowired
    private final RoomService roomService;

    private final TimeRecordRepository timeRecordRepository;

    // 일회 대여
    @Transactional
    public Long save(String email, Long siteId , OneReserveDto dto) {


        User user = userService.findByEmail(email);
        SavedUserInfo savedUserInfo = userInfoService.findById(user.getId());
        Site site = siteService.findById(siteId);
        Room room = roomService.findByName(dto.getRoomName());

        ReserveRecord savedRecord = reserveRepository.save(reserveRepository.save(ReserveRecord.onetimeReserve(savedUserInfo, site, room,  dto)));
        saveOneTimeRecord(dto.getReserveDate(), dto.getReserveTime(), savedRecord, room);
        return savedRecord.getId();




    }

    // 정기 대여
    @Transactional
    public Long saveRegular(String email, Long siteId , RegularReserveDto dto) {
        User user = userService.findByEmail(email);
        SavedUserInfo savedUserInfo = userInfoService.findById(user.getId());
        Site site = siteService.findById(siteId);
        Room room = roomService.findByName(dto.getRoomName());

        // 예약 날짜 구분해서 Time Record
         // 시간 구분해서 TimeRecord
        ReserveRecord savedRecord = reserveRepository.save(reserveRepository.save(ReserveRecord.regularReserve(savedUserInfo, site, room, dto)));
        return savedRecord.getId();
    }

    // 일회 대여 저장
    public Long saveOneTimeRecord(String reserveDate, List<String> reserveTime, ReserveRecord record , Room room) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(reserveDate, formatter);
        for (String time : reserveTime) {
            String[] parts = time.split(" ~ ");
            String start = parts[0];
            String end = parts[1];

            int startHour = Integer.parseInt(start.split(":")[0]);
            int endHour = Integer.parseInt(end.split(":")[0]);
            int startHourByMin = startHour * 60;
            int endHourByMin = endHour * 60;
            while(startHourByMin < endHourByMin) {
                TimeRecord timeRecord = TimeRecord.from(date, startHourByMin, startHourByMin+30, record, room);
                startHourByMin = startHourByMin+30;
                timeRecordRepository.save(timeRecord);
            }

        }

        return 1L;
    }

    
//    @Transactional
//    public List<AllReservedDto> readAllReserveList(Long siteId) {
//        List<ReserveRecord> reservedList = reserveRepository.findAllReserveBySiteId(siteId);
//        List<AllReservedDto> reserveList = reservedList.stream().map(reserve -> {
//            return reserve.getTimeRecordList().stream().map(time -> {AllReservedDto.toAllReserveList(reserve, time)}
//            );
//        }).collect(Collectors.toList());
//
//        System.out.println("reservedList = " + reservedList);
//        return reserveList;
//    }
    @Transactional
    public void saveTimeRecord(TimeRecord timeRecord){
        timeRecordRepository.save(timeRecord);
    }

    @Transactional
    public void saveReserveRecord(ReserveRecord reserveRecord){
        reserveRepository.save(reserveRecord);
    }

    @Transactional
    public ReserveRecord findById(Long id){
        return reserveRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such reserveRecord"));
    }


//    @Transactional
//    public List<ReserveDto> getMyReservations(Long savedUserInfoId) {
//        List<ReserveRecord> reserves = reserveRepository.findAllBySavedUserInfoId(savedUserInfoId);
//        return reserves.stream()
//                .map(ReserveDto::new)
//                .collect(Collectors.toList());
//    }



    @Transactional(readOnly = true)
    public List<ReserveCalResponse> findAllBySiteId(Long siteId) {
        List<ReserveRecord> temp = reserveRepository.findAllReserveBySiteId(siteId);
        List<ReserveCalResponse> ret = temp.stream().map(ReserveCalResponse::from).collect(Collectors.toList());
        return ret;
    }

}
