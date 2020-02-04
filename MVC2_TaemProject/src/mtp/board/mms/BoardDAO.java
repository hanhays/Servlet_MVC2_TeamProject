package mtp.board.mms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
	

}
