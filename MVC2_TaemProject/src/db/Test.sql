create table member(
m_id varchar2(15) primary key not null,
m_password varchar2(20) not null,
m_name varchar2(20) not null,
m_birth date default sysdate,
m_age number(3) not null, --insert할때 자동으로 update 할수 있는 기능 구현 필요(트랜잭션 생각하기)
m_phone varchar2(15) not null,
m_email varchar2(50) not null,
m_nickname varchar2(25) unique not null,
m_img varchar2(100) default 'member.jgp',
m_grade char(1) default 'a'
)

-- 등급에 관한거 a=일반회원,b=admin,c=나쁜놈

create table board(
b_num number(10) primary key not null,
m_id varchar2(15) references member (m_id),
b_title varchar2(60) not null,
b_content varchar2(1000) not null,
b_day date default sysdate,
b_cnt number(6) default 0,
b_root number(10) default 0,
b_step number(6) default 0,
b_indent number(6) default 0,
b_img varchar2(100) 
)
