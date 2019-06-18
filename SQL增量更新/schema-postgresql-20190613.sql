-- 2019-05-21 by simon 修改数据
update t_side_menu set url='/api/loggingEvents/list' where id=93126997827387392;

-- 2019-05-23 by simon 删除t_veri_code表
drop table t_veri_code;

-- 2019-05-23 by simon 饿了么组件字典添加富文本
INSERT INTO t_dict_type ( id, type_code, type_name, type_group_id, type_group_code, order_num ) VALUES ( 155566528442400770, 'neditor', '富文本', 155564655257845760, 'element_component', 15 );

-- 2019-05-27 by simon
-- 添加图标数据
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (45, 1000000000, '2019-05-21 14:58:07', NULL, NULL, 'fas fa-save', 'save', '保存', 1, 1);
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (46, 1000000000, '2019-05-24 09:43:16', NULL, NULL, 'fas fa-play-circle', 'play-circle', '播放，开始', 1, 1);
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (47, 1000000000, '2019-05-24 09:44:00', NULL, NULL, 'fas fa-stop-circle', 'stop-circle', '停止，结束', 1, 1);
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (48, 1000000000, '2019-05-24 09:47:36', NULL, NULL, 'fas fa-trash-alt', 'trash-alt', '删除', 1, 1);
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (49, 1000000000, '2019-05-24 09:48:22', NULL, NULL, 'fas fa-edit', 'edit', '编辑，修改', 1, 1);
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (50, 1000000000, '2019-05-24 09:49:52', NULL, NULL, 'fas fa-play', 'play', '播放，开始', 1, 1);
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (51, 1000000000, '2019-05-24 09:50:34', 1000000000, '2019-05-24 09:51:29', 'fas fa-stop', 'stop', '停止，结束', 1, 1);
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (52, 1000000000, '2019-05-24 09:50:56', NULL, NULL, 'fas fa-pause', 'pause', '暂停', 1, 1);
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (53, 1000000000, '2019-05-24 09:51:08', NULL, NULL, 'fas fa-pause-circle', 'pause-circle', '暂停', 1, 1);
INSERT INTO t_s_font_awesome(id, create_by, create_date, update_by, update_date, icon_class, label, tags, order_num, status) VALUES (54, 1000000000, '2019-05-27 09:56:57', NULL, NULL, 'fas fa-language', 'language', '语言，翻译', 1, 1);
-- 添加菜单数据
INSERT INTO t_side_menu(id, create_by, create_date, update_by, update_date, name, url, request_method, icon, pid, order_num, show_in_menu, entity_name, remark, link_id, menu_type) VALUES (163710764928991232, 1000000000, '2019-05-25 16:21:06', NULL, NULL, '富文本', '/vue/demo/ueditor', 'GET', 'fas fa-file', 155328152225185792, 2, 1, NULL, '', NULL, 2);
INSERT INTO t_side_menu(id, create_by, create_date, update_by, update_date, name, url, request_method, icon, pid, order_num, show_in_menu, entity_name, remark, link_id, menu_type) VALUES (164071030476767232, 1000000000, '2019-05-26 16:12:40', NULL, NULL, '个人中心', '/api/oauthUsers/userCenter', 'GET', 'fas fa-user-circle', 94105929343041536, 4, 1, NULL, '', NULL, 2);
-- 添加菜单权限
INSERT INTO t_side_menu_authority(id, create_by, create_date, update_by, update_date, side_menu_id, authority) VALUES (154250076330529404, NULL, NULL, NULL, NULL, 163710764928991232, 'ROLE_ADMIN');
INSERT INTO t_side_menu_authority(id, create_by, create_date, update_by, update_date, side_menu_id, authority) VALUES (154250076330529405, NULL, NULL, NULL, NULL, 163710764928991232, 'ROLE_SU');
INSERT INTO t_side_menu_authority(id, create_by, create_date, update_by, update_date, side_menu_id, authority) VALUES (154250076330529406, NULL, NULL, NULL, NULL, 163710764928991232, 'ROLE_USER');
INSERT INTO t_side_menu_authority(id, create_by, create_date, update_by, update_date, side_menu_id, authority) VALUES (154250076330529407, NULL, NULL, NULL, NULL, 164071030476767232, 'ROLE_SU');
INSERT INTO t_side_menu_authority(id, create_by, create_date, update_by, update_date, side_menu_id, authority) VALUES (154250076330529408, NULL, NULL, NULL, NULL, 164071030476767232, 'ROLE_ADMIN');
INSERT INTO t_side_menu_authority(id, create_by, create_date, update_by, update_date, side_menu_id, authority) VALUES (154250076330529409, NULL, NULL, NULL, NULL, 164071030476767232, 'ROLE_USER');
-- 修改菜单demo为组件管理
update t_side_menu SET name='组件管理',icon='fas fa-code' where id=155328152225185792;
-- 修改菜单“图标管理”的父菜单为“组件管理”
update t_side_menu set pid=155328152225185792 where id=154250076045316096;

