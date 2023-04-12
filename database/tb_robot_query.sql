create table if not exists tb_robot(
    robot_id varchar(20) primary key,
    -- 剩余电量
    battery int not null,
    -- 状态 0:停止 1:运行
    status int default 0
);

insert into tb_robot values ('robot-1', 80, 0);
insert into tb_robot values ('robot-2', 80, 0);
insert into tb_robot values ('robot-3', 80, 0);