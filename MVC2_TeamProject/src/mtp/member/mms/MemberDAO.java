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
		}
	}

	public boolean create(MemberDTO dto) {
		StringBuffer sql = new StringBuffer();
		boolean flag = false;
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

			int i = pstmt.executeUpdate();
			flag = i>0? true: false;
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
		sql.append("select m_id,m_name,m_birth,m_age,m_phone,m_email,m_nickname,m_grade from member ");
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(pstmt, conn);
		}
		return flag;
	}

	public PageVO listSearch(int currentPage, int target, String value) {
		PageVO pv = new PageVO(currentPage);
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append("(select m_id, m_name,m_birth, ");
		sql.append("m_age,m_phone, m_email, m_nickname, m_grade,rownum rnum ");
		sql.append("from(select * from member where ");
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
		sql.append("= ? )) ");
		sql.append("where rnum between ? and ?");
		try {
			conn = dataFactory.getConnection();
			int amount = getAmount(conn);
			pv.setAmount(amount);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, value);
			pstmt.setInt(2, pv.getStartNum());
			pstmt.setInt(3, pv.getEndNum());
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

	public PageVO list(int currentPage) {
		PageVO mp = new PageVO(currentPage);
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select m_id, m_name,m_birth,");
		sql.append("m_age,m_phone, m_email, m_nickname, m_grade,rownum rnum from (");
		sql.append("select * from member order by m_id desc)) ");
		sql.append("where rnum between ? and ?");
		try {
			conn = dataFactory.getConnection();
			int amount = getAmount(conn);
			mp.setAmount(amount);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, mp.getStartNum());
			pstmt.setInt(2, mp.getEndNum());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getRs(rs));
			}
			mp.setM_list(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return mp;
	}

	private MemberDTO getRs(ResultSet rs) throws Exception {
		return new MemberDTO(rs.getString(1), null, rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8).charAt(0));
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

}
