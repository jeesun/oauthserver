-- MySQL
-- logback日志记录表创建 start
-- https://github.com/qos-ch/logback/blob/v_1.0.13/logback-classic/src/main/java/ch/qos/logback/classic/db/script/mysql.sql
# Logback: the reliable, generic, fast and flexible logging framework.
# Copyright (C) 1999-2010, QOS.ch. All rights reserved.
#
# See http://logback.qos.ch/license.html for the applicable licensing
# conditions.

# This SQL script creates the required tables by ch.qos.logback.classic.db.DBAppender.
#
# It is intended for MySQL databases. It has been tested on MySQL 5.1.37
# on Linux


BEGIN;
DROP TABLE IF EXISTS logging_event_property;
DROP TABLE IF EXISTS logging_event_exception;
DROP TABLE IF EXISTS logging_event;
COMMIT;


BEGIN;
CREATE TABLE logging_event
  (
    timestmp         BIGINT NOT NULL,
    formatted_message  TEXT NOT NULL,
    logger_name       VARCHAR(254) NOT NULL,
    level_string      VARCHAR(254) NOT NULL,
    thread_name       VARCHAR(254),
    reference_flag    SMALLINT,
    arg0              VARCHAR(254),
    arg1              VARCHAR(254),
    arg2              VARCHAR(254),
    arg3              VARCHAR(254),
    caller_filename   VARCHAR(254) NOT NULL,
    caller_class      VARCHAR(254) NOT NULL,
    caller_method     VARCHAR(254) NOT NULL,
    caller_line       CHAR(4) NOT NULL,
    event_id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
  );
COMMIT;

BEGIN;
CREATE TABLE logging_event_property
  (
    event_id	      BIGINT NOT NULL,
    mapped_key        VARCHAR(36) NOT NULL,
    mapped_value      TEXT,
    PRIMARY KEY(event_id, mapped_key),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  );
COMMIT;

BEGIN;
CREATE TABLE logging_event_exception
  (
    event_id         BIGINT NOT NULL,
    i                SMALLINT NOT NULL,
    trace_line       VARCHAR(254) NOT NULL,
    PRIMARY KEY(event_id, i),
    FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
  );
COMMIT;
-- logback日志记录表创建 end

-- Oauth
create table if NOT EXISTS oauth_client_details (
  client_id VARCHAR(36) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

create table if NOT EXISTS oauth_client_token (
  token_id VARCHAR(36),
  token BLOB,
  authentication_id VARCHAR(36),
  user_name VARCHAR(255),
  client_id VARCHAR(36)
);

create table if NOT EXISTS oauth_access_token (
  token_id VARCHAR(36),
  token BLOB,
  authentication_id VARCHAR(36),
  user_name VARCHAR(255),
  client_id VARCHAR(36),
  authentication BLOB,
  refresh_token VARCHAR(255)
);

create table if NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(36),
  token BLOB,
  authentication BLOB
);

create table if NOT EXISTS oauth_code (
  code VARCHAR(255), authentication BLOB
);

/*==============================================================*/
/* Table: t_users                                               */
/*==============================================================*/
create table if NOT EXISTS t_users
(
   id                   bigint(20) not null auto_increment comment 'id',
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
   username             national varchar(255) not null comment '用户名',
   password             national varchar(255) not null comment '密码',
   enabled              tinyint(1) not null comment '有效',
   phone                national varchar(255) comment '手机号',
   email                national varchar(255) comment '邮箱',
   ssoid                national varchar(255) comment 'oppo账号ssoid',
   album_id             bigint(20) comment '播放列表id',
   address              national varchar(255) comment '地址',
   age                  int(4) comment '年龄',
   birth                date comment '生日',
   head_photo           national varchar(255) comment '头像',
   person_brief         national varchar(255) comment '个人简介',
   sex                  tinyint(1) comment '性别',
   visit_card           national varchar(255) comment '邀请码',
   login_type           int(4) comment '登录方式',
   login_status         varchar(25) comment '登录状态',
   login_date           datetime comment '登录时间',
   primary key (id),
   key email (email),
   key phone (phone)
);

alter table t_users comment '用户表';

CREATE TABLE if NOT EXISTS t_authorities (
   id                   bigint(20) not null auto_increment comment 'id',
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
   user_id              bigint(20) not null comment '用户id',
   authority            varchar(50) not null comment '权限',
   primary key (id),
   key ix_auth_username (user_id, authority)
);
alter table t_authorities comment '权限表';

-- 登录日志
CREATE TABLE IF NOT EXISTS t_log_login (
id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
create_time datetime(0),
ip varchar(255),
username varchar(255)
);

