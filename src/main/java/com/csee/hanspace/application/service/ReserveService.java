package com.csee.hanspace.application.service;


import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.application.dto.AllReservedDto;
import com.csee.hanspace.application.dto.OneReserveDto;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.*;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.SavedUserInfoRepository;
import com.csee.hanspace.domain.repository.SiteRepository;
import com.csee.hanspace.exception.ReserveRecordNotFoundException;
//import com.csee.hanspace.domain.repository.TimeRecordRepository;
import com.csee.hanspace.presentation.request.ChangeMStatusRequest;
import com.csee.hanspace.presentation.response.RegularResponse;
import com.csee.hanspace.presentation.response.ReserveCalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
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
    private final SiteRepository siteRepository;
    private final SavedUserInfoRepository savedUserInfoRepository;

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
    public Long save(OneReserveDto dto) {
        User user = userService.findByEmail(dto.getEmail());
        SavedUserInfo savedUserInfo = userInfoService.findById(user.getId());
        Site site = siteService.findById(dto.getSiteId());
        Room room = roomService.findByName(dto.getRoomName());

        ReserveRecord savedRecord = reserveRepository.save(ReserveRecord.onetimeReserve(savedUserInfo, site, room,  dto));
        return savedRecord.getId();
    }

    // 정기 대여
    @Transactional
    public void saveRegular(OneReserveDto dto) {
        User user = userService.findByEmail(dto.getEmail());
        SavedUserInfo savedUserInfo = userInfoService.findById(user.getId());
        Site site = siteService.findById(dto.getSiteId());
        Room room = roomService.findByName(dto.getRoomName());
        Long curId = reserveRepository.findCurrentRegularId(dto.getSiteId());
        System.out.println("curId = " + curId);
        String[] parts = dto.getReserveDate().split(" ");

        String startDateString = parts[0];
        String endDateString = parts[2];
        String daysString = parts[3];
        String weekdays = daysString.replaceAll("[()]", "");
        List<DayOfWeek> daysOfWeek = Arrays.stream(weekdays.split(","))
                .map(String::trim)
                .map(OneReserveDto::parseDayOfWeek)
                .collect(Collectors.toList());


        System.out.println(daysOfWeek);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);
        List<LocalDate> dates = ReserveRecord.getDatesForDayOfWeek(startDate, endDate, daysOfWeek);
