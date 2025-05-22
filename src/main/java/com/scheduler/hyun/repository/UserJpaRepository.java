package com.scheduler.hyun.repository;

import com.scheduler.hyun.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmail(@Email @NotBlank String userEmail);
}
