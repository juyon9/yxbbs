--3.1 用户表
--    账号(电子邮箱), 密码, 姓名, 状态(Y正常, N禁用), 注册时间
--3.2 板块表
--    名称, 帖子数
--3.3 帖子类别表
--    名称, 帖子数
--3.4 帖子表
--    标题, 内容, 作者, 创建时间, 回复数, 点赞数, 帖子类别, 所属板块
--3.5 帖子回复表
--    帖子, 回复人, 回复内容, 回复时间

drop table if exists t_user;
create table t_user(
    id int not null auto_increment comment '主键',
    account varchar(32) not null unique comment '账号,只能为邮箱地址',
    password char(24) not null comment '密码,加密存储',
    name varchar(16) not null comment '姓名,用户真实姓名',
    status char(1) not null comment '状态,y表示正常;n表示禁用, 该状态不允许登录',
    r_time datetime not null comment '注册时间, 不允许修改',
    PRIMARY KEY (id)
)collate='utf8mb4_unicode_ci' comment='用户表, 管理员密码yx_since2015';
insert into t_user values(1, 'admin', '6g1+92qDYnuVRhHv9n5CCA==', '管理员', 'y', '2019-05-17 16:00:00.0');

drop table if exists t_module;
create table t_module(
    id int not null auto_increment comment '主键',
    name varchar(32) not null comment '板块名称',
    p_cnt int not null comment '帖子数',
    PRIMARY KEY (id)
)collate='utf8mb4_unicode_ci' comment='板块表';
insert into t_module values
(1, '后端开发', 0),
(2, '前端开发', 0),
(3, '移动端', 0),
(4, '数据库', 0),
(5, '第三方应用', 0),
(6, '实用工具', 0),
(7, '其他', 0);

drop table if exists t_posts_sort;
create table t_posts_sort(
    id int not null auto_increment comment '主键',
    name varchar(32) not null comment '板块名称',
    p_cnt int not null comment '帖子数',
    PRIMARY KEY (id)
)collate='utf8mb4_unicode_ci' comment='帖子类别表';
insert into t_posts_sort values
(1, '提问', 0),
(2, '分享', 0),
(3, '讨论', 0),
(4, '建议', 0),
(5, '公告', 0);

drop table if exists t_posts;
create table t_posts(
    id int not null auto_increment comment '主键',
    title varchar(128) not null comment '标题', 
    content text not null comment '内容',
    author int not null comment '作者',
    c_time datetime not null comment '创建时间',
    r_cnt int not null comment '回复数',
    a_cnt int not null comment '点赞数',
    s_id int not null comment '帖子类别',
    m_id int not null comment '所属板块',
    PRIMARY KEY (id)
)collate='utf8mb4_unicode_ci' comment='帖子表';

drop table if exists t_reply;
create table t_reply(
    id int not null auto_increment comment '主键',
    p_id int not null comment '帖子id',
    u_id int not null comment '回复人',
    content text not null comment '回复内容',
    c_time datetime not null comment '回复时间',
    PRIMARY KEY (id)
)collate='utf8mb4_unicode_ci' comment='帖子回复表';