package mtp.board.concrete;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mtp.board.mms.BoardDAO;
import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDTO;
import mtp.paging.vo.PageVO;
import mtp.util.JsonParsing;
import mtp.view.forward.CommandAction;

public class BoardSearchCommand implements MemberCommand {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int target = Integer.parseInt(request.getParameter("category"));
		String content =  URLDecoder.decode(request.getParameter("content"),"UTF-8");
		content = URLDecoder.decode(content,"UTF-8");
		content = content.split("content=")[1];
		PrintWriter out = response.getWriter(); 
		HttpSession sess = request.getSession(false); 
		String flag =request.getParameter("flag");
		String id =request.getParameter("flag").equals("")?null:sess.getAttribute("dto")!=null?((MemberDTO)sess.getAttribute("dto")).getM_id():null;
		PageVO pv = new BoardDAO().listSearch(currentPage, target, content,id);
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
