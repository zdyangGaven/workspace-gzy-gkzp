create table sys_user(
    id			  int				identity(1,1)  primary key not null,	--id主键 自增长
    loginName     nvarchar(50)	NOT NULL ,									--登录用户名
    password      nvarchar(50)	NOT NULL ,									--登录密码
	email		  nvarchar(255)	,										--用户邮箱	（邮箱登录）
	phone         nvarchar(15)  ,										--用户手机号（手机号登录）
	createTime    datetime default CONVERT(varchar(100), GETDATE(), 20),	--用户创建时间
	pwdChangeTime datetime default CONVERT(varchar(100), GETDATE(), 20),	--改密码时间（初始值为用户创建时间）
	code		  varchar(255) ,											--'激活码'
	usertype      int			NOT NULL default 2,							--用户类型（0系统管理员 1、内网用户 2、普通用户）    
	state		  int           NOT NULL default 0 ,						--用户激活状态：0表示未激活，1表示激活
    nstatusid	  int default 1												--用户状态 0无效 1有效
);
--drop table sys_user;

select * from sys_user;


drop table sys_user;

select * from sys_user;
delete from sys_user;

insert into sys_user(loginname,password) values('aa','01746a86c7af708020a02f979f400689cb940a51');
insert into sys_user(loginname,password) values('qq','01746a86c7af708020a02f979f400689cb940a51');
insert into sys_user(loginname,password) values('ww','01746a86c7af708020a02f979f400689cb940a51');


