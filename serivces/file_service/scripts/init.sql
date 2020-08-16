

use file_service;

create table if not exists `files` (
    `id` char(19) not null comment 'File ID',
    `path` varchar(100) not null comment 'File Path',
    `desc` varchar(200) not null comment 'File Describe',
    `id_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    `created_time` datetime not null comment 'create time',
    `modified_time` datetime not null comment 'update time',
    primary key (`id`),
    unique key `uk_name` (`path`)
)engine=innodb default charset=utf8mb4 comment 'files';


create table if not exists `categories` (
    `id` char(19) not null comment 'Category ID',
    `name` varchar(20) not null comment 'Category Name',
    `desc` varchar(200) not null comment 'Category describe',
    `id_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    `created_time` datetime not null comment 'create time',
    `modified_time` datetime not null comment 'update time',
    primary key (`id`),
    unique key `uk_name` (`name`)
)engine=innodb default charset=utf8mb4 comment 'categories';