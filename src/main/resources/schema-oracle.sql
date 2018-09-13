-- used in tests that use HSQL
create table oauth_client_details (
  client_id VARCHAR(36) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4000),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(36),
  token BLOB,
  authentication_id VARCHAR(36) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(36)
);

create table oauth_access_token (
  token_id VARCHAR(36),
  token BLOB,
  authentication_id VARCHAR(36) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(36),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(36),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

create table oauth_approvals (
	userId VARCHAR(36),
	clientId VARCHAR(36),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(36) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4000),
  autoApproveScopes VARCHAR(256)
);

CREATE TABLE t_users (
  id number(38) NOT NULL PRIMARY KEY,
  create_by number(38),
  create_date DATE,
  update_by number(38),
  update_date DATE,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  enabled char(1) NOT NULL,
  phone varchar(255) DEFAULT NULL UNIQUE,
  email varchar(255) DEFAULT NULL UNIQUE,
  album_id number(38),
  address varchar(255),
  age int,
  birth DATE,
  head_photo varchar(255),
  person_brief varchar(255),
  sex char(1),
  visit_card varchar(255),
  login_type number(22),
  login_status varchar(25),
  login_date DATE
);
CREATE SEQUENCE USERS_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE TABLE t_authorities (
id number(38) NOT NULL PRIMARY KEY,
create_by number(38),
create_date DATE,
update_by number(38),
update_date DATE,
user_id number(38) NOT NULL,
authority VARCHAR(50) NOT NULL
);
CREATE SEQUENCE AUTHORITIES_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 登录日志
CREATE TABLE t_log_login (
id number(38) NOT NULL PRIMARY KEY,
create_by number(38),
create_date DATE,
update_by number(38),
update_date DATE,
create_time DATE,
ip varchar(255),
username varchar(255)
);
CREATE SEQUENCE LOG_LOGIN_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 重置密码
CREATE TABLE t_reset_pwd_info (
id number(38) NOT NULL primary key,
create_by number(38),
create_date DATE,
update_by number(38),
update_date DATE,
expires_in DATE,
secret_key varchar(255),
user_id number(38),
valid char(1) NOT NULL
);
CREATE SEQUENCE RESET_PWD_INFO_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 验证码
CREATE TABLE t_veri_code (
id number(38) NOT null primary key,
create_by number(38),
create_date DATE,
update_by number(38),
update_date DATE,
code int,
create_time int,
expires int,
phone varchar(255)
);
CREATE SEQUENCE VERI_CODE_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 二维码（用于扫码登录）
CREATE TABLE t_qr_code (
id number(38) not null primary key,
create_by number(38),
create_date DATE,
update_by number(38),
update_date DATE,
is_ok char(1),
sid varchar(255),
token varchar(255),
username varchar(255)
);
CREATE SEQUENCE QR_CODE_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 新闻
CREATE TABLE t_news_info (
id number(38) not null primary key,
create_by number(38),
create_date DATE,
update_by number(38),
update_date DATE,
title varchar(255),
user_id number(38),
status int,
content VARCHAR2(1024),
image_url varchar(255),
publish_date DATE,
tags varchar(255)
);
CREATE SEQUENCE NEWS_INFO_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 新闻标签
CREATE TABLE t_news_tag (
id number(38) not null primary key,
create_by number(38),
create_date DATE,
update_by number(38),
update_date DATE,
news_info_id number(38),
tag_id number(38)
);
CREATE SEQUENCE NEWS_TAG_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE UNIQUE INDEX ix_auth_username ON t_authorities(user_id, authority);

ALTER TABLE t_authorities add CONSTRAINT fk_authorities_users FOREIGN KEY (user_id) REFERENCES t_users (id);

INSERT INTO t_users (id, username, password, enabled, email, phone) VALUES (1, 'jeesun', '$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxu', 1, 'simon.sun.dev@hotmail.com', '18362102427');
INSERT INTO t_users (id, username, password, enabled, email, phone) VALUES (2, 'user2711', '$2a$11$BUiKPp8.pcym7sxXYPvZeOjl0BOoVl3PZT.1Wfb3kmIgooO/GfQ4G', 1, '18860902711@163.com', '18860902711');
INSERT INTO t_users (id, username, password, enabled, email, phone) VALUES (3, 'user6745', '$2a$11$a7XDbu2RvLjZdr4kCvqh2u7gwVXhwxdauIzbX3ZizbBU.HeV8BOky', 1, null, '18550046745');

INSERT INTO t_authorities(id,user_id,authority) VALUES (1, 1, 'ROLE_ADMIN');
INSERT INTO t_authorities(id,user_id,authority) VALUES (2, 1, 'ROLE_USER');
INSERT INTO t_authorities(id,user_id,authority) VALUES (3, 2, 'ROLE_USER');
INSERT INTO t_authorities(id,user_id,authority) VALUES (4, 3, 'ROLE_USER');