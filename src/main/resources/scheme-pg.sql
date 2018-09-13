-- PostgreSQL
CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id VARCHAR(36) PRIMARY KEY,
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
  token_id VARCHAR(36),
  token bytea,
  authentication_id VARCHAR(36),
  user_name VARCHAR(256),
  client_id VARCHAR(36)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(36),
  token bytea,
  authentication_id VARCHAR(36),
  user_name VARCHAR(256),
  client_id VARCHAR(36),
  authentication bytea,
  refresh_token VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(36),
  token bytea,
  authentication bytea
);

CREATE TABLE IF NOT EXISTS oauth_code (
  code VARCHAR(256), authentication bytea
);

CREATE TABLE IF NOT EXISTS oauth_approvals (
  userId VARCHAR(36),
  clientId VARCHAR(36),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

CREATE TABLE
IF
	NOT EXISTS t_users (
		ID BIGINT NOT NULL,
		create_by BIGINT,
		create_date TIMESTAMP,
		update_by BIGINT,
		update_date TIMESTAMP,
		username VARCHAR ( 255 ) NOT NULL,
		PASSWORD VARCHAR ( 255 ) NOT NULL,
		enabled BOOLEAN NOT NULL,
		phone VARCHAR ( 255 ),
		email VARCHAR ( 255 ),
		ssoid VARCHAR ( 255 ),
		album_id BIGINT,
		address VARCHAR ( 255 ),
		age INT,
		birth DATE,
		head_photo VARCHAR ( 255 ),
		person_brief VARCHAR ( 255 ),
		sex BOOLEAN,
		visit_card VARCHAR ( 255 ),
		login_type INT,
		login_status VARCHAR ( 25 ),
		login_date TIMESTAMP,
		PRIMARY KEY ( ID ),
		CONSTRAINT uk_users_email UNIQUE ( email ),
	CONSTRAINT uk_users_phone UNIQUE ( phone )
	);


CREATE TABLE IF NOT EXISTS t_authorities (
   id                   bigint not null,
   create_by            bigint,
   create_date          TIMESTAMP,
   update_by            bigint,
user_id bigint NOT NULL,
authority VARCHAR(50) NOT NULL,
   primary key (id)
);


-- 登录日志
CREATE TABLE IF NOT EXISTS t_log_login (
id bigint NOT NULL PRIMARY KEY,
create_by            bigint,
   create_date          TIMESTAMP,
   update_by            bigint,
   update_date          TIMESTAMP,
create_time timestamp(6),
ip varchar(255),
username varchar(255)
);

-- 重置密码
CREATE TABLE IF NOT EXISTS t_reset_pwd_info (
id bigint NOT NULL primary key,
create_by            bigint,
   create_date          TIMESTAMP,
   update_by            bigint,
   update_date          TIMESTAMP,
expires_in timestamp(6),
secret_key varchar(255),
user_id int8,
valid bool NOT NULL
);

-- 验证码
CREATE TABLE IF NOT EXISTS t_veri_code (
id bigint NOT null primary key,
create_by            bigint,
   create_date          TIMESTAMP,
   update_by            bigint,
   update_date          TIMESTAMP,
code int4,
create_time int8,
expires int4,
phone varchar(255)
);

-- 二维码（用于扫码登录）
CREATE TABLE IF NOT EXISTS t_qr_code (
id bigint not null primary key,
create_by            bigint,
   create_date          TIMESTAMP,
   update_by            bigint,
   update_date          TIMESTAMP,
is_ok bool,
sid varchar(255),
token varchar(255),
username varchar(255)
);

-- 新闻
CREATE TABLE IF NOT EXISTS t_news_info (
id bigint not null primary key,
create_by            bigint,
   create_date          TIMESTAMP,
   update_by            bigint,
   update_date          TIMESTAMP,
title varchar(255),
user_id bigint,
status int4,
content text,
image_url varchar(255),
publish_date timestamp(6),
tags varchar(255)
);

-- 新闻标签
CREATE TABLE IF NOT EXISTS t_news_tag (
id bigint not null primary key,
create_by            bigint,
   create_date          TIMESTAMP,
   update_by            bigint,
   update_date          TIMESTAMP,
news_info_id bigint,
tag_id bigint
);

/*==============================================================*/
/* Table: t_dict_type                                           */
/*==============================================================*/
create table if NOT EXISTS t_dict_type
(
   id                   bigint not null,
   create_by            bigint,
   create_date          TIMESTAMP,
   update_by            bigint,
   update_date          TIMESTAMP,
   type_code            varchar(255),
   type_name            varchar(255),
   type_group_id        bigint not null,
   order_num            int,
   primary key (id)
);

/*==============================================================*/
/* Table: t_dict_type_group                                     */
/*==============================================================*/
create table if NOT EXISTS t_dict_type_group
(
   id                   bigint not null,
   create_by            bigint,
   create_date          TIMESTAMP,
   update_by            bigint,
   update_date          TIMESTAMP,
   type_group_code      varchar(255) not null,
   type_group_name      varchar(255) not null,
   primary key (id)
);


-- 自增序列
CREATE SEQUENCE if NOT EXISTS users_id_seq;
ALTER TABLE t_users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');
CREATE SEQUENCE if NOT EXISTS log_login_id_seq;
ALTER TABLE t_log_login ALTER COLUMN id SET DEFAULT nextval('log_login_id_seq');
CREATE SEQUENCE if NOT EXISTS reset_pwd_info_id_seq;
ALTER TABLE t_reset_pwd_info ALTER COLUMN id SET DEFAULT nextval('reset_pwd_info_id_seq');
CREATE SEQUENCE if NOT EXISTS veri_code_id_seq;
ALTER TABLE t_veri_code ALTER COLUMN id SET DEFAULT nextval('veri_code_id_seq');
CREATE SEQUENCE if NOT EXISTS qr_code_id_seq;
ALTER TABLE t_qr_code ALTER COLUMN id SET DEFAULT nextval('qr_code_id_seq');
CREATE SEQUENCE if NOT EXISTS news_info_id_seq;
ALTER TABLE t_news_info ALTER COLUMN id SET DEFAULT nextval('news_info_id_seq');
CREATE SEQUENCE if NOT EXISTS news_tag_id_seq;
ALTER TABLE t_news_tag ALTER COLUMN id SET DEFAULT nextval('news_tag_id_seq');
CREATE SEQUENCE if NOT EXISTS authorities_id_seq;
ALTER TABLE t_authorities ALTER COLUMN id SET DEFAULT nextval('authorities_id_seq');

-- 两列唯一索引
CREATE UNIQUE INDEX if NOT EXISTS ix_auth_username ON t_authorities (user_id, authority);

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

INSERT INTO t_authorities (user_id, authority ) VALUES (1, 'ROLE_ADMIN' );
INSERT INTO t_authorities (user_id, authority ) VALUES (1, 'ROLE_USER' );
INSERT INTO t_authorities (user_id, authority ) VALUES (2, 'ROLE_USER' );
INSERT INTO t_authorities (user_id, authority ) VALUES (3, 'ROLE_USER' );