-- 2019-05-30 by simon 修改t_s_column_ui的id列为int8
alter table t_s_column_ui alter column id type int8;

-- 2019-05-30 by simon t_side_menu删除name列
alter table t_side_menu drop column name;

-- 2019-05-31 by simon 新增t_s_side_menu_multi_languages表
CREATE TABLE t_s_side_menu_multi_languages (
	id serial primary key,
	create_by int8,
	create_date timestamp,
	update_by int8,
	update_date timestamp,
	side_menu_id int8 NOT NULL,
	name varchar(255),
language varchar(25) NOT NULL
);
comment  on  column t_s_side_menu_multi_languages.create_by is '创建人id';
comment  on  column t_s_side_menu_multi_languages.create_date is '创建时间';
comment  on  column t_s_side_menu_multi_languages.update_by is '更新人id';
comment  on  column t_s_side_menu_multi_languages.update_date is '更新时间';
comment  on  column t_s_side_menu_multi_languages.side_menu_id is '侧边栏菜单id';
comment  on  column t_s_side_menu_multi_languages.name is '侧边栏菜单名称';
comment  on  column t_s_side_menu_multi_languages.language is '语言';
comment on table t_s_side_menu_multi_languages  is  '侧边栏菜单多语言';
alter table t_s_side_menu_multi_languages alter column id type int8;

