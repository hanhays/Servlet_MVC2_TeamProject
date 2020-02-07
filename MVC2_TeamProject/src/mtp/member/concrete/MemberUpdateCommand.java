package mtp.member.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.view.forward.CommandAction;

public class MemberUpdateCommand implements MemberCommand {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String sAge = request.getParameter("age");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String grade = request.getParameter("grade");
		
		int age = -1;
		if (sAge != null) {
			age = Integer.parseInt(sAge);
		}
		
		MemberDAO dao = new MemberDAO();
		
		MemberDTO dto = new MemberDTO(id, password, name, birth, age, phone, email, nickname, grade.charAt(0));
		dao.update(dto);
		/*
		 * String msg = dto != null ? "회원정보가 변경되었습니다." : "앗! 무엇인가 잘못되었습니다. 다시 시도해 주세요.";
		 * request.setAttribute("msg", msg);
		 */

		return new CommandAction(true, "read.do");
	}

}
