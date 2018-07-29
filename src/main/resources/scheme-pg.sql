-- PostgreSQL
CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);

CREATE TABLE IF NOT EXISTS oauth_code (
  code VARCHAR(256), authentication bytea
);

CREATE TABLE IF NOT EXISTS oauth_approvals (
  userId VARCHAR(256),
  clientId VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users (
  id bigint NOT NULL PRIMARY KEY,
  username VARCHAR(256) NOT NULL,
  password VARCHAR(256) NOT NULL,
  enabled bool NOT NULL,
  email VARCHAR(256) DEFAULT NULL UNIQUE,
  phone VARCHAR(256) DEFAULT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS authorities (
user_id bigint NOT NULL,
authority VARCHAR(50) NOT NULL
);

-- 用户信息
CREATE TABLE IF NOT EXISTS user_info (
id int8 NOT NULL PRIMARY KEY,
address varchar(255),
age int4,
birth varchar(255),
email varchar(255),
head_photo varchar(255),
person_brief varchar(255),
phone varchar(255),
sex bool,
user_id int8,
username varchar(255),
visit_card varchar(255)
);

-- 登录日志
CREATE TABLE IF NOT EXISTS log_login (
id int8 NOT NULL PRIMARY KEY,
create_time timestamp(6),
ip varchar(255),
username varchar(255)
);

-- 重置密码
CREATE TABLE IF NOT EXISTS reset_pwd_info (
id int8 NOT NULL primary key,
expires_in timestamp(6),
secret_key varchar(255),
user_id int8,
valid bool NOT NULL
);

-- 验证码
CREATE TABLE IF NOT EXISTS veri_code (
id int8 NOT null primary key,
code int4,
create_time int8,
expires int4,
phone varchar(255)
);

-- 二维码（用于扫码登录）
CREATE TABLE IF NOT EXISTS qr_code (
id int8 not null primary key,
is_ok bool,
sid varchar(255),
token varchar(255),
username varchar(255)
);

-- 新闻
CREATE TABLE IF NOT EXISTS news_info (
id int8 not null primary key,
title varchar(255),
user_id int8,
status int4,
content text,
image_url varchar(255),
publish_date timestamp(6),
tags varchar(255)
);

-- 新闻标签
CREATE TABLE IF NOT EXISTS news_tag (
id int8 not null primary key,
news_info_id int8,
tag_id int8
);

-- 自增序列
CREATE SEQUENCE if NOT EXISTS users_id_seq;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');
CREATE SEQUENCE if NOT EXISTS user_info_id_seq;
ALTER TABLE user_info ALTER COLUMN id SET DEFAULT nextval('user_info_id_seq');
CREATE SEQUENCE if NOT EXISTS log_login_id_seq;
ALTER TABLE log_login ALTER COLUMN id SET DEFAULT nextval('log_login_id_seq');
CREATE SEQUENCE if NOT EXISTS reset_pwd_info_id_seq;
ALTER TABLE reset_pwd_info ALTER COLUMN id SET DEFAULT nextval('reset_pwd_info_id_seq');
CREATE SEQUENCE if NOT EXISTS veri_code_id_seq;
ALTER TABLE veri_code ALTER COLUMN id SET DEFAULT nextval('veri_code_id_seq');
CREATE SEQUENCE if NOT EXISTS qr_code_id_seq;
ALTER TABLE qr_code ALTER COLUMN id SET DEFAULT nextval('qr_code_id_seq');
CREATE SEQUENCE if NOT EXISTS news_info_id_seq;
ALTER TABLE news_info ALTER COLUMN id SET DEFAULT nextval('news_info_id_seq');
CREATE SEQUENCE if NOT EXISTS news_tag_id_seq;
ALTER TABLE news_tag ALTER COLUMN id SET DEFAULT nextval('news_tag_id_seq');

-- 两列唯一索引
CREATE UNIQUE INDEX if NOT EXISTS ix_auth_username ON authorities (user_id, authority);

-- 添加外键
ALTER TABLE authorities ADD FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION;

-- 密码经过了加密，是secret
INSERT INTO oauth_client_details VALUES ('clientIdPassword', 'oauth2-resource', '$2a$11$uBcjOC6qWFpxkQJtPyMhPOweH.8gP3Ig1mt27mGDpBncR7gErOuF6', 'read,write,trust', 'password,authorization_code,refresh_token', null, 'ROLE_ADMIN,ROLE_USER', 7200, 5184000, null, 'false');

-- 密码经过了加密，全都是1234567890c
INSERT INTO users (id, username, password, enabled, email, phone) VALUES (1, 'jeesun', '$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxu', true, 'simon.sun.dev@hotmail.com', '18362102427');
INSERT INTO users (id, username, password, enabled, email, phone) VALUES (2, 'user2711', '$2a$11$BUiKPp8.pcym7sxXYPvZeOjl0BOoVl3PZT.1Wfb3kmIgooO/GfQ4G', true, '18860902711@163.com', '18860902711');
INSERT INTO users (id, username, password, enabled, email, phone) VALUES (3, 'user6745', '$2a$11$a7XDbu2RvLjZdr4kCvqh2u7gwVXhwxdauIzbX3ZizbBU.HeV8BOky', true, null, '18550046745');

INSERT INTO authorities VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities VALUES (1, 'ROLE_USER');
INSERT INTO authorities VALUES (2, 'ROLE_USER');
INSERT INTO authorities VALUES (3, 'ROLE_USER');

INSERT INTO user_info (id, address, age, birth, email, head_photo, person_brief, phone, sex, user_id, username, visit_card) VALUES (1, NULL, NULL, NULL, 'simon.sun.dev@hotmail.com', NULL, NULL, '18362102427', NULL, 1, 'jeesun', NULL);
INSERT INTO user_info (id, address, age, birth, email, head_photo, person_brief, phone, sex, user_id, username, visit_card) VALUES (3, NULL, NULL, NULL, '18860902711@163.com', NULL, NULL, '18860902711', NULL, 2, 'user2711', NULL);
INSERT INTO user_info (id, address, age, birth, email, head_photo, person_brief, phone, sex, user_id, username, visit_card) VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, '18550046745', NULL, 3, 'user6745', NULL);