//        List<LocalDate> dates = new ArrayList<>();
//        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
//            dates.add(date);
//        }
//        dates.add(endDate);


        for(LocalDate dateForRegular : dates) {
            System.out.println("dateForRegular = " + dateForRegular);
            reserveRepository.save(ReserveRecord.regularReserve(savedUserInfo, site, room, dto, curId+1 , dateForRegular, weekdays));
        }

    }

    @Transactional
    public List<AllReservedDto> readAllReserveList(Long siteId) {
        List<ReserveRecord> reservedList = reserveRepository.findAllReserveBySiteId(siteId);
        String question1 = siteRepository.findQuestion1BySiteId(siteId);
        String question2 = siteRepository.findQuestion2BySiteId(siteId);
        System.out.println("reservedList = " + reservedList);
        List<AllReservedDto> reserveList = reservedList.stream().map(record -> AllReservedDto.of(record, question1, question2)).collect(Collectors.toList());

        return reserveList;
    }
    @Transactional
    public List<AllReservedDto> readOneReserveList(Long siteId) {
        List<ReserveRecord> reservedList = reserveRepository.findAllReserveBySiteId(siteId);
        System.out.println("reservedList = " + reservedList);
        String question1 = siteRepository.findQuestion1BySiteId(siteId);
        String question2 = siteRepository.findQuestion2BySiteId(siteId);
        List<AllReservedDto> reserveList = reservedList.stream().filter(data -> !data.isRegular()).map(record -> AllReservedDto.of(record, question1, question2)).collect(Collectors.toList());
        return reserveList;
    }
    @Transactional
    public List<AllReservedDto> readAllRegularList(Long siteId) {
        List<ReserveRecord> reservedList = reserveRepository.findAllRegularReserve(siteId);
        String question1 = siteRepository.findQuestion1BySiteId(siteId);
        String question2 = siteRepository.findQuestion2BySiteId(siteId);
//        Map<Long, List<ReserveRecord>> dataMap = new HashMap<>();
//        reservedList.stream() // 스트림 생성
//                .map(record -> {
//                Long regularId = record.getRegularId();
//
//                if (!dataMap.containsKey(regularId)) {
//                    dataMap.put(regularId, new ArrayList<>());
//                }
//
//                List<ReserveRecord> dataListForId = dataMap.get(regularId);
//                dataListForId.add(record);
//            }
////                System.out.println("record = " + record);
////                return record;
//                )
//            .collect(Collectors.toList());
////        List<ReserveRecord> flattenedList = dataMap.values().stream() // 스트림 생성
////                .flatMap(Collection::stream).map(record -> {
////                    System.out.println("record = " + record);
////                    return record;
////                }).collect(Collectors.toList()); // 결과를 List로 수집
////        List<ReserveRecord> flattenedList = records.stream()
////                .flatMap(List::stream)
////                .collect(Collectors.toList());

        List<AllReservedDto> reserveList = reservedList.stream().filter(ReserveRecord::isRegular).map(record -> AllReservedDto.of(record, question1, question2)).collect(Collectors.toList());

        return reserveList;
    }

    @Transactional
    public List<AllReservedDto> readEachReserveList(Long siteId, Long regularId) {
        List<ReserveRecord> reservedList = reserveRepository.findBySiteIdAndRegularId(siteId, regularId);
        String question1 = siteRepository.findQuestion1BySiteId(siteId);
        String question2 = siteRepository.findQuestion2BySiteId(siteId);
        List<AllReservedDto> reserveList = reservedList.stream().map(record -> AllReservedDto.of(record, question1, question2)).collect(Collectors.toList());
        return reserveList;
    }

    @Transactional
    public Integer changeStatus(ChangeRequestDto dto){
        ReserveRecord record = reserveRepository.findBySiteIdAndId(dto.getSiteId(), dto.getRecordId());
        record.setStatus(dto.getStatusId());
        ReserveRecord newRecord = reserveRepository.save(record);
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

//    @Transactional
//    public void changeMultiStatus(ChangeMRequestDto dto) {
//        List<Long> list = dto.getRecordList();
//        System.out.println("list = " + list);
//        for(Long i : list) {
//            List<ReserveRecord> recordList = reserveRepository.findBySiteIdAndRegularId(dto.getSiteId(), i);
//            System.out.println("recordList = " + recordList);
//            for(ReserveRecord record: recordList) {
//                System.out.println("record = " + record);
//                record.setStatus(dto.getStatusId());
//                reserveRepository.save(record);
//            }
//        }
//    }

    @Transactional
    public void changeMultiStatus(ChangeMRequestDto dto) {
        List<Long> list = dto.getRecordList();
        System.out.println("list = " + list);
        for(Long i : list) {
            ReserveRecord record = reserveRepository.findBySiteIdAndId(dto.getSiteId(), i);
            System.out.println("record = " + record);
            record.setStatus(dto.getStatusId());
            reserveRepository.save(record);
        }
    }

    @Transactional
    public void changeMRegularStatus(ChangeMRequestDto dto) {
        List<Long> list = dto.getRecordList();
        System.out.println("list = " + list);
        System.out.println("dto.getStatusId() = " + dto.getStatusId());
        for(Long i: list) {
            List<ReserveRecord> recordList = reserveRepository.findBySiteIdAndRegularId(dto.getSiteId(), i);
            for (ReserveRecord record : recordList) {
                System.out.println("record = " + record);
                record.setStatus(dto.getStatusId());
                reserveRepository.save(record);
            }
        }
    }

    @Transactional
    public void deleteReserve(DeleteReserveDto dto) {
        reserveRepository.deleteReserveRecordBySiteIdAndId(dto.getSiteId(), dto.getRecordId());
    }

    @Transactional
    public void deleteRegularReserve(DeleteReserveDto dto) {
        reserveRepository.deleteReserveRecordBySiteIdAndRegularId(dto.getSiteId(), dto.getRecordId());
    }

//일회대여
    @Transactional
    public List<ReserveDto> getMyReservations(Long savedUserInfoId) {
        List<ReserveRecord> reserves = reserveRepository.findAllBySavedUserInfoId(savedUserInfoId);
        List<ReserveDto> reserveList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for(ReserveRecord reserve : reserves) {
            String deadline = "기한 만료";
            String status = "대기";
            if(reserve.getDate().isAfter(now)) {
                deadline = "만료되지 않음";
            }
            if(reserve.getStatus() == 2) {
                status = "승인";
            } else if(reserve.getStatus() == 3) {
                status = "거절";
            }
            ReserveDto reserveDto = new ReserveDto(reserve.getId(), reserve.getGroupName(), reserve.getPurpose(), reserve.getReservation(), reserve.getContact(), status, reserve.isRegular(), reserve.getRegularId(), reserve.getAnswer1(), reserve.getAnswer2(), reserve.getRoom().getName(), reserve.getRoom().getImage(), reserve.getDate(), reserve.getRegDate(), reserve.getReserveTime(), deadline, reserve.getSavedUserInfo().getUser().getName());
            reserveList.add(reserveDto);
        }
        return reserveList;
    }

    @Transactional
    public void deleteMReserve(DeleteMultiReserveDto dto) {
        List<Long> list = dto.getRecordList();
        for(Long id : list) {
            reserveRepository.deleteReserveRecordBySiteIdAndId(dto.getSiteId(), id);
        }
    }



    @Transactional
    public void deleteMultiRegular(DeleteMultiReserveDto dto) {
        List<Long> list = dto.getRecordList();
        for(Long id : list) {
            reserveRepository.deleteReserveRecordBySiteIdAndRegularId(dto.getSiteId(),id);
        }
    }

//    정기대여
@Transactional
public List<RegularReservationHistoryDto> getMyRegularReservations(Long savedUserInfoId) {
    List<ReserveRecord> reserves = reserveRepository.findAllRegularBySavedUserInfoId(savedUserInfoId);
    int tmpRegularId = -1;
    int loopTimes = 0;
    List<ReserveRecord> temp = new ArrayList<>();
    List<List<ReserveRecord>> reservesGroupBy = new ArrayList<>();
    List<RegularReservationHistoryDto> regularReserves = new ArrayList<>();

//    reserveid가 같은 것끼리 list를 만들고 그걸 다시 list에 담는다.
    for(ReserveRecord reserveRecord : reserves) {
//        System.out.println("regularId: " + reserveRecord.getRegularId() + " -name: " + reserveRecord.getReservation() + " -date: " + reserveRecord.getDate());
        loopTimes++;
//        마지막 element이면, reservesGroupBy에 담고 종료
        if(loopTimes == reserves.size()) {
            temp.add(reserveRecord);
            reservesGroupBy.add(temp);
            break;
        }
//      regularId의 값이 바뀌면
        if(tmpRegularId != reserveRecord.getRegularId().intValue()) {
//            처음이 아닌 경우에는 reservesGroupBy에 추가
            if(loopTimes != 1) {
                reservesGroupBy.add(temp);
            }
            tmpRegularId = reserveRecord.getRegularId().intValue();
            temp = new ArrayList<>();
            temp.add(reserveRecord);
        } else {
            temp.add(reserveRecord);
        }
    }

    for(List<ReserveRecord> reserveList : reservesGroupBy) {
        LocalDate startDate = reserveList.get(0).getDate();
        LocalDate endDate = reserveList.get(reserveList.size() - 1).getDate();
        ReserveRecord tmp = reserveList.get(0);
        String status = "";
        if(tmp.getStatus() == 1) status = "대기";
        else if(tmp.getStatus() == 2) status = "승인";
        else status = "거절";

        LocalDate now = LocalDate.now();
//        String deadline = "마감됨";
//        if(tmp.getDate().isAfter(now)) {
//            deadline = "마감되지 않음";
//        }

        RegularReservationHistoryDto regularReservationHistoryDto = new RegularReservationHistoryDto(tmp, status,  now, startDate, endDate);
        regularReserves.add(regularReservationHistoryDto);
    }

    return regularReserves;
}

    @Transactional
    public List<RegularResponseDto> getRegularReservations(Long siteId) {
//        List<ReserveRecord> reserves = reserveRepository.findAllRegularBySavedUserInfoId(savedUserInfoId);
        String question1 = siteRepository.findQuestion1BySiteId(siteId);
        String question2 = siteRepository.findQuestion2BySiteId(siteId);
        List<ReserveRecord> reserves = reserveRepository.findAllRegularBySiteId(siteId);
        int tmpRegularId = -1;
        int loopTimes = 0;
        List<ReserveRecord> temp = new ArrayList<>();
        List<List<ReserveRecord>> reservesGroupBy = new ArrayList<>();
        List<RegularResponseDto> regularReserves = new ArrayList<>();

//    reserveid가 같은 것끼리 list를 만들고 그걸 다시 list에 담는다.
        for(ReserveRecord reserveRecord : reserves) {
//        System.out.println("regularId: " + reserveRecord.getRegularId() + " -name: " + reserveRecord.getReservation() + " -date: " + reserveRecord.getDate());
            loopTimes++;
//        마지막 element이면, reservesGroupBy에 담고 종료
            if(loopTimes == reserves.size()) {
                temp.add(reserveRecord);
                reservesGroupBy.add(temp);
                break;
            }
//      regularId의 값이 바뀌면
            if(tmpRegularId != reserveRecord.getRegularId().intValue()) {
//            처음이 아닌 경우에는 reservesGroupBy에 추가
                if(loopTimes != 1) {
                    reservesGroupBy.add(temp);
                }
                tmpRegularId = reserveRecord.getRegularId().intValue();
                temp = new ArrayList<>();
                temp.add(reserveRecord);
            } else {
                temp.add(reserveRecord);
            }
        }

        for(List<ReserveRecord> reserveList : reservesGroupBy) {
            LocalDate startDate = reserveList.get(0).getDate();
            LocalDate endDate = reserveList.get(reserveList.size() - 1).getDate();
            ReserveRecord tmp = reserveList.get(0);
            String status = "";
            if(tmp.getStatus() == 1) status = "대기";
            else if(tmp.getStatus() == 2) status = "승인";
            else status = "거절";

            LocalDate now = LocalDate.now();
//        String deadline = "마감됨";
//        if(tmp.getDate().isAfter(now)) {
//            deadline = "마감되지 않음";
//        }


            RegularResponseDto dto = RegularResponseDto.of(tmp, status,  now, startDate, endDate, question1, question2);
            regularReserves.add(dto);
        }

        return regularReserves;
    }


    //    개별 예약
    @Transactional
    public List<ReserveDto> getEachReservations(Long regularId) {
        List<ReserveRecord> reserves = reserveRepository.findAllByRegularId(regularId);
        List<ReserveDto> reserveList = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for(ReserveRecord reserve : reserves) {
            String deadline = "기한 만료";
            String status = "대기";
            if(reserve.getDate().isAfter(now)) {
                deadline = "만료되지 않음";
            }
            if(reserve.getStatus() == 2) {
                status = "승인";
            } else if(reserve.getStatus() == 3) {
                status = "거절";
            }
            ReserveDto reserveDto = new ReserveDto(reserve.getId(), reserve.getGroupName(), reserve.getPurpose(), reserve.getReservation(), reserve.getContact(), status, reserve.isRegular(), reserve.getRegularId(), reserve.getAnswer1(), reserve.getAnswer2(), reserve.getRoom().getName(), reserve.getRoom().getImage(), reserve.getDate(), reserve.getRegDate(), reserve.getReserveTime(), deadline, reserve.getSavedUserInfo().getUser().getName());
            reserveList.add(reserveDto);
        }
        return reserveList;
//        return reserves.stream()
//            .map(ReserveDto::new)
//            .collect(Collectors.toList());
    }

//일회대여 더보기
    @Transactional
    public ReserveDetailDto findOneReservationDetail(Long reservationId) {

        ReserveRecord reserve = reserveRepository.findById(reservationId).orElseThrow(ReserveRecordNotFoundException::new);

        return reserve.toDetailDto();
    }

//정기대여 더보기
    @Transactional
    public ReserveDetailDto findRegularReservationDetail(Long regularId) {
        ReserveRecord reserve = reserveRepository.findTop1ByRegularId(regularId);
        return reserve.toDetailDto();
    }

// 예약 삭제
    @Transactional
    public Long delete(Long reservationId) {
        reserveRepository.deleteById(reservationId);
        return reservationId;
    }

//    정기대여 예약 삭제
    @Transactional
    public void deleteAllByRegular(Long regularId) {
        reserveRepository.deleteAllByRegularId(regularId);
    }



    @Transactional(readOnly = true)
    public List<ReserveCalResponse> findAllBySiteId(Long siteId) {
        List<ReserveCalResponse> ret = reserveRepository.findAllReserveBySiteId(siteId).stream().map(ReserveCalResponse::from).collect(Collectors.toList());
        return ret;
    }

}
