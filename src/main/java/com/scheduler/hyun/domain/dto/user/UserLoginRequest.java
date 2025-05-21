package com.scheduler.hyun.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequest {

    @Email
    @NotBlank
    private String userEmail;

    @NotBlank
    private String userPassword;
}
