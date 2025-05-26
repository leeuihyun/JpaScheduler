CREATE TABLE `user`
(
    `user_id`       bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '유저 식별자',
    `user_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `user_email`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created_at`    timestamp                                                     NOT NULL COMMENT '생성 시간/날짜',
    `updated_at`    timestamp                                                     NOT NULL COMMENT '수정 시간/날짜',
    `user_password` varchar(255) COLLATE utf8mb4_general_ci                       NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `unique_email` (`user_email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `schedule`
(
    `schedule_id`      bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '스케줄 식별자',
    `user_id`          bigint                                                        NOT NULL COMMENT '유저 식별자 외래키',
    `schedule_title`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `schedule_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `created_at`       timestamp                                                     NOT NULL COMMENT '생성 시간/날짜',
    `updated_at`       timestamp                                                     NOT NULL COMMENT '수정 시간/날짜',
    PRIMARY KEY (`schedule_id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `comment`
(
    `comment_id`      bigint    NOT NULL AUTO_INCREMENT COMMENT '댓글 식별자',
    `comment_content` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `user_id`         bigint    NOT NULL COMMENT '유저 외래 식별자',
    `schedule_id`     bigint    NOT NULL COMMENT '일정 외래 식별자',
    `created_at`      timestamp NOT NULL COMMENT '생성 시간/날짜',
    `updated_at`      timestamp NOT NULL COMMENT '수정 시간/날짜',
    PRIMARY KEY (`comment_id`),
    KEY `user_id` (`user_id`),
    KEY `schedule_id` (`schedule_id`),
    CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`schedule_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;