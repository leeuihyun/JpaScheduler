package com.scheduler.hyun.service;

import com.scheduler.hyun.domain.dto.user.UserCreateRequest;
import com.scheduler.hyun.domain.dto.user.UserResponse;
import com.scheduler.hyun.domain.dto.user.UserUpdateRequest;
import com.scheduler.hyun.domain.entity.User;
import com.scheduler.hyun.repository.UserJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    public UserServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Long createUser(UserCreateRequest userCreateRequest) {
        UserResponse user = userJpaRepository.save(userCreateRequest.toEntity()).toUserDto();
        return user.getUserId();
    }

    @Override
    public UserResponse searchUser(Long userId) throws Exception {

        return userJpaRepository.findById(userId)
            .orElseThrow(() -> new Exception("존재하지 않는 유저입니다.")).toUserDto();
    }

    @Transactional
    @Override
    public Long updateUser(UserUpdateRequest userUpdateRequest) throws Exception {

        Optional<User> optionalUser = userJpaRepository.findById(userUpdateRequest.getUserId());

        if (optionalUser.isEmpty()) {
            throw new Exception("존재하지 않는 유저입니다.");
        } else {
            User user = optionalUser.get();
            user.updateUser(userUpdateRequest);
            return user.getUserId();
        }
    }

    @Transactional
    @Override
    public Long deleteUser(Long userId) throws Exception {

        Optional<User> optionalUser = userJpaRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new Exception("존재하지 않는 유저입니다.");
        } else {
            User user = optionalUser.get();
            userJpaRepository.delete(user);
            return user.getUserId();
        }
    }
}
