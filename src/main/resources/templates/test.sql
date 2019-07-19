create table sys_user(
    id			int				identity(1,1)  primary key not null,--id主键 自增长
    loginName   nvarchar(50)	NOT NULL UNIQUE,					--登录用户名
    userName    nvarchar(50),	--用户名称
    password    nvarchar(50)	NOT NULL ,					--登录密码
	createTime  datetime default CONVERT(varchar(100), GETDATE(), 20), --用户创建时间
	STATUSID	int default 1 --用户状态 1有效
);
--drop table sys_user;

insert into sys_user(loginname,username,password) values('aa','a我的名字1','qwe');
insert into sys_user(loginname,username,password) values('qq','q我的名字2','qwe');
insert into sys_user(loginname,username,password) values('ww','w我的名字3','qwe');


