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
  id bigint UNIQUE,
  username VARCHAR(256) PRIMARY KEY,
  password VARCHAR(256),
  enabled bool,
  email VARCHAR(256) UNIQUE,
  phone VARCHAR(256) UNIQUE
);

-- 自增序列
CREATE SEQUENCE if NOT EXISTS users_id_seq;
alter TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');

INSERT INTO oauth_client_details VALUES ('clientIdPassword', 'oauth2-resource', 'secret', 'read,write,trust', 'password,authorization_code,refresh_token', null, 'ROLE_ADMIN,ROLE_USER', 7200, 5184000, null, null);

-- 密码经过了加密，全都是1234567890c
INSERT INTO users (username, password, enabled, id, email, phone) VALUES ('jeesun', '$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxu', true, 1, 'simon.sun.dev@hotmail.com', '18362102427');
INSERT INTO users (username, password, enabled, id, email, phone) VALUES ('user2711', '$2a$11$BUiKPp8.pcym7sxXYPvZeOjl0BOoVl3PZT.1Wfb3kmIgooO/GfQ4G', true, 2, null, '18860902711');
INSERT INTO users (username, password, enabled, id, email, phone) VALUES ('user6745', '$2a$11$a7XDbu2RvLjZdr4kCvqh2u7gwVXhwxdauIzbX3ZizbBU.HeV8BOky', true, 4, null, '18550046745');

INSERT INTO authorities VALUES ('jeesun', 'ROLE_ADMIN');
INSERT INTO authorities VALUES ('jeesun', 'ROLE_USER');
INSERT INTO authorities VALUES ('user2711', 'ROLE_USER');
INSERT INTO authorities VALUES ('user6745', 'ROLE_USER');