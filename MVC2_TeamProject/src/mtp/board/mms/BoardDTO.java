package mtp.board.mms;

import java.io.Serializable;

public class BoardDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int b_num;
	private String m_id;
	private String b_title;
	private String b_content;
	private String b_day;
	private int b_cnt;
	private int b_root;
	private int b_step;
	private int b_indent;


	public BoardDTO(int b_num, String m_id, String b_title, String b_content, String b_day, int b_cnt, int b_root,
			int b_step, int b_indent) {
		this.b_num = b_num;
		this.m_id = m_id;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_day = b_day;
		this.b_cnt = b_cnt;
		this.b_root = b_root;
		this.b_step = b_step;
		this.b_indent = b_indent;
	}

	public int getB_num() {
		return b_num;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public String getB_day() {
		return b_day;
	}

	public void setB_day(String b_day) {
		this.b_day = b_day;
	}

	public int getB_cnt() {
		return b_cnt;
	}

	public void setB_cnt(int b_cnt) {
		this.b_cnt = b_cnt;
	}

	public int getB_root() {
		return b_root;
	}

	public void setB_root(int b_root) {
		this.b_root = b_root;
	}

	public int getB_step() {
		return b_step;
	}

	public void setB_step(int b_step) {
		this.b_step = b_step;
	}

	public int getB_indent() {
		return b_indent;
	}

	public void setB_indent(int b_indent) {
		this.b_indent = b_indent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BoardDTO [b_num=" + b_num + ", m_id=" + m_id + ", b_title=" + b_title + ", b_content=" + b_content
				+ ", b_day=" + b_day + ", b_cnt=" + b_cnt + ", b_root=" + b_root + ", b_step=" + b_step + ", b_indent="
				+ b_indent + "]";
	}
	
	

}
