create table if not exists tb_task(
    id int primary key auto_increment,
    time int not null ,
    robot_id varchar(20) not null ,
    belt_id varchar(20) not null,
    -- 0: 已完成 1: 进行中
    status int default 1
);
drop table tb_task;
insert into tb_task values (1, 1677483647, 'robot-1', 'belt-1', 0);
insert into tb_task values (2, 1677583647, 'robot-2', 'belt-2', 0);
insert into tb_task values (3, 1677683647, 'robot-3', 'belt-3', 0);
insert into tb_task values (4, 1677783647, 'robot-1', 'belt-1', 0);
insert into tb_task values (5, 1677883647, 'robot-2', 'belt-2', 0);
insert into tb_task values (6, 1677983647, 'robot-3', 'belt-3', 0);
insert into tb_task values (7, 1678083647, 'robot-1', 'belt-1', 0);
insert into tb_task values (8, 1678183647, 'robot-2', 'belt-2', 0);
insert into tb_task values (9, 1678283647, 'robot-3', 'belt-3', 0);

update tb_task set status=1 where id between 6 and 9;