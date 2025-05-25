package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.user.UserCreateRequest;
import com.scheduler.hyun.domain.dto.user.UserLoginRequest;
import com.scheduler.hyun.domain.dto.user.UserResponse;
import com.scheduler.hyun.domain.dto.user.UserUpdateRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    Long createUser(UserCreateRequest userCreateRequest);

    UserResponse searchUser(Long userId);

    Long updateUser(UserUpdateRequest userUpdateRequest, HttpServletRequest httpServletRequest);

    Long deleteUser(Long userId, HttpServletRequest httpServletRequest);

    UserResponse logIn(UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest);

    Long logOut(HttpServletRequest httpServletRequest);
}
