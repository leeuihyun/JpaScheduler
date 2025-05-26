package com.scheduler.hyun.service;

import com.scheduler.hyun.config.PasswordEncoder;
import com.scheduler.hyun.domain.dto.user.UserCreateRequest;
import com.scheduler.hyun.domain.dto.user.UserLoginRequest;
import com.scheduler.hyun.domain.dto.user.UserResponse;
import com.scheduler.hyun.domain.dto.user.UserUpdateRequest;
import com.scheduler.hyun.domain.entity.User;
import com.scheduler.hyun.enums.ErrorEnum;
import com.scheduler.hyun.exception.ScheduleException;
import com.scheduler.hyun.repository.UserJpaRepository;
import com.scheduler.hyun.utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserJpaRepository userJpaRepository;
    private final AuthUtils authUtils;

    @Override
    public Long createUser(UserCreateRequest userCreateRequest) {

        User userEntity = User.builder()
            .userName(userCreateRequest.getUserName())
            .userEmail(userCreateRequest.getUserEmail())
            .userPassword(passwordEncoder.encode(userCreateRequest.getUserPassword()))
            .build();

        return userJpaRepository.save(userEntity).getUserId();
    }

    @Override
    public UserResponse searchUser(Long userId) {

        User user = userJpaRepository.findById(userId)
            .orElseThrow(() -> new ScheduleException(ErrorEnum.NO_EXIST_USER));

        return UserResponse.builder()
            .userId(user.getUserId())
            .userName(user.getUserName())
            .userEmail(user.getUserEmail())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }

    @Transactional
    @Override
    public Long updateUser(UserUpdateRequest userUpdateRequest,
        HttpServletRequest httpServletRequest) {

        User user = authUtils.authorizeUser(userUpdateRequest, httpServletRequest);
        user.updateUser(userUpdateRequest);

        return user.getUserId();
    }

    @Transactional
    @Override
    public Long deleteUser(Long userId, HttpServletRequest httpServletRequest) {

        User user = authUtils.authorizeUser(userId, httpServletRequest);
        userJpaRepository.delete(user);

        return user.getUserId();
    }

    @Override
    public UserResponse logIn(UserLoginRequest userLoginRequest,
        HttpServletRequest httpServletRequest) {

        User user = userJpaRepository.findByUserEmail(userLoginRequest.getUserEmail())
            .orElseThrow(() -> new ScheduleException(ErrorEnum.NO_EXIST_USER));

        if (!passwordEncoder.matches(userLoginRequest.getUserPassword(), user.getUserPassword())) {
            throw new ScheduleException(ErrorEnum.PASSWORD_MISMATCH);
        }

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("userId", user.getUserId());
        session.setMaxInactiveInterval(1800);

        return UserResponse.builder()
            .userId(user.getUserId())
            .userName(user.getUserName())
            .userEmail(user.getUserEmail())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }

    @Override
    public Long logOut(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession(false);
        Long userId = (Long) session.getAttribute("userId");

        session.invalidate();

        return userId;
    }
}
