create table sys_user(
    id			  int				identity(1,1)  primary key not null,	--id���� ������
    loginName     nvarchar(50)	NOT NULL ,									--��¼�û���
    password      nvarchar(50)	NOT NULL ,									--��¼����
	email		  nvarchar(255)	,										--�û�����	�������¼��
	phone         nvarchar(15)  ,										--�û��ֻ��ţ��ֻ��ŵ�¼��
	createTime    datetime default CONVERT(varchar(100), GETDATE(), 20),	--�û�����ʱ��
	pwdChangeTime datetime default CONVERT(varchar(100), GETDATE(), 20),	--������ʱ�䣨��ʼֵΪ�û�����ʱ�䣩
	code		  varchar(255) ,											--'������'
	usertype      int			NOT NULL default 2,							--�û����ͣ�0ϵͳ����Ա 1�������û� 2����ͨ�û���    
	state		  int           NOT NULL default 0 ,						--�û�����״̬��0��ʾδ���1��ʾ����
    nstatusid	  int default 1												--�û�״̬ 0��Ч 1��Ч
);
--drop table sys_user;

select * from sys_user;


drop table sys_user;

select * from sys_user;
delete from sys_user;

insert into sys_user(loginname,password) values('aa','01746a86c7af708020a02f979f400689cb940a51');
insert into sys_user(loginname,password) values('qq','01746a86c7af708020a02f979f400689cb940a51');
insert into sys_user(loginname,password) values('ww','01746a86c7af708020a02f979f400689cb940a51');


