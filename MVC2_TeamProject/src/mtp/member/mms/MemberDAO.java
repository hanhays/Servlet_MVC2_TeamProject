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
			/*
			 * if(rs!=null)rs = null; if(pstmt!=null)pstmt = null; if(conn!=null)conn =
			 * null;
			 */
			System.gc();
		}
	}

	public MemberDTO read(String id) {
		MemberDTO dto = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select m_id,m_name,m_birth,m_age,m_phone,m_email,m_nickname,m_img,m_grade from member ");
		sql.append("where m_id = ?");
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO(rs.getString(1), null, rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9).charAt(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
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
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO(rs.getString("m_id"), rs.getString("m_grade").charAt(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return dto;
	}

	public boolean delete(String id, String password) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();

		sql.append("delete from Member where m_id=? and m_password=?");
		
		try {

			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, id);
			pstmt.setString(2, password);
									
			int i = pstmt.executeUpdate();
			flag = i>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (flag) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeAll(pstmt, conn);
			}
		}
		return flag;
	}

	public String passwordCheck(String id, Connection conn) {
		StringBuffer sql = new StringBuffer();
		String password = null;
		sql.append("select m_password from member ");
		sql.append("where m_id = ?");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				password = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt);
		}
		return password;
	}
}
