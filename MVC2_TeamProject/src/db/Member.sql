create table member(
m_id       varchar2(15) primary key not null,
m_password varchar2(20) not null,
m_name     varchar2(20) not null,
m_birth    date not null,
m_age      number(3) not null, --insert할때 자동으로 update 할수 있는 기능 구현 필요(트랜잭션 생각하기)
m_phone    varchar2(15) not null,
m_email    varchar2(50) not null,
m_nickname varchar2(25) unique not null,
m_img      varchar2(100) default 'member.jgp',
m_grade    char(1) default 'a' --a = 일반회원,b = admin, c = 벤대상
)
SELECT * FROM DBA_SYS_PRIVS
WHERE GRANTEE = 'c3'

select * from dba_sys_privs

select * from dba_role_privs
where grantee = 'C3';

commit  

insert into member(m_id,m_password,m_name,m_age,m_phone,m_email,m_nickname,m_birth) 
values('g','g1234','ggg',5,'010-5555-8888','g@gmail.com','gnn','1993-03-22')
values('m001','1234','김씨',29,'010-5453-2487','ywyi1992@naver.com','첫회원임',sysdate)
 
update member set m_grade = 'b' where m_id='admin';

select * from member
select * from member where m_id = 'admin'

select * from member between ? and ?



select * from 
(select ronum rnum, m_id, m_password, m_name, to_char(m_birth, 'yyyy-mm-dd') m_birth, m_age, m_phone, m_email, m_nickname, m_grade from
(select * from member order by m_grade desc))
where rnum between 1 and 10

select * from member 

select count(*) from member

drop table member
		
