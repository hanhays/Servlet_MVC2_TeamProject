package mtp.member.mms;

import java.io.Serializable;

public class MemberDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String m_id;       
	private String m_password;
	private String m_name;     
	private String m_birth;    
	private int m_age;      
	private String m_phone;    
	private String m_email;    
	private String m_nickname;
	private char m_grade;    
	
	public MemberDTO() {
	}
	
	public MemberDTO(String m_id, char m_grade) {
		this.m_id = m_id;
		this.m_grade = m_grade;
	}

	public MemberDTO(String m_id, String m_password, String m_name, String m_birth, int m_age, String m_phone,
			String m_email, String m_nickname,char m_grade) {
		this.m_id = m_id;
		this.m_password = m_password;
		this.m_name = m_name;
		this.m_birth = m_birth;
		this.m_age = m_age;
		this.m_phone = m_phone;
		this.m_email = m_email;
		this.m_nickname = m_nickname;
		this.m_grade = m_grade;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_birth() {
		return m_birth;
	}

	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}

	public int getM_age() {
		return m_age;
	}

	public void setM_age(int m_age) {
		this.m_age = m_age;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_nickname() {
		return m_nickname;
	}

	public void setM_nickname(String m_nickname) {
		this.m_nickname = m_nickname;
	}

	public char getM_grade() {
		return m_grade;
	}

	public void setM_grade(char m_grade) {
		this.m_grade = m_grade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_id == null) ? 0 : m_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDTO other = (MemberDTO) obj;
		if (m_id == null) {
			if (other.m_id != null)
				return false;
		} else if (!m_id.equals(other.m_id))
			return false;
		return true;
	}
}
