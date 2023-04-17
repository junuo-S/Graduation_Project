create table if not exists tb_matter(
    id int primary key auto_increment,
    time int not null ,
    belt_id varchar(20) not null ,
    -- 0:残次品 1:良好
    quality int not null ,
    width int not null ,
    height int not null ,
    info varchar(50)
);
drop table tb_matter;
insert into tb_matter values (1, 1681387708, 'belt-1', 1, 30, 10, '表面光滑');
insert into tb_matter values (2, 1681387708, 'belt-2', 1, 32, 9, '表面略显粗糙');
insert into tb_matter values (3, 1681387708, 'belt-3', 0, 31, 11, '表面有很多斑点');
insert into tb_matter values (4, 1681387708, 'belt-1', 1, 29, 10, '带刺');
insert into tb_matter values (5, 1681387708, 'belt-2', 1, 28, 13, '高度偏高');
insert into tb_matter values (6, 1681387708, 'belt-3', 0, 27, 13, '长度过短，高度偏高');

insert into tb_matter values (null, 1681387708, 'belt-1', 1, 30, 10, '表面光滑');
insert into tb_matter values (null, 1681387708, 'belt-2', 1, 32, 9, '表面略显粗糙');
insert into tb_matter values (null, 1681387708, 'belt-3', 0, 31, 11, '表面有很多斑点');
insert into tb_matter values (null, 1681387708, 'belt-1', 1, 29, 10, '带刺');
insert into tb_matter values (null, 1681387708, 'belt-2', 1, 28, 13, '高度偏高');
insert into tb_matter values (null, 1681387708, 'belt-3', 0, 27, 13, '长度过短，高度偏高');
