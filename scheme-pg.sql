/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : PostgreSQL
 Source Server Version : 90501
 Source Host           : localhost:5432
 Source Catalog        : thymelte
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90501
 File Encoding         : 65001

 Date: 20/02/2019 10:03:59
*/


-- ----------------------------
-- Sequence structure for authorities_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."authorities_id_seq";
CREATE SEQUENCE "public"."authorities_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for log_login_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."log_login_id_seq";
CREATE SEQUENCE "public"."log_login_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for logging_event_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."logging_event_id_seq";
CREATE SEQUENCE "public"."logging_event_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for news_info_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."news_info_id_seq";
CREATE SEQUENCE "public"."news_info_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for news_tag_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."news_tag_id_seq";
CREATE SEQUENCE "public"."news_tag_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for qr_code_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."qr_code_id_seq";
CREATE SEQUENCE "public"."qr_code_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for reset_pwd_info_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."reset_pwd_info_id_seq";
CREATE SEQUENCE "public"."reset_pwd_info_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_account_bind_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_account_bind_id_seq";
CREATE SEQUENCE "public"."t_account_bind_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_bill_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_bill_id_seq";
CREATE SEQUENCE "public"."t_bill_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_log_login_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_log_login_id_seq";
CREATE SEQUENCE "public"."t_log_login_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_multi_language_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_multi_language_id_seq";
CREATE SEQUENCE "public"."t_multi_language_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_notice_msg_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_notice_msg_id_seq";
CREATE SEQUENCE "public"."t_notice_msg_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_s_quartz_job_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_s_quartz_job_id_seq";
CREATE SEQUENCE "public"."t_s_quartz_job_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_side_menu_authority_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_side_menu_authority_id_seq";
CREATE SEQUENCE "public"."t_side_menu_authority_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for t_side_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."t_side_menu_id_seq";
CREATE SEQUENCE "public"."t_side_menu_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."users_id_seq";
CREATE SEQUENCE "public"."users_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 3
CACHE 1;

-- ----------------------------
-- Sequence structure for veri_code_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."veri_code_id_seq";
CREATE SEQUENCE "public"."veri_code_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for logging_event
-- ----------------------------
DROP TABLE IF EXISTS "public"."logging_event";
CREATE TABLE "public"."logging_event" (
  "timestmp" int8 NOT NULL,
  "formatted_message" text COLLATE "pg_catalog"."default" NOT NULL,
  "logger_name" varchar(254) COLLATE "pg_catalog"."default" NOT NULL,
  "level_string" varchar(254) COLLATE "pg_catalog"."default" NOT NULL,
  "thread_name" varchar(254) COLLATE "pg_catalog"."default",
  "reference_flag" int2,
  "arg0" varchar(254) COLLATE "pg_catalog"."default",
  "arg1" varchar(254) COLLATE "pg_catalog"."default",
  "arg2" varchar(254) COLLATE "pg_catalog"."default",
  "arg3" varchar(254) COLLATE "pg_catalog"."default",
  "caller_filename" varchar(254) COLLATE "pg_catalog"."default" NOT NULL,
  "caller_class" varchar(254) COLLATE "pg_catalog"."default" NOT NULL,
  "caller_method" varchar(254) COLLATE "pg_catalog"."default" NOT NULL,
  "caller_line" char(4) COLLATE "pg_catalog"."default" NOT NULL,
  "event_id" int8 NOT NULL DEFAULT nextval(''logging_event_id_seq''::regclass)
)
;

