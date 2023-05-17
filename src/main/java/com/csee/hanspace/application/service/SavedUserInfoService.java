package com.csee.hanspace.application.service;

import com.csee.hanspace.application.dto.*;
import com.csee.hanspace.domain.entity.ReserveRecord;
import com.csee.hanspace.domain.entity.SavedUserInfo;
import com.csee.hanspace.domain.entity.Site;
import com.csee.hanspace.domain.entity.User;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.SavedUserInfoRepository;
import com.csee.hanspace.domain.repository.SiteRepository;
import com.csee.hanspace.domain.repository.UserRepository;
import com.csee.hanspace.presentation.request.SavedUserInfoEditRequest;
import com.csee.hanspace.presentation.request.SavedUserInfoRequest;
import com.csee.hanspace.presentation.response.SiteUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavedUserInfoService {
    private final SavedUserInfoRepository savedUserInfoRepository;
    private final UserRepository userRepository;
    private final SiteRepository siteRepository;
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
    public int changeUserAuth(ChangeAuthDto dto){
        System.out.println("dto = " + dto);
        SavedUserInfo userInfo = savedUserInfoRepository.findSavedUserInfoBySiteIdAndUserId(dto.getSiteId(), dto.getUserId());
        System.out.println("userInfo = " + userInfo);
        userInfo.setAuthority(dto.getAuth());
        SavedUserInfo editUserInfo = savedUserInfoRepository.save(userInfo);
        return editUserInfo.getAuthority();
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
        System.out.println("u_id! = " + u_id);
        System.out.println(reserveRepository.deleteUser(dto.getSiteId(), u_id));
        Long id = savedUserInfoRepository.deleteSavedUserInfoBySiteIdAndUserId(dto.getSiteId(), u_id);
        System.out.println("id = " + id);
        return id;
    }

    @Transactional
    public void deleteMUser(DeleteMUserDto dto){
        for(Long id : dto.getUserList()) {
            System.out.println("id = " + id);
            Long u_id = savedUserInfoRepository.findUserId(id, dto.getSiteId());
            System.out.println("u_id = " + u_id);
            reserveRepository.deleteUser(dto.getSiteId(), u_id);
            savedUserInfoRepository.deleteSavedUserInfoBySiteIdAndUserId(dto.getSiteId(), u_id);
        }

    }

    @Transactional
    public SiteUserResponse findSiteUserInfo(SavedUserInfoRequest request) throws NonUniqueResultException {
        System.out.println(request.getSiteId());
        System.out.println(request.getUserId());
        Integer check = savedUserInfoRepository.checkInfo(request.getUserId(), request.getSiteId());
        SavedUserInfo savedUserInfo = null;
        if (check == 0){
            User user = userRepository.findById(request.getUserId()).get();
            Site site = siteRepository.findById(request.getSiteId()).get();
            savedUserInfo = savedUserInfoRepository.save(SavedUserInfo.from(user, site));
        }
        else {
            savedUserInfo = savedUserInfoRepository.findSiteUserInfo(request.getUserId(), request.getSiteId());
        }
        return SiteUserResponse.from(savedUserInfo);
    }

    @Transactional
    public SiteUserResponse editSavedUserInfo(Long id, SavedUserInfoEditRequest request) throws Throwable {
        SavedUserInfo savedUserInfo = savedUserInfoRepository.findById(id).orElseThrow(()-> {
                    new NoSuchElementException("No such info found");
                    return null;
                }
        );
        SiteUserResponse ret = null;
        if(savedUserInfo != null){
            savedUserInfo.update(request);
            ret = SiteUserResponse.from(savedUserInfo);
        }
        return ret;
    }

    @Transactional
    public User findCreator(Long sid) {
        SavedUserInfo savedUserInfo =  savedUserInfoRepository.findCreator(sid);
        return savedUserInfo.getUser();
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

    //    사이트 만들 때, userId를 받아서 savedUserInfo 생성
    @Transactional
    public CreateSavedDto createSavedUserInfo(Long userId, Long siteId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("no such user"));
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("no such site"));
        SavedUserInfo newSavedUserInfo = new SavedUserInfo(user, site);
        SavedUserInfo savedUserInfo = savedUserInfoRepository.save(newSavedUserInfo);
        return savedUserInfo.toCreateDto();
    }

}
