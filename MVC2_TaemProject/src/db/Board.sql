create table board(
b_num number(10) primary key not null,
m_id varchar2(15) references member (m_id),
b_title varchar2(60) not null,
b_content varchar2(1000) not null,
b_day date default sysdate,
b_cnt number(6) default 0,
b_root number(10) null,
b_step number(6) default 0,
b_indent number(6) default 0,
b_img varchar2(100) 
)

drop table board

create table test(
num number(2) primary key
)

insert into test(num) values((select nvl(max(num),0)+1 from test));
select * from member
select * from board

--공지를 넣을것인가? 
insert into board(b_num,m_id,b_title,b_content) 
values(
		(select nvl(max(b_num),0)+1 from board),
		'admin','Test공지','Test공지'
		)
		
update  board set b_root = (select max(b_num) from BOARD )


delete from BOARD where b_num = 1;

select * from board



select * from BOARD