-- ----------------------------
-- Table structure for logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS "public"."logging_event_exception";
CREATE TABLE "public"."logging_event_exception" (
  "event_id" int8 NOT NULL,
  "i" int2 NOT NULL,
  "trace_line" varchar(254) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Table structure for logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS "public"."logging_event_property";
CREATE TABLE "public"."logging_event_property" (
  "event_id" int8 NOT NULL,
  "mapped_key" varchar(254) COLLATE "pg_catalog"."default" NOT NULL,
  "mapped_value" varchar(1024) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_access_token";
CREATE TABLE "public"."oauth_access_token" (
  "token_id" varchar(36) COLLATE "pg_catalog"."default",
  "token" bytea,
  "authentication_id" varchar(36) COLLATE "pg_catalog"."default",
  "user_name" varchar(256) COLLATE "pg_catalog"."default",
  "client_id" varchar(36) COLLATE "pg_catalog"."default",
  "authentication" bytea,
  "refresh_token" varchar(256) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------
INSERT INTO "public"."oauth_access_token" VALUES (''d6f1cdcb8fd814506a082485aaaa81cf'', E''\\254\\355\\000\\005sr\\000Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken\\014\\262\\2366\\033$\\372\\316\\002\\000\\006L\\000\\025additionalInformationt\\000\\017Ljava/util/Map;L\\000\\012expirationt\\000\\020Ljava/util/Date;L\\000\\014refreshTokent\\000?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\\000\\005scopet\\000\\017Ljava/util/Set;L\\000\\011tokenTypet\\000\\022Ljava/lang/String;L\\000\\005valueq\\000~\\000\\005xpsr\\000\\036java.util.Collections$EmptyMapY6\\024\\205Z\\334\\347\\320\\002\\000\\000xpsr\\000\\016java.util.Datehj\\201\\001KYt\\031\\003\\000\\000xpw\\010\\000\\000\\001h\\377\\375\\313\\014xsr\\000Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\\337Gc\\235\\320\\311\\267\\002\\000\\001L\\000\\012expirationq\\000~\\000\\002xr\\000Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\\341\\016\\012cT\\324^\\002\\000\\001L\\000\\005valueq\\000~\\000\\005xpt\\000$938131da-9c7f-4b89-94d6-570e05b5cf0bsq\\000~\\000\\011w\\010\\000\\000\\001j4\\215~\\014xsr\\000%java.util.Collections$UnmodifiableSet\\200\\035\\222\\321\\217\\233\\200U\\002\\000\\000xr\\000,java.util.Collections$UnmodifiableCollection\\031B\\000\\200\\313^\\367\\036\\002\\000\\001L\\000\\001ct\\000\\026Ljava/util/Collection;xpsr\\000\\027java.util.LinkedHashSet\\330l\\327Z\\225\\335*\\036\\002\\000\\000xr\\000\\021java.util.HashSet\\272D\\205\\225\\226\\270\\2674\\003\\000\\000xpw\\014\\000\\000\\000\\020?@\\000\\000\\000\\000\\000\\001t\\000\\020read,write,trustxt\\000\\006bearert\\000$e0a8ee88-289e-416a-8507-9b10768e6d7c'', ''94bf174601ac4fe6414b91116b3cec6a'', ''jeesun'', ''clientIdPassword'', E''\\254\\355\\000\\005sr\\000Aorg.springframework.security.oauth2.provider.OAuth2Authentication\\275@\\013\\002\\026bR\\023\\002\\000\\002L\\000\\015storedRequestt\\000<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\\000\\022userAuthenticationt\\0002Lorg/springframework/security/core/Authentication;xr\\000Gorg.springframework.security.authentication.AbstractAuthenticationToken\\323\\252(~nGd\\016\\002\\000\\003Z\\000\\015authenticatedL\\000\\013authoritiest\\000\\026Ljava/util/Collection;L\\000\\007detailst\\000\\022Ljava/lang/Object;xp\\000sr\\000&java.util.Collections$UnmodifiableList\\374\\017%1\\265\\354\\216\\020\\002\\000\\001L\\000\\004listt\\000\\020Ljava/util/List;xr\\000,java.util.Collections$UnmodifiableCollection\\031B\\000\\200\\313^\\367\\036\\002\\000\\001L\\000\\001cq\\000~\\000\\004xpsr\\000\\023java.util.ArrayListx\\201\\322\\035\\231\\307a\\235\\003\\000\\001I\\000\\004sizexp\\000\\000\\000\\002w\\004\\000\\000\\000\\002sr\\000\\031com.simon.model.Authority\\000\\000\\000\\000\\000\\000\\000\\001\\002\\000\\010L\\000\\011authorityt\\000\\022Ljava/lang/String;L\\000\\010createByt\\000\\020Ljava/lang/Long;L\\000\\012createDatet\\000\\020Ljava/util/Date;L\\000\\002idq\\000~\\000\\017L\\000\\010updateByq\\000~\\000\\017L\\000\\012updateDateq\\000~\\000\\020L\\000\\006userIdq\\000~\\000\\017L\\000\\010usernameq\\000~\\000\\016xpt\\000\\012ROLE_ADMINpppppsr\\000\\016java.lang.Long;\\213\\344\\220\\314\\217#\\337\\002\\000\\001J\\000\\005valuexr\\000\\020java.lang.Number\\206\\254\\225\\035\\013\\224\\340\\213\\002\\000\\000xp\\000\\000\\000\\000;\\232\\312\\000psq\\000~\\000\\015t\\000\\011ROLE_USERpppppsq\\000~\\000\\023\\000\\000\\000\\000;\\232\\312\\000pxq\\000~\\000\\014psr\\000:org.springframework.security.oauth2.provider.OAuth2Request\\000\\000\\000\\000\\000\\000\\000\\001\\002\\000\\007Z\\000\\010approvedL\\000\\013authoritiesq\\000~\\000\\004L\\000\\012extensionst\\000\\017Ljava/util/Map;L\\000\\013redirectUriq\\000~\\000\\016L\\000\\007refresht\\000;Lorg/springframework/security/oauth2/provider/TokenRequest;L\\000\\013resourceIdst\\000\\017Ljava/util/Set;L\\000\\015responseTypesq\\000~\\000\\034xr\\0008org.springframework.security.oauth2.provider.BaseRequest6(z>\\243qi\\275\\002\\000\\003L\\000\\010clientIdq\\000~\\000\\016L\\000\\021requestParametersq\\000~\\000\\032L\\000\\005scopeq\\000~\\000\\034xpt\\000\\020clientIdPasswordsr\\000%java.util.Collections$UnmodifiableMap\\361\\245\\250\\376t\\365\\007B\\002\\000\\001L\\000\\001mq\\000~\\000\\032xpsr\\000\\021java.util.HashMap\\005\\007\\332\\301\\303\\026`\\321\\003\\000\\002F\\000\\012loadFactorI\\000\\011thresholdxp?@\\000\\000\\000\\000\\000\\003w\\010\\000\\000\\000\\004\\000\\000\\000\\002t\\000\\012grant_typet\\000\\010passwordt\\000\\010usernamet\\000\\01318800000000xsr\\000%java.util.Collections$UnmodifiableSet\\200\\035\\222\\321\\217\\233\\200U\\002\\000\\000xq\\000~\\000\\011sr\\000\\027java.util.LinkedHashSet\\330l\\327Z\\225\\335*\\036\\002\\000\\000xr\\000\\021java.util.HashSet\\272D\\205\\225\\226\\270\\2674\\003\\000\\000xpw\\014\\000\\000\\000\\020?@\\000\\000\\000\\000\\000\\001t\\000\\020read,write,trustx\\001sq\\000~\\000+w\\014\\000\\000\\000\\020?@\\000\\000\\000\\000\\000\\002sr\\000Borg.springframework.security.core.authority.SimpleGrantedAuthority\\000\\000\\000\\000\\000\\000\\001\\244\\002\\000\\001L\\000\\004roleq\\000~\\000\\016xpt\\000\\011ROLE_USERsq\\000~\\000/t\\000\\012ROLE_ADMINxsq\\000~\\000"?@\\000\\000\\000\\000\\000\\000w\\010\\000\\000\\000\\020\\000\\000\\000\\000xppsq\\000~\\000+w\\014\\000\\000\\000\\020?@\\000\\000\\000\\000\\000\\000xsq\\000~\\000+w\\014\\000\\000\\000\\020?@\\000\\000\\000\\000\\000\\000xsr\\000Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\\000\\000\\000\\000\\000\\000\\001\\244\\002\\000\\002L\\000\\013credentialsq\\000~\\000\\005L\\000\\011principalq\\000~\\000\\005xq\\000~\\000\\003\\001sq\\000~\\000\\007sq\\000~\\000\\013\\000\\000\\000\\002w\\004\\000\\000\\000\\002q\\000~\\000\\021q\\000~\\000\\026xq\\000~\\000:sr\\000\\027java.util.LinkedHashMap4\\300N\\\\\\020l\\300\\373\\002\\000\\001Z\\000\\013accessOrderxq\\000~\\000"?@\\000\\000\\000\\000\\000\\006w\\010\\000\\000\\000\\010\\000\\000\\000\\002q\\000~\\000$q\\000~\\000%q\\000~\\000&q\\000~\\000''''x\\000psr\\000"com.simon.common.domain.UserEntity''''V:\\223\\313}:L\\002\\000\\015Z\\000\\007enabledL\\000\\007addressq\\000~\\000\\016L\\000\\003aget\\000\\023Ljava/lang/Integer;L\\000\\013authoritiesq\\000~\\000\\010L\\000\\005birthq\\000~\\000\\020L\\000\\005emailq\\000~\\000\\016L\\000\\011headPhotoq\\000~\\000\\016L\\000\\002idq\\000~\\000\\017L\\000\\010passwordq\\000~\\000\\016L\\000\\013personBriefq\\000~\\000\\016L\\000\\005phoneq\\000~\\000\\016L\\000\\003sext\\000\\023Ljava/lang/Boolean;L\\000\\010usernameq\\000~\\000\\016xp\\001psr\\000\\021java.lang.Integer\\022\\342\\240\\244\\367\\201\\2078\\002\\000\\001I\\000\\005valuexq\\000~\\000\\024\\000\\000\\000\\000sq\\000~\\000\\013\\000\\000\\000\\002w\\004\\000\\000\\000\\002q\\000~\\000\\021q\\000~\\000\\026xpt\\000\\02318800000000@163.compsq\\000~\\000\\023\\000\\000\\000\\000;\\232\\312\\000t\\000<$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxupt\\000\\01318800000000sr\\000\\021java.lang.Boolean\\315 r\\200\\325\\234\\372\\356\\002\\000\\001Z\\000\\005valuexp\\000t\\000\\006jeesun'', ''1bbfe3886c8ce73a55885bc08e11c964'');

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_approvals";
CREATE TABLE "public"."oauth_approvals" (
  "userid" varchar(36) COLLATE "pg_catalog"."default",
  "clientid" varchar(36) COLLATE "pg_catalog"."default",
  "scope" varchar(256) COLLATE "pg_catalog"."default",
  "status" varchar(10) COLLATE "pg_catalog"."default",
  "expiresat" timestamp(6),
  "lastmodifiedat" timestamp(6)
)
;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_client_details";
CREATE TABLE "public"."oauth_client_details" (
  "client_id" varchar(36) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_ids" varchar(256) COLLATE "pg_catalog"."default",
  "client_secret" varchar(256) COLLATE "pg_catalog"."default",
  "scope" varchar(256) COLLATE "pg_catalog"."default",
  "authorized_grant_types" varchar(256) COLLATE "pg_catalog"."default",
  "web_server_redirect_uri" varchar(256) COLLATE "pg_catalog"."default",
  "authorities" varchar(256) COLLATE "pg_catalog"."default",
  "access_token_validity" int4,
  "refresh_token_validity" int4,
  "additional_information" varchar(4096) COLLATE "pg_catalog"."default",
  "autoapprove" varchar(256) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_client_token";
CREATE TABLE "public"."oauth_client_token" (
  "token_id" varchar(36) COLLATE "pg_catalog"."default",
  "token" bytea,
  "authentication_id" varchar(36) COLLATE "pg_catalog"."default",
  "user_name" varchar(256) COLLATE "pg_catalog"."default",
  "client_id" varchar(36) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_code";
CREATE TABLE "public"."oauth_code" (
  "code" varchar(256) COLLATE "pg_catalog"."default",
  "authentication" bytea
)
;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_refresh_token";
CREATE TABLE "public"."oauth_refresh_token" (
  "token_id" varchar(36) COLLATE "pg_catalog"."default",
  "token" bytea,
  "authentication" bytea
)
;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
INSERT INTO "public"."oauth_refresh_token" VALUES (''1bbfe3886c8ce73a55885bc08e11c964'', E''\\254\\355\\000\\005sr\\000Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\\337Gc\\235\\320\\311\\267\\002\\000\\001L\\000\\012expirationt\\000\\020Ljava/util/Date;xr\\000Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\\341\\016\\012cT\\324^\\002\\000\\001L\\000\\005valuet\\000\\022Ljava/lang/String;xpt\\000$938131da-9c7f-4b89-94d6-570e05b5cf0bsr\\000\\016java.util.Datehj\\201\\001KYt\\031\\003\\000\\000xpw\\010\\000\\000\\001j4\\215~\\014x'', E''\\254\\355\\000\\005sr\\000Aorg.springframework.security.oauth2.provider.OAuth2Authentication\\275@\\013\\002\\026bR\\023\\002\\000\\002L\\000\\015storedRequestt\\000<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\\000\\022userAuthenticationt\\0002Lorg/springframework/security/core/Authentication;xr\\000Gorg.springframework.security.authentication.AbstractAuthenticationToken\\323\\252(~nGd\\016\\002\\000\\003Z\\000\\015authenticatedL\\000\\013authoritiest\\000\\026Ljava/util/Collection;L\\000\\007detailst\\000\\022Ljava/lang/Object;xp\\000sr\\000&java.util.Collections$UnmodifiableList\\374\\017%1\\265\\354\\216\\020\\002\\000\\001L\\000\\004listt\\000\\020Ljava/util/List;xr\\000,java.util.Collections$UnmodifiableCollection\\031B\\000\\200\\313^\\367\\036\\002\\000\\001L\\000\\001cq\\000~\\000\\004xpsr\\000\\023java.util.ArrayListx\\201\\322\\035\\231\\307a\\235\\003\\000\\001I\\000\\004sizexp\\000\\000\\000\\002w\\004\\000\\000\\000\\002sr\\000\\031com.simon.model.Authority\\000\\000\\000\\000\\000\\000\\000\\001\\002\\000\\010L\\000\\011authorityt\\000\\022Ljava/lang/String;L\\000\\010createByt\\000\\020Ljava/lang/Long;L\\000\\012createDatet\\000\\020Ljava/util/Date;L\\000\\002idq\\000~\\000\\017L\\000\\010updateByq\\000~\\000\\017L\\000\\012updateDateq\\000~\\000\\020L\\000\\006userIdq\\000~\\000\\017L\\000\\010usernameq\\000~\\000\\016xpt\\000\\012ROLE_ADMINpppppsr\\000\\016java.lang.Long;\\213\\344\\220\\314\\217#\\337\\002\\000\\001J\\000\\005valuexr\\000\\020java.lang.Number\\206\\254\\225\\035\\013\\224\\340\\213\\002\\000\\000xp\\000\\000\\000\\000;\\232\\312\\000psq\\000~\\000\\015t\\000\\011ROLE_USERpppppsq\\000~\\000\\023\\000\\000\\000\\000;\\232\\312\\000pxq\\000~\\000\\014psr\\000:org.springframework.security.oauth2.provider.OAuth2Request\\000\\000\\000\\000\\000\\000\\000\\001\\002\\000\\007Z\\000\\010approvedL\\000\\013authoritiesq\\000~\\000\\004L\\000\\012extensionst\\000\\017Ljava/util/Map;L\\000\\013redirectUriq\\000~\\000\\016L\\000\\007refresht\\000;Lorg/springframework/security/oauth2/provider/TokenRequest;L\\000\\013resourceIdst\\000\\017Ljava/util/Set;L\\000\\015responseTypesq\\000~\\000\\034xr\\0008org.springframework.security.oauth2.provider.BaseRequest6(z>\\243qi\\275\\002\\000\\003L\\000\\010clientIdq\\000~\\000\\016L\\000\\021requestParametersq\\000~\\000\\032L\\000\\005scopeq\\000~\\000\\034xpt\\000\\020clientIdPasswordsr\\000%java.util.Collections$UnmodifiableMap\\361\\245\\250\\376t\\365\\007B\\002\\000\\001L\\000\\001mq\\000~\\000\\032xpsr\\000\\021java.util.HashMap\\005\\007\\332\\301\\303\\026`\\321\\003\\000\\002F\\000\\012loadFactorI\\000\\011thresholdxp?@\\000\\000\\000\\000\\000\\003w\\010\\000\\000\\000\\004\\000\\000\\000\\002t\\000\\012grant_typet\\000\\010passwordt\\000\\010usernamet\\000\\01318800000000xsr\\000%java.util.Collections$UnmodifiableSet\\200\\035\\222\\321\\217\\233\\200U\\002\\000\\000xq\\000~\\000\\011sr\\000\\027java.util.LinkedHashSet\\330l\\327Z\\225\\335*\\036\\002\\000\\000xr\\000\\021java.util.HashSet\\272D\\205\\225\\226\\270\\2674\\003\\000\\000xpw\\014\\000\\000\\000\\020?@\\000\\000\\000\\000\\000\\001t\\000\\020read,write,trustx\\001sq\\000~\\000+w\\014\\000\\000\\000\\020?@\\000\\000\\000\\000\\000\\002sr\\000Borg.springframework.security.core.authority.SimpleGrantedAuthority\\000\\000\\000\\000\\000\\000\\001\\244\\002\\000\\001L\\000\\004roleq\\000~\\000\\016xpt\\000\\011ROLE_USERsq\\000~\\000/t\\000\\012ROLE_ADMINxsq\\000~\\000"?@\\000\\000\\000\\000\\000\\000w\\010\\000\\000\\000\\020\\000\\000\\000\\000xppsq\\000~\\000+w\\014\\000\\000\\000\\020?@\\000\\000\\000\\000\\000\\000xsq\\000~\\000+w\\014\\000\\000\\000\\020?@\\000\\000\\000\\000\\000\\000xsr\\000Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\\000\\000\\000\\000\\000\\000\\001\\244\\002\\000\\002L\\000\\013credentialsq\\000~\\000\\005L\\000\\011principalq\\000~\\000\\005xq\\000~\\000\\003\\001sq\\000~\\000\\007sq\\000~\\000\\013\\000\\000\\000\\002w\\004\\000\\000\\000\\002q\\000~\\000\\021q\\000~\\000\\026xq\\000~\\000:sr\\000\\027java.util.LinkedHashMap4\\300N\\\\\\020l\\300\\373\\002\\000\\001Z\\000\\013accessOrderxq\\000~\\000"?@\\000\\000\\000\\000\\000\\006w\\010\\000\\000\\000\\010\\000\\000\\000\\002q\\000~\\000$q\\000~\\000%q\\000~\\000&q\\000~\\000''''x\\000psr\\000"com.simon.common.domain.UserEntity''''V:\\223\\313}:L\\002\\000\\015Z\\000\\007enabledL\\000\\007addressq\\000~\\000\\016L\\000\\003aget\\000\\023Ljava/lang/Integer;L\\000\\013authoritiesq\\000~\\000\\010L\\000\\005birthq\\000~\\000\\020L\\000\\005emailq\\000~\\000\\016L\\000\\011headPhotoq\\000~\\000\\016L\\000\\002idq\\000~\\000\\017L\\000\\010passwordq\\000~\\000\\016L\\000\\013personBriefq\\000~\\000\\016L\\000\\005phoneq\\000~\\000\\016L\\000\\003sext\\000\\023Ljava/lang/Boolean;L\\000\\010usernameq\\000~\\000\\016xp\\001psr\\000\\021java.lang.Integer\\022\\342\\240\\244\\367\\201\\2078\\002\\000\\001I\\000\\005valuexq\\000~\\000\\024\\000\\000\\000\\000sq\\000~\\000\\013\\000\\000\\000\\002w\\004\\000\\000\\000\\002q\\000~\\000\\021q\\000~\\000\\026xpt\\000\\02318800000000@163.compsq\\000~\\000\\023\\000\\000\\000\\000;\\232\\312\\000t\\000<$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxupt\\000\\01318800000000sr\\000\\021java.lang.Boolean\\315 r\\200\\325\\234\\372\\356\\002\\000\\001Z\\000\\005valuexp\\000t\\000\\006jeesun'');

-- ----------------------------
-- Table structure for t_account_bind
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_account_bind";
CREATE TABLE "public"."t_account_bind" (
  "id" int8 NOT NULL DEFAULT nextval(''t_account_bind_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "user_id" int8,
  "account_type" int4,
  "account_no" varchar(50) COLLATE "pg_catalog"."default",
  "password" varchar(50) COLLATE "pg_catalog"."default",
  "secret_key" varchar(50) COLLATE "pg_catalog"."default",
  "is_bind" bit(1),
  "overdue_time" timestamp(6),
  "status" varchar(32) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for t_authorities
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_authorities";
CREATE TABLE "public"."t_authorities" (
  "id" int8 NOT NULL DEFAULT nextval(''authorities_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "user_id" int8 NOT NULL,
  "authority" varchar(50) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of t_authorities
-- ----------------------------
INSERT INTO "public"."t_authorities" VALUES (5, NULL, NULL, NULL, 1000000000, ''ROLE_ADMIN'');
INSERT INTO "public"."t_authorities" VALUES (6, NULL, NULL, NULL, 1000000000, ''ROLE_USER'');

-- ----------------------------
-- Table structure for t_bill
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_bill";
CREATE TABLE "public"."t_bill" (
  "id" int8 NOT NULL DEFAULT nextval(''t_bill_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "bill_type" varchar(50) COLLATE "pg_catalog"."default",
  "bill_status" varchar(50) COLLATE "pg_catalog"."default",
  "bill_desc" varchar(100) COLLATE "pg_catalog"."default",
  "receiving_address" varchar(100) COLLATE "pg_catalog"."default",
  "logistics_info" varchar(50) COLLATE "pg_catalog"."default",
  "logistics_no" varchar(50) COLLATE "pg_catalog"."default",
  "logistics_status" varchar(50) COLLATE "pg_catalog"."default",
  "bill_date" timestamp(6),
  "total_amount" float4,
  "quantity" int4,
  "out_trade_no" varchar(50) COLLATE "pg_catalog"."default",
  "to_member_name" varchar(50) COLLATE "pg_catalog"."default",
  "to_member_id" varchar(50) COLLATE "pg_catalog"."default",
  "payment_type" varchar(50) COLLATE "pg_catalog"."default",
  "payment_account_no" varchar(50) COLLATE "pg_catalog"."default",
  "reason" varchar(255) COLLATE "pg_catalog"."default",
  "integral_reward" int4,
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "bill_context" text COLLATE "pg_catalog"."default",
  "user_id" int8
)
;

-- ----------------------------
-- Table structure for t_dict_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_dict_type";
CREATE TABLE "public"."t_dict_type" (
  "id" int8 NOT NULL,
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "type_code" varchar(255) COLLATE "pg_catalog"."default",
  "type_name" varchar(255) COLLATE "pg_catalog"."default",
  "type_group_id" int8 NOT NULL,
  "type_group_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "order_num" int4
)
;

-- ----------------------------
-- Records of t_dict_type
-- ----------------------------
INSERT INTO "public"."t_dict_type" VALUES (100000001, NULL, NULL, NULL, NULL, ''0'', ''phone'', 1, '''', 1);
INSERT INTO "public"."t_dict_type" VALUES (100000002, NULL, NULL, NULL, NULL, ''1'', ''web'', 1, '''', 2);
INSERT INTO "public"."t_dict_type" VALUES (100000003, NULL, NULL, NULL, NULL, ''0'', ''不可用'', 2, '''', NULL);
INSERT INTO "public"."t_dict_type" VALUES (100000004, NULL, NULL, NULL, NULL, ''1'', ''可用'', 2, '''', NULL);
INSERT INTO "public"."t_dict_type" VALUES (100000005, NULL, NULL, NULL, NULL, ''ROLE_USER'', ''普通用户'', 3, ''role_type'', 2);
INSERT INTO "public"."t_dict_type" VALUES (100000006, NULL, NULL, NULL, NULL, ''ROLE_ADMIN'', ''管理员'', 3, ''role_type'', 1);
INSERT INTO "public"."t_dict_type" VALUES (100000007, NULL, NULL, NULL, NULL, ''ROLE_SU'', ''超管'', 3, ''role_type'', 0);
INSERT INTO "public"."t_dict_type" VALUES (100000008, NULL, NULL, NULL, NULL, ''true'', ''男'', 4, ''sex'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (100000009, NULL, NULL, NULL, NULL, ''false'', ''女'', 4, ''sex'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (100000010, NULL, ''2018-11-19 10:23:45'', NULL, NULL, ''0'', ''离线'', 5, '''', 1);
INSERT INTO "public"."t_dict_type" VALUES (100000011, NULL, ''2018-11-19 11:27:11'', NULL, NULL, ''1'', ''在线'', 5, '''', 2);
INSERT INTO "public"."t_dict_type" VALUES (100000012, NULL, NULL, NULL, NULL, ''0'', ''关闭'', 6, '''', NULL);
INSERT INTO "public"."t_dict_type" VALUES (100000013, NULL, NULL, NULL, NULL, ''1'', ''启用'', 6, '''', NULL);
INSERT INTO "public"."t_dict_type" VALUES (100000014, NULL, NULL, NULL, NULL, ''1'', ''一级菜单'', 7, '''', 1);
INSERT INTO "public"."t_dict_type" VALUES (100000015, NULL, NULL, NULL, NULL, ''2'', ''二级菜单'', 7, '''', 2);
INSERT INTO "public"."t_dict_type" VALUES (95854276256989184, NULL, ''2018-11-19 10:23:38'', NULL, NULL, ''2'', ''勿扰'', 5, '''', 3);
INSERT INTO "public"."t_dict_type" VALUES (96958562013544448, NULL, ''2018-11-22 11:34:26'', NULL, NULL, ''1'', ''待付款'', 96950073392365568, ''bill_status'', 1);
INSERT INTO "public"."t_dict_type" VALUES (96959193222742016, NULL, ''2018-11-22 11:34:11'', NULL, NULL, ''2'', ''已付款，待发货'', 96950073392365568, ''bill_status'', 2);
INSERT INTO "public"."t_dict_type" VALUES (96961451628953600, NULL, ''2018-11-22 11:43:09'', NULL, NULL, ''3'', ''已发货，待收货'', 96950073392365568, ''bill_status'', 1);
INSERT INTO "public"."t_dict_type" VALUES (96962768984342528, NULL, ''2018-11-22 11:48:23'', NULL, NULL, ''4'', ''交易成功'', 96950073392365568, ''bill_status'', 4);
INSERT INTO "public"."t_dict_type" VALUES (96963159130112000, NULL, ''2018-11-22 11:49:56'', NULL, NULL, ''5'', ''交易关闭'', 96950073392365568, ''bill_status'', 5);
INSERT INTO "public"."t_dict_type" VALUES (96963562651516928, NULL, ''2018-11-22 11:51:33'', NULL, NULL, ''6'', ''退款中'', 96950073392365568, ''bill_status'', 6);
INSERT INTO "public"."t_dict_type" VALUES (97357187684237312, NULL, ''2018-11-23 13:55:40'', NULL, NULL, ''1'', ''充值'', 97356870024429568, ''bill_type'', 1);
INSERT INTO "public"."t_dict_type" VALUES (97366040169676800, NULL, ''2018-11-23 14:30:51'', NULL, NULL, ''alipay'', ''支付宝'', 97365554750291968, ''payment_type'', 1);
INSERT INTO "public"."t_dict_type" VALUES (97366110180999168, NULL, ''2018-11-23 14:31:07'', NULL, NULL, ''wxpay'', ''微信'', 97365554750291968, ''payment_type'', 2);
INSERT INTO "public"."t_dict_type" VALUES (97536125287006208, NULL, ''2018-11-24 01:46:42'', NULL, NULL, ''1'', ''产品消息'', 97535843673047040, ''msg_type'', 1);
INSERT INTO "public"."t_dict_type" VALUES (97536221990879232, NULL, ''2018-11-24 01:47:05'', NULL, NULL, ''2'', ''安全消息'', 97535843673047040, ''msg_type'', 2);
INSERT INTO "public"."t_dict_type" VALUES (97536285903683584, NULL, ''2018-11-24 01:47:21'', NULL, NULL, ''3'', ''服务消息'', 97535843673047040, ''msg_type'', 3);
INSERT INTO "public"."t_dict_type" VALUES (97536343411785728, NULL, ''2018-11-24 01:47:34'', NULL, NULL, ''4'', ''活动消息'', 97535843673047040, ''msg_type'', 4);
INSERT INTO "public"."t_dict_type" VALUES (97536415625117696, NULL, ''2018-11-24 01:47:51'', NULL, NULL, ''5'', ''历史消息'', 97535843673047040, ''msg_type'', 5);
INSERT INTO "public"."t_dict_type" VALUES (97536494998126592, NULL, ''2018-11-24 01:48:10'', NULL, NULL, ''6'', ''故障消息'', 97535843673047040, ''msg_type'', 6);
INSERT INTO "public"."t_dict_type" VALUES (99136468701675520, NULL, ''2018-11-28 11:45:54'', NULL, NULL, ''1'', ''banner'', 99135515659337728, ''news_type'', 1);
INSERT INTO "public"."t_dict_type" VALUES (106065464517459968, NULL, ''2018-12-17 14:39:15'', NULL, NULL, ''easyui-calendar'', ''easyui-calendar'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106065623204757504, NULL, ''2018-12-17 14:39:53'', NULL, NULL, ''easyui-checkbox'', ''easyui-checkbox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106065824678150144, NULL, ''2018-12-17 14:40:41'', NULL, NULL, ''easyui-combobox'', ''easyui-combobox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106065929544138752, NULL, ''2018-12-17 14:41:06'', NULL, NULL, ''easyui-datebox'', ''easyui-datebox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106066093541425152, NULL, ''2018-12-17 14:41:45'', NULL, NULL, ''easyui-datetimebox'', ''easyui-datetimebox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106066157286457344, NULL, ''2018-12-17 14:42:00'', NULL, NULL, ''easyui-textbox'', ''easyui-textbox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106066363230978048, NULL, ''2018-12-17 14:42:49'', NULL, NULL, ''easyui-datetimespinner'', ''easyui-datetimespinner'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106066513508696064, NULL, ''2018-12-17 14:43:25'', NULL, NULL, ''easyui-filebox'', ''easyui-filebox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106066710796173312, NULL, ''2018-12-17 14:44:12'', NULL, NULL, ''easyui-maskedbox'', ''easyui-maskedbox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106066824096907264, NULL, ''2018-12-17 14:44:39'', NULL, NULL, ''easyui-numberbox'', ''easyui-numberbox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106066986638770176, NULL, ''2018-12-17 14:45:18'', NULL, NULL, ''easyui-numberspinner'', ''easyui-numberspinner'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106067095950721024, NULL, ''2018-12-17 14:45:44'', NULL, NULL, ''easyui-passwordbox'', ''easyui-passwordbox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106067188607090688, NULL, ''2018-12-17 14:46:06'', NULL, NULL, ''easyui-searchbox'', ''easyui-searchbox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106067282467225600, NULL, ''2018-12-17 14:46:29'', NULL, NULL, ''easyui-slider'', ''easyui-slider'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106067385764544512, NULL, ''2018-12-17 14:46:53'', NULL, NULL, ''easyui-switchbutton'', ''easyui-switchbutton'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106067475187105792, NULL, ''2018-12-17 14:47:15'', NULL, NULL, ''easyui-radiobutton'', ''easyui-radiobutton'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106067650790031360, NULL, ''2018-12-17 14:47:56'', NULL, NULL, ''easyui-tagbox'', ''easyui-tagbox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106067812547559424, NULL, ''2018-12-17 14:48:35'', NULL, NULL, ''easyui-timespinner'', ''easyui-timespinner'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106069021484384256, NULL, ''2018-12-17 14:53:23'', NULL, NULL, ''easyui-validatebox'', ''easyui-validatebox'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106350652392734720, NULL, ''2018-12-18 09:32:29'', NULL, NULL, ''image'', ''图片'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106351861832876032, NULL, ''2018-12-18 09:38:29'', NULL, NULL, ''rich_text'', ''富文本'', 99135515659337729, ''easyui_component'', 1);
INSERT INTO "public"."t_dict_type" VALUES (106353594952843264, NULL, ''2018-12-18 09:44:11'', NULL, NULL, ''t:select'', ''t:select'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (106353713701978112, NULL, ''2018-12-18 09:44:39'', NULL, NULL, ''t:dict'', ''t:dict'', 99135515659337729, ''easyui_component'', NULL);
INSERT INTO "public"."t_dict_type" VALUES (108265713587716096, NULL, ''2018-12-23 16:24:47'', NULL, NULL, ''1'', ''on'', 108265224984854528, ''job_status'', 1);
INSERT INTO "public"."t_dict_type" VALUES (108265787696873472, NULL, ''2018-12-23 18:01:51'', NULL, NULL, ''0'', ''off'', 108265224984854528, ''job_status'', 2);
INSERT INTO "public"."t_dict_type" VALUES (113787304258043904, NULL, ''2019-01-07 22:30:52'', NULL, NULL, ''1'', ''草稿'', 113787027396231168, ''news_status'', 1);
INSERT INTO "public"."t_dict_type" VALUES (113787421593698304, NULL, ''2019-01-07 22:30:58'', NULL, NULL, ''2'', ''发布'', 113787027396231168, ''news_status'', 2);
INSERT INTO "public"."t_dict_type" VALUES (119131930154237952, NULL, ''2019-01-22 16:00:43'', NULL, NULL, ''true'', ''有效'', 113787027396231169, ''enabled_status'', 1);
INSERT INTO "public"."t_dict_type" VALUES (119132047645081600, NULL, ''2019-01-22 16:01:11'', NULL, NULL, ''false'', ''无效'', 113787027396231169, ''enabled_status'', 2);

-- ----------------------------
-- Table structure for t_dict_type_group
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_dict_type_group";
CREATE TABLE "public"."t_dict_type_group" (
  "id" int8 NOT NULL,
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "type_group_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "type_group_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;

-- ----------------------------
-- Records of t_dict_type_group
-- ----------------------------
INSERT INTO "public"."t_dict_type_group" VALUES (1, NULL, NULL, NULL, NULL, ''login_type'', ''登录方式'');
INSERT INTO "public"."t_dict_type_group" VALUES (2, NULL, NULL, NULL, NULL, ''status'', ''状态'');
INSERT INTO "public"."t_dict_type_group" VALUES (3, NULL, NULL, NULL, NULL, ''role_type'', ''角色种类'');
INSERT INTO "public"."t_dict_type_group" VALUES (4, NULL, NULL, NULL, NULL, ''sex_type'', ''性别'');
INSERT INTO "public"."t_dict_type_group" VALUES (5, NULL, NULL, NULL, NULL, ''login_status'', ''登录状态'');
INSERT INTO "public"."t_dict_type_group" VALUES (6, NULL, NULL, NULL, NULL, ''alarm_clock_status'', ''闹钟状态'');
INSERT INTO "public"."t_dict_type_group" VALUES (7, NULL, NULL, NULL, NULL, ''menu_type'', ''菜单类型'');
INSERT INTO "public"."t_dict_type_group" VALUES (96950073392365568, NULL, ''2018-11-22 10:58:08'', NULL, NULL, ''bill_status'', ''订单状态'');
INSERT INTO "public"."t_dict_type_group" VALUES (97356870024429568, NULL, ''2018-11-23 13:54:24'', NULL, NULL, ''bill_type'', ''账单类型'');
INSERT INTO "public"."t_dict_type_group" VALUES (97365554750291968, NULL, ''2018-11-23 14:28:55'', NULL, NULL, ''payment_type'', ''支付方式'');
INSERT INTO "public"."t_dict_type_group" VALUES (97535843673047040, NULL, ''2018-11-24 01:45:35'', NULL, NULL, ''msg_type'', ''消息类型'');
INSERT INTO "public"."t_dict_type_group" VALUES (99135515659337728, NULL, ''2018-11-28 11:42:07'', NULL, NULL, ''news_type'', ''新闻类型'');
INSERT INTO "public"."t_dict_type_group" VALUES (99135515659337729, NULL, NULL, NULL, NULL, ''easyui_component'', ''EasyUI组件'');
INSERT INTO "public"."t_dict_type_group" VALUES (108265224984854528, NULL, ''2018-12-23 16:20:19'', NULL, NULL, ''job_status'', ''job状态'');
INSERT INTO "public"."t_dict_type_group" VALUES (113787027396231168, NULL, ''2019-01-07 22:01:59'', NULL, NULL, ''news_status'', ''新闻状态'');
INSERT INTO "public"."t_dict_type_group" VALUES (113787027396231169, NULL, NULL, NULL, NULL, ''enabled_status'', ''有效状态'');

-- ----------------------------
-- Table structure for t_log_login
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_log_login";
CREATE TABLE "public"."t_log_login" (
  "id" int8 NOT NULL DEFAULT nextval(''t_log_login_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "create_time" timestamp(6),
  "ip" varchar(255) COLLATE "pg_catalog"."default",
  "username" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for t_multi_language
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_multi_language";
CREATE TABLE "public"."t_multi_language" (
  "id" int8 NOT NULL DEFAULT nextval(''t_multi_language_id_seq''::regclass),
  "language_code" varchar(255) COLLATE "pg_catalog"."default",
  "reference" varchar(255) COLLATE "pg_catalog"."default",
  "message" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for t_news_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_news_info";
CREATE TABLE "public"."t_news_info" (
  "id" int8 NOT NULL DEFAULT nextval(''news_info_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "title" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" int8,
  "status" int4,
  "content" text COLLATE "pg_catalog"."default",
  "image_url" varchar(255) COLLATE "pg_catalog"."default",
  "publish_date" timestamp(6),
  "tags" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for t_news_tag
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_news_tag";
CREATE TABLE "public"."t_news_tag" (
  "id" int8 NOT NULL DEFAULT nextval(''news_tag_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "news_info_id" int8,
  "tag_id" int8
)
;

-- ----------------------------
-- Table structure for t_notice_msg
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_notice_msg";
CREATE TABLE "public"."t_notice_msg" (
  "id" int8 NOT NULL DEFAULT nextval(''t_notice_msg_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "user_id" int8,
  "msg_type" int4,
  "content" text COLLATE "pg_catalog"."default",
  "is_read" bool
)
;

-- ----------------------------
-- Table structure for t_qr_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_qr_code";
CREATE TABLE "public"."t_qr_code" (
  "id" int8 NOT NULL DEFAULT nextval(''qr_code_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "is_ok" bool,
  "sid" varchar(255) COLLATE "pg_catalog"."default",
  "token" varchar(255) COLLATE "pg_catalog"."default",
  "username" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for t_reset_pwd_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_reset_pwd_info";
CREATE TABLE "public"."t_reset_pwd_info" (
  "id" int8 NOT NULL DEFAULT nextval(''reset_pwd_info_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "expires_in" timestamp(6),
  "secret_key" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" int8,
  "valid" bool NOT NULL
)
;

-- ----------------------------
-- Table structure for t_s_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_s_quartz_job";
CREATE TABLE "public"."t_s_quartz_job" (
  "id" int8 NOT NULL DEFAULT nextval(''t_s_quartz_job_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "cron_expression" varchar(50) COLLATE "pg_catalog"."default",
  "method_name" varchar(255) COLLATE "pg_catalog"."default",
  "is_concurrent" int4,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "bean_name" varchar(255) COLLATE "pg_catalog"."default",
  "trigger_name" varchar(255) COLLATE "pg_catalog"."default",
  "job_status" int4,
  "spring_bean" varchar(255) COLLATE "pg_catalog"."default",
  "job_name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for t_side_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_side_menu";
CREATE TABLE "public"."t_side_menu" (
  "id" int8 NOT NULL DEFAULT nextval(''t_side_menu_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "url" varchar(255) COLLATE "pg_catalog"."default",
  "request_method" varchar(25) COLLATE "pg_catalog"."default",
  "icon" varchar(255) COLLATE "pg_catalog"."default",
  "pid" int8,
  "order_num" int4,
  "show_in_menu" bool,
  "entity_name" varchar(25) COLLATE "pg_catalog"."default",
  "remark" varchar(25) COLLATE "pg_catalog"."default",
  "link_id" int8
)
;

-- ----------------------------
-- Records of t_side_menu
-- ----------------------------
INSERT INTO "public"."t_side_menu" VALUES (119869773617172480, NULL, ''2019-01-24 16:52:39'', NULL, NULL, ''删除'', ''/api/quartzJobs/ids'', ''DELETE'', NULL, 119869773415845888, 0, ''t'', ''QuartzJob'', ''delete'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (12, NULL, NULL, NULL, NULL, ''权限管理'', ''/api/authorities?easyui-list'', ''*'', ''fa fa-users'', 1, 4, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (3, NULL, NULL, NULL, NULL, ''数据字典'', ''/dictTypeGroups?easyui-list'', ''*'', ''fa fa-book'', 1, 5, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (90997036798705664, NULL, NULL, NULL, NULL, ''druid'', ''/druid/index.html'', ''*'', ''fa fa-cog'', 90995059306004480, 3, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (8, NULL, NULL, NULL, NULL, ''菜单管理'', ''/sideMenus?easyui-list'', ''*'', ''fa fa-list-ol'', 1, 2, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (94108390556434432, NULL, ''2018-11-14 14:46:07'', NULL, NULL, ''了解OauthServer'', ''/easyui/home.html'', ''*'', '''', 94105929343041536, 1, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (1, NULL, NULL, NULL, NULL, ''系统管理'', '''', ''*'', ''fa fa-cog'', 0, 2, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (93126997827387392, NULL, NULL, NULL, NULL, ''日志管理'', ''/api/loggingEvents?easyui-list'', ''*'', ''fa fa-book'', 90995059306004480, 4, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (119869773600395264, NULL, ''2019-01-24 16:52:39'', NULL, NULL, ''修改'', ''/api/quartzJobs/edit'', ''GET,PATCH'', NULL, 119869773415845888, 0, ''f'', ''QuartzJob'', ''edit'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (80832133433655296, NULL, NULL, NULL, NULL, ''代码生成'', ''/tables?easyui-list'', ''*'', ''fa fa-table'', 90995059306004480, 1, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (90995059306004480, NULL, NULL, NULL, NULL, ''系统工具'', '''', ''*'', ''fa fa-user'', 0, 3, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (90995810619097088, NULL, NULL, NULL, NULL, ''swagger'', ''/swagger-ui.html'', ''*'', ''fa fa-cog'', 90995059306004480, 2, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (94105929343041536, NULL, ''2018-11-14 14:36:20'', NULL, NULL, ''主页'', NULL, ''*'', ''fa fa-home'', 0, 1, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (119869773587812352, NULL, ''2019-01-24 16:52:39'', NULL, NULL, ''新增'', ''/api/quartzJobs/add'', ''GET,POST'', NULL, 119869773415845888, 0, ''f'', ''QuartzJob'', ''add'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (118471356282179584, NULL, ''2019-01-20 20:15:50'', NULL, NULL, ''删除'', ''/api/newsInfos/ids'', ''DELETE'', NULL, 118471356118601728, 0, ''f'', ''NewsInfo'', ''delete'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (118471356265402368, NULL, ''2019-01-20 20:15:50'', NULL, NULL, ''修改'', ''/api/newsInfos/edit'', ''GET,PATCH'', NULL, 118471356118601728, 0, ''f'', ''NewsInfo'', ''edit'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (118471356248625152, NULL, ''2019-01-20 20:15:50'', NULL, NULL, ''新增'', ''/api/newsInfos/add'', ''GET,POST'', NULL, 118471356118601728, 0, ''f'', ''NewsInfo'', ''add'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (118471356227653632, NULL, ''2019-01-20 20:15:50'', NULL, NULL, ''查看'', ''/api/newsInfos/data'', ''GET'', NULL, 118471356118601728, 0, ''f'', ''NewsInfo'', ''data'', 118471356118601728);
INSERT INTO "public"."t_side_menu" VALUES (118471356118601728, NULL, ''2019-01-20 20:15:50'', NULL, NULL, ''新闻管理'', ''/api/newsInfos/list'', ''GET'', NULL, 94105929343041536, 0, ''t'', ''NewsInfo'', ''list'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (107876685461848070, NULL, NULL, NULL, NULL, ''角色管理'', ''/api/roleAuthorities/list'', ''GET'', ''fa fa-user'', 1, 6, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (107876685461848071, NULL, NULL, NULL, NULL, ''订单管理'', ''/api/bills?easyui-list'', ''*'', ''fa fa-usd'', 94105929343041536, 3, ''t'', NULL, NULL, NULL);
INSERT INTO "public"."t_side_menu" VALUES (119869773415845888, NULL, ''2019-01-24 16:52:39'', NULL, NULL, ''任务管理'', ''/api/quartzJobs/list'', ''GET'', ''fa fa-list-ul'', 1, 0, ''t'', ''QuartzJob'', ''list'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (119494224726069248, NULL, ''2019-01-23 16:00:21'', NULL, NULL, ''删除'', ''/api/oauthUsers/ids'', ''DELETE'', NULL, 119494224361164800, 0, ''f'', ''OauthUser'', ''delete'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (119869773566840832, NULL, ''2019-01-24 16:52:39'', NULL, NULL, ''查看'', ''/api/quartzJobs/data'', ''GET'', NULL, 119869773415845888, 0, ''f'', ''QuartzJob'', ''data'', 119869773415845888);
INSERT INTO "public"."t_side_menu" VALUES (119494224713486336, NULL, ''2019-01-23 16:00:21'', NULL, NULL, ''修改'', ''/api/oauthUsers/edit'', ''GET,PATCH'', NULL, 119494224361164800, 0, ''f'', ''OauthUser'', ''edit'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (119494224361164800, NULL, ''2019-01-23 16:00:21'', NULL, NULL, ''用户管理'', ''/api/oauthUsers/list'', ''GET'', NULL, 94105929343041536, 0, ''t'', ''OauthUser'', ''list'', NULL);
INSERT INTO "public"."t_side_menu" VALUES (119494224675737600, NULL, ''2019-01-23 16:00:21'', NULL, NULL, ''查看'', ''/api/oauthUsers/data'', ''GET'', NULL, 119494224361164800, 0, ''f'', ''OauthUser'', ''data'', 119494224361164800);
INSERT INTO "public"."t_side_menu" VALUES (119494224700903424, NULL, ''2019-01-23 16:00:21'', NULL, NULL, ''新增'', ''/api/oauthUsers/add'', ''GET,POST'', NULL, 119494224361164800, 0, ''f'', ''OauthUser'', ''add'', NULL);

-- ----------------------------
-- Table structure for t_side_menu_authority
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_side_menu_authority";
CREATE TABLE "public"."t_side_menu_authority" (
  "id" int8 NOT NULL DEFAULT nextval(''t_side_menu_authority_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "side_menu_id" int8,
  "authority" varchar(50) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of t_side_menu_authority
-- ----------------------------
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475709, NULL, NULL, NULL, NULL, 93126997827387392, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475708, NULL, NULL, NULL, NULL, 90997036798705664, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475707, NULL, NULL, NULL, NULL, 90995810619097088, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475706, NULL, NULL, NULL, NULL, 80832133433655296, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475705, NULL, NULL, NULL, NULL, 90995059306004480, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475704, NULL, NULL, NULL, NULL, 107876685461848070, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475703, NULL, NULL, NULL, NULL, 3, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475702, NULL, NULL, NULL, NULL, 12, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475701, NULL, NULL, NULL, NULL, 8, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475700, NULL, NULL, NULL, NULL, 107876685461848071, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475699, NULL, NULL, NULL, NULL, 94108390556434432, ''ROLE_ADMIN'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475740, NULL, NULL, NULL, NULL, 93126997827387392, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475738, NULL, NULL, NULL, NULL, 90995810619097088, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475739, NULL, NULL, NULL, NULL, 90997036798705664, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475736, NULL, NULL, NULL, NULL, 90995059306004480, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475737, NULL, NULL, NULL, NULL, 80832133433655296, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475732, NULL, NULL, NULL, NULL, 8, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475733, NULL, NULL, NULL, NULL, 12, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475734, NULL, NULL, NULL, NULL, 3, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475735, NULL, NULL, NULL, NULL, 107876685461848070, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475698, NULL, NULL, NULL, NULL, 119869773617172480, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475696, NULL, NULL, NULL, NULL, 119869773587812352, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475697, NULL, NULL, NULL, NULL, 119869773600395264, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475695, NULL, NULL, NULL, NULL, 119869773415845888, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475694, NULL, NULL, NULL, NULL, 119869773566840832, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475692, NULL, NULL, NULL, NULL, 119494224726069248, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475693, NULL, NULL, NULL, NULL, 119869773415845888, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475691, NULL, NULL, NULL, NULL, 119494224713486336, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475690, NULL, NULL, NULL, NULL, 119494224700903424, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475689, NULL, NULL, NULL, NULL, 119494224361164800, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475688, NULL, NULL, NULL, NULL, 119494224675737600, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475687, NULL, NULL, NULL, NULL, 119494224361164800, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475731, NULL, NULL, NULL, NULL, 119869773617172480, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475730, NULL, NULL, NULL, NULL, 119869773600395264, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475729, NULL, NULL, NULL, NULL, 119869773587812352, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475728, NULL, NULL, NULL, NULL, 119869773415845888, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475727, NULL, NULL, NULL, NULL, 119869773566840832, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475726, NULL, NULL, NULL, NULL, 119869773415845888, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475725, NULL, NULL, NULL, NULL, 1, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475724, NULL, NULL, NULL, NULL, 107876685461848071, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475723, NULL, NULL, NULL, NULL, 94108390556434432, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475722, NULL, NULL, NULL, NULL, 119494224726069248, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475686, NULL, NULL, NULL, NULL, 118471356282179584, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475721, NULL, NULL, NULL, NULL, 119494224713486336, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475720, NULL, NULL, NULL, NULL, 119494224700903424, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475719, NULL, NULL, NULL, NULL, 119494224361164800, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475718, NULL, NULL, NULL, NULL, 119494224675737600, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475717, NULL, NULL, NULL, NULL, 119494224361164800, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475716, NULL, NULL, NULL, NULL, 118471356282179584, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475685, NULL, NULL, NULL, NULL, 118471356265402368, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475684, NULL, NULL, NULL, NULL, 118471356248625152, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475683, NULL, NULL, NULL, NULL, 118471356118601728, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475682, NULL, NULL, NULL, NULL, 118471356227653632, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475681, NULL, NULL, NULL, NULL, 118471356118601728, ''ROLE_USER'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475715, NULL, NULL, NULL, NULL, 118471356265402368, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475714, NULL, NULL, NULL, NULL, 118471356248625152, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475713, NULL, NULL, NULL, NULL, 118471356118601728, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475712, NULL, NULL, NULL, NULL, 118471356227653632, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475711, NULL, NULL, NULL, NULL, 118471356118601728, ''ROLE_SU'');
INSERT INTO "public"."t_side_menu_authority" VALUES (119869773688475710, NULL, NULL, NULL, NULL, 94105929343041536, ''ROLE_SU'');

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_users";
CREATE TABLE "public"."t_users" (
  "id" int8 NOT NULL DEFAULT nextval(''users_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "username" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "enabled" bool NOT NULL,
  "area_code" varchar(25) COLLATE "pg_catalog"."default",
  "phone" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "ssoid" varchar(255) COLLATE "pg_catalog"."default",
  "album_id" int8,
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "age" int4,
  "birth" date,
  "head_photo" varchar(255) COLLATE "pg_catalog"."default",
  "person_brief" varchar(255) COLLATE "pg_catalog"."default",
  "sex" bool,
  "visit_card" varchar(255) COLLATE "pg_catalog"."default",
  "login_type" int4,
  "login_status" varchar(25) COLLATE "pg_catalog"."default",
  "login_date" timestamp(6)
)
;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO "public"."t_users" VALUES (1000000000, NULL, NULL, NULL, NULL, ''jeesun'', ''$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxu'', ''t'', ''+86'', ''18800000000'', ''18800000000@163.com'', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "public"."t_users" VALUES (1000000001, NULL, NULL, NULL, NULL, ''user2711'', ''$2a$11$BUiKPp8.pcym7sxXYPvZeOjl0BOoVl3PZT.1Wfb3kmIgooO/GfQ4G'', ''t'', ''+86'', ''18800000001'', ''18800000001@163.com'', NULL, NULL, NULL, NULL, ''2018-01-30'', NULL, NULL, ''f'', NULL, NULL, NULL, ''2018-10-27 02:56:23'');
INSERT INTO "public"."t_users" VALUES (1000000002, NULL, NULL, NULL, NULL, ''user6745'', ''$2a$11$a7XDbu2RvLjZdr4kCvqh2u7gwVXhwxdauIzbX3ZizbBU.HeV8BOky'', ''t'', ''+86'', ''18800000002'', ''18800000002@163.com'', NULL, NULL, NULL, NULL, ''2009-09-14'', NULL, NULL, ''f'', NULL, NULL, NULL, ''2018-10-27 03:38:24'');
INSERT INTO "public"."t_users" VALUES (1000000003, NULL, NULL, NULL, NULL, ''simon'', ''$2a$11$t4akVchfgOv00XxB/ZKLlOmweUoL/Aed4CiJqQjaiRLZpBU3AWfxu'', ''t'', ''+86'', ''18800000003'', ''18800000003@163.com'', NULL, NULL, NULL, NULL, NULL, ''/fileUpload/微信图片_20181016142155.png'', NULL, ''f'', NULL, NULL, NULL, ''2019-01-22 17:48:48'');
INSERT INTO "public"."t_users" VALUES (1000000004, NULL, NULL, NULL, NULL, ''sss3'', ''$2a$11$rTQdNRld6Cc02vK9btH6tupdQW7aqbfcNlaD8ioigbADeHJWEcLM6'', ''f'', ''+86'', ''18800000004'', ''18800000004@163.com'', NULL, NULL, '''', NULL, NULL, NULL, '''', ''f'', NULL, NULL, NULL, ''2019-01-23 17:48:50'');
INSERT INTO "public"."t_users" VALUES (1000000005, NULL, NULL, NULL, NULL, ''admin'', ''$2a$11$7pwrZUA2nOx8jWr6P1H3f.eEsmUnFmzZOs1jVxEEQSXa3HForbGKS'', ''t'', ''+86'', ''18800000005'', ''18800000005@163.com'', NULL, NULL, '''', NULL, NULL, NULL, '''', ''t'', NULL, NULL, NULL, ''2019-01-22 17:48:53'');

-- ----------------------------
-- Table structure for t_veri_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_veri_code";
CREATE TABLE "public"."t_veri_code" (
  "id" int8 NOT NULL DEFAULT nextval(''veri_code_id_seq''::regclass),
  "create_by" int8,
  "create_date" timestamp(6),
  "update_by" int8,
  "update_date" timestamp(6),
  "code" int4,
  "create_time" int8,
  "expires" int4,
  "phone" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval(''"public"."authorities_id_seq"'', 9, true);
SELECT setval(''"public"."log_login_id_seq"'', 2, false);
SELECT setval(''"public"."logging_event_id_seq"'', 2, false);
SELECT setval(''"public"."news_info_id_seq"'', 2, false);
SELECT setval(''"public"."news_tag_id_seq"'', 2, false);
SELECT setval(''"public"."qr_code_id_seq"'', 2, false);
SELECT setval(''"public"."reset_pwd_info_id_seq"'', 2, false);
ALTER SEQUENCE "public"."t_account_bind_id_seq"
OWNED BY "public"."t_account_bind"."id";
SELECT setval(''"public"."t_account_bind_id_seq"'', 2, false);
ALTER SEQUENCE "public"."t_bill_id_seq"
OWNED BY "public"."t_bill"."id";
SELECT setval(''"public"."t_bill_id_seq"'', 2, false);
ALTER SEQUENCE "public"."t_log_login_id_seq"
OWNED BY "public"."t_log_login"."id";
SELECT setval(''"public"."t_log_login_id_seq"'', 2, false);
ALTER SEQUENCE "public"."t_multi_language_id_seq"
OWNED BY "public"."t_multi_language"."id";
SELECT setval(''"public"."t_multi_language_id_seq"'', 2, false);
ALTER SEQUENCE "public"."t_notice_msg_id_seq"
OWNED BY "public"."t_notice_msg"."id";
SELECT setval(''"public"."t_notice_msg_id_seq"'', 2, false);
ALTER SEQUENCE "public"."t_s_quartz_job_id_seq"
OWNED BY "public"."t_s_quartz_job"."id";
SELECT setval(''"public"."t_s_quartz_job_id_seq"'', 2, false);
ALTER SEQUENCE "public"."t_side_menu_authority_id_seq"
OWNED BY "public"."t_side_menu_authority"."id";
SELECT setval(''"public"."t_side_menu_authority_id_seq"'', 2, false);
ALTER SEQUENCE "public"."t_side_menu_id_seq"
OWNED BY "public"."t_side_menu"."id";
SELECT setval(''"public"."t_side_menu_id_seq"'', 2, false);
SELECT setval(''"public"."users_id_seq"'', 4, false);
SELECT setval(''"public"."veri_code_id_seq"'', 2, true);

-- ----------------------------
-- Primary Key structure for table logging_event
-- ----------------------------
ALTER TABLE "public"."logging_event" ADD CONSTRAINT "logging_event_pkey" PRIMARY KEY ("event_id");

-- ----------------------------
-- Primary Key structure for table logging_event_exception
-- ----------------------------
ALTER TABLE "public"."logging_event_exception" ADD CONSTRAINT "logging_event_exception_pkey" PRIMARY KEY ("event_id", "i");

-- ----------------------------
-- Primary Key structure for table logging_event_property
-- ----------------------------
ALTER TABLE "public"."logging_event_property" ADD CONSTRAINT "logging_event_property_pkey" PRIMARY KEY ("event_id", "mapped_key");

-- ----------------------------
-- Primary Key structure for table oauth_client_details
-- ----------------------------
ALTER TABLE "public"."oauth_client_details" ADD CONSTRAINT "oauth_client_details_pkey" PRIMARY KEY ("client_id");

-- ----------------------------
-- Primary Key structure for table t_account_bind
-- ----------------------------
ALTER TABLE "public"."t_account_bind" ADD CONSTRAINT "t_account_bind_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_authorities
-- ----------------------------
CREATE UNIQUE INDEX "ix_auth_username" ON "public"."t_authorities" USING btree (
  "user_id" "pg_catalog"."int8_ops" ASC NULLS LAST,
  "authority" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table t_authorities
-- ----------------------------
ALTER TABLE "public"."t_authorities" ADD CONSTRAINT "t_authorities_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_bill
-- ----------------------------
ALTER TABLE "public"."t_bill" ADD CONSTRAINT "t_bill_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_dict_type
-- ----------------------------
ALTER TABLE "public"."t_dict_type" ADD CONSTRAINT "t_dict_type_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_dict_type_group
-- ----------------------------
ALTER TABLE "public"."t_dict_type_group" ADD CONSTRAINT "t_dict_type_group_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_log_login
-- ----------------------------
ALTER TABLE "public"."t_log_login" ADD CONSTRAINT "t_log_login_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_multi_language
-- ----------------------------
ALTER TABLE "public"."t_multi_language" ADD CONSTRAINT "t_multi_language_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_news_info
-- ----------------------------
ALTER TABLE "public"."t_news_info" ADD CONSTRAINT "t_news_info_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_news_tag
-- ----------------------------
ALTER TABLE "public"."t_news_tag" ADD CONSTRAINT "t_news_tag_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_notice_msg
-- ----------------------------
ALTER TABLE "public"."t_notice_msg" ADD CONSTRAINT "t_notice_msg_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_qr_code
-- ----------------------------
ALTER TABLE "public"."t_qr_code" ADD CONSTRAINT "t_qr_code_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_reset_pwd_info
-- ----------------------------
ALTER TABLE "public"."t_reset_pwd_info" ADD CONSTRAINT "t_reset_pwd_info_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_s_quartz_job
-- ----------------------------
ALTER TABLE "public"."t_s_quartz_job" ADD CONSTRAINT "t_s_quartz_job_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_side_menu
-- ----------------------------
ALTER TABLE "public"."t_side_menu" ADD CONSTRAINT "t_side_menu_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_side_menu_authority
-- ----------------------------
ALTER TABLE "public"."t_side_menu_authority" ADD CONSTRAINT "t_side_menu_authority_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table t_users
-- ----------------------------
ALTER TABLE "public"."t_users" ADD CONSTRAINT "uk_users_email" UNIQUE ("email");
ALTER TABLE "public"."t_users" ADD CONSTRAINT "uk_users_phone" UNIQUE ("phone");

-- ----------------------------
-- Primary Key structure for table t_users
-- ----------------------------
ALTER TABLE "public"."t_users" ADD CONSTRAINT "t_users_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_veri_code
-- ----------------------------
ALTER TABLE "public"."t_veri_code" ADD CONSTRAINT "t_veri_code_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table logging_event_exception
-- ----------------------------
ALTER TABLE "public"."logging_event_exception" ADD CONSTRAINT "logging_event_exception_event_id_fkey" FOREIGN KEY ("event_id") REFERENCES "public"."logging_event" ("event_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table logging_event_property
-- ----------------------------
ALTER TABLE "public"."logging_event_property" ADD CONSTRAINT "logging_event_property_event_id_fkey" FOREIGN KEY ("event_id") REFERENCES "public"."logging_event" ("event_id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table t_authorities
-- ----------------------------
ALTER TABLE "public"."t_authorities" ADD CONSTRAINT "t_authorities_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "public"."t_users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table t_dict_type
-- ----------------------------
ALTER TABLE "public"."t_dict_type" ADD CONSTRAINT "fk_type_group_id" FOREIGN KEY ("type_group_id") REFERENCES "public"."t_dict_type_group" ("id") ON DELETE RESTRICT ON UPDATE RESTRICT;


-- update by 20190318
INSERT INTO t_authorities VALUES (6, NULL, NULL, NULL, 1000000000, 'ROLE_SU');
INSERT INTO t_s_quartz_job VALUES (1, 1, '2018-12-21 22:21:26', 1000000000, '2019-01-25 14:25:43', '*/5 * * * * ?', 'execute', 0, '测试', 'com.simon.task.SampleTask', 'org.quartz.CronTrigger', 0, NULL, 'com.simon.task.SampleTask');
