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

import mtp.paging.vo.PageVO;

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
	public boolean create(MemberDTO dto) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member");
		sql.append("(m_id, m_password, m_name, m_birth, m_age, m_phone, m_email, m_nickname) ");
		sql.append("values(?,?,?,?,?,?,?,?)");
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
			flag = pstmt.executeUpdate() > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, conn);
		}
		return flag;
	}

	public MemberDTO read(String id) { 
		MemberDTO dto = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select /*+ INDEX_FFS(member member_list_read_index) */ ");
		sql.append("m_id,m_name,m_birth,m_age,m_phone,m_email,m_nickname,m_grade from member ");
		sql.append("where m_id = ?");
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = getRs(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
	}
	
	public void update_admin(MemberDTO dto) {

		StringBuffer sql = new StringBuffer();
		sql.append("update member set ");
		sql.append("m_grade = ? ");
		sql.append("where m_id = ?");

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			String grade = String.valueOf(dto.getM_grade());
			pstmt.setString(1, grade);
			pstmt.setString(2, dto.getM_id());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, conn);
		}
	}

	public void update(MemberDTO dto) {

		StringBuffer sql = new StringBuffer();
		sql.append("update member set ");
		sql.append("m_password = ?, m_name = ?, m_phone = ?, m_email = ?, m_nickname = ? ");
		sql.append("where m_id = ?");

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			conn.setAutoCommit(false);

			pstmt.setString(1, dto.getM_password());
			pstmt.setString(2, dto.getM_name());
			pstmt.setString(3, dto.getM_phone());
			pstmt.setString(4, dto.getM_email());
			pstmt.setString(5, dto.getM_nickname());
			pstmt.setString(6, dto.getM_id());

			pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, conn);
		}
	}
	
	public MemberDTO updateui(String id) {
		return read(id);
	}
	
	public boolean delete(String m_id, String m_pw) throws Exception {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from Member ");
		sql.append("where m_id=? and m_password=?");
		try {
			conn = dataFactory.getConnection();

			conn.setAutoCommit(false);

			String password = passwordCheck(conn, m_id);

			pstmt = conn.prepareStatement(sql.toString());

			if (password != null ? password.equals(m_pw) : false) {
				pstmt.setString(1, m_id);
				pstmt.setString(2, m_pw);
				flag = pstmt.executeUpdate() > 0 ? true : false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!flag)
					throw new Exception();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
			} finally {
				closeAll(pstmt, conn);
			}
		}
		return flag;
	}

	public PageVO list(int currentPage) {
		PageVO pv = new PageVO(currentPage);
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select /*+ INDEX_FFS(member member_list_read_index) */ ");
		sql.append("m_id,m_name,m_birth,m_age,m_phone,m_email,m_nickname,m_grade from( ");
		sql.append("select m_id, m_name,m_birth,");
		sql.append("m_age,m_phone, m_email, m_nickname, m_grade,rownum rnum from (");
		sql.append("select /*+ INDEX_FFS(member member_list_read_index) */ ");
		sql.append("m_id,m_name,m_birth,m_age,m_phone,m_email,m_nickname,m_grade from member ");
		sql.append("order by m_id desc");
		sql.append("))where rnum between ? and ?");
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
			pv.setM_list(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return pv;
	}

	public PageVO listSearch(int currentPage, int target, String content) {
		PageVO pv = new PageVO(currentPage);
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select /*+ INDEX_FFS(member member_list_read_index) */ ");
		sql.append("m_id,m_name,m_birth,m_age,m_phone,m_email,m_nickname,m_grade from ");
		sql.append("(select m_id, m_name,m_birth, ");
		sql.append("m_age,m_phone, m_email, m_nickname, m_grade,rownum rnum from(");
		sql.append("select /*+ INDEX_FFS(member member_list_read_index) */ ");
		sql.append("m_id,m_name,m_birth,m_age,m_phone,m_email,m_nickname,m_grade ");
		sql.append("from member ");
		if (content != null) {
			sql.append("where ");
			switch (target) {
			case 0:
				sql.append("m_grade ");
				break;
			case 1:
				sql.append("m_id ");
				break;
			case 2:
				sql.append("m_name ");
				break;
			case 3:
				sql.append("m_nickname ");
				break;
			case 4:
				sql.append("m_phone ");
				break;
			case 5:
				sql.append("m_email ");
				break;
			}
			sql.append("like ?");
		} 
		sql.append("order by m_id asc");
		sql.append("))where rnum between ? and ?");
		try {
			conn = dataFactory.getConnection();
			int amount = getAmount(conn);
			pv.setAmount(amount);
			pstmt = conn.prepareStatement(sql.toString());
			if(content ==null) {
				pstmt.setInt(1, pv.getStartNum());
				pstmt.setInt(2, pv.getEndNum());
			}else {
				pstmt.setString(1, "%" + content + "%");
				pstmt.setInt(2, pv.getStartNum());
				pstmt.setInt(3, pv.getEndNum());
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(getRs(rs));
			}
			pv.setM_list(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return pv;
	}
	
	public MemberDTO login(String id, String password,boolean flag) {
		MemberDTO dto = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select m_id,m_grade from member where m_id =? ");
		if(flag) {
			sql.append("and m_password = ?");
		}
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			if(flag) {
				pstmt.setString(2, password);
			}
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
	private MemberDTO getRs(ResultSet rs) throws Exception { 
		return new MemberDTO(rs.getString(1), null, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8).charAt(0));
	}
	private String passwordCheck(Connection conn, String id) {
		String password = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select m_password from member ");
		sql.append("where m_id = ?");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				password = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt);
		}

		return password;
	}

	private int getAmount(Connection conn) {
		int amount = 0;
		String sql = "select count(*) from member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				amount = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt);
		}
		return amount;
	}
}
