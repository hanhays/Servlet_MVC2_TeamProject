package mtp.board.concrete;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mtp.board.mms.BoardDAO;
import mtp.board.mms.BoardDTO;
import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDTO;
import mtp.paging.vo.PageVO;
import mtp.view.forward.CommandAction;

public class BoardListCommand implements MemberCommand {

	public BoardListCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
//		String currentPage_ = request.getParameter("currentPage");
//		int currentPage = 1;
//		if (currentPage_ != null) currentPage = Integer.parseInt(currentPage_);
//		
////		List<BoardDTO> list = new BoardDAO().list();
//		
//		PageVO pv = new BoardDAO().list(currentPage);
//	
//		request.setAttribute("list", pv.getB_list());
//		request.setAttribute("pv", pv);
//		
//		return new CommandAction(false, "board_list.jsp");
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int target = Integer.parseInt(request.getParameter("category"));
		String content = request.getParameter("content");
		content = URLDecoder.decode(content, "UTF-8");
		content = content.split("content=")[1];
		PrintWriter out = response.getWriter();
		PageVO pv = new BoardDAO().listSearch(currentPage, target, content);
		out.print("[[");
		out.print(getListParsingJSON(pv.getB_list()));
		out.print("],[");
		out.print(getPagingJSON(pv));
		out.print("]]");
		return null;
	}
	
	private String getPagingJSON(PageVO pv) {
		String msg = "{";
		msg+="\"amount\":"+pv.getAmount()+",";
		msg+="\"totalPage\":"+pv.getTotalPage()+",";
		msg+="\"currentPage\":"+pv.getCurrentPage()+",";
		msg+="\"beginPageNum\":"+pv.getBeginPageNum()+",";
		msg+="\"stopPageNum\":"+pv.getStopPageNum()+",";
		msg+="\"startNum\":"+pv.getStartNum()+",";
		msg+="\"endNum\":"+pv.getEndNum()+"}";
		return msg;
	}
	
	private String getListParsingJSON(List<BoardDTO> list) {
		String msg = "{ ";
		for (BoardDTO x : list) {
			msg += "\"b_num\" :\" "+x.getB_num()+"\", ";
			msg += "\"b_title\" :\" "+x.getB_title()+"\", ";
			msg += "\"m_id\" :\" "+x.getM_id()+"\", ";
			msg += "\"b_day\" :"+x.getB_day()+"\", ";
			msg += "\"b_cnt\" :\""+x.getB_cnt() + "\", ";
			msg += "\"b_indent\" :\""+x.getB_indent() + "\", ";
			msg += "\"b_step\" :\""+x.getB_step() + "\", ";
			msg += "\"b_root\" :\""+x.getB_root()+ "\"}, ";
		}
		if(msg.length()>10) {
		msg = msg.substring(0, msg.length() - 2);
		}
		return msg ;
	}

}