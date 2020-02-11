package mtp.util;

import java.util.List;

import mtp.board.mms.BoardDTO;
import mtp.member.mms.MemberDTO;
import mtp.paging.vo.PageVO;

public class JsonParsing<E> {
	private String[] msg;
	private PageVO pv;

	@SuppressWarnings("unused")
	private JsonParsing() {
		// TODO Auto-generated constructor stub
	}

	public JsonParsing(PageVO pv, List<E> list) {
		this.pv=pv;
		if (pv != null && list != null ? list.size() > 0 ? true : false : false) {
			this.msg = new String[] { switchList(list), pagin(pv) };
		}
	}
	@SuppressWarnings("unchecked")
	private String switchList(List<E> list) {
		return list.get(0) instanceof MemberDTO ? 
			   memberList(((List<MemberDTO>) list))
			 : boardList(((List<BoardDTO>) list));
	}

	private String pagin(PageVO pv) {
		StringBuffer msg = new StringBuffer();
		msg.append("{");
		msg.append("\"beginPageNum\":");
		msg.append(pv.getBeginPageNum());
		msg.append(",\"stopPageNum\":");
		msg.append(pv.getStopPageNum());
		msg.append(",\"currentPage\":");
		msg.append(pv.getCurrentPage());
		msg.append(",\"totalPage\":");
		msg.append(pv.getTotalPage());
		msg.append(",\"startNum\":");
		msg.append(pv.getStartNum());
		msg.append(",\"amount\":");
		msg.append(pv.getAmount());
		msg.append(",\"endNum\":");
		msg.append(pv.getEndNum());
		msg.append("}");
		return msg.toString();
	}
	private String boardList(List<BoardDTO> list) {
		StringBuffer msg = new StringBuffer();
		for (BoardDTO x : list) {
			msg.append("{ ");
			msg.append("\"b_num\":");
			msg.append(x.getB_num());
			msg.append(",\"m_id\":\"");
			msg.append(x.getM_id());
			msg.append("\",\"b_title\":\"");
			msg.append(x.getB_title());
			msg.append("\",\"b_day\":\"");
			msg.append(x.getB_day());
			msg.append("\",\"b_cnt\":");
			msg.append(x.getB_cnt());
			msg.append(",\"b_root\":");
			msg.append(x.getB_root());
			msg.append(",\"b_indent\":");	
			msg.append(x.getB_indent());
			msg.append("}, ");
		}
		return comaSplit(msg);
	}
	private String memberList(List<MemberDTO> list) {
		StringBuffer msg = new StringBuffer();
		for (MemberDTO x : list) {
			msg.append("{");
			msg.append("\"m_id\":\"");
			msg.append(x.getM_id());
			msg.append("\",\"m_name\":\"");
			msg.append(x.getM_name());
			msg.append("\",\"m_birth\":\"");
			msg.append(x.getM_birth());
			msg.append("\",\"m_age\":");
			msg.append(x.getM_age());
			msg.append(",\"m_phone\":\"");
			msg.append(x.getM_phone());
			msg.append("\",\"m_email\":\"");
			msg.append(x.getM_email());
			msg.append("\",\"m_nickname\":\"");
			msg.append(x.getM_nickname());
			msg.append("\",\"m_grade\":\"");
			msg.append(x.getM_grade());
			msg.append("\"}, ");
		}
		return comaSplit(msg);
	}

	private String comaSplit(StringBuffer msg) {
		return msg.substring(0, msg.lastIndexOf(","));
	}

	public String[] getMsg() {
		return msg==null?new String[]{"",pagin(pv)}:msg;
	}
}
