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
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String date = request.getParameter("date");
		System.out.println(year);
		System.out.println(month);
		System.out.println(date); 
		StringBuffer birth = new StringBuffer();
		birth.append(year);
		birth.append("-");
		birth.append(month);
		birth.append("-");
		birth.append(date);
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String img = request.getParameter("img");
		System.out.println(img);
		int age = new MemberUtil().getAge(year);
		MemberDAO dao = new MemberDAO();
		dao.create(new MemberDTO(id,password,name, birth.toString(), age,phone, email,nickname, null, 'a'));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CommandAction(true, "list.do");

	}

}
