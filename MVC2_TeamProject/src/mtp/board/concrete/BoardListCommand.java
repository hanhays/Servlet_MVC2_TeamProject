package mtp.board.concrete;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mtp.board.mms.BoardDAO;
import mtp.board.mms.BoardDTO;
import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDTO;
import mtp.paging.vo.PageVO;
import mtp.util.JsonParsing;
import mtp.view.forward.CommandAction;

public class BoardListCommand implements MemberCommand {

	public BoardListCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		String currentPage_ = request.getParameter("currentPage");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		int currentPage = 1;
		if(currentPage_ != null) currentPage = Integer.parseInt(currentPage_);
		HttpSession sess = request.getSession(false); 
		System.out.println(request.getParameter("flag"));
		boolean flag = Boolean.valueOf(request.getParameter("flag"));
		String id =Boolean.valueOf(request.getParameter("flag"))?null:sess.getAttribute("dto")!=null?((MemberDTO)sess.getAttribute("dto")).getM_id():null;
		PageVO pv = new BoardDAO().list(currentPage,id);
		PrintWriter out = response.getWriter();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JsonParsing jp = new JsonParsing(pv, pv.getB_list());
		String[] msg = jp.getMsg();
		if(msg!=null) { 
			out.print("[");
			out.print("[");
			out.print(msg[0]);
			out.print("]");
			out.print(",[");
			out.print(msg[1]);
			out.print("]");
			out.print("]");
		}
		return null;
	}
}