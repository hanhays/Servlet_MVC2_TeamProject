create table board(
b_num number(10) primary key not null,
m_id varchar2(15) references member (m_id),
b_title varchar2(60) not null,
b_content varchar2(1000) not null,
b_day date default sysdate,
b_cnt number(6) default 0,
b_root number(10) null,
b_step number(6) default 0,
b_indent number(6) default 0
) 
select /*+ INDEX_FFS(board board_list_read_index) */ b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent from( 
select b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent, rownum rnum from (
select /*+ INDEX_FFS(board board_list_read_index) */ b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent from board order by b_root desc, b_step asc
))where rnum between 1 and 10

select /*+ INDEX_FFS(board board_list_read_index) */ b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent from( 
select b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent, rownum rnum from (
select /*+ INDEX_FFS(board board_list_read_index) */ b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent from board  where m_id like '%m%' order by b_root desc, b_step asc))
where rnum between 1 and 3

create index board_list_read_index
on board(b_num, m_id, b_title, b_content, b_day, b_cnt, b_root, b_step, b_indent)

select * from 
(select b_num, m_id, b_title, b_day, b_cnt, b_indent, rownum rnum from 
(select * from board order by b_root desc, b_step asc)) 
where rnum between ? and ?

drop table board

create table test(
num number(2) primary key
)

insert into board(
b_num ,m_id, b_title, b_content)
values((select nvl(max(b_num),0)+1 from board),'admin','공지','공지라네~')
insert into board(b_num,m_id, b_title, b_content) values((select nvl(max(b_num),0)+1 from board),'admin','공지','공지라네~')

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
commit

select /*+ INDEX_FFS(board board_list_read_index) */ b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent from( 
select b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent, rownum rnum from ( 
select /*+ INDEX_FFS(board board_list_read_index) */ b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent from board  
where b_num like 5 order by b_root desc, b_step asc
)
)where rnum between 1 and 10

select * from BOARD
	

drop table board
