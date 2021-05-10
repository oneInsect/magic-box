drop database blog;
create database blog;
use blog;


CREATE TABLE IF NOT EXISTS `t_users` (
 `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
 `user_name` varchar(20) NOT NULL COMMENT '用户名',
 `user_password` varchar(100) NOT NULL COMMENT '用户密码',
 `user_email` varchar(30) NOT NULL COMMENT '用户邮箱',
 `user_profile_photo` varchar(255) COMMENT '用户头像',
 `created_time` datetime COMMENT '创建时间',
 `modified_time` datetime COMMENT '修改时间',
 `user_birthday` date DEFAULT NULL COMMENT '用户生日',
 `user_age` tinyint(4) DEFAULT NULL COMMENT '用户年龄',
 `user_telephone_number` int(11) COMMENT '用户手机号',
 `user_nickname` varchar(20) COMMENT '用户昵称',
 `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
 PRIMARY KEY (`user_id`),
 KEY `user_name` (`user_name`),
 KEY `user_nickname` (`user_nickname`),
 KEY `user_email` (`user_email`),
 KEY `user_telephone_number` (`user_telephone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_role`(
    `role_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(64) NOT NULL,
    `description` VARCHAR(200) ,
    `created_time` datetime COMMENT '创建时间',
    `modified_time` datetime COMMENT '修改时间',
    `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `uk_name` (`role_name`)
)engine=innodb default charset=utf8mb4;

CREATE TABLE IF NOT EXISTS `t_permission`(
    `permission_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `permission_name` VARCHAR(64) NOT NULL,
    `description` VARCHAR(200) ,
    `created_time` datetime COMMENT '创建时间',
    `modified_time` datetime COMMENT '修改时间',
    `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    PRIMARY KEY (`permission_id`),
    UNIQUE KEY `uk_name` (`permission_name`)
)engine=innodb default charset=utf8mb4;

CREATE TABLE IF NOT EXISTS `t_role_permission_relation`(
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` INT UNSIGNED NOT NULL,
  `permission_id` INT UNSIGNED NOT NULL,
  `created_time` datetime COMMENT '创建时间',
  `modified_time` datetime COMMENT '修改时间',
  `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
  PRIMARY KEY (`id`)
)engine=innodb default charset=utf8mb4;

CREATE TABLE IF NOT EXISTS `t_user_role_relation`(
 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
 `user_id` INT UNSIGNED NOT NULL,
 `role_id` INT UNSIGNED NOT NULL,
 `created_time` datetime COMMENT '创建时间',
 `modified_time` datetime COMMENT '修改时间',
 `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
 PRIMARY KEY (`id`)
)engine=innodb default charset=utf8mb4;

CREATE TABLE IF NOT EXISTS `t_operation_record`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `operation_type` TINYINT UNSIGNED NOT NULL,
    `operation_user` INT UNSIGNED NOT NULL,
    `operation_time` DATETIME NOT NULL,
    `content` VARCHAR(200) NOT NULL,
    `created_time` datetime COMMENT '创建时间',
    `modified_time` datetime COMMENT '修改时间',
    `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    PRIMARY KEY (`id`)
)engine=innodb default charset=utf8mb4;

# ----------- user and permission -----------

CREATE TABLE IF NOT EXISTS `t_articles` (
 `article_id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '博文ID',
 `user_id` bigint(20) NOT NULL COMMENT '发表用户ID',
 `article_title` text NOT NULL COMMENT '博文标题',
 `article_content` longtext NOT NULL COMMENT '博文内容',
 `article_views` bigint(20) NOT NULL COMMENT '浏览量',
 `article_comment_count` bigint(20) NOT NULL COMMENT '评论总数',
 `article_like_count` bigint(20) NOT NULL,
 `created_time` datetime COMMENT '创建时间',
 `modified_time` datetime COMMENT '修改时间',
 `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
 PRIMARY KEY (`article_id`),
 KEY `user_id` (`user_id`),
 CONSTRAINT `t_articles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_comments` (
 `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
 `user_id` bigint(20) NOT NULL COMMENT '发表用户ID',
 `article_id` bigint(20) NOT NULL COMMENT '评论博文ID',
 `comment_like_count` bigint(20) NOT NULL COMMENT '点赞数',
 `created_time` datetime COMMENT '创建时间',
 `modified_time` datetime COMMENT '修改时间',
 `comment_content` text NOT NULL COMMENT '评论内容',
 `parent_comment_id` bigint(20) NOT NULL COMMENT '父评论ID',
 `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
 PRIMARY KEY (`comment_id`),
 KEY `article_id` (`article_id`),
 KEY `comment_date` (`created_time`),
 KEY `parent_comment_id` (`parent_comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_labels` (
 `label_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
 `label_name` varchar(20) NOT NULL COMMENT '标签名称',
 `label_alias` varchar(15) NOT NULL COMMENT '标签别名',
 `label_description` text NOT NULL COMMENT '标签描述',
 `created_time` datetime COMMENT '创建时间',
 `modified_time` datetime COMMENT '修改时间',
 `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
 PRIMARY KEY (`label_id`),
 KEY `label_name` (`label_name`),
 KEY `label_alias` (`label_alias`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_set_artitle_label` (
 `article_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
 `label_id` bigint(20) NOT NULL,
 `created_time` datetime COMMENT '创建时间',
 `modified_time` datetime COMMENT '修改时间',
 `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
 PRIMARY KEY (`article_id`),
 KEY `label_id` (`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_set_artitle_sort` (
 `article_id` bigint(20) NOT NULL COMMENT '文章ID',
 `sort_id` bigint(20) NOT NULL COMMENT '分类ID',
 `created_time` datetime COMMENT '创建时间',
 `modified_time` datetime COMMENT '修改时间',
 `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
 PRIMARY KEY (`article_id`,`sort_id`),
 KEY `sort_id` (`sort_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_sorts` (
 `sort_id` bigint(20) NOT NULL COMMENT '分类ID',
 `sort_name` varchar(50) NOT NULL COMMENT '分类名称',
 `sort_alias` varchar(15) NOT NULL COMMENT '分类别名',
 `sort_description` text NOT NULL COMMENT '分类描述',
 `parent_sort_id` bigint(20) NOT NULL COMMENT '父分类ID',
 `created_time` datetime COMMENT '创建时间',
 `modified_time` datetime COMMENT '修改时间',
 `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
 PRIMARY KEY (`sort_id`),
 KEY `sort_name` (`sort_name`),
 KEY `sort_alias` (`sort_alias`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_user_follow` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识ID',
 `user_id` bigint(20) NOT NULL COMMENT '用户ID',
 `user_follow_id` bigint(20) NOT NULL COMMENT '被关注ID',
 `created_time` datetime COMMENT '创建时间',
 `modified_time` datetime COMMENT '修改时间',
 `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
 PRIMARY KEY (`id`),
 KEY `user_id` (`user_id`)

) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
