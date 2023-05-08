package com.csee.hanspace.application.service;

import com.csee.hanspace.application.dto.UserListDto;
import com.csee.hanspace.domain.entity.SavedUserInfo;
import com.csee.hanspace.domain.entity.User;
import com.csee.hanspace.domain.repository.ReserveRepository;
import com.csee.hanspace.domain.repository.UserRepository;
import com.csee.hanspace.presentation.request.UserEmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }


//    @Transactional
//    public User signup(UserEmailRequest request) {
//        User ret = userRepository.save(User.from(request));
//        return ret;
//    }

    @Transactional
    public User findElseSignUp(UserEmailRequest request) {
        User ret = userRepository.findByEmail(request.getEmail());
        if(ret == null){
            ret = userRepository.save(User.from(request));
        }
        return ret;
    }
}
