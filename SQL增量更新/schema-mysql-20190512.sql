-- 2019-05-11 by simon 修改t_s_column_ui的id列为long类型
ALTER TABLE t_s_column_ui MODIFY COLUMN id INT(20);

-- 2019-05-13 by simon 修改t_reset_pwd_info的expires_in列为datetime类型。@Magic 宋雷广
ALTER TABLE t_reset_pwd_info MODIFY COLUMN expires_in datetime;

-- 2019-05-14 by simon 修改t_bill的bill_context列为varchar(255)类型
ALTER TABLE t_bill MODIFY COLUMN bill_context VARCHAR (255) COMMENT '订单内容';

-- 2019-05-21 by simon 修改数据
update t_side_menu set url='/api/loggingEvents/list' where id=93126997827387392;