package mtp.board.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mtp.member.mms.MemberDTO;
import mtp.paging.vo.PageVO;

public class BoardDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BoardDAO() {
		try {
			dataFactory = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	private void closeAll(Object...obj) {
		try {
			if(obj!=null) {
				for(int i=0; i< obj.length;i++) {
					if(obj[i] instanceof ResultSet) {
						((ResultSet)obj[i]).close();
					}
					if(obj[i] instanceof PreparedStatement) {
						((PreparedStatement)obj[i]).close();
					}
					if(obj[i] instanceof Connection) {
						((Connection)obj[i]).close();
					}
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void create(BoardDTO dto) throws Exception {
		StringBuffer sql =new StringBuffer("insert into board(");
		sql.append("b_num,m_id,b_title,b_content) ");
		sql.append("values((select nvl(max(b_num),0)+1 from board),?,?,?)");
		boolean flag = false;
		try {
			conn = dataFactory.getConnection();
	
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getM_id());
			pstmt.setString(2, dto.getB_title());
			pstmt.setString(3, dto.getB_content());
			if(pstmt.executeUpdate()>0);
			flag = rootUpdate(conn,dto.getM_id());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!flag) throw new Exception();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
			}finally {
				closeAll(pstmt, conn);
			}
		}
	}
	public boolean rootUpdate(Connection conn,String id) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("update board set b_root=b_num ");
		sql.append("where m_id = ?");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,id);
			flag=pstmt.executeUpdate()>0 ? true:false;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(pstmt);
		}
		return flag;
	}
	public BoardDTO read(int num) throws Exception {
		BoardDTO dto = null;
		StringBuffer sql =new StringBuffer("select * from board  where b_num = ?");
		boolean flag = false;
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(flag);
			increaseReadcnt(num, conn);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//int readcnt = rs.getInt("readcnt")+1;
				dto = getRs(rs);
				//dto.setB_cnt(dto.getB_cnt()+1);
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!flag)throw new Exception();
				conn.commit();
			}catch (Exception e) {
				conn.rollback();
			}finally {
				closeAll(rs, pstmt, conn);
			}
		}
		return dto;
	}

	private void increaseReadcnt(int num, Connection conn) {
		String sql = "update board set b_cnt= b_cnt+1 where b_num =?";
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt);
		}
	}

	public void delete(int num) {
		StringBuffer sql =new StringBuffer("delete from board where b_num =");
		sql.append("?");
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, conn);
		}
	}

	public BoardDTO updateui(int num) {
		BoardDTO dto = null;
		StringBuffer sql =new StringBuffer("select * from board where b_num = ?");
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto =getRs(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
	}

	public void update(BoardDTO dto) {
		StringBuffer sql =new StringBuffer("update board set");
		sql.append("b_title=?, b_content=?, b_day=sysdate");
		sql.append("where num=?");
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getB_title());
			pstmt.setString(2, dto.getB_content());
			pstmt.setInt(3, dto.getB_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, conn);
		}
	}

	public BoardDTO replyui(int num) {
		BoardDTO dto = null;
		StringBuffer sql =new StringBuffer("select * from board  where b_num = ?");
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) { 
				dto =getRs(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
	}

//	public void reply(int originalnum, String title, String content, String author) {
//		StringBuffer sql =new StringBuffer("insert into board(b_num, b_title, m_id, b_content, b_root, b_step, b_indent) ");
//		sql.append("values(?,?,?,?,?,?,?)");
//		boolean is = false;
//		try {
//			conn = dataFactory.getConnection();
//			conn.setAutoCommit(false);
//			BoardDTO dto = updateui(originalnum);
//			increaseRepStep(conn, dto);
//			int num = createNum(conn);
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, num);
//			pstmt.setString(2, title);
//			pstmt.setString(3, author);
//			pstmt.setString(4, content);
//			pstmt.setInt(5, dto.getB_root());
//			pstmt.setInt(6, dto.getB_step()+1);
//			pstmt.setInt(7, dto.getB_indent()+1);
//			is=pstmt.executeUpdate()>0?true:false;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(is) conn.commit();
//				else conn.rollback();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			closeAll(pstmt, conn);
//		}
//	}
	
	private void increaseRepStep(Connection conn, BoardDTO dto) {
		StringBuffer sql = new StringBuffer();
		sql.append("update board set b_step = b_step+1 ");
		sql.append("where b_root=? and b_step > ?");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getB_root());
			pstmt.setInt(2, dto.getB_step());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt);
		}
	}
	public List<BoardDTO> searchBoard(String m_id) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from board where ");
		sql.append("m_id ");
		sql.append("like ?");
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+m_id+"%");
			System.out.println(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getRs(rs));
			}
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		} return list;
	}
	
	public PageVO list(int currentPage,String id) {
		PageVO pv = new PageVO(currentPage);
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select /*+ INDEX_FFS(board board_list_read_index) */ ");
		sql.append("b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent from( ");
		sql.append("select b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent, ");
		sql.append("rownum rnum from (");
		sql.append("select /*+ INDEX_FFS(board board_list_read_index) */ ");
		sql.append("b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent ");
		sql.append("from board ");
		if(id!=null) {
			sql.append("where m_id = ? ");
		}
		sql.append("order by b_root desc, b_step asc)) ");
		sql.append("where rnum between ? and ?");
		try {
			conn = dataFactory.getConnection();
			int amount = getAmount(conn);
			pv.setAmount(amount);
			pstmt = conn.prepareStatement(sql.toString());
			if(id != null) {
				pstmt.setString(1,id); 
				pstmt.setInt(2, pv.getStartNum());
				pstmt.setInt(3, pv.getEndNum()); 
			}else {
				pstmt.setInt(1, pv.getStartNum());
				pstmt.setInt(2, pv.getEndNum());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getRs(rs));
			}
			pv.setB_list(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return pv;
	}

	private BoardDTO getRs(ResultSet rs) throws Exception {
		return new BoardDTO(rs.getInt(1), rs.getString(3),rs.getString(2), null, rs.getString(4), rs.getInt(5), rs.getInt(1), 0,
				rs.getInt(7));
	}
	private int getAmount(Connection conn) {
		int amount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql =new StringBuffer("select count(b_num) from board");//count(*)
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, null);
		}
		return amount;
	}
	
	public PageVO listSearch(int currentPage, int target, String content,String id) {
		PageVO pv = new PageVO(currentPage);
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select /*+ INDEX_FFS(board board_list_read_index) */ ");
		sql.append("b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent from( ");
		sql.append("select b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent, ");
		sql.append("rownum rnum from ( ");
		sql.append("select /*+ INDEX_FFS(board board_list_read_index) */ ");
		sql.append("b_num, b_title, m_id, b_day, b_cnt, b_root, b_step, b_indent ");
		sql.append("from board  where ");
		if (content != null) {
			switch (target) {
			case 0:
				sql.append("b_num ");
				break;
			case 1:
				sql.append("b_title ");
				break;
			case 2: 
				sql.append("m_id ");
				break;
			case 3:
				sql.append("b_day ");
				break;
			default:
				break;
			} sql.append("like ?");
		}
		
		if(id!=null) {
			sql.append("m_id = ?");
		}
		sql.append("order by b_root desc, b_step asc))");
		sql.append("where rnum between ? and ?");
		System.out.println(sql.toString());
		try {
			conn = dataFactory.getConnection();
			int amount = getAmount(conn);
			pv.setAmount(amount);
			pstmt = conn.prepareStatement(sql.toString());
			if (content == null) {
				pstmt.setString(1, id);
				pstmt.setInt(2, pv.getStartNum());
				pstmt.setInt(3, pv.getEndNum());
			} else {
				pstmt.setString(1,"%" + content +"%");
				pstmt.setInt(2, pv.getStartNum());
				pstmt.setInt(3, pv.getEndNum());
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(getRs(rs));
			}
			pv.setB_list(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return pv;
	}


}
