package mtp.member.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.view.forward.CommandAction;

public class MemberLoginCommand implements MemberCommand {

	public MemberLoginCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		String id = request.getParameter("id");
		String password= request.getParameter("password");
		MemberDTO dto=new MemberDAO().login(id,password);
		if(dto !=null) {
			HttpSession sess= request.getSession();
			sess.setAttribute("dto",dto);
			sess.setMaxInactiveInterval(30);
			return new CommandAction(true,"/MVC2_TeamProject/");
		}
		request.setAttribute("id", id);
		request.setAttribute("msg","아이디와 비번을 확인해 주세요.");
		return new CommandAction(false,"loginui.do");
	}

}
