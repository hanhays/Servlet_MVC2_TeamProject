package mtp.member.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.view.forward.CommandAction;

public class MemberSearchCommand implements MemberCommand {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		
		int target = Integer.parseInt(request.getParameter("category"));
		String value = request.getParameter("content");
		
		//MemberDTO memberdto = new MemberDAO().listBySth(target, value);
		
		//request.setAttribute("memberdto", memberdto);
		
		return new CommandAction(false, "member_lilst.jsp");
	}

}
