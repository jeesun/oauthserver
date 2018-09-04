-- MySQL
create table if NOT EXISTS oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
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
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table if NOT EXISTS oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
);

create table if NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
);

create table if NOT EXISTS oauth_code (
  code VARCHAR(255), authentication BLOB
);

CREATE TABLE if NOT EXISTS users (
   id                   bigint(20) not null auto_increment comment 'id',
   username             varchar(255) not null comment '用户名',
   password             varchar(255) not null comment '密码',
   enabled              tinyint(1) not null comment '有效',
   phone                varchar(255) comment '手机号',
   email                varchar(255) comment '邮箱',
   address              varchar(255) comment '地址',
   age                  int(4) comment '年龄',
   birth                varchar(255) comment '生日',
   head_photo           varchar(255) comment '头像',
   person_brief         varchar(255) comment '个人简介',
   sex                  tinyint(1) comment '性别',
   visit_card           varchar(255) comment '邀请码',
   primary key (id),
   key email (email),
   key phone (phone)
);
alter table users comment '用户表';

CREATE TABLE if NOT EXISTS authorities (
   user_id              bigint(20) not null comment '用户id',
   authority            varchar(50) not null comment '权限',
   key ix_auth_username (user_id, authority)
);
alter table authorities comment '权限表';

-- 登录日志
CREATE TABLE IF NOT EXISTS log_login (
id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
create_time datetime(0),
ip varchar(255),
username varchar(255)
);

-- 重置密码
CREATE TABLE IF NOT EXISTS reset_pwd_info (
id BIGINT AUTO_INCREMENT NOT NULL primary key,
expires_in timestamp(6),
secret_key varchar(255),
user_id bigint,
valid bool NOT NULL
);

-- 验证码
CREATE TABLE IF NOT EXISTS veri_code (
id BIGINT AUTO_INCREMENT NOT null primary key,
code int(4),
create_time int(8),
expires int(4),
phone varchar(255)
);

-- 二维码（用于扫码登录）
CREATE TABLE IF NOT EXISTS qr_code (
id BIGINT AUTO_INCREMENT not null primary key,
is_ok bool,
sid varchar(255),
token varchar(255),
username varchar(255)
);

-- 新闻
CREATE TABLE IF NOT EXISTS news_info (
id BIGINT AUTO_INCREMENT not null primary key,
title varchar(255),
user_id bigint,
status int(4),
content text,
image_url varchar(255),
publish_date datetime(0),
tags varchar(255)
);

-- 新闻标签
CREATE TABLE IF NOT EXISTS news_tag (
id BIGINT AUTO_INCREMENT not null primary key,
news_info_id bigint,
tag_id bigint
);

-- 两列唯一索引
set @x := (select count(*) from information_schema.statistics where table_name = 'authorities' and index_name = 'ix_auth_username' and table_schema = database());
set @sql := if( @x > 0, 'select ''Index exists.''', 'CREATE UNIQUE INDEX ix_auth_username ON authorities(user_id, authority)');
PREPARE stmt FROM @sql;
EXECUTE stmt;

-- 添加外键
ALTER TABLE authorities ADD FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- 密码经过了加密，是secret
-- INSERT INTO oauth_client_details VALUES ('clientIdPassword', 'oauth2-resource', '$2a$11$uBcjOC6qWFpxkQJtPyMhPOweH.8gP3Ig1mt27mGDpBncR7gErOuF6', 'read,write,trust', 'password,authorization_code,refresh_token', null, 'ROLE_ADMIN,ROLE_USER', 7200, 5184000, null, 'false');

-- 密码经过了加密，全都是1234567890c
INSERT INTO users (id, username, password, enabled, email, phone) VALUES (1, 'jeesun', '$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxu', true, 'simon.sun.dev@hotmail.com', '18362102427');
INSERT INTO users (id, username, password, enabled, email, phone) VALUES (2, 'user2711', '$2a$11$BUiKPp8.pcym7sxXYPvZeOjl0BOoVl3PZT.1Wfb3kmIgooO/GfQ4G', true, '18860902711@163.com', '18860902711');
INSERT INTO users (id, username, password, enabled, email, phone) VALUES (3, 'user6745', '$2a$11$a7XDbu2RvLjZdr4kCvqh2u7gwVXhwxdauIzbX3ZizbBU.HeV8BOky', true, null, '18550046745');

INSERT INTO authorities VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities VALUES (1, 'ROLE_USER');
INSERT INTO authorities VALUES (2, 'ROLE_USER');
INSERT INTO authorities VALUES (3, 'ROLE_USER');
