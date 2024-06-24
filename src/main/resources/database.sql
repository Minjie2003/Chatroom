-- 创建my_chatroom数据库
create database if not exists my_chatroom charset utf8;
-- 使用my_chatroom数据库
use my_chatroom

-- 删除用户表
drop table if exists user_info;
-- 创建用户表
create table if not exists user_info (
    id int unsigned primary key auto_increment,
    location varchar(50) not null default '中国',
    sex varchar(2) not null default '男',
    username varchar(50) not null default '新用户',
    account_num varchar(20) unique,
    password varchar(20) not null,
    birthday timestamp not null default CURRENT_TIMESTAMP,
    create_time timestamp default CURRENT_TIMESTAMP,
    update_time timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
    state int default 2,
    mail varchar(50) unique,
    photo varchar(255) default 'head/默认头像.png'
) charset utf8;
-- 插入测试用户数据

insert into user_info (username,account_num,password,mail)values("张三","123","123","1");
insert into user_info (username,account_num,password,mail)values("李四","1234","123","2");
insert into user_info (username,account_num,password,mail)values("王五","12","123","3");
insert into user_info (username,account_num,password,mail)values("赵六","13","123","4");
insert into user_info (username,account_num,password,mail)values("钱七","23","123","5");

-- 删除会话表
drop table if exists session_info;
-- 创建会话表
create table if not exists session_info(
    id int unsigned primary key auto_increment,
    category tinyint default 1, -- 1 为用户会话 0 为聊天室会话
    is_deleted tinyint default 0, -- 1 已经不可用 0 可用
    create_time timestamp default now(),
    update_time timestamp default now() on update now()
)charset utf8;
-- 插入测试会话数据
insert into session_info (id) values (null); -- 张三李四的好友会话
insert into session_info (id,category) values (null,0); -- 张三创建的聊天室会话


-- 删除联系人分组表
drop table if exists contact_group_info;
-- 创建联系人分组表
create table if not exists contact_group_info(
    id int unsigned primary key auto_increment,
    name varchar(20) default "好友",
    category tinyint not null,
    user_id int unsigned,
    is_default tinyint default 1,-- 是否是默认的好友存放组
    create_time timestamp default now(),
    update_time timestamp default now() on update now()
)charset utf8;
-- 插入默认用户联系人分组
insert into contact_group_info (user_id,is_default,category) values(1,1,1); -- 给id为1的用户插入一条默认的好友分组数据
insert into contact_group_info (user_id,is_default,category) values(2,1,1); -- 给id为2的用户插入一条默认的好友分组数据
insert into contact_group_info (user_id,is_default,category) values(3,1,1); -- 给id为3的用户插入一条默认的好友分组数据
insert into contact_group_info (user_id,is_default,category) values(4,1,1); -- 给id为4的用户插入一条默认的好友分组数据
insert into contact_group_info (user_id,is_default,category) values(5,1,1); -- 给id为5的用户插入一条默认的好友分组数据

-- 插入默认群聊联系人分组
insert into contact_group_info (user_id,is_default,category,name) values(1,1,0,'群聊');-- 给id为1的用户插入一条默认的群聊分组数据
insert into contact_group_info (user_id,is_default,category,name) values(2,1,0,'群聊');-- 给id为2的用户插入一条默认的群聊分组数据
insert into contact_group_info (user_id,is_default,category,name) values(3,1,0,'群聊');-- 给id为3的用户插入一条默认的群聊分组数据
insert into contact_group_info (user_id,is_default,category,name) values(4,1,0,'群聊');-- 给id为4的用户插入一条默认的群聊分组数据
insert into contact_group_info (user_id,is_default,category,name) values(5,1,0,'群聊');-- 给id为5的用户插入一条默认的群聊分组数据
-- 删除联系人会话表
drop table if exists contact_session_info;
-- 创建联系人会话表
create TABLE if not exists contact_session_info(
    id int unsigned primary key auto_increment,
    user_id int unsigned,
    nick_name varchar(50) not null,
    session_id int unsigned,
    is_hide tinyint default 0,
    unread_num int unsigned default 0,
    last_message_id int unsigned default 0,
    group_id int unsigned not null,
    contact_id int unsigned not null,
    create_time timestamp default now(),
    update_time timestamp default now() on update now(),
    is_deleted tinyint default 0
    )charset utf8;
