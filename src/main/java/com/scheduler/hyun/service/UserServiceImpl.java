package com.scheduler.hyun.service;

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

    private final UserJpaRepository userJpaRepository;
    private final AuthUtils authUtils;

    @Override
    public Long createUser(UserCreateRequest userCreateRequest) {

        UserResponse user = userJpaRepository.save(userCreateRequest.toEntity()).toUserDto();

        return user.getUserId();
    }

    @Override
    public UserResponse searchUser(Long userId) {

        return userJpaRepository.findById(userId)
            .orElseThrow(() -> new ScheduleException(ErrorEnum.NO_EXIST_USER)).toUserDto();
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

        if (!userLoginRequest.getUserPassword().equals(user.getUserPassword())) {
            throw new ScheduleException(ErrorEnum.PASSWORD_MISMATCH);
        }

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("userId", user.getUserId());
        session.setMaxInactiveInterval(1800);

        return user.toUserDto();
    }

    @Override
    public Long logOut(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession(false);
        Long userId = (Long) session.getAttribute("userId");

        session.invalidate();

        return userId;
    }
}
