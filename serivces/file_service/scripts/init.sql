

use file_service;

create table if not exists `files` (
    `id` char(19) not null comment 'File ID',
    `cateId` char(19) not null comment 'Category ID',
    `name` varchar(50) not null comment 'File Name',
    `path` varchar(100) not null comment 'File Path',
    `file_desc` varchar(200) not null comment 'File Describe',
    `id_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    `created_time` datetime not null comment 'create time',
    `modified_time` datetime not null comment 'update time',
    primary key (`id`),
    unique key `uk_name` (`path`)
)engine=innodb default charset=utf8mb4 comment 'files';


create table if not exists `categories` (
    `id` char(19) not null comment 'Category ID',
    `name` varchar(20) not null comment 'Category Name',
    `cate_desc` varchar(200) not null comment 'Category describe',
    `id_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    `created_time` datetime not null comment 'create time',
    `modified_time` datetime not null comment 'update time',
    primary key (`id`),
    unique key `uk_name` (`name`)
)engine=innodb default charset=utf8mb4 comment 'categories';


create table if not exists `accesskey`(
    `id` char(19) not null comment 'Accesskey ID',
    `accesskey` char(19) not null comment 'Accesskey',
    `id_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    `created_time` datetime not null comment 'create time',
    primary key (`id`),
    unique key `accesskey` (`accesskey`)
)engine=innodb default charset=utf8mb4 comment 'categories';