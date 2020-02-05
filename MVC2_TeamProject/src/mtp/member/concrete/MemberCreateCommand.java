package mtp.member.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.view.forward.CommandAction;

public class MemberCreateCommand implements MemberCommand {

	public MemberCreateCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String what)
			throws IOException, ServletException {

		String m_id = request.getParameter("id");
		String m_password = request.getParameter("password");
		String m_name = request.getParameter("name");
		String m_birth = request.getParameter("birth");
		String sAge = request.getParameter("age");
		int m_age = -1;
		if(sAge != null) {
			m_age = Integer.parseInt(sAge);
		}
		String m_phone = request.getParameter("phone");
		String m_email = request.getParameter("email");
		String m_nickname = request.getParameter("nickname");
		//String m_img = request.getParameter("img"); // 이미지는 파일이다. 파일올릴 수 있어야한다.
		


		MemberDAO dao = new MemberDAO();
		//dao.insert(new MemberDTO(m_id,m_name,m_age));
		
		//바인딩할것없다./
		return new CommandAction(true, "list.do");

	}

}
