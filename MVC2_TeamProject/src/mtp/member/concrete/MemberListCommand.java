package mtp.member.concrete;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.paging.vo.MemberPageVO;
import mtp.view.forward.CommandAction;

public class MemberListCommand implements MemberCommand {
	public MemberListCommand() {
	}
	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String what)
			throws IOException, ServletException {
		
		String currentPage_ = request.getParameter("currentPage");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		int currentPage = 1;
		if(currentPage_ != null) currentPage = Integer.parseInt(currentPage_);
		
		MemberPageVO mp = new MemberDAO().list(currentPage);
		
		request.setAttribute("m_list", mp.getList());
		request.setAttribute("mp", mp);
		
		return new CommandAction(false, "member_list.jsp"); 
	}

}
