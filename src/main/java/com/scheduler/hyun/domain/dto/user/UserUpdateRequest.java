package com.scheduler.hyun.domain.dto.user;

import com.scheduler.hyun.domain.dto.user.model.UserIdModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequest implements UserIdModel {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(min = 1, max = 4)
    private String userName;

    @NotBlank
    @Email
    private String userEmail;
}
