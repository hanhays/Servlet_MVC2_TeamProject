package mtp.member.concrete;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.view.forward.CommandAction;

public class MemberUpdateAdminCommand implements MemberCommand {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		
		String id = request.getParameter("id");
		String grade = request.getParameter("grade");
		
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO(id, null, null, null, 0, null, null, null, grade.charAt(0));
		dao.update_admin(dto);
		return new CommandAction(true, "list.do");
	}

}
