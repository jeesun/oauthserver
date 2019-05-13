-- 2019-05-11 by simon 修改t_s_column_ui的id列为long类型
ALTER TABLE t_s_column_ui MODIFY COLUMN id INT(20);

-- 2019-05-13 by simon 修改t_reset_pwd_info的expires_in列为datetime类型。@Magic 宋雷广
ALTER TABLE t_reset_pwd_info MODIFY COLUMN expires_in datetime;