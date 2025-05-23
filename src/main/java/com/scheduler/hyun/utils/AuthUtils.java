package com.scheduler.hyun.utils;

import com.scheduler.hyun.domain.dto.user.model.UserIdModel;
import com.scheduler.hyun.domain.entity.User;
import com.scheduler.hyun.enums.ErrorEnum;
import com.scheduler.hyun.exception.ScheduleException;
import com.scheduler.hyun.repository.UserJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtils {

    private final UserJpaRepository userJpaRepository;

    public User authorizeUser(UserIdModel userIdModel, HttpServletRequest httpServletRequest) {
        Optional<User> optionalUser = userJpaRepository.findById(userIdModel.getUserId());

        if (optionalUser.isEmpty()) {
            throw new ScheduleException(ErrorEnum.NO_EXIST_USER);
        }

        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");

        if (userId == null || !userIdModel.getUserId().equals(userId)) {
            throw new ScheduleException(ErrorEnum.NO_AUTHORIZATION);
        }

        return optionalUser.get();
    }

    public User authorizeUser(Long userId, HttpServletRequest httpServletRequest) {
        Optional<User> optionalUser = userJpaRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ScheduleException(ErrorEnum.NO_EXIST_USER);
        }

        Long sessionUserId = (Long) httpServletRequest.getSession().getAttribute("userId");

        if (!userId.equals(sessionUserId)) {
            throw new ScheduleException(ErrorEnum.NO_AUTHORIZATION);
        }

        return optionalUser.get();
    }
}
