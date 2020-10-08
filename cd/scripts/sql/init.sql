
CREATE DATABASE magic_service_mgt;
USE magic_service_mgt;

CREATE TABLE IF NOT EXISTS `t_docker_image` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `owner` INT UNSIGNED NOT NULL COMMENT 't_user.id',
  `image_name` varchar(50) not null comment 'Image Name',
  `image_path` varchar(100) not null comment 'Image Path',
  `is_base_images` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0',
  `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
  `created_time` datetime not null comment 'create time',
  `modified_time` datetime not null comment 'update time',
  primary key (`id`),
  unique key `uk_path` (`image_path`)
)ENGINE = innodb DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `t_services` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `owner` INT UNSIGNED NOT NULL COMMENT 't_user.id',
  `service_name` varchar(50) not null,
  `image_id` INT UNSIGNED NOT NULL COMMENT 't_docker_image.id',
  `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
  `created_time` datetime not null comment 'create time',
  `modified_time` datetime not null comment 'update time',
  primary key (`id`),
  unique key `uk_path` (`service_name`)
)ENGINE = innodb DEFAULT CHARSET = utf8mb4;