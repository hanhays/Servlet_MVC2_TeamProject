package mtp.member.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.util.member.MemberUtil;
import mtp.view.forward.CommandAction;

public class MemberCreateCommand implements MemberCommand {

	public MemberCreateCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String what)
			throws IOException, ServletException {
		try {
		String m_id = request.getParameter("id");
		String m_password = request.getParameter("password");
		String m_name = request.getParameter("name");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String date = request.getParameter("date");
		System.out.println(year);
		System.out.println(month);
		System.out.println(date); 
		StringBuffer m_birth = new StringBuffer();
		m_birth.append(year);
		m_birth.append("-");
		m_birth.append(month);
		m_birth.append("-");
		m_birth.append(date);
		String m_phone = request.getParameter("phone");
		String m_email = request.getParameter("email");
		String m_nickname = request.getParameter("nickname");
//		String m_img = request.getParameter("img");
//		System.out.println(m_img);
		int m_age = new MemberUtil().getAge(year);
		MemberDAO dao = new MemberDAO();
		dao.create(new MemberDTO(m_id, m_password, m_name, m_birth.toString(), m_age, m_phone, m_email, m_nickname, 'a'));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new CommandAction(true, "list.do");

	}

}
