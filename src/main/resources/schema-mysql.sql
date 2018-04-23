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
  autoapprove tinyint
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
  id int(8) AUTO_INCREMENT UNIQUE,
  username varchar(255) NOT NULL PRIMARY KEY,
  password varchar(255) NOT NULL,
  enabled tinyint(1) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL UNIQUE,
  email varchar(255) DEFAULT NULL UNIQUE
);

INSERT INTO oauth_client_details VALUES ('clientIdPassword', 'oauth2-resource', 'secret', 'read,write,trust', 'password,authorization_code,refresh_token', null, 'ROLE_ADMIN,ROLE_USER', 7200, 5184000, null, null);

-- 密码经过了加密，全都是1234567890c
INSERT INTO users (username, password, enabled, id, email, phone) VALUES ('jeesun', '$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxu', true, 1, 'simon.sun.dev@hotmail.com', '18362102427');
INSERT INTO users (username, password, enabled, id, email, phone) VALUES ('user2711', '$2a$11$BUiKPp8.pcym7sxXYPvZeOjl0BOoVl3PZT.1Wfb3kmIgooO/GfQ4G', true, 2, null, '18860902711');
INSERT INTO users (username, password, enabled, id, email, phone) VALUES ('user6745', '$2a$11$a7XDbu2RvLjZdr4kCvqh2u7gwVXhwxdauIzbX3ZizbBU.HeV8BOky', true, 3, null, '18550046745');

INSERT INTO authorities VALUES ('jeesun', 'ROLE_ADMIN');
INSERT INTO authorities VALUES ('jeesun', 'ROLE_USER');
INSERT INTO authorities VALUES ('user2711', 'ROLE_USER');
INSERT INTO authorities VALUES ('user6745', 'ROLE_USER');