-- 插入用户会话信息


-- 张三和李四的好友会话
insert into contact_session_info (nick_name,user_id,session_id,contact_id,group_id) values("张三",2,1,1,2);
insert into contact_session_info (nick_name,user_id,session_id,contact_id,group_id) values("李四",1,1,2,1);
-- 张三和他的朋友们的聊天室会话
insert into contact_session_info (nick_name,user_id,session_id,contact_id,group_id) values('张三日记',1,2,2,6);
insert into contact_session_info (nick_name,user_id,session_id,contact_id,group_id) values('张三日记',2,2,2,7);
insert into contact_session_info (nick_name,user_id,session_id,contact_id,group_id) values('张三日记',3,2,2,8);
insert into contact_session_info (nick_name,user_id,session_id,contact_id,group_id) values('张三日记',4,2,2,9);
insert into contact_session_info (nick_name,user_id,session_id,contact_id,group_id) values('张三日记',5,2,2,10);

-- 删除聊天室表
drop table if exists chatroom_info;
-- 创建聊天室表
create table if not exists chatroom_info(
    session_id int unsigned unique,
    creator_id int unsigned not null,
    name varchar(50) not null,
    user_num int default 1,
    photo varchar(100) default "head/群聊头像.png",
    create_time timestamp default now(),
    update_time timestamp default now() on update now()
)charset utf8;

insert into chatroom_info (session_id,creator_id,name,user_num) values(2,1,"张三狂徒日记",5);

-- 删除信息表
drop table if exists message_info;
-- 创建消息表
create table if not exists message_info(
    id int unsigned primary key auto_increment,
    session_id int unsigned,
    send_id int unsigned,
    send_time timestamp default now(),
    category tinyint,
    content varchar(1024)
)charset utf8;

-- 删除通知表
drop table if exists advice_info;
-- 创建通知表
create table if not exists advice_info(
    id int unsigned primary key auto_increment,
    send_id int unsigned,
    receive_id int unsigned,
    content varchar(255),
    category int,
    is_read tinyint default 0,
    result int default 0,
    send_time timestamp default now(),
    chatroom_id int unsigned default 0
)charset utf8;
-- 插入通知数据
-- 好友请求数据 张三向李四发送了添加好友请求 并且李四接受了好友请求
insert into advice_info (send_id,receive_id,content,category,is_read,result,send_time)values(1,2,'老四，我是你三哥',0,1,2,'2023-10-10 20:30:30');
-- 张三向李四，王五，赵六，钱七发送的邀请进群请求，都同意了
insert into advice_info (send_id,receive_id,content,category,is_read,result,send_time,chatroom_id)values(1,2,'老四，速速进群',1,1,2,'2023-10-10 20:30:30',2);
insert into advice_info (send_id,receive_id,content,category,is_read,result,send_time,chatroom_id)values(1,3,'老五，速速进群',1,1,2,'2023-10-10 20:30:30',2);
insert into advice_info (send_id,receive_id,content,category,is_read,result,send_time,chatroom_id)values(1,4,'老六，速速进群',1,1,2,'2023-10-10 20:30:30',2);
insert into advice_info (send_id,receive_id,content,category,is_read,result,send_time,chatroom_id)values(1,5,'老七，速速进群',1,1,2,'2023-10-10 20:30:30',2);
-- 李四举报张三，管理员的处理结果为警告
insert into advice_info (send_id,receive_id,content,category,is_read,result,send_time)values(2,1,'xxxx',3,1,1,'2023-10-10 20:30:30');
-- 管理员对张三发送了警告

insert into advice_info (send_id,receive_id,content,category,is_read,result,send_time)values(0,1,'xxxx',4,0,0,'2023-10-10 20:30:30');
-- 删除管理员表
drop table if exists manager_info;
-- 创建管理员表
create table if not exists manager_info(
    id int unsigned primary key auto_increment,
    account_num varchar(20),
    password varchar(20),
    update_time timestamp default now() on update now(),
    create_time timestamp default now()
)charset utf8;
-- 插入一条默认的管理员信息
insert into manager_info (account_num,password) values("admin","admin");