INSERT INTO t_s_side_menu_multi_languages VALUES (1, NULL, NULL, NULL, NULL, 119869773617172480, '删除', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (2, NULL, NULL, NULL, NULL, 12, '权限管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (3, NULL, NULL, NULL, NULL, 3, '数据字典', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (4, NULL, NULL, NULL, NULL, 90997036798705664, 'druid', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (5, NULL, NULL, NULL, NULL, 8, '菜单管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (6, NULL, NULL, NULL, NULL, 94108390556434432, '了解OauthServer', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (7, NULL, NULL, NULL, NULL, 1, '系统管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (8, NULL, NULL, NULL, NULL, 93126997827387392, '日志管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (9, NULL, NULL, NULL, NULL, 119869773600395264, '修改', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (10, NULL, NULL, NULL, NULL, 80832133433655296, '代码生成', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (11, NULL, NULL, NULL, NULL, 90995059306004480, '系统工具', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (12, NULL, NULL, NULL, NULL, 90995810619097088, 'swagger', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (13, NULL, NULL, NULL, NULL, 94105929343041536, '主页', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (14, NULL, NULL, NULL, NULL, 119869773587812352, '新增', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (15, NULL, NULL, NULL, NULL, 118471356282179584, '删除', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (16, NULL, NULL, NULL, NULL, 118471356265402368, '修改', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (17, NULL, NULL, NULL, NULL, 118471356248625152, '新增', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (18, NULL, NULL, NULL, NULL, 118471356227653632, '查看', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (19, NULL, NULL, NULL, NULL, 118471356118601728, '新闻管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (20, NULL, NULL, NULL, NULL, 107876685461848070, '角色管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (21, NULL, NULL, NULL, NULL, 107876685461848071, '订单管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (22, NULL, NULL, NULL, NULL, 119869773415845888, '任务管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (23, NULL, NULL, NULL, NULL, 119494224726069248, '删除', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (24, NULL, NULL, NULL, NULL, 119869773566840832, '查看', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (25, NULL, NULL, NULL, NULL, 119494224713486336, '修改', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (26, NULL, NULL, NULL, NULL, 119494224361164800, '用户管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (27, NULL, NULL, NULL, NULL, 119494224675737600, '查看', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (28, NULL, NULL, NULL, NULL, 119494224700903424, '新增', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (29, NULL, NULL, NULL, NULL, 155328152225185792, '组件管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (30, NULL, NULL, NULL, NULL, 154250076045316096, '图标管理', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (31, NULL, NULL, NULL, NULL, 154250076150173696, '查看', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (32, NULL, NULL, NULL, NULL, 154250076171145216, '新增', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (33, NULL, NULL, NULL, NULL, 154250076196311040, '修改', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (34, NULL, NULL, NULL, NULL, 154250076221476864, '删除', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (35, NULL, NULL, NULL, NULL, 155328152225185793, '删除', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (36, NULL, NULL, NULL, NULL, 155328152225185794, '修改', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (37, NULL, NULL, NULL, NULL, 155328152225185795, '新增', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (38, NULL, NULL, NULL, NULL, 155328152225185796, '查看', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (39, NULL, NULL, NULL, NULL, 155328152225185797, '删除', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (40, NULL, NULL, NULL, NULL, 155328152225185798, '修改', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (41, NULL, NULL, NULL, NULL, 155328152225185799, '新增', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (42, NULL, NULL, NULL, NULL, 155328152225185800, '查看', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (43, NULL, NULL, NULL, NULL, 155328152225185801, '删除', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (44, NULL, NULL, NULL, NULL, 155328152225185802, '修改', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (45, NULL, NULL, NULL, NULL, 155328152225185803, '新增', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (46, NULL, NULL, NULL, NULL, 155328152225185804, '查看', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (47, NULL, NULL, NULL, NULL, 163710764928991232, '富文本', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (48, NULL, NULL, NULL, NULL, 164071030476767232, '个人中心', 'zh_CN');
INSERT INTO t_s_side_menu_multi_languages VALUES (49, NULL, NULL, NULL, NULL, 119869773617172480, 'delete', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (50, NULL, NULL, NULL, NULL, 12, 'authorities', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (51, NULL, NULL, NULL, NULL, 3, 'dictonary', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (52, NULL, NULL, NULL, NULL, 90997036798705664, 'druid', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (53, NULL, NULL, NULL, NULL, 8, 'menus', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (54, NULL, NULL, NULL, NULL, 94108390556434432, 'know OauthServer', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (55, NULL, NULL, NULL, NULL, 1, 'system', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (56, NULL, NULL, NULL, NULL, 93126997827387392, 'log', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (57, NULL, NULL, NULL, NULL, 119869773600395264, 'edit', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (58, NULL, NULL, NULL, NULL, 80832133433655296, 'code generator', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (59, NULL, NULL, NULL, NULL, 90995059306004480, 'tools', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (60, NULL, NULL, NULL, NULL, 90995810619097088, 'swagger', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (61, NULL, NULL, NULL, NULL, 94105929343041536, 'home', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (62, NULL, NULL, NULL, NULL, 119869773587812352, 'add', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (63, NULL, NULL, NULL, NULL, 118471356282179584, 'delete', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (64, NULL, NULL, NULL, NULL, 118471356265402368, 'edit', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (65, NULL, NULL, NULL, NULL, 118471356248625152, 'add', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (66, NULL, NULL, NULL, NULL, 118471356227653632, 'view', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (67, NULL, NULL, NULL, NULL, 118471356118601728, 'news', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (68, NULL, NULL, NULL, NULL, 107876685461848070, 'roles', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (69, NULL, NULL, NULL, NULL, 107876685461848071, 'bill', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (70, NULL, NULL, NULL, NULL, 119869773415845888, 'jobs', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (71, NULL, NULL, NULL, NULL, 119494224726069248, 'delete', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (72, NULL, NULL, NULL, NULL, 119869773566840832, 'view', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (73, NULL, NULL, NULL, NULL, 119494224713486336, 'edit', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (74, NULL, NULL, NULL, NULL, 119494224361164800, 'users', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (75, NULL, NULL, NULL, NULL, 119494224675737600, 'view', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (76, NULL, NULL, NULL, NULL, 119494224700903424, 'add', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (77, NULL, NULL, NULL, NULL, 155328152225185792, 'components', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (78, NULL, NULL, NULL, NULL, 154250076045316096, 'icons', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (79, NULL, NULL, NULL, NULL, 154250076150173696, 'view', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (80, NULL, NULL, NULL, NULL, 154250076171145216, 'add', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (81, NULL, NULL, NULL, NULL, 154250076196311040, 'edit', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (82, NULL, NULL, NULL, NULL, 154250076221476864, 'delete', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (83, NULL, NULL, NULL, NULL, 155328152225185793, 'delete', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (84, NULL, NULL, NULL, NULL, 155328152225185794, 'edit', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (85, NULL, NULL, NULL, NULL, 155328152225185795, 'add', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (86, NULL, NULL, NULL, NULL, 155328152225185796, 'view', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (87, NULL, NULL, NULL, NULL, 155328152225185797, 'delete', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (88, NULL, NULL, NULL, NULL, 155328152225185798, 'edit', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (89, NULL, NULL, NULL, NULL, 155328152225185799, 'add', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (90, NULL, NULL, NULL, NULL, 155328152225185800, 'view', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (91, NULL, NULL, NULL, NULL, 155328152225185801, 'delete', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (92, NULL, NULL, NULL, NULL, 155328152225185802, 'edit', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (93, NULL, NULL, NULL, NULL, 155328152225185803, 'add', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (94, NULL, NULL, NULL, NULL, 155328152225185804, 'view', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (95, NULL, NULL, NULL, NULL, 163710764928991232, 'rich text', 'en_US');
INSERT INTO t_s_side_menu_multi_languages VALUES (96, NULL, NULL, NULL, NULL, 164071030476767232, 'user center', 'en_US');

-- 2019-06-02 by simon 删除t_qr_code表
drop table t_qr_code;

-- 2019-06-03 by simon t_dict_type_group删除type_group_name列
alter table t_dict_type_group drop column type_group_name;

-- 2019-06-04 by simon t_dict_type删除type_name列
alter table t_dict_type drop column type_name;

-- 2019-06-04 by simon 创建字典组多语言表和字典多语言表并插入数据
-- 创建字典组多语言表
CREATE TABLE t_s_dict_type_group_multi_languages (
	id serial PRIMARY KEY,
	create_by int8,
	create_date timestamp,
	update_by int8,
	update_date timestamp,
	dict_type_group_id int8 NOT NULL,
	name varchar(255),
  language varchar(25) NOT NULL
);

COMMENT ON COLUMN t_s_dict_type_group_multi_languages.id is 'id';
COMMENT ON COLUMN t_s_dict_type_group_multi_languages.create_by is '创建人id';
COMMENT ON COLUMN t_s_dict_type_group_multi_languages.create_date is '创建时间';
COMMENT ON COLUMN t_s_dict_type_group_multi_languages.update_by is '更新人id';
COMMENT ON COLUMN t_s_dict_type_group_multi_languages.update_date is '更新时间';
COMMENT ON COLUMN t_s_dict_type_group_multi_languages.dict_type_group_id is '字典组id';
COMMENT ON COLUMN t_s_dict_type_group_multi_languages.name is '字典组名称';
COMMENT ON COLUMN t_s_dict_type_group_multi_languages.language is '语言';
alter table t_s_dict_type_group_multi_languages alter column id type int8;

-- 字典组多语言表插入数据
INSERT INTO t_s_dict_type_group_multi_languages VALUES (1, NULL, NULL, NULL, NULL, 1, '登录方式', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (2, NULL, NULL, NULL, NULL, 2, '状态', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (3, NULL, NULL, NULL, NULL, 3, '角色种类', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (4, NULL, NULL, NULL, NULL, 4, '性别', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (5, NULL, NULL, NULL, NULL, 5, '登录状态', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (6, NULL, NULL, NULL, NULL, 6, '闹钟状态', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (7, NULL, NULL, NULL, NULL, 7, '菜单类型', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (8, NULL, NULL, NULL, NULL, 96950073392365568, '订单状态', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (9, NULL, NULL, NULL, NULL, 97356870024429568, '账单类型', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (10, NULL, NULL, NULL, NULL, 97365554750291968, '支付方式', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (11, NULL, NULL, NULL, NULL, 97535843673047040, '消息类型', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (12, NULL, NULL, NULL, NULL, 99135515659337728, '新闻类型', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (13, NULL, NULL, NULL, NULL, 155564655257845760, '饿了么Element组件', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (14, NULL, NULL, NULL, NULL, 108265224984854528, 'job状态', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (15, NULL, NULL, NULL, NULL, 113787027396231168, '新闻状态', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (16, NULL, NULL, NULL, NULL, 113787027396231169, '有效状态', 'zh_CN');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (17, NULL, NULL, NULL, NULL, 1, 'login type', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (18, NULL, NULL, NULL, NULL, 2, 'status', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (19, NULL, NULL, NULL, NULL, 3, 'role type', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (20, NULL, NULL, NULL, NULL, 4, 'sex type', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (21, NULL, NULL, NULL, NULL, 5, 'login status', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (22, NULL, NULL, NULL, NULL, 6, 'alarm clock status', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (23, NULL, NULL, NULL, NULL, 7, 'menu type', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (24, NULL, NULL, NULL, NULL, 96950073392365568, 'bill status', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (25, NULL, NULL, NULL, NULL, 97356870024429568, 'bill type', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (26, NULL, NULL, NULL, NULL, 97365554750291968, 'payment type', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (27, NULL, NULL, NULL, NULL, 97535843673047040, 'message type', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (28, NULL, NULL, NULL, NULL, 99135515659337728, 'news type', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (29, NULL, NULL, NULL, NULL, 155564655257845760, 'Element component', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (30, NULL, NULL, NULL, NULL, 108265224984854528, 'job status', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (31, NULL, NULL, NULL, NULL, 113787027396231168, 'news status', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (32, NULL, NULL, NULL, NULL, 113787027396231169, 'enabled status', 'en_US');
INSERT INTO t_s_dict_type_group_multi_languages VALUES (167345989231837184, NULL, NULL, NULL, NULL, 167345989147951104, 'demo', 'en_US');


-- 创建字典多语言表
CREATE TABLE t_s_dict_type_multi_languages (
	id serial PRIMARY KEY,
	create_by int8,
	create_date timestamp,
	update_by int8,
	update_date timestamp,
	dict_type_id int8 NOT NULL,
	name varchar(255),
  language varchar(25) NOT NULL
);

COMMENT ON COLUMN t_s_dict_type_multi_languages.id is 'id';
COMMENT ON COLUMN t_s_dict_type_multi_languages.create_by is '创建人id';
COMMENT ON COLUMN t_s_dict_type_multi_languages.create_date is '创建时间';
COMMENT ON COLUMN t_s_dict_type_multi_languages.update_by is '更新人id';
COMMENT ON COLUMN t_s_dict_type_multi_languages.update_date is '更新时间';
COMMENT ON COLUMN t_s_dict_type_multi_languages.dict_type_id is '字典id';
COMMENT ON COLUMN t_s_dict_type_multi_languages.name is '字典名称';
COMMENT ON COLUMN t_s_dict_type_multi_languages.language is '语言';
alter table t_s_dict_type_multi_languages alter column id type int8;

-- 字典多语言表插入数据
INSERT INTO t_s_dict_type_multi_languages VALUES (1, NULL, NULL, NULL, NULL, 100000001, 'phone', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (2, NULL, NULL, NULL, NULL, 100000002, 'web', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (3, NULL, NULL, NULL, NULL, 100000003, '不可用', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (4, NULL, NULL, NULL, NULL, 100000004, '可用', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (5, NULL, NULL, NULL, NULL, 100000005, '普通用户', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (6, NULL, NULL, NULL, NULL, 100000006, '管理员', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (7, NULL, NULL, NULL, NULL, 100000007, '超管', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (8, NULL, NULL, NULL, NULL, 100000008, '男', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (9, NULL, NULL, NULL, NULL, 100000009, '女', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (10, NULL, NULL, NULL, NULL, 100000010, '离线', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (11, NULL, NULL, NULL, NULL, 100000011, '在线', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (12, NULL, NULL, NULL, NULL, 100000012, '关闭', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (13, NULL, NULL, NULL, NULL, 100000013, '启用', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (14, NULL, NULL, NULL, NULL, 100000014, '一级菜单', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (15, NULL, NULL, NULL, NULL, 100000015, '二级菜单', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (16, NULL, NULL, NULL, NULL, 95854276256989184, '勿扰', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (17, NULL, NULL, NULL, NULL, 96958562013544448, '待付款', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (18, NULL, NULL, NULL, NULL, 96959193222742016, '已付款，待发货', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (19, NULL, NULL, NULL, NULL, 96961451628953600, '已发货，待收货', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (20, NULL, NULL, NULL, NULL, 96962768984342528, '交易成功', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (21, NULL, NULL, NULL, NULL, 96963159130112000, '交易关闭', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (22, NULL, NULL, NULL, NULL, 96963562651516928, '退款中', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (23, NULL, NULL, NULL, NULL, 97357187684237312, '充值', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (28, NULL, NULL, NULL, NULL, 97366040169676800, '支付宝', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (29, NULL, NULL, NULL, NULL, 97366110180999168, '微信', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (30, NULL, NULL, NULL, NULL, 97536125287006208, '产品消息', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (31, NULL, NULL, NULL, NULL, 97536221990879232, '安全消息', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (32, NULL, NULL, NULL, NULL, 97536285903683584, '服务消息', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (33, NULL, NULL, NULL, NULL, 97536343411785728, '活动消息', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (34, NULL, NULL, NULL, NULL, 97536415625117696, '历史消息', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (35, NULL, NULL, NULL, NULL, 97536494998126592, '故障消息', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (36, NULL, NULL, NULL, NULL, 99136468701675520, 'banner', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (37, NULL, NULL, NULL, NULL, 155566528442400769, '富文本', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (38, NULL, NULL, NULL, NULL, 155566528442400768, '穿梭框', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (39, NULL, NULL, NULL, NULL, 155566444615041024, '颜色选择器', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (40, NULL, NULL, NULL, NULL, 155566354039046144, '评分', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (41, NULL, NULL, NULL, NULL, 155566295306207232, '上传', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (42, NULL, NULL, NULL, NULL, 155566223822684160, '日期选择器', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (43, NULL, NULL, NULL, NULL, 155566122928701440, '时间选择器', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (44, NULL, NULL, NULL, NULL, 155566025453076480, '滑块', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (45, NULL, NULL, NULL, NULL, 155565960655273984, '开关', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (46, NULL, NULL, NULL, NULL, 155565909979693056, '级联选择器', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (47, NULL, NULL, NULL, NULL, 155565797303910400, '选择器', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (48, NULL, NULL, NULL, NULL, 155565698930704384, '计数器', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (49, NULL, NULL, NULL, NULL, 155565420294701056, '多选框', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (50, NULL, NULL, NULL, NULL, 155565360102244352, '单选框', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (51, NULL, NULL, NULL, NULL, 155565261192167424, '输入框', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (52, NULL, NULL, NULL, NULL, 108265713587716096, 'on', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (53, NULL, NULL, NULL, NULL, 108265787696873472, 'off', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (54, NULL, NULL, NULL, NULL, 113787304258043904, '草稿', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (55, NULL, NULL, NULL, NULL, 113787421593698304, '发布', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (56, NULL, NULL, NULL, NULL, 119131930154237952, '激活', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (57, NULL, NULL, NULL, NULL, 119132047645081600, '禁用', 'zh_CN');
INSERT INTO t_s_dict_type_multi_languages VALUES (58, NULL, NULL, NULL, NULL, 100000001, 'phone', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (59, NULL, NULL, NULL, NULL, 100000002, 'web', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (60, NULL, NULL, NULL, NULL, 100000003, 'disabled', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (61, NULL, NULL, NULL, NULL, 100000004, 'enabled', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (62, NULL, NULL, NULL, NULL, 100000005, 'user', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (63, NULL, NULL, NULL, NULL, 100000006, 'admin', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (64, NULL, NULL, NULL, NULL, 100000007, 'su', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (65, NULL, NULL, NULL, NULL, 100000008, 'male', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (66, NULL, NULL, NULL, NULL, 100000009, 'female', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (67, NULL, NULL, NULL, NULL, 100000010, 'offline', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (68, NULL, NULL, NULL, NULL, 100000011, 'online', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (69, NULL, NULL, NULL, NULL, 100000012, 'closed', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (70, NULL, NULL, NULL, NULL, 100000013, 'opened', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (71, NULL, NULL, NULL, NULL, 100000014, 'level 1', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (72, NULL, NULL, NULL, NULL, 100000015, 'level 2', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (73, NULL, NULL, NULL, NULL, 95854276256989184, 'do not disturb', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (74, NULL, NULL, NULL, NULL, 96958562013544448, 'wait pay', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (75, NULL, NULL, NULL, NULL, 96959193222742016, 'payed', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (76, NULL, NULL, NULL, NULL, 96961451628953600, 'shipped', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (77, NULL, NULL, NULL, NULL, 96962768984342528, 'done', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (78, NULL, NULL, NULL, NULL, 96963159130112000, 'closed', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (79, NULL, NULL, NULL, NULL, 96963562651516928, 'refunding', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (80, NULL, NULL, NULL, NULL, 97357187684237312, 'recharge', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (85, NULL, NULL, NULL, NULL, 97366040169676800, 'ali pay', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (86, NULL, NULL, NULL, NULL, 97366110180999168, 'wechat pay', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (87, NULL, NULL, NULL, NULL, 97536125287006208, 'product', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (88, NULL, NULL, NULL, NULL, 97536221990879232, 'safe', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (89, NULL, NULL, NULL, NULL, 97536285903683584, 'service', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (90, NULL, NULL, NULL, NULL, 97536343411785728, 'activity', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (91, NULL, NULL, NULL, NULL, 97536415625117696, 'history', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (92, NULL, NULL, NULL, NULL, 97536494998126592, 'fault', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (93, NULL, NULL, NULL, NULL, 99136468701675520, 'banner', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (94, NULL, NULL, NULL, NULL, 155566528442400769, 'Rich Text', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (95, NULL, NULL, NULL, NULL, 155566528442400768, 'Transfer', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (96, NULL, NULL, NULL, NULL, 155566444615041024, 'ColorPicker', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (97, NULL, NULL, NULL, NULL, 155566354039046144, 'Rate', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (98, NULL, NULL, NULL, NULL, 155566295306207232, 'Upload', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (99, NULL, NULL, NULL, NULL, 155566223822684160, 'DatePicker', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (100, NULL, NULL, NULL, NULL, 155566122928701440, 'TimePicker', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (101, NULL, NULL, NULL, NULL, 155566025453076480, 'Slider', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (102, NULL, NULL, NULL, NULL, 155565960655273984, 'Switch', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (103, NULL, NULL, NULL, NULL, 155565909979693056, 'Cascader', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (104, NULL, NULL, NULL, NULL, 155565797303910400, 'Select', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (105, NULL, NULL, NULL, NULL, 155565698930704384, 'InputNumber', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (106, NULL, NULL, NULL, NULL, 155565420294701056, 'Checkbox', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (107, NULL, NULL, NULL, NULL, 155565360102244352, 'Radio', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (108, NULL, NULL, NULL, NULL, 155565261192167424, 'input', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (109, NULL, NULL, NULL, NULL, 108265713587716096, 'on', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (110, NULL, NULL, NULL, NULL, 108265787696873472, 'off', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (111, NULL, NULL, NULL, NULL, 113787304258043904, 'draft', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (112, NULL, NULL, NULL, NULL, 113787421593698304, 'published', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (113, NULL, NULL, NULL, NULL, 119131930154237952, 'enabled', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (114, NULL, NULL, NULL, NULL, 119132047645081600, 'disabled', 'en_US');
INSERT INTO t_s_dict_type_multi_languages VALUES (167346041576751104, NULL, NULL, NULL, NULL, 167346041534808064, 'demo1', 'en_US');

-- 创建视图
create view view_role_zh_cn as
SELECT tdt.*,tsdtml.name as type_name from t_dict_type tdt LEFT JOIN t_s_dict_type_multi_languages tsdtml ON tdt.id=tsdtml.dict_type_id AND tsdtml.language='zh_CN' WHERE tdt.type_group_code='role_type' ORDER BY tdt.order_num asc;

create view view_role_en_us as
SELECT tdt.*,tsdtml.name as type_name from t_dict_type tdt LEFT JOIN t_s_dict_type_multi_languages tsdtml ON tdt.id=tsdtml.dict_type_id AND tsdtml.language='en_US' WHERE tdt.type_group_code='role_type' ORDER BY tdt.order_num asc;

create view t_dict_type_zh_cn as
SELECT tdt.*,tsdtml.name as type_name from t_dict_type tdt LEFT JOIN t_s_dict_type_multi_languages tsdtml ON tdt.id=tsdtml.dict_type_id AND tsdtml.language='zh_CN' order by tdt.id asc;

create view t_dict_type_en_us as
SELECT tdt.*,tsdtml.name as type_name from t_dict_type tdt LEFT JOIN t_s_dict_type_multi_languages tsdtml ON tdt.id=tsdtml.dict_type_id AND tsdtml.language='en_US' order by tdt.id asc;

create view t_dict_type_group_en_us as
SELECT tdtg.*,tddtgml.name as type_group_name from t_dict_type_group tdtg left join
t_s_dict_type_group_multi_languages tddtgml ON tdtg.id=tddtgml.dict_type_group_id and tddtgml.language='en_US' order by tdtg.id asc;

create view t_dict_type_group_zh_cn as
SELECT tdtg.*,tddtgml.name as type_group_name from t_dict_type_group tdtg left join
t_s_dict_type_group_multi_languages tddtgml ON tdtg.id=tddtgml.dict_type_group_id and tddtgml.language='zh_CN' order by tdtg.id asc;

-- 重命名，因为同样的表名长度，在oracle中太长了
alter table t_s_dict_type_multi_languages RENAME TO t_s_dt_multi_languages;
alter table t_s_dict_type_group_multi_languages RENAME TO t_s_dtg_multi_languages;
-- 重建视图
DROP VIEW view_role_zh_cn;
DROP VIEW view_role_en_us;
DROP VIEW t_dict_type_zh_cn;
DROP VIEW t_dict_type_en_us;
DROP VIEW t_dict_type_group_en_us;
DROP VIEW t_dict_type_group_zh_cn;

create view view_role_zh_cn as
SELECT tdt.*,tsdtml.name as type_name from t_dict_type tdt LEFT JOIN t_s_dt_multi_languages tsdtml ON tdt.id=tsdtml.dict_type_id AND tsdtml.language='zh_CN' WHERE tdt.type_group_code='role_type' ORDER BY tdt.order_num asc;

create view view_role_en_us as
SELECT tdt.*,tsdtml.name as type_name from t_dict_type tdt LEFT JOIN t_s_dt_multi_languages tsdtml ON tdt.id=tsdtml.dict_type_id AND tsdtml.language='en_US' WHERE tdt.type_group_code='role_type' ORDER BY tdt.order_num asc;

create view t_dict_type_zh_cn as
SELECT tdt.*,tsdtml.name as type_name from t_dict_type tdt LEFT JOIN t_s_dt_multi_languages tsdtml ON tdt.id=tsdtml.dict_type_id AND tsdtml.language='zh_CN' order by tdt.id asc;

create view t_dict_type_en_us as
SELECT tdt.*,tsdtml.name as type_name from t_dict_type tdt LEFT JOIN t_s_dt_multi_languages tsdtml ON tdt.id=tsdtml.dict_type_id AND tsdtml.language='en_US' order by tdt.id asc;

create view t_dict_type_group_en_us as
SELECT tdtg.*,tddtgml.name as type_group_name from t_dict_type_group tdtg left join
t_s_dtg_multi_languages tddtgml ON tdtg.id=tddtgml.dict_type_group_id and tddtgml.language='en_US' order by tdtg.id asc;

create view t_dict_type_group_zh_cn as
SELECT tdtg.*,tddtgml.name as type_group_name from t_dict_type_group tdtg left join
t_s_dtg_multi_languages tddtgml ON tdtg.id=tddtgml.dict_type_group_id and tddtgml.language='zh_CN' order by tdtg.id asc;

-- 2019-06-02 by simon 删除t_log_login表和t_multi_language表
drop table t_log_login;
drop table t_multi_language;

-- 2019-06-13 by simon 重命名t_s_font_awesome字段
ALTER TABLE t_s_font_awesome rename column label to label_en_us;
ALTER TABLE t_s_font_awesome rename column tags to label_zh_cn;

-- 2019-06-14 by simon 修改t_side_menu数据
UPDATE t_side_menu SET request_method='GET' WHERE remark='list';

-- 2019-06-18 by simon 饿了么组件字典添加DateTimePicker
INSERT INTO t_dict_type(id, create_by, create_date, update_by, update_date, type_code, type_group_id, type_group_code, order_num) VALUES (155566122928701441, NULL, '2019-06-04 17:21:11', NULL, NULL, 'DateTimePicker', 155564655257845760, 'element_component', 10);
INSERT INTO t_s_dt_multi_languages(id, create_by, create_date, update_by, update_date, dict_type_id, name, language) VALUES (115, NULL, NULL, NULL, NULL, 155566122928701441, 'DateTimePicker', 'en_US');
INSERT INTO t_s_dt_multi_languages(id, create_by, create_date, update_by, update_date, dict_type_id, name, language) VALUES (116, NULL, NULL, NULL, NULL, 155566122928701441, '日期时间选择器', 'zh_CN');

-- 2019-06-18 by simon t_s_column_ui表修改
alter table t_s_column_ui add extra_info varchar(50);
COMMENT ON COLUMN t_s_column_ui.extra_info IS '补充信息';
ALTER TABLE t_s_column_ui ADD allow_search BOOLEAN DEFAULT FALSE;
COMMENT ON COLUMN t_s_column_ui.allow_search IS '允许搜索';
ALTER TABLE t_s_column_ui ADD hidden BOOLEAN DEFAULT FALSE;
COMMENT ON COLUMN t_s_column_ui.hidden IS '是否隐藏';
ALTER TABLE t_s_column_ui ADD allow_input BOOLEAN DEFAULT TRUE;
COMMENT ON COLUMN t_s_column_ui.allow_input IS '允许输入';