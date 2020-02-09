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
		boolean flag = false;
		try {
			String m_id = request.getParameter("id");
			String m_password = request.getParameter("password");
			String m_name = request.getParameter("name");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			StringBuffer m_birth = new StringBuffer();
			m_birth.append(year);
			m_birth.append("-");
			m_birth.append(month);
			m_birth.append("-");
			m_birth.append(date);
			String m_phone = request.getParameter("phone");
			String m_email = request.getParameter("email");
			String m_nickname = request.getParameter("nickname");
			int m_age = new MemberUtil().getAge(year);
			MemberDAO dao = new MemberDAO();
			flag =dao.create(new MemberDTO(m_id, m_password, m_name, m_birth.toString(), m_age, m_phone, m_email,m_nickname, 'a'));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			return new CommandAction(true, "/MVC2_TeamProject/");
		}
		return new CommandAction(true, "fail.do");
	}

}
