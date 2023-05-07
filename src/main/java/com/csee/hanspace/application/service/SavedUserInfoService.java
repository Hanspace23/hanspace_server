package com.csee.hanspace.application.service;

import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.SavedUserInfo;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.SavedUserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavedUserInfoService {
    private final SavedUserInfoRepository savedUserInfoRepository;
    private final ReserveRepository reserveRepository;

    @Transactional
    public SavedUserInfo findById(Long userId) {
        return savedUserInfoRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("no savedInfo data"));
    }

    @Transactional
    public void save(SavedUserInfo savedUserInfo){
        savedUserInfoRepository.save(savedUserInfo);
    }

    @Transactional
    public List<UserListDto> readUserList(UserListDto dto){
        List<SavedUserInfo> list = savedUserInfoRepository.findSavedUserInfoBySiteId(dto.getSiteId());
        List<UserListDto> dtoList = list.stream().map(UserListDto::of).collect(Collectors.toList());
        return dtoList;
    }

    @Transactional
    public int changeUserStatus(ChangeStatusDto dto){
        SavedUserInfo userInfo = savedUserInfoRepository.findSavedUserInfoBySiteIdAndId(dto.getSiteId(), dto.getUserId());
        userInfo.setStatus(dto.getStatusId());
        SavedUserInfo editUserInfo = savedUserInfoRepository.save(userInfo);
        return editUserInfo.getStatus();
    }

    @Transactional
    public void changeMUserStatus(ChangeMStatusDto dto){
        for(Long i : dto.getUserList()) {
            SavedUserInfo userInfo = savedUserInfoRepository.findSavedUserInfoBySiteIdAndId(dto.getSiteId(), i);
            System.out.println("userInfo = " + userInfo);
            userInfo.setStatus(dto.getStatusId());
            savedUserInfoRepository.save(userInfo);
        }
    }

    @Modifying
    @Transactional
    public Long deleteUser(DeleteUserDto dto){
        Long u_id = savedUserInfoRepository.findUserId(dto.getUserId(), dto.getSiteId());
        reserveRepository.deleteUser(dto.getSiteId(), u_id);
        Long id = savedUserInfoRepository.deleteSavedUserInfoBySiteIdAndUserId(dto.getSiteId(), dto.getUserId());
        return id;
    }

    @Transactional
    public void deleteMUser(DeleteMUserDto dto){
        for(Long id : dto.getUserList()) {
            System.out.println("id = " + id);
            Long u_id = savedUserInfoRepository.findUserId(id, dto.getSiteId());
            System.out.println("u_id = " + u_id);
            reserveRepository.deleteUser(dto.getSiteId(), u_id);
            savedUserInfoRepository.deleteSavedUserInfoBySiteIdAndUserId(dto.getSiteId(), id);
        }

    }

//    이용중인 사이트 리스트
    @Transactional
    public List<SiteDto> getSubscribedSites(Long userId) {
        List<SavedUserInfo> savedUserInfoList = savedUserInfoRepository.findAllByUserIdAndUser(userId);
        return savedUserInfoList.stream().map(SiteDto::new).collect(Collectors.toList());
    }

    //    관리중인 사이트 리스트
    @Transactional
    public List<SiteDto> getManagingSites(Long userId) {
        List<SavedUserInfo> savedUserInfoList = savedUserInfoRepository.findAllByUserIdAndAdmin(userId);
        return savedUserInfoList.stream().map(SiteDto::new).collect(Collectors.toList());
    }

    //    운영중인 사이트 리스트
    @Transactional
    public List<SiteDto> getCreateSites(Long userId) {
        List<SavedUserInfo> savedUserInfoList = savedUserInfoRepository.findAllByUserIdAndCreator(userId);
        return savedUserInfoList.stream().map(SiteDto::new).collect(Collectors.toList());
    }

}
