package mtp.member.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtp.member.interfaces.MemberCommand;
import mtp.member.mms.MemberDAO;
import mtp.member.mms.MemberDTO;
import mtp.view.forward.CommandAction;

public class MemberUpdateUICommand implements MemberCommand {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException, ServletException {
		MemberDTO dto = (MemberDTO)request.getSession(false).getAttribute("dto");
		String id = dto.getM_id();
		char grade = dto.getM_grade();
		
		if (grade == 'b') {
			id = request.getParameter("id");
			MemberDAO dao = new MemberDAO();
			dto = dao.updateui(id);
			request.setAttribute("dto", dto);
			return new CommandAction(false, "member_update_admin.jsp");
		}else {
			request.setAttribute("dto", new MemberDAO().updateui(id));
			return new CommandAction(false, "member_update.jsp");	
		}
		
	}

}
