create table sys_user(
    id			int				identity(1,1)  primary key not null,--id���� ������
    loginName   nvarchar(50)	NOT NULL UNIQUE,					--��¼�û���
    userName    nvarchar(50),	--�û�����
    password    nvarchar(50)	NOT NULL ,					--��¼����
	createTime  datetime default CONVERT(varchar(100), GETDATE(), 20), --�û�����ʱ��
	STATUSID	int default 1 --�û�״̬ 1��Ч
);
--drop table sys_user;

insert into sys_user(loginname,username,password) values('aa','a�ҵ�����1','qwe');
insert into sys_user(loginname,username,password) values('qq','q�ҵ�����2','qwe');
insert into sys_user(loginname,username,password) values('ww','w�ҵ�����3','qwe');


