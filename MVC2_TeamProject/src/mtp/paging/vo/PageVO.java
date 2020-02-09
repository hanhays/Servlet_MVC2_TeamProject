package mtp.paging.vo;

import java.util.List;

import mtp.board.mms.BoardDTO;
import mtp.member.mms.MemberDTO;

public class PageVO {

	private int currentPage = 1;
	private int perPage = 10;
	private int pageLine = 10;
	private int amount;
	private int totalPage;
	private int startNum;
	private int endNum;
	private int beginPageNum;
	private int stopPageNum;
	
	private List<MemberDTO> m_list;
	private List<BoardDTO> b_list;
	public PageVO() {
	}
	public PageVO(int currentPage) {
		this.currentPage = currentPage;
		executeAll();
	}
	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		executeAll();
	}


	public int getPerPage() {
		return perPage;
	}


	public void setPerPage(int perPage) {
		this.perPage = perPage;
		executeAll();
	}


	public int getPageLine() {
		return pageLine;
	}


	public void setPageLine(int pageLine) {
		this.pageLine = pageLine;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
		executeAll();
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getStartNum() {
		return startNum;
	}


	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}


	public int getEndNum() {
		return endNum;
	}


	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}


	public int getBeginPageNum() {
		return beginPageNum;
	}


	public void setBeginPageNum(int beginPageNum) {
		this.beginPageNum = beginPageNum;
	}


	public int getStopPageNum() {
		return stopPageNum;
	}


	public void setStopPageNum(int stopPageNum) {
		this.stopPageNum = stopPageNum;
	}


	public List<MemberDTO> getM_list() {
		return m_list;
	}


	public void setM_list(List<MemberDTO> m_list) {
		this.m_list = m_list;
	}


	public List<BoardDTO> getB_list() {
		return b_list;
	}


	public void setB_list(List<BoardDTO> b_list) {
		this.b_list = b_list;
	}


	public void executeAll() {
		totalPage = (amount - 1) / perPage + 1;

		startNum = (currentPage - 1) * perPage + 1;

		endNum = currentPage * perPage;
		if (endNum > amount)
			endNum = amount;

		beginPageNum = ((currentPage - 1) / pageLine) * pageLine + 1;

		stopPageNum = beginPageNum + (pageLine - 1);
		if (stopPageNum > totalPage)
			stopPageNum = totalPage;
	}

}
