package com.csee.hanspace.presentation.controller;

import com.csee.hanspace.application.service.*;
import com.csee.hanspace.domain.entity.*;
import com.csee.hanspace.domain.repository.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/dummy")
public class TestController {
    UserRepository userRepository;
    SiteRepository siteRepository;
    ReserveRepository reserveRepository;

    RoomRepository roomRepository;
    SavedUserInfoRepository savedUserInfoRepository;
    TagRepository tagRepository;
    TimeRecordRepository timeRecordRepository;

    RoomTagRepository roomTagRepository;


    @Autowired
    UserService userService;

    @Autowired
    SiteService siteService;

    @Autowired
    ReserveService reserveService;

    @Autowired
    RoomService roomService;

    @Autowired
    SavedUserInfoService savedUserInfoService;

    @Autowired
    TagService tagService;




    @GetMapping("/all")
    public ResponseEntity<Void> initAll() {

        saveUser();
        saveSite();
        saveRoom();
        saveUserInfo();
        saveReserveRecord();
        saveTag();
        saveRoomTags();
        saveTimeRecord();

        return ResponseEntity.ok(null);
    }

    @GetMapping("/user")
    public ResponseEntity<Void> initUser() {
        saveUser();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/site")
    public ResponseEntity<Void> initSite() {
        saveSite();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/room")
    public ResponseEntity<Void> initRoom() {
        saveRoom();
        return ResponseEntity.ok(null);
    }
    @GetMapping("/userSaveInfo")
    public ResponseEntity<Void> initUserSaveInfo() {
        saveUserInfo();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/reserveRecord")
    public ResponseEntity<Void> initReserveRecord() {
        saveReserveRecord();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/tag")
    public ResponseEntity<Void> initTag() {
        saveTag();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/roomTag")
    public ResponseEntity<Void> initRoomTag() {
        saveRoomTags();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/timeRecord")
    public ResponseEntity<Void> initTimeRecord() {
        saveTimeRecord();
        return ResponseEntity.ok(null);
    }



    private void saveUser() {


//        User.builder().name("이인혁").email("21700559@handong.ac.kr").build());
        userService.save(User.builder().name("이인혁").email("21700559@handong.ac.kr").build());
        userService.save(User.builder().name("방제형").email("21700328@handong.ac.kr").build());
        userService.save(User.builder().name("홍성헌").email("lukehongg@handong.ac.kr").build());
        userService.save(User.builder().name("배주영").email("bae@handong.ac.kr").build());
    }

    private void saveSite() {
        siteService.save(Site.builder().name("한동 공간 대여 시스템").description("강의실 대여 시스템입니다.").logo("null").link("hbl").company("(주)홍방이").maxDate(30).maxTime(180).restriction(1).question1("관련 교수").question2(null).timeUnit(60).build());
        siteService.save(Site.builder().name("MT 공간 대여 시스템").description("MT 공간 대여 시스템입니다.").logo("null").link("mt").company("(주)홍방이").maxDate(20).maxTime(120).restriction(2).question1(null).question2(null).timeUnit(30).build());
        siteService.save(Site.builder().name("한동 팀모임 공간 대여 시스템").description("한동대학교 학생을 위한 팀모임 공간 대여 시스템입니다.").logo("null").link("mt").company("(주)홍방이").maxDate(20).maxTime(120).restriction(3).question1("팀교수님").question2("팀장이름").timeUnit(30).build());
    }

    private void saveRoom() {
        roomService.save(Room.builder().image(null).name("NTH 219").capacity(40).description("빔프로젝트 있음. 전자 칠판 있음. 깨끗하게 사용해주셔야 이후에 예약 가능합니다.").available(true).startTime(9).endTime(21).reserveCnt(10).site(siteService.findById(1L)).build());
        roomService.save(Room.builder().image(null).name("NTH 220").capacity(40).description("빔프로젝트 있음.").available(true).startTime(540).endTime(1260).reserveCnt(20).site(siteService.findById(1L)).build());
        roomService.save(Room.builder().image(null).name("NTH 221").capacity(30).description("빔프로젝트 있음.").available(true).startTime(540).endTime(1260).reserveCnt(20).site(siteService.findById(1L)).build());
        roomService.save(Room.builder().image(null).name("NTH 222").capacity(20).description("").available(true).startTime(540).endTime(1260).reserveCnt(20).site(siteService.findById(1L)).build());
        roomService.save(Room.builder().image(null).name("NTH 223").capacity(10).description("").available(true).startTime(480).endTime(1320).reserveCnt(20).site(siteService.findById(1L)).build());
        roomService.save(Room.builder().image(null).name("NTH 224").capacity(50).description("").available(false).startTime(480).endTime(1320).reserveCnt(20).site(siteService.findById(1L)).build());
    }
    private void saveUserInfo() {
        savedUserInfoService.save(SavedUserInfo.builder().groupName("홍방이팀").purpose("캡스톤").reservation("이인혁").contact("01034321112").authority(3).approve(true).site(siteService.findById(1L)).user(userService.findByEmail("21700559@handong.ac.kr")).build());
        savedUserInfoService.save(SavedUserInfo.builder().groupName("김광교수님팀").purpose("팀모임").reservation("방제형").contact("01038483212").authority(1).approve(true).site(siteService.findById(1L)).user(userService.findByEmail("21700328@handong.ac.kr")).build());
        savedUserInfoService.save(SavedUserInfo.builder().groupName("장소연교수님팀").purpose("팀모임").reservation("홍성헌").contact("01038433212").authority(2).approve(true).site(siteService.findById(1L)).user(userService.findByEmail("lukehongg@handong.ac.kr")).build());
        savedUserInfoService.save(SavedUserInfo.builder().groupName("김기석교수님팀").purpose("팀모임").reservation("이인혁").contact("01038453212").authority(3).approve(true).site(siteService.findById(1L)).user(userService.findByEmail("21700559@handong.ac.kr")).build());
        savedUserInfoService.save(SavedUserInfo.builder().groupName("테스트").purpose("팀모임").reservation("배주영").contact("01011453212").authority(3).approve(true).site(siteService.findById(1L)).user(userService.findByEmail("21700559@handong.ac.kr")).build());
        savedUserInfoService.save(SavedUserInfo.builder().groupName("테스트").purpose("팀모임").reservation("배주영").contact("01011453212").authority(3).approve(true).site(siteService.findById(1L)).user(userService.findByEmail("21700559@handong.ac.kr")).build());

    }

    private void saveReserveRecord() {
        reserveService.saveReserveRecord(ReserveRecord.builder().groupName("홍방이팀").purpose("캡스톤").reservation("이인혁").contact("01034321112").status(2).regular(false).regularId(0L).answer1("김광, 장소연").answer2(null).site(siteService.findById(1L)).room(roomService.findByName("NTH 219")).savedUserInfo(savedUserInfoService.findById(1L)).weekdays(null).reserveTime("13:00 ~ 15:00").build());
        reserveService.saveReserveRecord(ReserveRecord.builder().groupName("CRA").purpose("동아리모임").reservation("안병웅").contact("01034351114").status(1).regular(false).regularId(0L).answer1("조성배").answer2(null).site(siteService.findById(1L)).room(roomService.findByName("NTH 220")).savedUserInfo(savedUserInfoService.findById(2L)).weekdays(null).reserveTime("12:00 ~ 13:00 , 15:00 ~ 16:00").build());
        reserveService.saveReserveRecord(ReserveRecord.builder().groupName("GHOST").purpose("동아리모임").reservation("홍성헌").contact("01034352414").status(2).regular(true).regularId(1L).answer1("안민규").answer2(null).site(siteService.findById(1L)).room(roomService.findByName("NTH 221")).savedUserInfo(savedUserInfoService.findById(3L)).weekdays("월, 목").reserveTime("12:00 ~ 14:00").build());
        reserveService.saveReserveRecord(ReserveRecord.builder().groupName("슬기짜기").purpose("동아리모임").reservation("송다빈").contact("01034352444").status(3).regular(true).regularId(2L).answer1("홍신").answer2(null).site(siteService.findById(1L)).room(roomService.findByName("NTH 223")).savedUserInfo(savedUserInfoService.findById(4L)).weekdays("화, 금").reserveTime("14:00 ~ 15:00").build());
    }

    private void saveTag() {
        tagService.saveTag(Tag.builder().name("세미나실").site(siteService.findById(1L)).build());
        tagService.saveTag(Tag.builder().name("실습실").site(siteService.findById(1L)).build());
        tagService.saveTag(Tag.builder().name("창문 많음").site(siteService.findById(1L)).build());
        tagService.saveTag(Tag.builder().name("햇볕이 잘듦").site(siteService.findById(1L)).build());
    }

    private void saveRoomTags() {
        tagService.saveRoomTags(RoomTags.builder().tag(tagService.findById(1L)).room(roomService.findById(1L)).build());
        tagService.saveRoomTags(RoomTags.builder().tag(tagService.findById(2L)).room(roomService.findById(1L)).build());
        tagService.saveRoomTags(RoomTags.builder().tag(tagService.findById(3L)).room(roomService.findById(1L)).build());
        tagService.saveRoomTags(RoomTags.builder().tag(tagService.findById(4L)).room(roomService.findById(2L)).build());
        tagService.saveRoomTags(RoomTags.builder().tag(tagService.findById(4L)).room(roomService.findById(3L)).build());

    }

    private void saveTimeRecord() {
        reserveService.saveTimeRecord(TimeRecord.builder().startDate(LocalDate.of(2023, Month.JUNE, 27)).startTime(660).endTime(720).room(roomService.findById(1L)).reserveRecord(reserveService.findById(1L)).build());
        reserveService.saveTimeRecord(TimeRecord.builder().startDate(LocalDate.of(2023, Month.JUNE, 28)).startTime(540).endTime(600).room(roomService.findById(1L)).reserveRecord(reserveService.findById(1L)).build());
        reserveService.saveTimeRecord(TimeRecord.builder().startDate(LocalDate.of(2023, Month.JUNE, 28)).startTime(720).endTime(840).room(roomService.findById(2L)).reserveRecord(reserveService.findById(2L)).build());
        reserveService.saveTimeRecord(TimeRecord.builder().startDate(LocalDate.of(2023, Month.JUNE, 28)).startTime(720).endTime(780).room(roomService.findById(2L)).reserveRecord(reserveService.findById(3L)).build());
        reserveService.saveTimeRecord(TimeRecord.builder().startDate(LocalDate.of(2023, Month.JUNE, 29)).startTime(720).endTime(780).room(roomService.findById(2L)).reserveRecord(reserveService.findById(3L)).build());
        reserveService.saveTimeRecord(TimeRecord.builder().startDate(LocalDate.of(2023, Month.JUNE, 30)).startTime(720).endTime(780).room(roomService.findById(2L)).reserveRecord(reserveService.findById(3L)).build());
        reserveService.saveTimeRecord(TimeRecord.builder().startDate(LocalDate.of(2023, Month.JULY, 1)).startTime(720).endTime(840).room(roomService.findById(3L)).reserveRecord(reserveService.findById(4L)).build());
        reserveService.saveTimeRecord(TimeRecord.builder().startDate(LocalDate.of(2023, Month.JULY, 2)).startTime(660).endTime(720).room(roomService.findById(4L)).reserveRecord(reserveService.findById(2L)).build());
    }




}
