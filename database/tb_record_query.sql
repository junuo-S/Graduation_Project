create table if not exists tb_record(
    id int primary key auto_increment,
    time int not null,
    robot_id varchar(20) not null,
    belt_id varchar(20) not null,
    -- 检测第几个托辊，默认第0个
    roller int default 0,
    -- -1故障，0工作异常，1工作正常
    status int default 1,
    bzText varchar(50)
);
drop table tb_record;
-- 托辊的常见故障：托辊摩擦异响、滚筒外表面磨损、锈蚀严重、水灰尘进入轴承密封圈，润滑脂变质，加速损坏、轴承卡涩、轴承的强度、刚度、疲劳破损等。
insert into tb_record values (1, 1681713662, 'robot-1', 'belt-1', 0, 1, '工作正常');
insert into tb_record values (2, 1681713667, 'robot-1', 'belt-1', 1, 0, '托辊摩擦异响');
insert into tb_record values (3, 1681713672, 'robot-1', 'belt-1', 2, -1, '托辊轴承损坏');
insert into tb_record values (4, 1681713677, 'robot-1', 'belt-1', 3, 1, '工作正常');
insert into tb_record values (5, 1681713682, 'robot-1', 'belt-1', 4, -1, '筒皮与轴承座脱焊');

insert into tb_record values (null, 1681713662, 'robot-2', 'belt-2', 0, 0, '托辊摩擦异响');
insert into tb_record values (null, 1681713667, 'robot-2', 'belt-2', 1, 1, '工作正常');
insert into tb_record values (null, 1681713672, 'robot-2', 'belt-2', 2, 1, '工作正常');
insert into tb_record values (null, 1681713677, 'robot-2', 'belt-2', 3, -1, '托辊轴承损坏');
insert into tb_record values (null, 1681713682, 'robot-2', 'belt-2', 4, -1, '轴承脱出');

insert into tb_record values (null, 1681713662, 'robot-3', 'belt-3', 0, 1, '工作正常');
insert into tb_record values (null, 1681713667, 'robot-3', 'belt-3', 1, 1, '工作正常');
insert into tb_record values (null, 1681713672, 'robot-3', 'belt-3', 2, -1, '托辊轴承损坏');
insert into tb_record values (null, 1681713677, 'robot-3', 'belt-3', 3, 1, '工作正常');
insert into tb_record values (null, 1681713682, 'robot-3', 'belt-3', 4, 1, '工作正常');