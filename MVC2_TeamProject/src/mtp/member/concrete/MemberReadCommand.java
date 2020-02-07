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

public class MemberReadCommand implements MemberCommand {

	public MemberReadCommand() {
	}

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response, String what)
			throws IOException, ServletException {
		HttpSession sess = request.getSession(false);
		String sess_id =sess != null? ((MemberDTO) sess.getAttribute("dto")).getM_id():null;
		String id = sess_id != null ? sess_id.equals("admin") ? request.getParameter("id") : sess_id : null;
		MemberDTO dto = null;
		if (id != null) {
			dto = new MemberDAO().read(id); 
			request.setAttribute("dto",dto );
		}
		return new CommandAction(false, "member_read.jsp");
	}

}
