package mtp.member.concrete;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.paging.vo.PageVO;
import mtp.util.JsonParsing;
import mtp.view.forward.CommandAction;

public class MemberListCommand implements MemberCommand {

	public MemberListCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {

		String currentPage_ = request.getParameter("currentPage");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		int currentPage = 1;
		if(currentPage_ != null) currentPage = Integer.parseInt(currentPage_);
		
		PageVO pv = new MemberDAO().list(currentPage);
		PrintWriter out = response.getWriter(); 
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JsonParsing jp = new JsonParsing(pv, pv.getM_list());
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
