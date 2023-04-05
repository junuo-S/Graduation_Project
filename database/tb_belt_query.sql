create table if not exists tb_belt(
    belt_id varchar(20) primary key,
    length int not null
);

insert into tb_belt values ('belt-1', 10);
insert into tb_belt values ('belt-2', 10);
insert into tb_belt values ('belt-3', 10);