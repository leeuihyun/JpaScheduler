package com.scheduler.hyun.controller;

import com.scheduler.hyun.domain.dto.user.UserCreateRequest;
import com.scheduler.hyun.domain.dto.user.UserIdResponse;
import com.scheduler.hyun.domain.dto.user.UserLoginRequest;
import com.scheduler.hyun.domain.dto.user.UserResponse;
import com.scheduler.hyun.domain.dto.user.UserUpdateRequest;
import com.scheduler.hyun.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserIdResponse> createUser(
        @RequestBody @Valid UserCreateRequest userCreateRequest) {

        Long userId = userService.createUser(userCreateRequest);
        return ResponseEntity.ok().body(new UserIdResponse(userId));
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<UserResponse> searchUser(
        @PathVariable @NotNull @Min(value = 0) Long userId) {

        UserResponse user = userService.searchUser(userId);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserIdResponse> updateUser(
        @RequestBody @Valid UserUpdateRequest userUpdateRequest,
        HttpServletRequest httpServletRequest) {

        Long userId = userService.updateUser(userUpdateRequest, httpServletRequest);
        return ResponseEntity.ok().body(new UserIdResponse(userId));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserIdResponse> deleteUser(
        @PathVariable @NotNull @Min(value = 0) Long userId,
        HttpServletRequest httpServletRequest) {

        Long deletedUserId = userService.deleteUser(userId, httpServletRequest);
        return ResponseEntity.ok().body(new UserIdResponse(deletedUserId));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> logIn(@RequestBody @Valid UserLoginRequest userLoginRequest,
        HttpServletRequest httpServletRequest) {

        UserResponse user = userService.logIn(userLoginRequest, httpServletRequest);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserIdResponse> logOut(HttpServletRequest httpServletRequest) {

        Long logoutUserId = userService.logOut(httpServletRequest);
        return ResponseEntity.ok().body(new UserIdResponse(logoutUserId));
    }
}
