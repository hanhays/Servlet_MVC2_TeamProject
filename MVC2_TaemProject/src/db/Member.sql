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


insert into member(m_id,m_password,m_name,m_age,m_phone,m_email,m_nickname,m_birth) 
values('admin','1234','관리자',100,'010-9999-8888','ywyi1992@gmail.com','admin',sysdate)


select * from member

drop table member

