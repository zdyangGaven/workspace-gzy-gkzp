create table sys_user(
    id			int				identity(1,1)  primary key not null,	--id主键 自增长
    loginName   nvarchar(50)	NOT NULL UNIQUE,						--登录用户名
    password    nvarchar(50)	NOT NULL ,								--登录密码
	email		varchar(255)	NOT NULL UNIQUE,						--用户邮箱
	createTime  datetime default CONVERT(varchar(100), GETDATE(), 20),	--用户创建时间
	code		varchar(255) ,									--'激活码'
	state		int            NOT NULL default 0 ,						--用户激活状态：0表示未激活，1表示激活
    nstatusid	int default 1											--用户状态 0无效 1有效
);
--drop table sys_user;


insert into sys_user(loginname,password,email) values('aa','a我的名字1','qwe');
insert into sys_user(loginname,username,password) values('qq','q我的名字2','qwe');
insert into sys_user(loginname,username,password) values('ww','w我的名字3','qwe');


