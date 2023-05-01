package com.csee.hanspace.application.service;


import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.application.dto.AllReservedDto;
import com.csee.hanspace.application.dto.OneReserveDto;
import com.csee.hanspace.application.dto.RegularReserveDto;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.*;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.exception.ReserveRecordNotFoundException;
//import com.csee.hanspace.domain.repository.TimeRecordRepository;
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

//    private final TimeRecordRepository timeRecordRepository;

    // 일회 대여
    @Transactional
    public Long save(String email, Long siteId , OneReserveDto dto) {


        User user = userService.findByEmail(email);
        SavedUserInfo savedUserInfo = userInfoService.findById(user.getId());
        Site site = siteService.findById(siteId);
        Room room = roomService.findByName(dto.getRoomName());
        String fullReserveTime = String.join(" , " , dto.getReserveTime());
        ReserveRecord savedRecord = reserveRepository.save(ReserveRecord.onetimeReserve(savedUserInfo, site, room,  dto, fullReserveTime));
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
        String fullReserveTime = String.join(" , " , dto.getReserveTime());
        Long curId = reserveRepository.findCurrentRegularId(siteId);
        String[] parts = dto.getReserveDate().split(" ");
        String daysString = parts[3];
        String weekdays = daysString.replaceAll("[()]", "");

        ReserveRecord savedRecord = reserveRepository.save(ReserveRecord.regularReserve(savedUserInfo, site, room, dto, fullReserveTime, curId+1, weekdays));
        saveRegularTimeRecord(dto.getReserveDate(), dto.getReserveTime(), savedRecord, room);
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
//            while(startHourByMin < endHourByMin) {
//                TimeRecord timeRecord = TimeRecord.from(date, startHourByMin, startHourByMin+30, record, room);
//                startHourByMin = startHourByMin+30;
//                timeRecordRepository.save(timeRecord);
//            }
        }
        return 1L;
    }

    public Long saveRegularTimeRecord(String reserveDate, List<String> reserveTime, ReserveRecord record , Room room) {
        // 날짜 처리  (정기 대여 날짜 범위 LISt에 있음) List 반복해서 TimeRecord 저장하면서 시간 모두 저장
        // 요일 저장
        // regularId 불러와서 가장 큰 값 + 1
        // reserveTime 에 저장
        String[] parts = reserveDate.split(" ");
        String startDateString = parts[0];
        String endDateString = parts[2];
        String daysString = parts[3];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);

        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);

        List<LocalDate> dates = new ArrayList<>();
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            dates.add(date);
        }
        dates.add(endDate);

        for(LocalDate dateForRegular : dates) {
            for (String time : reserveTime) {
                String[] partTime = time.split(" ~ ");
                String start = partTime[0];
                String end = partTime[1];

                int startHour = Integer.parseInt(start.split(":")[0]);
                int endHour = Integer.parseInt(end.split(":")[0]);
                int startHourByMin = startHour * 60;
                int endHourByMin = endHour * 60;
//                while(startHourByMin < endHourByMin) {
//                    TimeRecord timeRecord = TimeRecord.from(dateForRegular, startHourByMin, startHourByMin+30, record, room);
//                    startHourByMin = startHourByMin+30;
//                    timeRecordRepository.save(timeRecord);
//                }
            }
        }
        return 1L;
    }

//    @Transactional
//    public List<AllReservedDto> readAllReserveList(Long siteId) {
//        List<ReserveRecord> reservedList = reserveRepository.findAllReserveBySiteId(siteId);
//        List<AllReservedDto> reserveList = reservedList.stream()
//                .flatMap(reserve -> reserve.getTimeRecordList().stream()
//                        .map(time -> AllReservedDto.toAllReserveList(reserve, time)))
//                .collect(Collectors.toList());
//
//        System.out.println("reservedList = " + reservedList);
//        return reserveList;
//    }

//    @Transactional
//    public List<AllReservedDto> readEachReserveList(Long siteId, Long regularId) {
//        List<ReserveRecord> reservedList = reserveRepository.findBySiteIdAndRegularId(siteId, regularId);
//        List<AllReservedDto> reserveList = reservedList.stream()
//                .flatMap(reserve -> reserve.getTimeRecordList().stream()
//                        .map(time -> AllReservedDto.toAllReserveList(reserve, time)))
//                .collect(Collectors.toList());
//
//        System.out.println("reservedList = " + reservedList);
//        return reserveList;
//    }

    @Transactional
    public Integer changeStatus(ChangeRequestDto dto){
        ReserveRecord record = reserveRepository.findBySiteIdAndId(dto.getSiteId(), dto.getRecordId());
        record.setStatus(dto.getStatusId());
        ReserveRecord newRecord = reserveRepository.save(record);
        System.out.println("newRecord = " + newRecord);

        return newRecord.getStatus();
    }

    public void changeRegularStatus(ChangeRequestDto dto){
        List<ReserveRecord> records = reserveRepository.findBySiteIdAndRegularId(dto.getSiteId(), dto.getRecordId());
        records.stream().forEach(record -> record.setStatus((dto.getStatusId())));
        List<ReserveRecord> list = reserveRepository.saveAll(records);
    }
//    @Transactional
//    public void saveTimeRecord(TimeRecord timeRecord){
//        timeRecordRepository.save(timeRecord);
//    }

    @Transactional
    public void saveReserveRecord(ReserveRecord reserveRecord){
        reserveRepository.save(reserveRecord);
    }

    @Transactional
    public ReserveRecord findById(Long id){
        return reserveRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such reserveRecord"));
    }

    @Transactional
    public void changeMultiStatus(ChangeMRequestDto dto) {
        List<Long> list = dto.getRecordList();
        System.out.println("list = " + list);
        for(Long i : list) {
            ReserveRecord record = reserveRepository.findBySiteIdAndId(dto.getSiteId(), i);
            record.setStatus(dto.getStatusId());
            reserveRepository.save(record);
        }
    }

    @Transactional
    public void changeMRegularStatus(ChangeMRequestDto dto) {
        List<Long> list = dto.getRecordList();

        for(Long i : list) {

            List<ReserveRecord> recordList = reserveRepository.findBySiteIdAndRegularId(dto.getSiteId(), i);
            for(ReserveRecord record: recordList) {

                reserveRepository.save(record);
            }

        }
    }




//    @Transactional
//    public List<ReserveDto> getMyReservations(Long savedUserInfoId) {
//        List<ReserveRecord> reserves = reserveRepository.findAllBySavedUserInfoId(savedUserInfoId);
//        return reserves.stream()
//                .map(ReserveDto::new)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public ReserveDetailDto find(Long reservationId) {
//
//        ReserveRecord reserve = reserveRepository.findById(reservationId).orElseThrow(ReserveRecordNotFoundException::new);
//
//        return reserve.toDetailDto();
//    }
//
//    @Transactional
//    public Long delete(Long reservationId) {
//        reserveRepository.deleteById(reservationId);
//        return reservationId;
//    }



    @Transactional(readOnly = true)
    public List<ReserveCalResponse> findAllBySiteId(Long siteId) {
        List<ReserveRecord> temp = reserveRepository.findAllReserveBySiteId(siteId);
        List<ReserveCalResponse> ret = temp.stream().map(ReserveCalResponse::from).collect(Collectors.toList());
        return ret;
    }

}
