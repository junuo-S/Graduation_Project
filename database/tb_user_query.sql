create table if not exists tb_user
(
    -- 主键
    id        int primary key auto_increment,
    -- 用户名
    user_name varchar(20) not null,
    -- 密码
    password  varchar(20) not null,
    -- 是否为管理员
    is_admin  bool default false,
    -- 状态 1表示启用 0表示禁用
    status    int  default 1
);

insert into tb_user values(1, 'admin', 'admin', true, 1);
insert into tb_user value(null, 'normal_user', 'normal_user', false, 1);