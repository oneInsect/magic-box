
CREATE DATABASE magic_service_mgt;
USE magic_service_mgt;

CREATE TABLE IF NOT EXISTS `t_docker_image` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `image_name` varchar(50) not null comment 'Image Name',
  `image_path` varchar(100) not null comment 'Image Path',
  `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
  `created_time` datetime not null comment 'create time',
  `modified_time` datetime not null comment 'update time',
  primary key (`id`),
  unique key `uk_path` (`image_path`)
)ENGINE = innodb DEFAULT CHARSET = utf8mb4;