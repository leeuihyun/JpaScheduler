package com.scheduler.hyun.utils;

import com.scheduler.hyun.domain.dto.user.model.UserIdModel;
import com.scheduler.hyun.domain.entity.User;
import com.scheduler.hyun.repository.UserJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtils {

    private final UserJpaRepository userJpaRepository;

    public User authorizeUser(UserIdModel userIdModel, HttpServletRequest httpServletRequest)
        throws Exception {
        Optional<User> optionalUser = userJpaRepository.findById(userIdModel.getUserId());

        if (optionalUser.isEmpty()) {
            throw new Exception("존재하지 않는 유저입니다.");
        }

        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");

        if (userId == null || !userIdModel.getUserId().equals(userId)) {
            throw new Exception("본인의 정보만 수정할 수 있습니다.");
        }

        return optionalUser.get();
    }

    public User authorizeUser(Long userId, HttpServletRequest httpServletRequest)
        throws Exception {
        Optional<User> optionalUser = userJpaRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new Exception("존재하지 않는 유저입니다.");
        }

        Long sessionUserId = (Long) httpServletRequest.getSession().getAttribute("userId");

        if (!userId.equals(sessionUserId)) {
            throw new Exception("본인의 정보만 수정할 수 있습니다.");
        }

        return optionalUser.get();
    }
}