-- 重置密码
CREATE TABLE IF NOT EXISTS t_reset_pwd_info (
id BIGINT AUTO_INCREMENT NOT NULL primary key,
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
expires_in timestamp(6),
secret_key varchar(255),
user_id bigint,
valid bool NOT NULL
);

-- 验证码
CREATE TABLE IF NOT EXISTS t_veri_code (
id BIGINT AUTO_INCREMENT NOT null primary key,
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
code int(4),
create_time bigint(20),
expires int(4),
phone varchar(255)
);

-- 二维码（用于扫码登录）
CREATE TABLE IF NOT EXISTS t_qr_code (
id BIGINT AUTO_INCREMENT not null primary key,
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
is_ok bool,
sid varchar(255),
token varchar(255),
username varchar(255)
);

-- 新闻
CREATE TABLE IF NOT EXISTS t_news_info (
id BIGINT AUTO_INCREMENT not null primary key,
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
title varchar(255),
user_id bigint,
status int(4),
content text,
image_url varchar(255),
publish_date datetime(0),
tags varchar(255)
);

-- 新闻标签
CREATE TABLE IF NOT EXISTS t_news_tag (
id BIGINT AUTO_INCREMENT not null primary key,
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
news_info_id bigint,
tag_id bigint
);

/*==============================================================*/
/* Table: t_dict_type                                           */
/*==============================================================*/
create table if NOT EXISTS t_dict_type
(
   id                   bigint(20) not null auto_increment comment 'id',
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
   type_code            national varchar(255) not null comment '字典编码',
   type_name            national varchar(255) not null comment '字典编码名称',
   type_group_id        bigint(20) not null comment '字典组id',
   order_num            int(4) comment '排序',
   primary key (id)
);

alter table t_dict_type comment '字典';

/*==============================================================*/
/* Table: t_dict_type_group                                     */
/*==============================================================*/
create table if NOT EXISTS t_dict_type_group
(
   id                   bigint(20) not null auto_increment comment 'id',
   create_by            bigint(20) comment '创建人id',
   create_date          datetime comment '创建时间',
   update_by            bigint(20) comment '更新人id',
   update_date          datetime comment '更新时间',
   type_group_code      national varchar(255) not null comment '字典组编码',
   type_group_name      national varchar(255) not null comment '字典组名称',
   primary key (id)
);

alter table t_dict_type_group comment '字典组';

-- 两列唯一索引
set @x := (select count(*) from information_schema.statistics where table_name = 't_authorities' and index_name = 'ix_auth_username' and table_schema = database());
set @sql := if( @x > 0, 'select ''Index exists.''', 'CREATE UNIQUE INDEX ix_auth_username ON t_authorities(user_id, authority)');
PREPARE stmt FROM @sql;
EXECUTE stmt;

-- 添加外键
ALTER TABLE t_authorities ADD FOREIGN KEY (user_id) REFERENCES t_users (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table t_dict_type add constraint fk_type_group_id foreign key (type_group_id)
      references t_dict_type_group (id) on delete restrict on update restrict;

-- 密码经过了加密，是secret
-- INSERT INTO oauth_client_details VALUES ('clientIdPassword', 'oauth2-resource', '$2a$11$uBcjOC6qWFpxkQJtPyMhPOweH.8gP3Ig1mt27mGDpBncR7gErOuF6', 'read,write,trust', 'password,authorization_code,refresh_token', null, 'ROLE_ADMIN,ROLE_USER', 7200, 5184000, null, 'false');

-- 密码经过了加密，全都是1234567890c
INSERT INTO t_users (id, username, password, enabled, email, phone) VALUES (1, 'jeesun', '$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxu', true, 'simon.sun.dev@hotmail.com', '18362102427');
INSERT INTO t_users (id, username, password, enabled, email, phone) VALUES (2, 'user2711', '$2a$11$BUiKPp8.pcym7sxXYPvZeOjl0BOoVl3PZT.1Wfb3kmIgooO/GfQ4G', true, '18860902711@163.com', '18860902711');
INSERT INTO t_users (id, username, password, enabled, email, phone) VALUES (3, 'user6745', '$2a$11$a7XDbu2RvLjZdr4kCvqh2u7gwVXhwxdauIzbX3ZizbBU.HeV8BOky', true, null, '18550046745');

INSERT INTO t_authorities(user_id,authority) VALUES (1, 'ROLE_ADMIN');
INSERT INTO t_authorities(user_id,authority) VALUES (1, 'ROLE_USER');
INSERT INTO t_authorities(user_id,authority) VALUES (2, 'ROLE_USER');
INSERT INTO t_authorities(user_id,authority) VALUES (3, 'ROLE_USER');
