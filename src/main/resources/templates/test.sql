create table sys_user(
    id			int				identity(1,1)  primary key not null,	--id���� ������
    loginName   nvarchar(50)	NOT NULL UNIQUE,						--��¼�û���
    password    nvarchar(50)	NOT NULL ,								--��¼����
	email		varchar(255)	NOT NULL UNIQUE,						--�û�����
	createTime  datetime default CONVERT(varchar(100), GETDATE(), 20),	--�û�����ʱ��
	code		varchar(255) ,									--'������'
	state		int            NOT NULL default 0 ,						--�û�����״̬��0��ʾδ���1��ʾ����
    nstatusid	int default 1											--�û�״̬ 0��Ч 1��Ч
);
--drop table sys_user;


insert into sys_user(loginname,password,email) values('aa','a�ҵ�����1','qwe');
insert into sys_user(loginname,username,password) values('qq','q�ҵ�����2','qwe');
insert into sys_user(loginname,username,password) values('ww','w�ҵ�����3','qwe');


