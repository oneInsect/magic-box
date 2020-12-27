


use file_service;
drop table `files`;
drop table `categories`;
drop table `accesskey`;

create table if not exists `files` (
    `id` char(19) not null comment 'File ID',
    `cate_id` char(19) not null comment 'Category ID',
    `name` varchar(50) not null comment 'File Name',
    `path` varchar(100) not null comment 'File Path',
    `file_desc` varchar(200) not null comment 'File Describe',
    `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    `created_time` datetime not null comment 'create time',
    `modified_time` datetime not null comment 'update time',
    primary key (`id`),
    unique key `uk_path` (`path`)
)engine=innodb default charset=utf8mb4 comment 'files';


create table if not exists `categories` (
    `id` char(19) not null comment 'Category ID',
    `name` varchar(20) not null comment 'Category Name',
    `cate_desc` varchar(200) not null comment 'Category describe',
    `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    `created_time` datetime not null comment 'create time',
    `modified_time` datetime not null comment 'update time',
    primary key (`id`),
    unique key `uk_name` (`name`)
)engine=innodb default charset=utf8mb4 comment 'categories';


create table if not exists `accesskey`(
    `id` char(19) not null comment 'Accesskey ID',
    `accesskey` char(50) not null comment 'Accesskey',
    `is_deleted` tinyint(1) unsigned not null default '0' comment 'logic delete 0: (false) exist, 1: (true) deleted',
    `created_time` datetime not null comment 'create time',
    primary key (`id`),
    unique key `accesskey` (`accesskey`)
)engine=innodb default charset=utf8mb4 comment 'categories';



# insert into files(id, cate_id, name, path, file_desc, created_time, modified_time) values("123456", "555", "myfile", "/xxx", "first file", "2020-01-01 10:10:10", "2020-01-01 10:10:10");
# insert into categories(id, name, cate_desc, created_time, modified_time) values("555", "first cate", "xxxx", "2020-01-01 10:10:10", "2020-01-01 10:10:10");
# insert into accesskey(id, accesskey, created_time)values ("d123", "keyddd", "2020-01-01 10:10:10");
#
# select * from files;
# select * from categories;
# select * from accesskey;
#
#
# SELECT  id,cate_id,path,name,file_desc,created_time,modified_time,is_deleted  FROM files  WHERE  is_deleted=0     ORDER BY modified_time DESC;
# SELECT id,name,file_desc,created_time,modified_time,is_deleted FROM categories WHERE id=?  AND is_deleted=0