package com.scheduler.hyun.domain.dto.user;

import com.scheduler.hyun.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequest {

    @NotBlank
    @Size(min = 2, max = 10)
    private String userName;

    @Email
    @NotBlank
    private String userEmail;

    public User toEntity() {
        return User.builder()
            .userName(this.userName)
            .userEmail(this.userEmail)
            .build();
    }
}
