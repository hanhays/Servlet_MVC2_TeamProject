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

	public boolean delete(String m_id, String m_pw)throws Exception { 

		boolean flag = false; //boolean은 트랜잭션을 위해 선언함. 트랜잭션의 기준이 됨
		StringBuffer sql = new StringBuffer();

		sql.append("delete from Member ");
		sql.append("where m_id=? and m_password=?");  //id와 pw가 동일할 때 delete를 해준다.

		try {

			conn = dataFactory.getConnection();
			
			conn.setAutoCommit(false); //default로 commit이 진행되기 때문에 autocommit 해제하는 과정
			
			String password= passwordCheck(conn, m_id);  //pw를 받아와서 검증을 하는. 에러. 
			//세션이 있는 아이디를 받아오는.
			//검증을 함. delete 하기 전에 먼저 pw 부터 검증을 하고, 만약 pw가 일치하지 않으면 rollback시킨다.
			pstmt = conn.prepareStatement(sql.toString());
			
			//pw가 일치하지 않으면 if문이 들어가지 않으므로 rollback시킴.
			if(password!=null?password.equals(m_pw):false) { //pw가 null이 아니면, pw체크에서 받아온 pw와 파라메터로 받아온 pw를 비교함
				pstmt.setString(1, m_id);
				pstmt.setString(2, m_pw);
				
				//105번이 commit과 rollback 여부를 최종적으로 검증
				flag = pstmt.executeUpdate() > 0 ? true : false; //성공적으로 실행이 되면 1을 반환하고, 실패하면 0을 반환
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!flag)
				throw new Exception();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
			}finally {
				closeAll(pstmt, conn);
			}
		}

		return flag;  //무조건 return
	}
	private String passwordCheck(Connection conn,String id) {
		String password = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select m_password from member ");
		sql.append("where m_id = ?");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				password = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(rs,pstmt);
		}
		
		return password ;
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
