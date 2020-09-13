

CREATE DATABASE magic_user;
USE magic_user;

CREATE TABLE IF NOT EXISTS `t_user`(
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(64) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `mobile` VARCHAR(20),
  `email` VARCHAR(64) ,
  `created_time` DATETIME NOT NULL,
  `login_time` DATETIME,
  `last_login_time` DATETIME,
  `count` INT UNSIGNED COMMENT 'Login Times',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`user_name`)
)engine=innodb default charset=utf8mb4;


CREATE TABLE IF NOT EXISTS `t_role`(
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(64) NOT NULL,
  `description` VARCHAR(200) ,
  `created_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`role_name`)
)engine=innodb default charset=utf8mb4;


CREATE TABLE IF NOT EXISTS `t_right`(
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `right_name` VARCHAR(64) NOT NULL,
  `description` VARCHAR(200) ,
  `created_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`right_name`)
)engine=innodb default charset=utf8mb4;


CREATE TABLE IF NOT EXISTS `t_role_right_relation`(
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` INT UNSIGNED NOT NULL,
  `right_id` INT UNSIGNED NOT NULL,
  `right_type` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
)engine=innodb default charset=utf8mb4;


CREATE TABLE IF NOT EXISTS `t_user_role_relation`(
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `role_id` INT UNSIGNED NOT NULL,
  `right_type` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`)
)engine=innodb default charset=utf8mb4;


CREATE TABLE IF NOT EXISTS `t_operation_record`(
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `operation_type` TINYINT UNSIGNED NOT NULL,
  `operation_user` INT UNSIGNED NOT NULL,
  `operation_time` DATETIME NOT NULL,
  `content` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`)
)engine=innodb default charset=utf8mb4;



