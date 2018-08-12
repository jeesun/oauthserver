-- used in tests that use HSQL
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
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
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
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

CREATE TABLE users (
  id number(38) NOT NULL PRIMARY KEY,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  enabled char(1) NOT NULL,
  phone varchar(255) DEFAULT NULL UNIQUE,
  email varchar(255) DEFAULT NULL UNIQUE,
  address varchar(255),
  age int,
  birth varchar(255),
  head_photo varchar(255),
  person_brief varchar(255),
  sex char(1),
  visit_card varchar(255)
);
CREATE SEQUENCE USERS_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE TABLE authorities (
user_id number(38) NOT NULL,
authority VARCHAR(50) NOT NULL
);

-- 登录日志
CREATE TABLE log_login (
id number(38) NOT NULL PRIMARY KEY,
create_time DATE,
ip varchar(255),
username varchar(255)
);
CREATE SEQUENCE LOG_LOGIN_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 重置密码
CREATE TABLE reset_pwd_info (
id number(38) NOT NULL primary key,
expires_in DATE,
secret_key varchar(255),
user_id number(38),
valid char(1) NOT NULL
);
CREATE SEQUENCE RESET_PWD_INFO_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 验证码
CREATE TABLE veri_code (
id number(38) NOT null primary key,
code int,
create_time int,
expires int,
phone varchar(255)
);
CREATE SEQUENCE VERI_CODE_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 二维码（用于扫码登录）
CREATE TABLE qr_code (
id number(38) not null primary key,
is_ok char(1),
sid varchar(255),
token varchar(255),
username varchar(255)
);
CREATE SEQUENCE QR_CODE_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

-- 新闻
CREATE TABLE news_info (
id number(38) not null primary key,
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
CREATE TABLE news_tag (
id number(38) not null primary key,
news_info_id number(38),
tag_id number(38)
);
CREATE SEQUENCE NEWS_TAG_ID_SEQ MINVALUE 1 NOMAXVALUE INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE UNIQUE INDEX ix_auth_username ON authorities(user_id, authority);

ALTER TABLE authorities add CONSTRAINT fk_authorities_users FOREIGN KEY (user_id) REFERENCES users (id);

INSERT INTO users (id, username, password, enabled, email, phone) VALUES (1, 'jeesun', '$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxu', 1, 'simon.sun.dev@hotmail.com', '18362102427');
INSERT INTO users (id, username, password, enabled, email, phone) VALUES (2, 'user2711', '$2a$11$BUiKPp8.pcym7sxXYPvZeOjl0BOoVl3PZT.1Wfb3kmIgooO/GfQ4G', 1, '18860902711@163.com', '18860902711');
INSERT INTO users (id, username, password, enabled, email, phone) VALUES (3, 'user6745', '$2a$11$a7XDbu2RvLjZdr4kCvqh2u7gwVXhwxdauIzbX3ZizbBU.HeV8BOky', 1, null, '18550046745');

INSERT INTO authorities VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities VALUES (1, 'ROLE_USER');
INSERT INTO authorities VALUES (2, 'ROLE_USER');
INSERT INTO authorities VALUES (3, 'ROLE_USER');