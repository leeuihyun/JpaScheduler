package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.user.UserCreateRequest;
import com.scheduler.hyun.domain.dto.user.UserResponse;
import com.scheduler.hyun.domain.dto.user.UserUpdateRequest;

public interface UserService {

    Long createUser(UserCreateRequest userCreateRequest);

    UserResponse searchUser(Long userId) throws Exception;

    Long updateUser(UserUpdateRequest userUpdateRequest) throws Exception;

    Long deleteUser(Long userId) throws Exception;
}
