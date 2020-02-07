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
			System.gc();
		}
	} 
	
	public void create(MemberDTO dto) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member");
		sql.append("(m_id, m_password, m_name, m_birth, m_age, m_phone, m_email, m_nickname) ");
		sql.append("values(?,?,?,?,?,?,?,?)");
//		sql에 m_img 이미지 업로드 
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getM_id());
			pstmt.setString(2, dto.getM_password());
			pstmt.setString(3, dto.getM_name());
			pstmt.setString(4, dto.getM_birth());
			pstmt.setInt(5, dto.getM_age());
			pstmt.setString(6, dto.getM_phone());
			pstmt.setString(7, dto.getM_email());
			pstmt.setString(8, dto.getM_nickname());
	
			pstmt.executeUpdate();
//			int i =pstmt.executeUpdate();
			//i가 1일경우에 temp에서 member 이미지 이동하고 나서
			//m_img의 값을 update 하고 트렌젝션 여부 확인
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, conn);
		}
	}
	private boolean fileupdate(Connection conn) {
		boolean flag = false;
		
		return flag ;
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

		sql.append("delete from Member");
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
