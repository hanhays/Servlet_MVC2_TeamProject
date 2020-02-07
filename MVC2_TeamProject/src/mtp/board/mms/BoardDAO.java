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
	
	public PageVO list(int currentPage) {
		PageVO pv = new PageVO(currentPage);
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select b_num, m_id, b_title, b_day, ");
		sql.append("b_cnt, b_indent, rownum rnum from (");
		sql.append("select * from board orderby b_root desc, b_step asc)) ");
		sql.append("where rnum between ? and ?");
		try {
			conn = dataFactory.getConnection();
			
			int amount = getAmount(conn);
			pv.setAmount(amount);
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, pv.getStartNum());
			pstmt.setInt(2, pv.getEndNum());
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
	
	private int getAmount(Connection conn) {
		int amount = 0;
		String sql = "select count(*) from board";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) amount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt);
		} return amount;
	}
	
	private BoardDTO getRs(ResultSet rs) throws Exception {
		return new BoardDTO(rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3), 
							null, 
							rs.getString(4), 
							rs.getInt(5), 
							0, 
							0, 
							rs.getInt(6));
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
	

}
