package mtp.member.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mtp.paging.vo.MemberPageVO;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public MemberDAO() {
		try {
			dataFactory = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private void closeAll(Object... obj) {
		try {
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					if (obj[i] instanceof ResultSet) {
						((ResultSet) obj[i]).close();
					}
					if (obj[i] instanceof PreparedStatement) {
						((PreparedStatement) obj[i]).close();
					}
					if (obj[i] instanceof Connection) {
						((Connection) obj[i]).close();
					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null)rs = null;
			if(pstmt!=null)pstmt = null; 
			if(conn!=null)conn = null; 
			System.gc();
		}
	} 
	public MemberDTO read(String id) { 
		MemberDTO dto = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select m_id,m_name,m_birth,m_age,m_phone,m_email,m_nickname,m_grade from member ");
		sql.append("where m_id = ?");
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()) { 
				dto = new MemberDTO(rs.getString(1),
									null,
									rs.getString(2),
									rs.getString(3),
									rs.getInt(4),
									rs.getString(5),
									rs.getString(6),
									rs.getString(7),
									rs.getString(8).charAt(0));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs,pstmt,conn);
		}
		return dto ;
	}

	public boolean delete(String m_id, String m_pw) {

		boolean flag = false;
		StringBuffer sql = new StringBuffer();

		sql.append("delete from Member ");
		sql.append("where m_id=? and m_password=?");

		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, m_id);
			pstmt.setString(2, m_pw);

			flag = pstmt.executeUpdate() > 0 ? true : false;

			/*
			 * if(i>0) { System.out.println("삭제되었습니다."); }else {
			 * System.out.println("비밀번호를 다시 확인해주세요."); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, conn);
		}

		return flag;
	}
	
	
	public MemberPageVO list(int m_currentPage) {
		MemberPageVO mp = new MemberPageVO(m_currentPage);
		List<MemberDTO> m_list = new ArrayList<MemberDTO>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append("(select ronum rnum, m_id, m_password, m_name, m_birth, m_age, m_phone, m_email, m_nickname from ");
		sb.append("(select * from member order by m_grade desc)) ");
		sb.append("where rnum between ? and ?");
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		} 
		return mp;
	}
 
	public MemberDTO login(String id, String password) {
		MemberDTO dto = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select m_id,m_grade from member where m_id =? and m_password = ?");
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,id);
			pstmt.setString(2,password);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO(rs.getString("m_id"),rs.getString("m_grade").charAt(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs,pstmt,conn);
		}
		
		return dto;
	}

}
